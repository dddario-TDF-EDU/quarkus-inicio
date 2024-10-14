package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.agoncal.fascicle.quarkus.book.acceso.AutorRepository;
import org.agoncal.fascicle.quarkus.book.acceso.CategoriaRepository;
import org.agoncal.fascicle.quarkus.book.acceso.LibroRepository;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.IsbnNumbers;
import org.agoncal.fascicle.quarkus.book.servicio.numberResources.NumberProxy;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transformador.BookMapper;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.time.Instant;

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
  LibroRepository libroRepository;

  @Inject
  AutorRepository autorRepository;

  @Inject
  CategoriaRepository categoriaRepository;
  @Fallback(fallbackMethod = "fallbackPersistBook")
  public LibroDTO persistBook(@Valid CrearLibroDTO crearLibroDTO) {
    crearLibroDTO.setAutores(corregirIdAutores(crearLibroDTO));
    if (crearLibroDTO.getAutores() != null & revisarCategoria(crearLibroDTO) != null) {
        asignarValoresIsbn(crearLibroDTO);
        LibroEntity newBook = bookMapper.dtoToNewEntity(crearLibroDTO);
      System.out.println(newBook.id_libro + " mi ID");
      libroRepository.createNewBookRepo(newBook); //???
      System.out.println(newBook.id_libro + " mi ID nuevo?");
      return bookMapper.entitytoDTO(newBook);
    } else {
      return null;
    }
  }

  private Set<AutorSimpleDTO> corregirIdAutores(CrearLibroDTO crearLibroDTO) {
    Set<AutorSimpleDTO> autores = crearLibroDTO.getAutores();
//    System.out.println(autores.getClass() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    for (AutorSimpleDTO autor: autores
         ) {
      if (autorRepository.findAutorByIdRepo(autor.getId_autor()) == null) {
        autores.remove(autor);
      } else {
        System.out.println("SI HAY AUTOR");
      }
    }
    return autores;
  }

  private Integer revisarCategoria(CrearLibroDTO crearLibroDTO) {
    if (categoriaRepository.findCategoriaByIdRepo(crearLibroDTO.getCategoriaId()) != null) {
      System.out.println("hay categoriaaaaaaaaaaa");
      return crearLibroDTO.categoria.getId_categoria();
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
    List<LibroDTO> booksDTO = bookMapper.entityListToListDTO(booksEntities);
//    for (LibroEntity libroEntity : booksEntities
//         ) {
//      booksDTO.add(bookMapper.aDTO(libroEntity));
//    }
    return booksDTO; //AAAAAA
  }


  public LibroDTO findBookById(Integer id) {
    LibroEntity bookQuery = libroRepository.findBookByIdRepo(id);
    if (bookQuery != null) {
      //BookEntity bookEntity = bookQuery.get();
      LibroDTO result = bookMapper.entitytoDTO(bookQuery);
      return result;
    } else {
      return null;
    }

  }

  public LibroDTO findRandomBook() {
    return bookMapper.entitytoDTO(libroRepository.findRandomBookRepo());
  }
  public LibroDTO updateBook(@Valid LibroDTO book) {
    LibroEntity entity = libroRepository.findById(Long.valueOf(book.getId_libro()));
    if (entity != null) {
      bookMapper.updateBookFromDTO(book, entity);
      libroRepository.persist(entity);
      return bookMapper.entitytoDTO(entity);
    }
    return null;
  }

  public void deleteBook(Integer id) {
    libroRepository.deleteBookByIdRepo(id);
  }

}
