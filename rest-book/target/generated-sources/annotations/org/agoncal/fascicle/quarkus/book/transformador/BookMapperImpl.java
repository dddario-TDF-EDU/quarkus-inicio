package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.BookEntity;
import org.agoncal.fascicle.quarkus.book.transferible.BookDTO;
import org.agoncal.fascicle.quarkus.book.transferible.CreateBookDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-27T13:16:18-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toDTO(BookEntity bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setIsbn_13( bookEntity.isbn13 );
        bookDTO.setIsbn_10( bookEntity.isbn10 );
        if ( bookEntity.id != null ) {
            bookDTO.setId( bookEntity.id );
        }
        bookDTO.setTitle( bookEntity.title );
        bookDTO.setAuthor( bookEntity.author );
        bookDTO.setYearOfPublication( bookEntity.yearOfPublication );
        bookDTO.setNbOfPages( bookEntity.nbOfPages );
        bookDTO.setRank( bookEntity.rank );
        bookDTO.setPrice( bookEntity.price );
        bookDTO.setSmallImageUrl( bookEntity.smallImageUrl );
        bookDTO.setMediumImageUrl( bookEntity.mediumImageUrl );
        bookDTO.setDescription( bookEntity.description );

        return bookDTO;
    }

    @Override
    public BookEntity toEntity(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.isbn13 = bookDTO.getIsbn_13();
        bookEntity.isbn10 = bookDTO.getIsbn_10();
        bookEntity.title = bookDTO.getTitle();
        bookEntity.author = bookDTO.getAuthor();
        bookEntity.yearOfPublication = bookDTO.getYearOfPublication();
        bookEntity.nbOfPages = bookDTO.getNbOfPages();
        bookEntity.rank = bookDTO.getRank();
        bookEntity.price = bookDTO.getPrice();
        bookEntity.smallImageUrl = bookDTO.getSmallImageUrl();
        bookEntity.mediumImageUrl = bookDTO.getMediumImageUrl();
        bookEntity.description = bookDTO.getDescription();

        return bookEntity;
    }

    @Override
    public BookEntity toNewEntity(CreateBookDTO newBook) {
        if ( newBook == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.title = newBook.getTitle();
        bookEntity.author = newBook.getAuthor();
        bookEntity.yearOfPublication = newBook.getYearOfPublication();

        return bookEntity;
    }
}
