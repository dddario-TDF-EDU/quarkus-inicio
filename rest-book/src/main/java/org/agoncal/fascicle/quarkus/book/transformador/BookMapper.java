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
  LibroDTO aDTO(LibroEntity libroEntity);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  LibroEntity toEntity(LibroDTO libroDTO);

  @Mapping(target = "yearOfPublication", source = "yearOfPublication")
  LibroEntity toNewEntity(CrearLibroDTO newBook);

  List<LibroDTO> toListDTO(List<LibroEntity> libroEntityList);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  @Mapping(target = "autores", source = "autores_de_libros")
  void updateBookFromDTO(LibroDTO libroDTO, @MappingTarget LibroEntity libroEntity);

}
