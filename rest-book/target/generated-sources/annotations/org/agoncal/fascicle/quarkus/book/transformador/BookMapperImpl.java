package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T13:07:59-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class BookMapperImpl implements BookMapper {

    @Override
    public LibroDTO aDTO(LibroEntity libroEntity) {
        if ( libroEntity == null ) {
            return null;
        }

        LibroDTO libroDTO = new LibroDTO();

        libroDTO.setIsbn_13( libroEntity.isbn13 );
        libroDTO.setIsbn_10( libroEntity.isbn10 );
        libroDTO.setYearOfPublication( libroEntity.yearOfPublication );
        libroDTO.setSmallImageUrl( libroEntity.smallImageUrl );
        libroDTO.setMediumImageUrl( libroEntity.mediumImageUrl );
        libroDTO.id_libro = libroEntity.id_libro;
        libroDTO.titulo = libroEntity.titulo;
        libroDTO.num_paginas = libroEntity.num_paginas;
        libroDTO.ranking = libroEntity.ranking;
        libroDTO.precio = libroEntity.precio;
        libroDTO.descripcion = libroEntity.descripcion;

        return libroDTO;
    }

    @Override
    public LibroEntity toEntity(LibroDTO libroDTO) {
        if ( libroDTO == null ) {
            return null;
        }

        LibroEntity libroEntity = new LibroEntity();

        libroEntity.isbn13 = libroDTO.getIsbn_13();
        libroEntity.isbn10 = libroDTO.getIsbn_10();
        libroEntity.id_libro = libroDTO.id_libro;
        libroEntity.titulo = libroDTO.titulo;
        libroEntity.yearOfPublication = libroDTO.getYearOfPublication();
        libroEntity.num_paginas = libroDTO.num_paginas;
        libroEntity.ranking = libroDTO.ranking;
        libroEntity.precio = libroDTO.precio;
        libroEntity.smallImageUrl = libroDTO.getSmallImageUrl();
        libroEntity.mediumImageUrl = libroDTO.getMediumImageUrl();
        libroEntity.descripcion = libroDTO.descripcion;

        return libroEntity;
    }

    @Override
    public LibroEntity toNewEntity(CrearLibroDTO newBook) {
        if ( newBook == null ) {
            return null;
        }

        LibroEntity libroEntity = new LibroEntity();

        libroEntity.yearOfPublication = newBook.getYearOfPublication();

        return libroEntity;
    }

    @Override
    public List<LibroDTO> toListDTO(List<LibroEntity> libroEntityList) {
        if ( libroEntityList == null ) {
            return null;
        }

        List<LibroDTO> list = new ArrayList<LibroDTO>( libroEntityList.size() );
        for ( LibroEntity libroEntity : libroEntityList ) {
            list.add( aDTO( libroEntity ) );
        }

        return list;
    }

    @Override
    public void updateBookFromDTO(LibroDTO libroDTO, LibroEntity libroEntity) {
        if ( libroDTO == null ) {
            return;
        }

        libroEntity.isbn13 = libroDTO.getIsbn_13();
        libroEntity.isbn10 = libroDTO.getIsbn_10();
        if ( libroEntity.autores_de_libros != null ) {
            Set<AutorEntity> set = libroDTO.autores;
            if ( set != null ) {
                libroEntity.autores_de_libros.clear();
                libroEntity.autores_de_libros.addAll( set );
            }
            else {
                libroEntity.autores_de_libros = null;
            }
        }
        else {
            Set<AutorEntity> set = libroDTO.autores;
            if ( set != null ) {
                libroEntity.autores_de_libros = new LinkedHashSet<AutorEntity>( set );
            }
        }
        libroEntity.id_libro = libroDTO.id_libro;
        libroEntity.titulo = libroDTO.titulo;
        libroEntity.yearOfPublication = libroDTO.getYearOfPublication();
        libroEntity.num_paginas = libroDTO.num_paginas;
        libroEntity.ranking = libroDTO.ranking;
        libroEntity.precio = libroDTO.precio;
        libroEntity.smallImageUrl = libroDTO.getSmallImageUrl();
        libroEntity.mediumImageUrl = libroDTO.getMediumImageUrl();
        libroEntity.descripcion = libroDTO.descripcion;
    }
}
