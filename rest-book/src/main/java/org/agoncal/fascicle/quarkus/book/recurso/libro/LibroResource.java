package org.agoncal.fascicle.quarkus.book.recurso.libro;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.agoncal.fascicle.quarkus.book.servicio.LibroService;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;

import org.agoncal.fascicle.quarkus.book.transferible.libro.UpdateLibroDTO;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

//import javax.inject.Inject;
//import javax.validation.Valid;
//import javax.ws.rs.*;
//import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

//import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/libros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Libro Endpoint")
//@Authenticated
@ApplicationScoped
public class LibroResource {
  @Inject
  LibroService libroService;
  private static final Logger LOGGER = Logger.getLogger(LibroResource.class);
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
//    @PermitAll
    public String ping() {
      return "ping";
    }

  @Operation(summary = "Returns a random libro")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class)))
  // tag::adocMetrics[]
  @Counted(name = "countGetRandomBook", description = "Counts how many times the getRandomBook method has been invoked")
  @Timed(name = "timeGetRandomBook", description = "Times how long it takes to invoke the getRandomBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Path("/random")
  public Response getRandomBook() {
    LibroDTO book = libroService.findRandomBook();
    LOGGER.debug("Found random book " + book);
    return Response.ok(book).build();
  }

  @Operation(summary = "Returns all the libros from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No books")
  // tag::adocMetrics[]
  @Counted(name = "countGetAllLibros", description = "Counts how many times the getAllLibros method has been invoked")
  @Timed(name = "timeGetAllLibros", description = "Times how long it takes to invoke the getAllLibros method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  //@PermitAll
  public Response getAllBooks() {
    List<LibroDTO> books = libroService.findAllBooks();
    LOGGER.debug("Total number of books " + books);
    return Response.ok(books).build();
  }

  @Operation(summary = "Returns todos los libros con ranking mayor al ingresado")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No books")
  // tag::adocMetrics[]
  @Counted(name = "countGetAllLibros", description = "Counts how many times the getAllLibros method has been invoked")
  @Timed(name = "timeGetAllLibros", description = "Times how long it takes to invoke the getAllLibros method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Path("/mayorA/{rank}")
  //@PermitAll
  public Response getByRank(@Parameter(description = "rank", required = true)
                            @PathParam("rank") double rank) {
    List<LibroDTO> books = libroService.returnByRank(rank);
    if (books != null) {
      return Response.ok(books).build();
    } else {
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Returns todos los libros segun categoria y subcategoria")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No books")
  // tag::adocMetrics[]
  @Counted(name = "countGetByCategoria", description = "Counts how many times the getByCategoria method has been invoked")
  @Timed(name = "timeGetByCategoria", description = "Times how long it takes to invoke the getByCategoria method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  @Path("/categoria/{id_categoria}")
  //@PermitAll
  public Response getByCategoria(@Parameter(description = "id_categoria", required = true)
                            @PathParam("id_categoria") Integer id_categoria) {
    List<LibroDTO> books = libroService.returnByCategoria(id_categoria);
    if (books != null) {
      return Response.ok(books).build();
    } else {
      return Response.status(NOT_FOUND).build();
    }
  }


  @Operation(summary = "Returns a book for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class)))
  @APIResponse(responseCode = "404", description = "The book is not found for the given identifier")
  // tag::adocMetrics[]
  @Counted(name = "countGetBook", description = "Counts how many times the getBook method has been invoked")
  @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the getBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]

  @GET
  @Path("/{id}")
  //@PermitAll
  public Response getBook(@Parameter(description = "Book identifier", required = true)
                          @PathParam("id") Integer id) {
    LibroDTO book = libroService.findBookById(id);
    if (book != null) {
      LOGGER.debug("Found book " + book);
      return Response.ok(book).build();
    } else {
      LOGGER.debug("No book found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Returns a book for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class)))
  @APIResponse(responseCode = "404", description = "The book is not found for the given identifier")
  // tag::adocMetrics[]
  @Counted(name = "countGetBook", description = "Counts how many times the getBook method has been invoked")
  @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the getBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]

  @GET
  @Path("autor/{id}")
  //@PermitAll
  public Response getBookByAutor(@Parameter(description = "Book identifier", required = true)
                          @PathParam("id") Integer id) {
    List<LibroDTO> book = libroService.findBookByAutorId(id);
    if (book != null) {
      LOGGER.debug("Found book " + book);
      return Response.ok(book).build();
    } else {
      LOGGER.debug("No books found with autor_id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  // PARTE CON Keycloak
  @Operation(summary = "Creates a valid book")
  @APIResponse(responseCode = "201", description = "The URI of the created book",
    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
//  @Counted(name = "countCreateBook", description = "Counts how many times the createBook method has been invoked")
//  @Timed(name = "timeCreateBook", description = "Times how long it takes to invoke the createBook method", unit = MetricUnits.MILLISECONDS)
  @POST
//  @RolesAllowed("admin")
  public Response createBook(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CrearLibroDTO.class))) @Valid CrearLibroDTO newLibro, @Context UriInfo uriInfo) {
    LibroDTO bookCreado = libroService.persistBook(newLibro);
    if (bookCreado != null) {
      UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(bookCreado.id_libro));
      LOGGER.debug("News book created with URI " + builder.build().toString());
      return Response.created(builder.build()).build();
    } else {
      System.out.println("No se creo el libro ");
      LOGGER.debug("No se creo el libro " );
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Updates an existing book")
  @APIResponse(responseCode = "200", description = "The updated book", content =
  @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = LibroDTO.class)))
  @Counted(name = "countUpdateBook", description = "Counts how many times the updateBook method has been invoked")
  @Timed(name = "timeUpdateBook", description = "Times how long it takes to invoke the updateBook method", unit = MetricUnits.MILLISECONDS)
  @Transactional
  @PUT
  //@RolesAllowed("admin")
  public Response updateBook(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateLibroDTO.class))) @Valid UpdateLibroDTO book) {
    LOGGER.debug(book + "asdasds");
    LibroDTO libroDTO = libroService.updateBook(book);
    LOGGER.debug("Book updated with new valued " + book);
    return Response.ok(book).build();
  }

  @Operation(summary = "Deletes an existing book")
  @APIResponse(responseCode = "204", description = "The book has been successfully deleted")
  // tag::adocMetrics[]
  @Counted(name = "countDeleteBook", description = "Counts how many times the deleteBook method has been invoked")
  @Timed(name = "timeDeleteBook", description = "Times how long it takes to invoke the deleteBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @DELETE
  @Path("/{id}")
  //@RolesAllowed("admin")
  public Response deleteBook(@Parameter(description = "Book identifier", required = true) @PathParam("id") Integer id) {
    libroService.deleteBook(id);
    LOGGER.debug("Book deleted with " + id);
    return Response.noContent().build();
  }
}

