package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.CategoriaRepository;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.UpdateNombreCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transformador.CategoriaMapper;

import java.util.HashSet;
import java.util.List;

@ApplicationScoped
public class CategoriaService {


  @Inject
  CategoriaRepository categoriaRepository;

  @Inject
  CategoriaMapper categoriaMapper;


  public CategoriaDTO persistCategoria(CrearCategoriaDTO crearCategoriaDTO) {
    System.out.println("cuerpo en servicio" + crearCategoriaDTO.getNombre());
    CategoriaEntity categoriaEntity = categoriaMapper.dtoToNewEntity(crearCategoriaDTO);
    System.out.println("cuerpo en servicio transformado a identidad" + categoriaEntity.nombre);
    if(categoriaRepository.findCategoriaByNombre(crearCategoriaDTO.nombre) == null) {
      categoriaRepository.createCategoriaRepo(categoriaEntity);
      return categoriaMapper.entityToDTO(categoriaEntity);
    } else {
      return null;
    }
  }

  public CategoriaDTO findCategoriaById(@Valid Integer id) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByIdRepo(id);
    return categoriaMapper.entityToDTO(categoriaEntity);
  }

  public CategoriaDTO findCategoriaByName(@Valid CategoriaDTO categoriaDTO) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByNombre(categoriaDTO.getNombre());
    return categoriaMapper.entityToDTO(categoriaEntity);
  }

  public List<CategoriaDTO> returnAllCategorias() {
    System.out.print("asdasdsaasdasdaaaaaaa");
    return categoriaMapper.listEntityToListDTO(categoriaRepository.returnAllCategoriasRepo());
  }

  public boolean deleteCategoriaById(Long id) {
    return categoriaRepository.deleteCategoriaById(id);
  }

  public UpdateNombreCategoriaDTO updateNombreCategoria(@Valid UpdateNombreCategoriaDTO categoriaDTO) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByIdRepo(categoriaDTO.getId_categoria());
    if (categoriaEntity != null) {
      categoriaMapper.updateNombreCategoriaFromDTO(categoriaDTO, categoriaEntity);
      categoriaRepository.updateCategoriaRepo(categoriaEntity);
      System.out.println("llegueeeeeeea datos persistidos" + categoriaDTO.getNombre());
      return categoriaMapper.entityToNombreDTO(categoriaEntity);
    }
    return null;
  }

  @Transactional
  public CategoriaDTO addSubcategoria(@Valid CategoriaDTO subcategoria, Integer id) {
    CategoriaEntity categoriaEntityPadre = categoriaRepository.findCategoriaByIdRepo(id);
    CategoriaEntity categoriaEntityHija = categoriaRepository.findCategoriaByIdRepo(subcategoria.getId_categoria());
    if (categoriaEntityPadre == null || categoriaEntityHija == null) {
      return null;
    } else {
//      HashSet<CategoriaDTO> test = new HashSet<CategoriaDTO>();
//      test.add(subcategoria);
//      CategoriaDTO categoriaPadre = categoriaMapper.entityToDTO(categoriaEntityPadre);
//      categoriaPadre.setSubcategorias(test);
//      categoriaMapper.updateSubcategoriaFromDTO(categoriaPadre,categoriaEntityPadre);
//      System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + categoriaPadre.getSubcategorias().stream().count() + " y "  + "aaaaaaaaaaaaaaaaaaaaaaaaa");
//      categoriaRepository.updateCategoriaRepo(categoriaEntityPadre);

      categoriaEntityPadre.subcategorias.add(categoriaEntityHija);
      categoriaRepository.updateCategoriaRepo(categoriaEntityPadre);

//      categoriaRepository.addSubcategoriaRepo(categoriaEntityPadre,categoriaEntityHija);
//      categoriaEntityPadre = categoriaRepository.findCategoriaByIdRepo(id);
      return categoriaMapper.entityToDTO(categoriaEntityPadre);
    }
  }

}
