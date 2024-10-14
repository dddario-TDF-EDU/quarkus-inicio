package org.agoncal.fascicle.quarkus.book.recurso.comentario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import org.agoncal.fascicle.quarkus.book.servicio.ComentarioService;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.ComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.CrearComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.UpdateComentarioDTO;
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

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Comentarios Endpoint")
//@Authenticated
@ApplicationScoped
public class ComentarioResource {

  @Inject
  ComentarioService comentarioService;
  private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class);
  @GET
  @Path("/ping")
  @Produces(MediaType.TEXT_PLAIN)
//    @PermitAll
  public String ping() {
    return "ping";
  }


  @Operation(summary = "Returns all the comentario from the database")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ComentarioDTO.class, type = SchemaType.ARRAY)))
  @APIResponse(responseCode = "204", description = "No books")
  // tag::adocMetrics[]
  @Counted(name = "countGetAllBooks", description = "Counts how many times the getAllBooks method has been invoked")
  @Timed(name = "timeGetAllBooks", description = "Times how long it takes to invoke the getAllBooks method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @GET
  //@PermitAll
  public Response getAllBooks() {
    List<ComentarioDTO> comentarioDTOList = comentarioService.returnAllComentarios();
    LOGGER.debug("Total number of comentarios " + comentarioDTOList);
    return Response.ok(comentarioDTOList).build();
  }

  @Operation(summary = "Returns a book for a given identifier")
  @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ComentarioDTO.class)))
  @APIResponse(responseCode = "404", description = "The book is not found for the given identifier")
  // tag::adocMetrics[]
  @Counted(name = "countGetBook", description = "Counts how many times the getBook method has been invoked")
  @Timed(name = "timeGetBook", description = "Times how long it takes to invoke the getBook method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]

  @GET
  @Path("/{id}")
  //@PermitAll
  public Response getComentario(@Parameter(description = "Comentario identifier", required = true)
                          @PathParam("id") Integer id) {
    ComentarioDTO comentarioDTO = comentarioService.findComentarioById(id);
    if (comentarioDTO != null) {
      LOGGER.debug("Found comentario " + comentarioDTO);
      return Response.ok(comentarioDTO).build();
    } else {
      LOGGER.debug("No comentario found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  // PARTE CON Keycloak
  @Operation(summary = "Crea un comentario valido")
  @APIResponse(responseCode = "201", description = "The URI of the created comentario",
    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
  @Counted(name = "countCreateComentario", description = "Counts how many times the createComentario method has been invoked")
  @Timed(name = "timeCreateComentario", description = "Times how long it takes to invoke the createComentario method", unit = MetricUnits.MILLISECONDS)
  @POST
//  @RolesAllowed("admin")
  public Response createComentario(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CrearComentarioDTO.class))) @Valid CrearComentarioDTO newComentario, @Context UriInfo uriInfo) {
    ComentarioDTO comentarioDTO = comentarioService.persistComentario(newComentario);
    if (comentarioDTO != null) {
      UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(comentarioDTO.getId_comentario()));
      LOGGER.debug("News comentario created with URI " + builder.build().toString());
      return Response.created(builder.build()).build();
    } else {
      return Response.status(NOT_FOUND).build();
    }
  }

  @Operation(summary = "Actualiza un comentario existente")
  @APIResponse(responseCode = "200", description = "The updated comentario", content =
  @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ComentarioDTO.class)))
  @Counted(name = "countUpdateComentario", description = "Counts how many times the updateComentario method has been invoked")
  @Timed(name = "timeUpdateComentario", description = "Times how long it takes to invoke the updateComentario method", unit = MetricUnits.MILLISECONDS)
  @Transactional
  @PUT
  //@RolesAllowed("admin")
  public Response updateComentario(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateComentarioDTO.class))) @Valid UpdateComentarioDTO updateComentarioDTO) {
    LOGGER.debug(updateComentarioDTO + "asdasds");
    ComentarioDTO comentarioEditado = comentarioService.updateComentario(updateComentarioDTO);
    LOGGER.debug("Comentario updated with new valued " + updateComentarioDTO);
    return Response.ok(comentarioEditado).build();
  }

  @Operation(summary = "Deletes an existing comentario")
  @APIResponse(responseCode = "204", description = "The book has been successfully deleted")
  // tag::adocMetrics[]
  @Counted(name = "countDeleteComentario", description = "Counts how many times the deleteComentario method has been invoked")
  @Timed(name = "timeDeleteComentario", description = "Times how long it takes to invoke the deleteComentario method", unit = MetricUnits.MILLISECONDS)
  // end::adocMetrics[]
  @DELETE
  @Path("/{id}")
  //@RolesAllowed("admin")
  public Response deleteComentario(@Parameter(description = "Comentario identifier", required = true) @PathParam("id") Integer id) {
    comentarioService.deleteComentarioById(id);
    LOGGER.debug("Comentario deleted with " + id);
    return Response.noContent().build();
  }
}
