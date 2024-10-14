package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper()
public interface BookMapper {


  @Mapping(target = "isbn_13", source = "isbn13")
  @Mapping(target = "isbn_10", source = "isbn10")
  LibroDTO entitytoDTO(LibroEntity libroEntity);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  LibroEntity dtoToEntity(LibroDTO libroDTO);

  @Mapping(target = "yearOfPublication", source = "yearOfPublication")
  @Mapping(target = "autores_de_libros", source = "autores")
  @Mapping(target = "categoriaEntity", source = "categoria")
  LibroEntity dtoToNewEntity(CrearLibroDTO crearLibroDTO);

  List<LibroDTO> entityListToListDTO(List<LibroEntity> libroEntityList);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  @Mapping(target = "autores_de_libros", source = "idAutores")
  void updateBookFromDTO(LibroDTO libroDTO, @MappingTarget LibroEntity libroEntity);

}
