package org.agoncal.fascicle.quarkus.book.recurso.categoria;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.agoncal.fascicle.quarkus.book.servicio.CategoriaService;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;

import org.agoncal.fascicle.quarkus.book.transferible.categoria.UpdateNombreCategoriaDTO;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

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
    List<CategoriaSimpleDTO> categorias = categoriaService.returnAllCategorias();
    return Response.ok(categorias).build();
  }

  @GET
  @Path("findById/{id}")
  public Response getCategoria(@Parameter(description = "Categoria identifier", required = true)
                          @PathParam("id") Integer id) {
    CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);
    if (categoriaDTO != null) {
      LOGGER.debug("Found categoria " + categoriaDTO);
      return Response.ok(categoriaDTO).build();
    } else {
      LOGGER.debug("No categoria found with id " + id);
      System.out.println("No categoria found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

  @GET
  @Path("findByName/{nombre}")
  public Response getCategoriaByName(@Parameter(description = "Categoria identifier", required = true)
                               @PathParam("nombre") String nombre) {
    CategoriaDTO categoriaDTO = categoriaService.findCategoriaByName(nombre);
    if (categoriaDTO != null) {
      LOGGER.debug("Found categoria " + categoriaDTO);
      return Response.ok(categoriaDTO).build();
    } else {
      LOGGER.debug("No categoria found with name " + nombre);
      return Response.status(NOT_FOUND).build();
    }
  }


  @POST
  public Response createCategoria(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CrearCategoriaDTO.class))) @Valid CrearCategoriaDTO newCategoria, @Context UriInfo uriInfo) {
    System.out.println("Cuerpo de la solicitud: " + newCategoria.getNombre());
    CategoriaDTO categoriaDTO = categoriaService.persistCategoria(newCategoria);
    if (categoriaDTO!= null) {
      UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(categoriaDTO.getId_categoria()));
      LOGGER.debug("News categoria created with URI " + builder.build().toString());
      return Response.created(builder.build()).build();
    } else {
      return Response.serverError().build();
    }
  }

  @POST
  @Path("addSubcategoria/{id}")
  public Response addSubcategoria(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CategoriaDTO.class)))@PathParam("id") Integer id, @Valid CategoriaDTO subcategoria, @Context UriInfo uriInfo) {
    CategoriaDTO categoriaFinal = categoriaService.addSubcategoria(subcategoria, id);
    if (categoriaFinal == null) {
      return Response.serverError().build();
    } else {
      return Response.ok(categoriaFinal).build();
    }
  }

  @DELETE
  @Path("removeSubcategoria/{id}")
  public Response removeSubcategoria(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CategoriaDTO.class)))@PathParam("id") Integer id, @Valid CategoriaDTO subcategoria, @Context UriInfo uriInfo) {
    CategoriaDTO categoriaFinal = categoriaService.removeSubcategoria(subcategoria, id);
    if (categoriaFinal == null) {
      return Response.serverError().build();
    } else {
      return Response.ok(categoriaFinal).build();
    }
  }

  @PUT
  public Response updateNombreCategoria(@RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UpdateNombreCategoriaDTO.class))) @Valid UpdateNombreCategoriaDTO categoria) {
    LOGGER.debug((categoria));
    categoria = categoriaService.updateNombreCategoria(categoria);
    return Response.ok(categoria).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteCategoria(@Parameter(description = "Categoria identifier", required = true) @PathParam("id") Integer id) {
    categoriaService.deleteCategoriaById(id);
    LOGGER.debug("Categoria deleted with " + id);
    return Response.noContent().build();
  }

}
