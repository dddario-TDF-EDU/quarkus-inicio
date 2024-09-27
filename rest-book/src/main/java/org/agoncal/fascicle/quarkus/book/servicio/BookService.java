package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.DBAccess;
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
  DBAccess dbAccess;

  @Fallback(fallbackMethod = "fallbackPersistBook")
  public BookDTO persistBook(@Valid CreateBookDTO book) {
// The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    BookEntity newBook = bookMapper.toNewEntity(book);
    newBook.isbn13 = isbnNumbers.getIsbn13();
    newBook.isbn10 = isbnNumbers.getIsbn10();
    LOGGER.info(newBook);
    dbAccess.createNewBook(newBook); //???
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
    List<BookEntity> booksEntity = BookEntity.listAll();
    List<BookDTO> booksDTO = new ArrayList<>();
    for (BookEntity bookEntity: booksEntity
         ) {
      booksDTO.add(bookMapper.toDTO(bookEntity));
    }
    return booksDTO; //AAAAAA
  }


  public BookDTO findBookById(Long id) {
    Optional <BookEntity> bookQuery = dbAccess.findById(id);
    if (bookQuery.isPresent()) {
      BookEntity bookEntity = bookQuery.get();
      BookDTO result = bookMapper.toDTO(bookEntity);
      return result;
    } else {
      return null;
    }

  }

  public BookDTO findRandomBook() {
    return bookMapper.toDTO(dbAccess.findRandomBook());
  }
  public BookDTO updateBook(@Valid BookDTO book) {
    if (book.getId() > 0) {
      if (findBookById(book.getId()) != null) {
        BookEntity entity = dbAccess.updateBook(bookMapper.toEntity(book));
        return bookMapper.toDTO(entity);
      }
    }
    return null;
  }
  public void deleteBook(Long id) {
    dbAccess.deleteById(id);
  }

}

