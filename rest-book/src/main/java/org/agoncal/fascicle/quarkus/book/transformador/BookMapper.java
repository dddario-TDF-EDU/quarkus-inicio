package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Inject;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.UpdateLibroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper()
public abstract class BookMapper {

  @Inject
  AutorMapper autorMapper;


  public LibroDTO entityToDTO(LibroEntity libroEntity) {
      LibroDTO libroDTO = auxEntityToDTO(libroEntity);
      libroDTO.setCategoria(libroEntity.categoriaEntity.nombre);
      libroDTO.setAutores(autorMapper.listEntityToListEnLibroDTO(libroEntity.autores_de_libros));
      return libroDTO;
  }

  @Mapping(target = "isbn_13", source = "isbn13")
  @Mapping(target = "isbn_10", source = "isbn10")
  abstract protected LibroDTO auxEntityToDTO(LibroEntity libroEntity);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  abstract public LibroEntity dtoToEntity(LibroDTO libroDTO);


  @Mapping(target = "autores_de_libros", ignore = true)
  @Mapping(target = "categoriaEntity", ignore = true)
  @Mapping(target = "ranking", constant = "0")
  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  abstract public LibroEntity dtoToNewEntity(CrearLibroDTO crearLibroDTO);

  @Mapping(source = "isbn13", target = "isbn_13")
  @Mapping(source = "isbn10", target = "isbn_10")
  public List<LibroDTO> entityListToListDTO(List<LibroEntity> libroEntityList) {
    List<LibroDTO> libroDTOList = new ArrayList<>();
    for (LibroEntity libro: libroEntityList
         ) {
      LibroDTO libroDTO = entityToDTO(libro);
      libroDTO.autores = autorMapper.listEntityToListEnLibroDTO(libro.autores_de_libros);
      libroDTOList.add(libroDTO);
    }
    return libroDTOList;
  }

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  abstract public void updateBookFromDTO(UpdateLibroDTO updateLibroDTO, @MappingTarget LibroEntity libroEntity);
}
