package org.agoncal.fascicle.quarkus.book.recurso.categoria;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.agoncal.fascicle.quarkus.book.servicio.CategoriaService;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/api/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Categoria Endpoint")
@ApplicationScoped
public class CategoriaResource {
  @Inject
  CategoriaService categoriaService;

  private static final Logger LOGGER = Logger.getLogger(CategoriaResource.class);

  @GET
  public Response getAllCategorias() {
    System.out.print("Hello wordasdasdsadsds ");
    List<CategoriaDTO> categorias = categoriaService.returnAllCategorias();
    LOGGER.debug("Total number of categorias " + categorias);
    return Response.ok(categorias).build();
  }

  @GET
  @Path("/{id}")
  public Response getCategoria(@Parameter(description = "Categoria identifier", required = true)
                          @PathParam("id") Long id) {
    CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);
    if (categoriaDTO != null) {
      LOGGER.debug("Found categoria " + categoriaDTO);
      return Response.ok(categoriaDTO).build();
    } else {
      LOGGER.debug("No categoria found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }


  @POST
  public Response createCategoria(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CrearCategoriaDTO.class))) @Valid CrearCategoriaDTO newCategoria, @Context UriInfo uriInfo) {
    CategoriaDTO categoriaDTO = categoriaService.persistCategoria(newCategoria);
//    if (categoriaDTO!= null) {
      UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(categoriaDTO.getId_categoria()));
      LOGGER.debug("News categoria created with URI " + builder.build().toString());
      LOGGER.debug("asdasddsad " + categoriaDTO);
      return Response.created(builder.build()).build();
//    } else {
//      return Response.serverError().build();
//    }
  }

  @PUT
  public Response updateCategoria(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CategoriaDTO.class))) @Valid CategoriaDTO categoria) {
    LOGGER.debug((categoria));
    categoria = categoriaService.updateCategoria(categoria);
    return Response.ok(categoria).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteCategoria(@Parameter(description = "Categoria identifier", required = true) @PathParam("id") Long id) {
    categoriaService.deleteCategoriaById(id);
    LOGGER.debug("Categoria deleted with " + id);
    return Response.noContent().build();
  }

}
