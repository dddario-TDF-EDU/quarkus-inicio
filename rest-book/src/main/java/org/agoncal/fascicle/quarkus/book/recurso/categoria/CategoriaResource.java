package org.agoncal.fascicle.quarkus.book.recurso.categoria;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.agoncal.fascicle.quarkus.book.recurso.libro.BookResource;
import org.agoncal.fascicle.quarkus.book.servicio.CategoriaService;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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

  private static final Logger LOGGER = Logger.getLogger(BookResource.class);

  @GET
  //@PermitAll
  public Response getAllCategorias() {
    List<CategoriaDTO> categorias = categoriaService.returnAllCategorias();
    LOGGER.debug("Total number of categorias " + categorias);
    return Response.ok(categorias).build();
  }

  @GET
  @Path("/{id}")
  //@PermitAll
  public Response getCategoria(@Parameter(description = "Categoria identifier", required = true)
                          @PathParam("id") Long id) {
    CategoriaDTO categoriaDTO = categoriaService.findCategoriaById(id);
    if (categoriaDTO != null) {
      LOGGER.debug("Found categoria " + categoriaDTO);
      return Response.ok(categoriaDTO).build();
    } else {
      LOGGER.debug("No book found with id " + id);
      return Response.status(NOT_FOUND).build();
    }
  }

}
