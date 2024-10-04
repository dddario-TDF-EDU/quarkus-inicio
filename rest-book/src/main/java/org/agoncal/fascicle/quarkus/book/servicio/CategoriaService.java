package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.CategoriaRepository;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CrearCategoriaDTO;
import org.agoncal.fascicle.quarkus.book.transformador.CategoriaMapper;

import java.util.List;

@ApplicationScoped
public class CategoriaService {


  @Inject
  CategoriaRepository categoriaRepository;

  @Inject
  CategoriaMapper categoriaMapper;


  public CategoriaDTO persistCategoria(@Valid CrearCategoriaDTO crearCategoriaDTO) {
    CategoriaEntity categoriaEntity = categoriaMapper.toNewEntity(crearCategoriaDTO);
    if(categoriaRepository.findCategoriaByNombre(categoriaEntity.nombre) != null) {
      categoriaRepository.persist(categoriaEntity);
      return categoriaMapper.toDTO(categoriaEntity);
    } else {
      return null;
    }
  }

  public CategoriaDTO findCategoriaById(@Valid Long id) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByIdRepo(id);
    return categoriaMapper.toDTO(categoriaEntity);
  }

  public CategoriaDTO findCategoriaByName(@Valid CategoriaDTO categoriaDTO) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByNombre(categoriaDTO.getNombre());
    return categoriaMapper.toDTO(categoriaEntity);
  }

  public List<CategoriaDTO> returnAllCategorias() {
    return categoriaMapper.toListDTO(categoriaRepository.returnAllCategoriasRepo());
  }

  public boolean deleteCategoriaById(Long id) {
    return categoriaRepository.deleteCategoriaById(id);
  }

  public CategoriaDTO updateCategoria(@Valid CategoriaDTO categoriaDTO) {
    CategoriaEntity categoriaEntity = categoriaRepository.findCategoriaByIdRepo(categoriaDTO.getId_categoria());
    if (categoriaEntity != null) {
       categoriaMapper.updateCategoriaFromDTO(categoriaDTO, categoriaEntity);
       categoriaRepository.persist(categoriaEntity);
       return categoriaMapper.toDTO(categoriaEntity);
    }
    return null;
  }

}
