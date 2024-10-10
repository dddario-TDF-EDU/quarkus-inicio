package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.UpdateNombreCategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper()
public interface CategoriaMapper {

  CategoriaDTO entityToDTO(CategoriaEntity categoriaEntity);

  CategoriaEntity dtoToEntity(CategoriaDTO categoriaDTO);

  CategoriaEntity dtoToNewEntity(CrearCategoriaDTO crearCategoriaDTO);

  UpdateNombreCategoriaDTO entityToNombreDTO(CategoriaEntity categoriaEntity);

  List<CategoriaDTO> listEntityToListDTO(List<CategoriaEntity> categoriaEntityList);
  List<CategoriaSimpleDTO> listEntityToListSimpleDTO(List<CategoriaEntity> categoriaEntityList);

  void updateNombreCategoriaFromDTO(UpdateNombreCategoriaDTO categoriaDTO, @MappingTarget  CategoriaEntity categoriaEntity);

  void updateSubcategoriaFromDTO(CategoriaDTO categoriaDTO, @MappingTarget  CategoriaEntity categoriaEntity);

}
