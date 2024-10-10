package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.CategoriaRepository;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaSencillaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.UpdateNombreCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transformador.CategoriaMapper;

import java.util.HashSet;
import java.util.List;

@Transactional
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
    if (categoriaRepository.findCategoriaByNombre(crearCategoriaDTO.nombre) == null) {
      categoriaRepository.createCategoriaRepo(categoriaEntity);
      return categoriaMapper.entityToDTO(categoriaEntity);
    } else {
      return null;
    }
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public CategoriaDTO findCategoriaById(@Valid Integer id) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByIdRepo(id);
    return categoriaMapper.entityToDTO(categoriaEntity);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public CategoriaDTO findCategoriaByName(@Valid String nombre) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByNombre(nombre);
    return categoriaMapper.entityToDTO(categoriaEntity);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<CategoriaSencillaDTO> returnAllCategorias() {
    return categoriaMapper.listEntityToListSimpleDTO(categoriaRepository.returnAllCategoriasRepo());
  }

  public void deleteCategoriaById(Integer id) {
    categoriaRepository.deleteCategoriaById(id);
  }

  public UpdateNombreCategoriaDTO updateNombreCategoria(@Valid UpdateNombreCategoriaDTO categoriaDTO) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByIdRepo(categoriaDTO.getId_categoria());
    if (categoriaEntity != null) {
      categoriaMapper.updateNombreCategoriaFromDTO(categoriaDTO, categoriaEntity);
      categoriaRepository.updateCategoriaRepo(categoriaEntity);
      return categoriaMapper.entityToNombreDTO(categoriaEntity);
    }
    return null;
  }


  public CategoriaDTO addSubcategoria(@Valid CategoriaDTO subcategoria, Integer id) {
    CategoriaEntity catPadre = categoriaRepository.findCategoriaByIdRepo(id);
    if (catPadre == null) {
      return null;
    } else {
      CategoriaEntity catHija = categoriaRepository.findCategoriaByIdRepo(subcategoria.getId_categoria());
      if (catHija == null) {
        return null;
      } else {
        return categoriaMapper.entityToDTO(categoriaRepository.addSubcategoriaRepo(catPadre, catHija));
      }
    }
  }
  public CategoriaDTO removeSubcategoria(@Valid CategoriaDTO subcategoria, Integer id) {
    CategoriaEntity catPadre = categoriaRepository.findCategoriaByIdRepo(id);
    if (catPadre == null) {
      return null;
    } else {
      CategoriaEntity catHija = categoriaRepository.findCategoriaByIdRepo(subcategoria.getId_categoria());
      if (catHija == null) {
        return null;
      } else {
        return categoriaMapper.entityToDTO(categoriaRepository.removeSubcategoriaRepo(catPadre, catHija));
      }
    }
  }


}
