package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper()
public interface CategoriaMapper {

  CategoriaDTO toDTO(CategoriaEntity categoriaEntity);

  CategoriaEntity toEntity(CategoriaDTO categoriaDTO);

  CategoriaEntity toNewEntity(CrearCategoriaDTO crearCategoriaDTO);

  List<CategoriaDTO> toListDTO(List<CategoriaEntity> categoriaEntityList);

  void updateCategoriaFromDTO(CategoriaDTO categoriaDTO, @MappingTarget CategoriaEntity categoriaEntity);


}
