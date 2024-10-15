package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.libro.CrearLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.UpdateLibroDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T13:20:06-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class BookMapperImpl implements BookMapper {

    @Override
    public LibroDTO entityToDTO(LibroEntity libroEntity) {
        if ( libroEntity == null ) {
            return null;
        }

        LibroDTO libroDTO = new LibroDTO();

        libroDTO.setIsbn_13( libroEntity.isbn13 );
        libroDTO.setIsbn_10( libroEntity.isbn10 );
        libroDTO.setId_libro( libroEntity.id_libro );
        libroDTO.setYearOfPublication( libroEntity.yearOfPublication );
        libroDTO.setSmallImageUrl( libroEntity.smallImageUrl );
        libroDTO.setMediumImageUrl( libroEntity.mediumImageUrl );
        libroDTO.titulo = libroEntity.titulo;
        libroDTO.num_paginas = libroEntity.num_paginas;
        libroDTO.ranking = libroEntity.ranking;
        libroDTO.precio = libroEntity.precio;
        libroDTO.descripcion = libroEntity.descripcion;

        return libroDTO;
    }

    @Override
    public LibroEntity dtoToEntity(LibroDTO libroDTO) {
        if ( libroDTO == null ) {
            return null;
        }

        LibroEntity libroEntity = new LibroEntity();

        libroEntity.isbn13 = libroDTO.getIsbn_13();
        libroEntity.isbn10 = libroDTO.getIsbn_10();
        libroEntity.id_libro = libroDTO.getId_libro();
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
    public LibroEntity dtoToNewEntity(CrearLibroDTO crearLibroDTO) {
        if ( crearLibroDTO == null ) {
            return null;
        }

        LibroEntity libroEntity = new LibroEntity();

        libroEntity.isbn13 = crearLibroDTO.getIsbn_13();
        libroEntity.isbn10 = crearLibroDTO.getIsbn_10();
        libroEntity.titulo = crearLibroDTO.getTitulo();
        libroEntity.yearOfPublication = crearLibroDTO.getYearOfPublication();
        libroEntity.num_paginas = crearLibroDTO.getNum_paginas();
        libroEntity.ranking = crearLibroDTO.getRanking();
        libroEntity.precio = crearLibroDTO.getPrecio();

        return libroEntity;
    }

    @Override
    public List<LibroDTO> entityListToListDTO(List<LibroEntity> libroEntityList) {
        if ( libroEntityList == null ) {
            return null;
        }

        List<LibroDTO> list = new ArrayList<LibroDTO>( libroEntityList.size() );
        for ( LibroEntity libroEntity : libroEntityList ) {
            list.add( entityToDTO( libroEntity ) );
        }

        return list;
    }

    @Override
    public void updateBookFromDTO(UpdateLibroDTO updateLibroDTO, LibroEntity libroEntity) {
        if ( updateLibroDTO == null ) {
            return;
        }

        libroEntity.isbn13 = updateLibroDTO.getIsbn_13();
        libroEntity.isbn10 = updateLibroDTO.getIsbn_10();
        libroEntity.id_libro = updateLibroDTO.getId_libro();
        libroEntity.titulo = updateLibroDTO.getTitulo();
        libroEntity.yearOfPublication = updateLibroDTO.getYearOfPublication();
        libroEntity.num_paginas = updateLibroDTO.getNum_paginas();
        libroEntity.ranking = updateLibroDTO.getRanking();
        libroEntity.precio = updateLibroDTO.getPrecio();
        libroEntity.smallImageUrl = updateLibroDTO.getSmallImageUrl();
        libroEntity.mediumImageUrl = updateLibroDTO.getMediumImageUrl();
        libroEntity.descripcion = updateLibroDTO.getDescripcion();
    }
}
