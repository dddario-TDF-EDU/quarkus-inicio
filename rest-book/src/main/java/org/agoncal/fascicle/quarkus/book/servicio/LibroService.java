package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.agoncal.fascicle.quarkus.book.acceso.AutorRepository;
import org.agoncal.fascicle.quarkus.book.acceso.CategoriaRepository;
import org.agoncal.fascicle.quarkus.book.acceso.LibroRepository;
import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.NumberProxy;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.UpdateLibroDTO;
import org.agoncal.fascicle.quarkus.book.transformador.AutorMapper;
import org.agoncal.fascicle.quarkus.book.transformador.BookMapper;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.time.Instant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class LibroService {
  private static final Logger LOGGER = Logger.getLogger(LibroService.class);

  @Inject
  @RestClient
  NumberProxy numberProxy;
  @Inject
  BookMapper bookMapper;

  @Inject
  AutorMapper autorMapper;

  @Inject
  LibroRepository libroRepository;

  @Inject
  AutorRepository autorRepository;

  @Inject
  CategoriaRepository categoriaRepository;
  @Fallback(fallbackMethod = "fallbackPersistBook")
  public LibroDTO persistBook(@Valid CrearLibroDTO crearLibroDTO) {
    crearLibroDTO.setId_autores(corregirIdAutores(crearLibroDTO));
    if (crearLibroDTO.getId_autores() != null & revisarCategoria(crearLibroDTO) != null) {
        asignarValoresIsbn(crearLibroDTO);
        LibroEntity newBook = bookMapper.dtoToNewEntity(crearLibroDTO);
        System.out.println(newBook.id_libro + " mi ID");
        newBook.categoriaEntity = categoriaRepository.findCategoriaByIdRepo(crearLibroDTO.getId_categoria());
        newBook.autores_de_libros = asignarAutores(crearLibroDTO);
        detallarClase(newBook);
        libroRepository.createNewBookRepo(newBook); //???
        System.out.println(newBook.id_libro + " mi ID nuevo?");
      return bookMapper.entityToDTO(newBook);
    } else {
      return null;
    }
  }

  public  List<LibroDTO> findBookByAutorId(Integer id_autor) {
    List<LibroDTO> libroDTOList = bookMapper.entityListToListDTO(libroRepository.returnByAutor(id_autor));
    if (libroDTOList != null) {
      return libroDTOList;
    } else {
      return null;
    }
  }

  private void detallarClase(LibroEntity libroEntity) {
    System.out.println("LIBROOOOOOOOOOOOO");
    System.out.println("id libro: "+libroEntity.id_libro);
    System.out.println("titulo: "+libroEntity.titulo);
    System.out.println("isbn10: " +libroEntity.isbn10);
    System.out.println("isbn13: " +libroEntity.isbn13);
    System.out.println("year: " +libroEntity.yearOfPublication);
    System.out.println("num_pags: " +libroEntity.num_paginas);
    System.out.println("rank: " +libroEntity.ranking);
    System.out.println("precio: " +libroEntity.precio);
    System.out.println("small: " +libroEntity.smallImageUrl);
    System.out.println("medium: " +libroEntity.mediumImageUrl);
    System.out.println("descripcion: " +libroEntity.descripcion);
    System.out.println("categoria: " +libroEntity.categoriaEntity.id_categoria);
    System.out.println("autores: " +libroEntity.autores_de_libros.size());
  }

  private Set<AutorEntity> asignarAutores(CrearLibroDTO crearLibroDTO) {
    Set<Integer> id_autores = crearLibroDTO.getId_autores();
    Set<AutorEntity> autorEntities = new HashSet<>();
    for (Integer id_autor: id_autores
    ) {
      autorEntities.add(autorRepository.findAutorByIdRepo(id_autor));
    }

    return autorEntities;
  }

  private Set<Integer> corregirIdAutores(CrearLibroDTO crearLibroDTO) {
    Set<Integer> autores = crearLibroDTO.getId_autores();
    System.out.println(autores.size() + " aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa tamaño");
    for (Integer id_autor: autores
         ) {
      if (autorRepository.findAutorByIdRepo(id_autor) == null) {
        System.out.println("AUTOR" + id_autor + "removidooooo");
        autores.remove(id_autor);
      } else {
        System.out.println("SI HAY AUTOR" + id_autor);
      }
    }
    return autores;
  }

  private Integer revisarCategoria(CrearLibroDTO crearLibroDTO) {
    if (categoriaRepository.findCategoriaByIdRepo(crearLibroDTO.getId_categoria()) != null) {
      System.out.println("hay categoriaaaaaaaaaaa");
      return crearLibroDTO.getId_categoria();
    } else {
      return null;
    }
  }

  private void asignarValoresIsbn(CrearLibroDTO crearLibroDTO) {
    IsbnNumbers isbnNumbers = numberProxy.generateIsbnNumbers();
    crearLibroDTO.setIsbn_13(isbnNumbers.getIsbn13());
    crearLibroDTO.setIsbn_10(isbnNumbers.getIsbn10());
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
    List<LibroEntity> booksEntities = libroRepository.returnAllBooksRepo();
    List<LibroDTO> libroDTOList = new ArrayList<>();
    if (booksEntities != null) {
      for (LibroEntity libroEntity: booksEntities
      ) {
        LibroDTO item = bookMapper.entityToDTO(libroEntity);
        item.autores = autorMapper.listEntityToListEnLibroDTO(libroEntity.autores_de_libros);
        item.setCategoria(libroEntity.categoriaEntity.nombre);
        libroDTOList.add(item);
      }
      return libroDTOList;
    }

    return null;
  }





  public LibroDTO findBookById(Integer id) {
    LibroEntity bookQuery = libroRepository.findBookByIdRepo(id);
    if (bookQuery != null) {
      //BookEntity bookEntity = bookQuery.get();
      LibroDTO result = bookMapper.entityToDTO(bookQuery);
      return result;
    } else {
      return null;
    }

  }

  public LibroDTO findRandomBook() {
    return bookMapper.entityToDTO(libroRepository.findRandomBookRepo());
  }
  public LibroDTO updateBook(@Valid UpdateLibroDTO book) {
    LibroEntity entity = libroRepository.findBookByIdRepo(book.getId_libro());
    if (entity != null) {
      System.out.println("SOY NULLL  " + entity);
      bookMapper.updateBookFromDTO(book, entity);
      libroRepository.persist(entity);
      return bookMapper.entityToDTO(entity);
    }
    return null;
  }

  public void deleteBook(Integer id) {
    libroRepository.deleteBookByIdRepo(id);
  }

}
