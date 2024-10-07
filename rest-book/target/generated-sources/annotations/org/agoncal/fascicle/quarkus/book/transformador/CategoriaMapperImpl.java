package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-07T13:40:57-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaDTO toDTO(CategoriaEntity categoriaEntity) {
        if ( categoriaEntity == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setNombre( categoriaEntity.nombre );
        categoriaDTO.id_categoria = categoriaEntity.id_categoria;

        return categoriaDTO;
    }

    @Override
    public CategoriaEntity toEntity(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.id_categoria = categoriaDTO.getId_categoria();
        categoriaEntity.nombre = categoriaDTO.getNombre();

        return categoriaEntity;
    }

    @Override
    public CategoriaEntity toNewEntity(CrearCategoriaDTO crearCategoriaDTO) {
        if ( crearCategoriaDTO == null ) {
            return null;
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.nombre = crearCategoriaDTO.getNombre();

        return categoriaEntity;
    }

    @Override
    public List<CategoriaDTO> toListDTO(List<CategoriaEntity> categoriaEntityList) {
        if ( categoriaEntityList == null ) {
            return null;
        }

        List<CategoriaDTO> list = new ArrayList<CategoriaDTO>( categoriaEntityList.size() );
        for ( CategoriaEntity categoriaEntity : categoriaEntityList ) {
            list.add( toDTO( categoriaEntity ) );
        }

        return list;
    }

    @Override
    public void updateCategoriaFromDTO(CategoriaDTO categoriaDTO, CategoriaEntity categoriaEntity) {
        if ( categoriaDTO == null ) {
            return;
        }

        categoriaEntity.id_categoria = categoriaDTO.getId_categoria();
        categoriaEntity.nombre = categoriaDTO.getNombre();
    }
}
