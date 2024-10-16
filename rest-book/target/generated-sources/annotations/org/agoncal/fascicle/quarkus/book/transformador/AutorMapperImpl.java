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
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorEnLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-16T13:42:43-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class AutorMapperImpl implements AutorMapper {

    @Override
    public AutorDTO entityToDTO(AutorEntity autorEntity) {
        if ( autorEntity == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setNombre( autorEntity.nombre );
        autorDTO.setApellido( autorEntity.apellido );
        autorDTO.setNacionalidad( autorEntity.nacionalidad );
        autorDTO.setAutorias( libroEntitySetToLibroDTOSet( autorEntity.autorias ) );
        autorDTO.id_autor = autorEntity.id_autor;

        return autorDTO;
    }

    @Override
    public AutorEntity dtoToEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        AutorEntity autorEntity = new AutorEntity();

        autorEntity.id_autor = autorDTO.getId_autor();
        autorEntity.nombre = autorDTO.getNombre();
        autorEntity.apellido = autorDTO.getApellido();
        autorEntity.nacionalidad = autorDTO.getNacionalidad();
        autorEntity.autorias = libroDTOSetToLibroEntitySet( autorDTO.getAutorias() );

        return autorEntity;
    }

    @Override
    public AutorEntity dtoToNewEntity(CrearAutorDTO newAutor) {
        if ( newAutor == null ) {
            return null;
        }

        AutorEntity autorEntity = new AutorEntity();

        autorEntity.nombre = newAutor.getNombre();
        autorEntity.apellido = newAutor.getApellido();
        autorEntity.nacionalidad = newAutor.getNacionalidad();

        return autorEntity;
    }

    @Override
    public List<AutorSimpleDTO> listEntityToListSimpleDTO(List<AutorEntity> autorEntityList) {
        if ( autorEntityList == null ) {
            return null;
        }

        List<AutorSimpleDTO> list = new ArrayList<AutorSimpleDTO>( autorEntityList.size() );
        for ( AutorEntity autorEntity : autorEntityList ) {
            list.add( autorEntityToAutorSimpleDTO( autorEntity ) );
        }

        return list;
    }

    @Override
    public Set<AutorEnLibroDTO> listEntityToListEnLibroDTO(Set<AutorEntity> autorEntityList) {
        if ( autorEntityList == null ) {
            return null;
        }

        Set<AutorEnLibroDTO> set = new LinkedHashSet<AutorEnLibroDTO>( Math.max( (int) ( autorEntityList.size() / .75f ) + 1, 16 ) );
        for ( AutorEntity autorEntity : autorEntityList ) {
            set.add( autorEntityToAutorEnLibroDTO( autorEntity ) );
        }

        return set;
    }

    @Override
    public void updateEntityFromDto(AutorDTO autorDTO, AutorEntity autorEntity) {
        if ( autorDTO == null ) {
            return;
        }

        autorEntity.id_autor = autorDTO.getId_autor();
        autorEntity.nombre = autorDTO.getNombre();
        autorEntity.apellido = autorDTO.getApellido();
        autorEntity.nacionalidad = autorDTO.getNacionalidad();
        if ( autorEntity.autorias != null ) {
            Set<LibroEntity> set = libroDTOSetToLibroEntitySet( autorDTO.getAutorias() );
            if ( set != null ) {
                autorEntity.autorias.clear();
                autorEntity.autorias.addAll( set );
            }
            else {
                autorEntity.autorias = null;
            }
        }
        else {
            Set<LibroEntity> set = libroDTOSetToLibroEntitySet( autorDTO.getAutorias() );
            if ( set != null ) {
                autorEntity.autorias = set;
            }
        }
    }

    protected LibroDTO libroEntityToLibroDTO(LibroEntity libroEntity) {
        if ( libroEntity == null ) {
            return null;
        }

        LibroDTO libroDTO = new LibroDTO();

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

    protected Set<LibroDTO> libroEntitySetToLibroDTOSet(Set<LibroEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<LibroDTO> set1 = new LinkedHashSet<LibroDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( LibroEntity libroEntity : set ) {
            set1.add( libroEntityToLibroDTO( libroEntity ) );
        }

        return set1;
    }

    protected LibroEntity libroDTOToLibroEntity(LibroDTO libroDTO) {
        if ( libroDTO == null ) {
            return null;
        }

        LibroEntity libroEntity = new LibroEntity();

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

    protected Set<LibroEntity> libroDTOSetToLibroEntitySet(Set<LibroDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<LibroEntity> set1 = new LinkedHashSet<LibroEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( LibroDTO libroDTO : set ) {
            set1.add( libroDTOToLibroEntity( libroDTO ) );
        }

        return set1;
    }

    protected AutorSimpleDTO autorEntityToAutorSimpleDTO(AutorEntity autorEntity) {
        if ( autorEntity == null ) {
            return null;
        }

        AutorSimpleDTO autorSimpleDTO = new AutorSimpleDTO();

        autorSimpleDTO.setNombre( autorEntity.nombre );
        autorSimpleDTO.setApellido( autorEntity.apellido );
        autorSimpleDTO.setNacionalidad( autorEntity.nacionalidad );
        autorSimpleDTO.id_autor = autorEntity.id_autor;

        return autorSimpleDTO;
    }

    protected AutorEnLibroDTO autorEntityToAutorEnLibroDTO(AutorEntity autorEntity) {
        if ( autorEntity == null ) {
            return null;
        }

        AutorEnLibroDTO autorEnLibroDTO = new AutorEnLibroDTO();

        autorEnLibroDTO.setApellido( autorEntity.apellido );
        autorEnLibroDTO.setNombre( autorEntity.nombre );

        return autorEnLibroDTO;
    }
}
