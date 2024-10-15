package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.ComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.CrearComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.UpdateComentarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ComentarioMapper {

  @Mapping(source = "libro.id_libro", target = "libro_id")
  ComentarioDTO entityToDTO(ComentarioEntity comentarioEntity);

  ComentarioEntity dtoToEntity(ComentarioDTO comentarioDTO);
  @Mapping(target = "libro", ignore = true)
  ComentarioEntity dtoToNewEntity(CrearComentarioDTO crearComentarioDTO);

  @Mapping(source = "libro.id_libro", target = "libro_id")
  List<ComentarioDTO> listEntityToListDTO(List<ComentarioEntity> comentarioEntities);

  void updateEntityFromDTO(UpdateComentarioDTO updateComentarioDTO, @MappingTarget ComentarioEntity comentarioEntity);
}
