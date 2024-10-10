package org.agoncal.fascicle.quarkus.book.recurso.autor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import org.agoncal.fascicle.quarkus.book.servicio.AuthorService;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;


@Path("/api/autores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Autores Endpoint")
@ApplicationScoped
public class AutorResource {
  @Inject
  AuthorService authorService;

  private static final Logger LOGGER = Logger.getLogger(AutorResource.class);

  @GET
  public Response getAllAutores() {
    List<AutorSimpleDTO> categorias = authorService.findAllAutores();
    return Response.ok(categorias).build();
  }

  @GET
  @Path("findById/{id}")
  public Response getAutor(@Parameter(description = "Autor identifier", required = true)
                               @PathParam("id") Integer id) {
    AutorDTO autorDTO = authorService.findAutorById(id);
    if (autorDTO != null) {
      LOGGER.debug("Found autor " + autorDTO);
      return Response.ok(autorDTO).build();
    } else {
      LOGGER.debug("No autor found with id " + id);
      System.out.println("No autor found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @GET
  @Path("findByName/{nombre}")
  public Response getCategoriaByName(@Parameter(description = "Autor identifier", required = true)
                                     @PathParam("nombre") String nombre) {
    AutorDTO autorDTO = authorService.findAutorByName(nombre);
    if (autorDTO != null) {
      LOGGER.debug("Found autor " + autorDTO);
      return Response.ok(autorDTO).build();
    } else {
      LOGGER.debug("No autor found with name " + nombre);
      return Response.status(NOT_FOUND).build();
    }
  }


  @POST
  public Response createAutor(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CrearAutorDTO.class))) @Valid CrearAutorDTO newAutor, @Context UriInfo uriInfo) {
    System.out.println("Cuerpo de la solicitud: " + newAutor.getNombre());
    AutorDTO autorDTO = authorService.persistAutor(newAutor);
    if (autorDTO!= null) {
      UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(autorDTO.getId_autor()));
      LOGGER.debug("News autor created with URI " + builder.build().toString());
      return Response.created(builder.build()).build();
    } else {
      System.out.println("No autor found with id aaaaaaaaaaaaa" );
      return Response.serverError().build();
    }
  }



  @PUT
  public Response updateAutor(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AutorDTO.class))) @Valid AutorDTO autorDTO) {
    LOGGER.debug((autorDTO));
    autorDTO = authorService.updateAutor(autorDTO);
    return Response.ok(autorDTO).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteAutor(@Parameter(description = "Autor identifier", required = true) @PathParam("id") Integer id) {
    authorService.deleteAutor(id);
    LOGGER.debug("Autor deleted with " + id);
    return Response.noContent().build();
  }
}
