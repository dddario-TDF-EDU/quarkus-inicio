package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-04T13:03:54-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class AutorMapperImpl implements AutorMapper {

    @Override
    public AutorDTO toDTO(AutorEntity autorEntity) {
        if ( autorEntity == null ) {
            return null;
        }

        AutorDTO autorDTO = new AutorDTO();

        autorDTO.setNombre( autorEntity.nombre );
        autorDTO.setApellido( autorEntity.apellido );
        autorDTO.setNacionalidad( autorEntity.nacionalidad );

        return autorDTO;
    }

    @Override
    public AutorEntity toEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        AutorEntity autorEntity = new AutorEntity();

        autorEntity.nombre = autorDTO.getNombre();
        autorEntity.apellido = autorDTO.getApellido();
        autorEntity.nacionalidad = autorDTO.getNacionalidad();

        return autorEntity;
    }

    @Override
    public AutorEntity toNewEntity(CrearAutorDTO newAutor) {
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
    public List<AutorDTO> toListDTO(List<AutorEntity> autorEntityList) {
        if ( autorEntityList == null ) {
            return null;
        }

        List<AutorDTO> list = new ArrayList<AutorDTO>( autorEntityList.size() );
        for ( AutorEntity autorEntity : autorEntityList ) {
            list.add( toDTO( autorEntity ) );
        }

        return list;
    }

    @Override
    public void updateAutorFromDto(AutorDTO autorDTO, AutorEntity autorEntity) {
        if ( autorDTO == null ) {
            return;
        }

        autorEntity.nombre = autorDTO.getNombre();
        autorEntity.apellido = autorDTO.getApellido();
        autorEntity.nacionalidad = autorDTO.getNacionalidad();
    }
}
