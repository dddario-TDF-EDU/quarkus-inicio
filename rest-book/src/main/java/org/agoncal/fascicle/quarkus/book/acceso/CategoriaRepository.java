package org.agoncal.fascicle.quarkus.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;

import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CategoriaRepository implements PanacheRepository<CategoriaEntity> {

  @Inject
  EntityManager em;

  public void createCategoriaRepo(@Valid CategoriaEntity newCategoria) { persist(newCategoria); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<CategoriaEntity> returnAllCategoriasRepo() { return listAll(); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  CategoriaEntity findCategoriaByIdRepo (Long id) { return findById(id); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  CategoriaEntity findCategoriaByNombre (String nombre) {
    return find("nombre", nombre).firstResult();
  }


  public CategoriaEntity updateCategoriaRepo(@Valid CategoriaEntity categoria) {
    CategoriaEntity updateEntity = em.merge(categoria);
    return  updateEntity;
  }

  public boolean deleteCategoriaById(Long id) { return deleteById(id); }

  public void persist(CategoriaEntity categoriaEntity) { PanacheRepository.super.persist(categoriaEntity); }

}
