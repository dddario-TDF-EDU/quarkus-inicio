package org.agoncal.fascicle.quarkus.book.transformador;

import org.agoncal.fascicle.quarkus.book.modelo.BookEntity;
import org.agoncal.fascicle.quarkus.book.transferible.BookDTO;
import org.agoncal.fascicle.quarkus.book.transferible.CreateBookDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = )
public interface BookMapper {
  BookEntity toEntity(CreateBookDTO newBook);
  BookDTO toDTO(BookEntity bookEntity);
  List<BookDTO> toDTOList(List<BookEntity> books);
}
