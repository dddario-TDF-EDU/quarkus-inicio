package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.ComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.CrearComentarioDTO;
import org.agoncal.fascicle.quarkus.book.transferible.comentario.UpdateComentarioDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-15T13:20:06-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class ComentarioMapperImpl implements ComentarioMapper {

    @Override
    public ComentarioDTO entityToDTO(ComentarioEntity comentarioEntity) {
        if ( comentarioEntity == null ) {
            return null;
        }

        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setLibro_id( comentarioEntityLibroId_libro( comentarioEntity ) );
        comentarioDTO.setNro_linea( comentarioEntity.nro_linea );
        comentarioDTO.setId_comentario( comentarioEntity.id_comentario );
        comentarioDTO.setEmail( comentarioEntity.email );
        comentarioDTO.setTexto( comentarioEntity.texto );
        comentarioDTO.setPuntuacion( comentarioEntity.puntuacion );

        return comentarioDTO;
    }

    @Override
    public ComentarioEntity dtoToEntity(ComentarioDTO comentarioDTO) {
        if ( comentarioDTO == null ) {
            return null;
        }

        ComentarioEntity comentarioEntity = new ComentarioEntity();

        comentarioEntity.id_comentario = comentarioDTO.getId_comentario();
        comentarioEntity.email = comentarioDTO.getEmail();
        comentarioEntity.texto = comentarioDTO.getTexto();
        comentarioEntity.puntuacion = comentarioDTO.getPuntuacion();
        comentarioEntity.nro_linea = comentarioDTO.getNro_linea();

        return comentarioEntity;
    }

    @Override
    public ComentarioEntity dtoToNewEntity(CrearComentarioDTO crearComentarioDTO) {
        if ( crearComentarioDTO == null ) {
            return null;
        }

        ComentarioEntity comentarioEntity = new ComentarioEntity();

        comentarioEntity.email = crearComentarioDTO.getEmail();
        comentarioEntity.texto = crearComentarioDTO.getTexto();
        if ( crearComentarioDTO.getPuntuacion() != null ) {
            comentarioEntity.puntuacion = crearComentarioDTO.getPuntuacion().shortValue();
        }

        return comentarioEntity;
    }

    @Override
    public List<ComentarioDTO> listEntityToListDTO(List<ComentarioEntity> comentarioEntities) {
        if ( comentarioEntities == null ) {
            return null;
        }

        List<ComentarioDTO> list = new ArrayList<ComentarioDTO>( comentarioEntities.size() );
        for ( ComentarioEntity comentarioEntity : comentarioEntities ) {
            list.add( entityToDTO( comentarioEntity ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDTO(UpdateComentarioDTO updateComentarioDTO, ComentarioEntity comentarioEntity) {
        if ( updateComentarioDTO == null ) {
            return;
        }

        comentarioEntity.id_comentario = updateComentarioDTO.getId_comentario();
        comentarioEntity.email = updateComentarioDTO.getEmail();
        comentarioEntity.texto = updateComentarioDTO.getTexto();
        comentarioEntity.puntuacion = updateComentarioDTO.getPuntuacion();
    }

    private Integer comentarioEntityLibroId_libro(ComentarioEntity comentarioEntity) {
        if ( comentarioEntity == null ) {
            return null;
        }
        LibroEntity libro = comentarioEntity.libro;
        if ( libro == null ) {
            return null;
        }
        Integer id_libro = libro.id_libro;
        if ( id_libro == null ) {
            return null;
        }
        return id_libro;
    }
}
