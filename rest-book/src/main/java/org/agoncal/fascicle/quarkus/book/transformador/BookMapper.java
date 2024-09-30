package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.BookEntity;
import org.agoncal.fascicle.quarkus.book.transferible.BookDTO;
import org.agoncal.fascicle.quarkus.book.transferible.CreateBookDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper()
public interface BookMapper {


  @Mapping(target = "isbn_13", source = "isbn13")
  @Mapping(target = "isbn_10", source = "isbn10")
  BookDTO toDTO(BookEntity bookEntity);

  @Mapping(target = "isbn13", source = "isbn_13")
  @Mapping(target = "isbn10", source = "isbn_10")
  BookEntity toEntity(BookDTO bookDTO);


  @Mapping(target = "title", source = "title")
  @Mapping(target = "author", source = "author")
  @Mapping(target = "yearOfPublication", source = "yearOfPublication")
  BookEntity toNewEntity(CreateBookDTO newBook);
  //es un repeat del toDTO
  //List<BookDTO> toDTOList(List<BookEntity> books);
}
