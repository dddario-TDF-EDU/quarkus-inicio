package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.UpdateLibroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper()
public interface BookMapper {


  @Mapping(target = "isbn_13", source = "isbn13")
  @Mapping(target = "isbn_10", source = "isbn10")
  LibroDTO entityToDTO(LibroEntity libroEntity);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  LibroEntity dtoToEntity(LibroDTO libroDTO);


  @Mapping(target = "autores_de_libros", ignore = true)
  @Mapping(target = "categoriaEntity", ignore = true)
  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  LibroEntity dtoToNewEntity(CrearLibroDTO crearLibroDTO);

  @Mapping(source = "isbn13", target = "isbn_13")
  @Mapping(source = "isbn10", target = "isbn_10")
  List<LibroDTO> entityListToListDTO(List<LibroEntity> libroEntityList);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  void updateBookFromDTO(UpdateLibroDTO updateLibroDTO, @MappingTarget LibroEntity libroEntity);

}
