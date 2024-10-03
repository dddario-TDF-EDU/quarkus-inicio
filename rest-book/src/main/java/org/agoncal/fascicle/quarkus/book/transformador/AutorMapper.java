package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper()
public interface AutorMapper {

  AutorDTO toDTO(AutorEntity autorEntity);

  AutorEntity toEntity(AutorDTO autorDTO);

  AutorEntity toNewEntity(CrearAutorDTO newAutor);

  List<AutorDTO> toListDTO(List<AutorEntity> autorEntityList);

  void updateAutorFromDto(AutorDTO autorDTO, @MappingTarget AutorEntity autorEntity);

}
