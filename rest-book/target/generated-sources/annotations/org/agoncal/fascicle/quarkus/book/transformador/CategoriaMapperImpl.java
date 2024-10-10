package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.inject.Named;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaSencillaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.UpdateNombreCategoriaDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T11:58:29-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@Singleton
@Named
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaDTO entityToDTO(CategoriaEntity categoriaEntity) {
        if ( categoriaEntity == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setNombre( categoriaEntity.nombre );
        categoriaDTO.setSubcategorias( categoriaEntitySetToCategoriaDTOSet( categoriaEntity.subcategorias ) );
        categoriaDTO.id_categoria = categoriaEntity.id_categoria;

        return categoriaDTO;
    }

    @Override
    public CategoriaEntity dtoToEntity(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.id_categoria = categoriaDTO.getId_categoria();
        categoriaEntity.nombre = categoriaDTO.getNombre();
        categoriaEntity.subcategorias = categoriaDTOSetToCategoriaEntitySet( categoriaDTO.getSubcategorias() );

        return categoriaEntity;
    }

    @Override
    public CategoriaEntity dtoToNewEntity(CrearCategoriaDTO crearCategoriaDTO) {
        if ( crearCategoriaDTO == null ) {
            return null;
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.nombre = crearCategoriaDTO.getNombre();

        return categoriaEntity;
    }

    @Override
    public UpdateNombreCategoriaDTO entityToNombreDTO(CategoriaEntity categoriaEntity) {
        if ( categoriaEntity == null ) {
            return null;
        }

        UpdateNombreCategoriaDTO updateNombreCategoriaDTO = new UpdateNombreCategoriaDTO();

        updateNombreCategoriaDTO.setNombre( categoriaEntity.nombre );
        updateNombreCategoriaDTO.id_categoria = categoriaEntity.id_categoria;

        return updateNombreCategoriaDTO;
    }

    @Override
    public List<CategoriaDTO> listEntityToListDTO(List<CategoriaEntity> categoriaEntityList) {
        if ( categoriaEntityList == null ) {
            return null;
        }

        List<CategoriaDTO> list = new ArrayList<CategoriaDTO>( categoriaEntityList.size() );
        for ( CategoriaEntity categoriaEntity : categoriaEntityList ) {
            list.add( entityToDTO( categoriaEntity ) );
        }

        return list;
    }

    @Override
    public List<CategoriaSencillaDTO> listEntityToListSimpleDTO(List<CategoriaEntity> categoriaEntityList) {
        if ( categoriaEntityList == null ) {
            return null;
        }

        List<CategoriaSencillaDTO> list = new ArrayList<CategoriaSencillaDTO>( categoriaEntityList.size() );
        for ( CategoriaEntity categoriaEntity : categoriaEntityList ) {
            list.add( categoriaEntityToCategoriaSencillaDTO( categoriaEntity ) );
        }

        return list;
    }

    @Override
    public void updateNombreCategoriaFromDTO(UpdateNombreCategoriaDTO categoriaDTO, CategoriaEntity categoriaEntity) {
        if ( categoriaDTO == null ) {
            return;
        }

        categoriaEntity.id_categoria = categoriaDTO.getId_categoria();
        categoriaEntity.nombre = categoriaDTO.getNombre();
    }

    @Override
    public void updateSubcategoriaFromDTO(CategoriaDTO categoriaDTO, CategoriaEntity categoriaEntity) {
        if ( categoriaDTO == null ) {
            return;
        }

        categoriaEntity.id_categoria = categoriaDTO.getId_categoria();
        categoriaEntity.nombre = categoriaDTO.getNombre();
        if ( categoriaEntity.subcategorias != null ) {
            Set<CategoriaEntity> set = categoriaDTOSetToCategoriaEntitySet( categoriaDTO.getSubcategorias() );
            if ( set != null ) {
                categoriaEntity.subcategorias.clear();
                categoriaEntity.subcategorias.addAll( set );
            }
            else {
                categoriaEntity.subcategorias = null;
            }
        }
        else {
            Set<CategoriaEntity> set = categoriaDTOSetToCategoriaEntitySet( categoriaDTO.getSubcategorias() );
            if ( set != null ) {
                categoriaEntity.subcategorias = set;
            }
        }
    }

    protected Set<CategoriaDTO> categoriaEntitySetToCategoriaDTOSet(Set<CategoriaEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoriaDTO> set1 = new LinkedHashSet<CategoriaDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoriaEntity categoriaEntity : set ) {
            set1.add( entityToDTO( categoriaEntity ) );
        }

        return set1;
    }

    protected Set<CategoriaEntity> categoriaDTOSetToCategoriaEntitySet(Set<CategoriaDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoriaEntity> set1 = new LinkedHashSet<CategoriaEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoriaDTO categoriaDTO : set ) {
            set1.add( dtoToEntity( categoriaDTO ) );
        }

        return set1;
    }

    protected CategoriaSencillaDTO categoriaEntityToCategoriaSencillaDTO(CategoriaEntity categoriaEntity) {
        if ( categoriaEntity == null ) {
            return null;
        }

        CategoriaSencillaDTO categoriaSencillaDTO = new CategoriaSencillaDTO();

        categoriaSencillaDTO.setNombre( categoriaEntity.nombre );
        categoriaSencillaDTO.id_categoria = categoriaEntity.id_categoria;

        return categoriaSencillaDTO;
    }
}
