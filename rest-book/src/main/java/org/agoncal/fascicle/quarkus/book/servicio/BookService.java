package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.client.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.client.NumberProxy;

import org.agoncal.fascicle.quarkus.book.transferible.BookDTO;
import org.agoncal.fascicle.quarkus.book.transferible.CreateBookDTO;
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
  @Fallback(fallbackMethod = "fallbackPersistBook")
  public BookDTO persistBook(@Valid CreateBookDTO book) {
// The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    book.isbn13 = isbnNumbers.getIsbn13();
    book.isbn10 = isbnNumbers.getIsbn10();
    // AAAAAA BookEntity.persist(book);
    return book;
  }

  private BookDTO fallbackPersistBook(BookDTO book) throws FileNotFoundException {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(book);
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    }
    throw new IllegalStateException();
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<BookDTO> findAllBooks() {
    return BookDTO.listAll(); //AAAAAA
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional<BookDTO> findBookById(Long id) {
    return BookEntity.findByIdOptional(id); //AAAAA
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public BookDTO findRandomBook() {
    BookEntity randomBook = null; // AAAAA
    while (randomBook == null) {
      randomBook = BookEntity.findRandom();
    }
    return randomBook;
  }
  public BookDTO updateBook(@Valid BookDTO book) {
    BookEntity entity = em.merge(book); // AAAAA
    return entity;
  }
  public void deleteBook(Long id) {
    BookEntity.deleteById(id);
  }

}

