package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.LibroRepository;
import org.agoncal.fascicle.quarkus.book.acceso.ComentarioRepository;
import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.ComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.CrearComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.UpdateComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transformador.ComentarioMapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Transactional
@ApplicationScoped
public class ComentarioService {

  @Inject
  ComentarioRepository comentarioRepository;

  @Inject
  ComentarioMapper comentarioMapper;

  @Inject
  LibroRepository libroRepository;

  public ComentarioDTO persistComentario(@Valid CrearComentarioDTO crearComentarioDTO) {
    System.out.println(crearComentarioDTO.libro_id + "aaaaaaaaaaaa mi id");
    LibroEntity libroEntity = libroRepository.findBookByIdRepo(crearComentarioDTO.libro_id);
    if (libroEntity != null) {
      System.out.println(libroEntity.id_libro + "traje mi id");
      ComentarioEntity comentarioEntity = comentarioMapper.dtoToNewEntity(crearComentarioDTO);
      comentarioEntity.libro = libroEntity;
      describirEntity(comentarioEntity);
      comentarioEntity.nro_linea = devolverNroLinea(comentarioEntity.libro.id_libro);
      //comentarioEntity.nro_linea = comentarioRepository.getLineas(comentarioEntity.libro.id_libro);
      comentarioRepository.createComentarioRepo(comentarioEntity);
      return comentarioMapper.entityToDTO(comentarioEntity);
    } else {
      return null;
    }
  }

  private Integer devolverNroLinea(Integer id_libro) {
    List<ComentarioEntity> comentarioEntities = comentarioRepository.getLineas(id_libro);
    if (comentarioEntities == null) {
      return 1;
    } else {
      ComentarioEntity comentarioEntity = comentarioEntities.get(comentarioEntities.size()-1);
      return comentarioEntity.nro_linea + 1;
    }
  }

  private void describirEntity(ComentarioEntity comentarioEntity) {
    System.out.println("email  " + comentarioEntity.email);
    System.out.println("texto  " + comentarioEntity.texto);
    System.out.println("puntuacion  " + comentarioEntity.puntuacion);
    System.out.println("nro_linea  " + comentarioEntity.nro_linea);
    System.out.println("libro  " + comentarioEntity.libro);
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public ComentarioDTO findComentarioById(@Valid Integer id) {
    ComentarioEntity comentarioEntity = comentarioRepository.findComentarioByIdRepo(id);
    return comentarioMapper.entityToDTO(comentarioEntity);
  }

  public void deleteComentarioById(Integer id) {
    //no maneja los deletes donde no sea correcta el ID
    comentarioRepository.deleteComentarioById(id);
  };

  public ComentarioDTO updateComentario(@Valid UpdateComentarioDTO updateComentarioDTO) {
    ComentarioEntity comentarioEntity = comentarioRepository.findComentarioByIdRepo(updateComentarioDTO.getId_comentario());
    if (comentarioEntity != null) {
      comentarioMapper.updateEntityFromDTO(updateComentarioDTO, comentarioEntity);
      comentarioRepository.updateComentarioRepo(comentarioEntity);
      return comentarioMapper.entityToDTO(comentarioEntity);
    } else {
      return null;
    }
  }

  public List<ComentarioDTO> returnAllComentarios() {
    return comentarioMapper.listEntityToListDTO(comentarioRepository.returnAllComentariosRepo());
  }
}
