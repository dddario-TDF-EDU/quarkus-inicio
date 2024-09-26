package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;

import org.agoncal.fascicle.quarkus.book.modelo.BookEntity;
import org.agoncal.fascicle.quarkus.book.transferible.BookDTO;
import org.agoncal.fascicle.quarkus.book.transferible.CreateBookDTO;
import org.agoncal.fascicle.quarkus.book.transformador.BookMapper;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jboss.logging.Logger;

//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.json.bind.JsonbBuilder;
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookService {
  private static final Logger LOGGER = Logger.getLogger(BookService.class);
  @Inject
  EntityManager em;
  @Inject
  @RestClient
  NumberProxy numberProxy;
  @Inject
  BookMapper bookMapper;

  @Fallback(fallbackMethod = "fallbackPersistBook")
  public BookDTO persistBook(@Valid CreateBookDTO book) {
// The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    BookEntity newBook = bookMapper.toNewEntity(book);
    newBook.isbn13 = isbnNumbers.getIsbn13();
    newBook.isbn10 = isbnNumbers.getIsbn10();
    BookEntity.persist(newBook);
    return bookMapper.toDTO(newBook);
  }

  private BookDTO fallbackPersistBook(CreateBookDTO newBook) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(newBook);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    }
    throw new IllegalStateException();
  }
//  @Transactional(Transactional.TxType.SUPPORTS)
//  public List<BookDTO> findAllBooks() {
//    List<BookEntity> booksEntity = BookEntity.listAll();
//    List<BookDTO> booksDTO = new ArrayList<>();
//    for (BookEntity bookEntity: booksEntity
//         ) {
//      booksDTO.add(bookMapper.toDTO(bookEntity));
//    }
//    return booksDTO; //AAAAAA
//  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public BookDTO findBookById(Long id) {
    Optional <BookEntity> bookQuery = BookEntity.findByIdOptional(id);
    if (bookQuery.isPresent()) {
      BookEntity bookEntity = bookQuery.get();
      BookDTO result = bookMapper.toDTO(bookEntity);
      return result;
    } else {
      return null;
    }

  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public BookDTO findRandomBook() {
    BookEntity randomBook = null; // AAAAA
    while (randomBook == null) {
      randomBook = BookEntity.findRandom();
    }
    return bookMapper.toDTO(randomBook);
  }
  public BookDTO updateBook(@Valid BookDTO book) {
    BookEntity entity = em.merge(bookMapper.toEntity(book)); // AAAAA
    return bookMapper.toDTO(entity);
  }
  public void deleteBook(Long id) {
    BookEntity.deleteById(id);
  }

}

