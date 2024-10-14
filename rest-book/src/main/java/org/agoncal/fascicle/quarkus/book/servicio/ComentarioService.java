package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.LibroRepository;
import org.agoncal.fascicle.quarkus.book.acceso.ComentarioRepository;
import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.ComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.CrearComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.UpdateComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transformador.ComentarioMapper;

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
    if (libroRepository.findBookByIdRepo(crearComentarioDTO.libro_id) != null) {
      ComentarioEntity comentarioEntity = comentarioMapper.dtoToNewEntity(crearComentarioDTO);
      comentarioEntity.nro_linea = comentarioRepository.getNroLinea(comentarioEntity.libro.id_libro);
      comentarioRepository.createComentarioRepo(comentarioEntity);
      return comentarioMapper.entityToDTO(comentarioEntity);
    } else {
      return null;
    }
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public ComentarioDTO findComentarioById(@Valid Integer id) {
    ComentarioEntity comentarioEntity = comentarioRepository.findComentarioByIdRepo(id);
    return comentarioMapper.entityToDTO(comentarioEntity);
  }

  public void deleteComentarioById(Integer id) { comentarioRepository.deleteComentarioById(id); };

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
