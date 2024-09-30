package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.agoncal.fascicle.quarkus.book.acceso.BookRepository;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.NumberProxy;

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
import java.math.BigDecimal;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookService {
  private static final Logger LOGGER = Logger.getLogger(BookService.class);

  @Inject
  @RestClient
  NumberProxy numberProxy;
  @Inject
  BookMapper bookMapper;

  @Inject
  BookRepository bookRepository;

  @Fallback(fallbackMethod = "fallbackPersistBook")
  public BookDTO persistBook(@Valid CreateBookDTO book) {
// The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    BookEntity newBook = bookMapper.toNewEntity(book);
    newBook.isbn13 = isbnNumbers.getIsbn13();
    newBook.isbn10 = isbnNumbers.getIsbn10();
    LOGGER.info(newBook);
    bookRepository.createNewBookRepo(newBook); //???
    return bookMapper.toDTO(newBook);
  }

//  private BookDTO fallbackPersistBook(CreateBookDTO newBook) throws FileNotFoundException {
//    LOGGER.warn("Falling back on persisting a book");
//    String bookJson = JsonbBuilder.create().toJson(newBook);
//    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
//      out.println(bookJson);
//    }
//    throw new IllegalStateException();
//  }
  private BookDTO fallbackPersistBook(CreateBookDTO newBook) {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(newBook);

    // Intentar guardar el JSON en un archivo
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    } catch (FileNotFoundException e) {
      LOGGER.error("Error writing to file: ", e);
    }

    // Retornar un objeto BookDTO vacío o con valores predeterminados
    return new BookDTO(); // O ajusta esto según sea necesario
  }


  public List<BookDTO> findAllBooks() {
    List<BookEntity> booksEntities = bookRepository.listAll();
    List<BookDTO> booksDTO = new ArrayList<>();
    for (BookEntity bookEntity: booksEntities
         ) {
      booksDTO.add(bookMapper.toDTO(bookEntity));
    }
    return booksDTO; //AAAAAA
  }


  public BookDTO findBookById(Long id) {
    BookEntity bookQuery = bookRepository.findByIdRepo(id);
    if (bookQuery != null) {
      //BookEntity bookEntity = bookQuery.get();
      BookDTO result = bookMapper.toDTO(bookQuery);
      return result;
    } else {
      return null;
    }

  }

  public BookDTO findRandomBook() {
    return bookMapper.toDTO(bookRepository.findRandomBookRepo());
  }
  public BookDTO updateBook(@Valid BookDTO book) {
    if (book.getIdBook() > 0) {
      BookEntity entity = bookRepository.findById(book.getIdBook());
      if (entity != null) {
        mapNewBook(book, entity);
        bookRepository.persist(entity);
        return bookMapper.toDTO(entity);
      }
    }
    return null;
  }

  private void mapNewBook(BookDTO book, BookEntity entity) {
    entity.title = book.title;
    entity.isbn13 = book.isbn_13;
    entity.isbn10 = book.isbn_10;
    entity.author = book.author;
    entity.yearOfPublication = book.yearOfPublication;
    entity.nbOfPages = book.nbOfPages;
    entity.rank = book.rank;
    entity.price = book.price;
    entity.smallImageUrl = book.smallImageUrl;
    entity.mediumImageUrl = book.mediumImageUrl;
    entity.description = book.description;
  }

  public boolean deleteBook(Long id) {
    return bookRepository.deleteByIdRepo(id);
  }

}

