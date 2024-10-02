package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.validation.Valid;

import org.agoncal.fascicle.quarkus.book.acceso.BookRepository;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.NumberProxy;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transformador.BookMapper;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.time.Instant;

import java.util.ArrayList;
import java.util.List;

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
  public LibroDTO persistBook(@Valid CrearLibroDTO book) {
// The Book microservice invokes the Number microservice
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    LibroEntity newBook = bookMapper.toNewEntity(book);
    newBook.isbn13 = isbnNumbers.getIsbn13();
    newBook.isbn10 = isbnNumbers.getIsbn10();
    LOGGER.info(newBook);
    bookRepository.createNewBookRepo(newBook); //???
    return bookMapper.aDTO(newBook);
  }

//  private BookDTO fallbackPersistBook(CreateBookDTO newBook) throws FileNotFoundException {
//    LOGGER.warn("Falling back on persisting a book");
//    String bookJson = JsonbBuilder.create().toJson(newBook);
//    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
//      out.println(bookJson);
//    }
//    throw new IllegalStateException();
//  }
  private LibroDTO fallbackPersistBook(CrearLibroDTO newBook) {
    LOGGER.warn("Falling back on persisting a book");
    String bookJson = JsonbBuilder.create().toJson(newBook);

    // Intentar guardar el JSON en un archivo
    try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
      out.println(bookJson);
    } catch (FileNotFoundException e) {
      LOGGER.error("Error writing to file: ", e);
    }

    // Retornar un objeto BookDTO vacío o con valores predeterminados
    return new LibroDTO(); // O ajusta esto según sea necesario
  }


  public List<LibroDTO> findAllBooks() {
    List<LibroEntity> booksEntities = bookRepository.listAll();
    List<LibroDTO> booksDTO = new ArrayList<>();
    for (LibroEntity libroEntity : booksEntities
         ) {
      booksDTO.add(bookMapper.aDTO(libroEntity));
    }
    return booksDTO; //AAAAAA
  }


  public LibroDTO findBookById(Long id) {
    LibroEntity bookQuery = bookRepository.findByIdRepo(id);
    if (bookQuery != null) {
      //BookEntity bookEntity = bookQuery.get();
      LibroDTO result = bookMapper.aDTO(bookQuery);
      return result;
    } else {
      return null;
    }

  }

  public LibroDTO findRandomBook() {
    return bookMapper.aDTO(bookRepository.findRandomBookRepo());
  }
  public LibroDTO updateBook(@Valid LibroDTO book) {
    if (book.getIdBook() > 0) {
      LibroEntity entity = bookRepository.findById(book.getIdBook());
      if (entity != null) {
        mapNewBook(book, entity);
        bookRepository.persist(entity);
        return bookMapper.aDTO(entity);
      }
    }
    return null;
  }

  private void mapNewBook(LibroDTO book, LibroEntity entity) {
    entity.titulo = book.titulo;
    entity.isbn13 = book.isbn_13;
    entity.isbn10 = book.isbn_10;
    entity.autores_de_libros = book.autores;
    entity.yearOfPublication = book.yearOfPublication;
    entity.num_paginas = book.num_paginas;
    entity.ranking = book.ranking;
    entity.precio = book.precio;
    entity.smallImageUrl = book.smallImageUrl;
    entity.mediumImageUrl = book.mediumImageUrl;
    entity.descripcion = book.descripcion;
  }

  public boolean deleteBook(Long id) {
    return bookRepository.deleteByIdRepo(id);
  }

}

