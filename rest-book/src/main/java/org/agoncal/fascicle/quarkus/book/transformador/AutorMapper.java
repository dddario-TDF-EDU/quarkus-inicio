package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper()
public interface AutorMapper {

  AutorDTO entityToDTO(AutorEntity autorEntity);

  AutorEntity dtoToEntity(AutorDTO autorDTO);

  AutorEntity dtoToNewEntity(CrearAutorDTO newAutor);

  List<AutorSimpleDTO> listEntityToListSimpleDTO(List<AutorEntity> autorEntityList);

  void updateEntityFromDto(AutorDTO autorDTO, @MappingTarget AutorEntity autorEntity);

}
