package org.agoncal.fascicle.quarkus.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PreRemove;
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
  public List<CategoriaEntity> returnAllCategoriasRepo() {
    return em.createQuery("FROM CategoriaEntity", CategoriaEntity.class).getResultList();
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public CategoriaEntity findCategoriaByIdRepo (Integer id) {
    return em.find(CategoriaEntity.class, id);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public CategoriaEntity findCategoriaByNombre (String nombre) {
    CategoriaEntity resultado = em.createQuery( "SELECT c FROM CategoriaEntity c WHERE c.nombre = :nombre", CategoriaEntity.class).setParameter("nombre", nombre).getResultList().stream().findFirst().orElse(null);
//    if (resultado == null) {
//      return null;
//    } else {
      return resultado;
    //}
  }

  public CategoriaEntity updateCategoriaRepo(@Valid CategoriaEntity categoria) {
    return em.merge(categoria);
  }

  public CategoriaEntity addSubcategoriaRepo(@Valid CategoriaEntity padre, @Valid CategoriaEntity hija) {
    padre.subcategorias.add(hija);
    updateCategoriaRepo(padre);
    return  padre;
  }

  public CategoriaEntity removeSubcategoriaRepo(@Valid CategoriaEntity padre, @Valid CategoriaEntity hija) {
    padre.subcategorias.remove(hija);
    updateCategoriaRepo(padre);
    return  padre;
  }

  public void deleteCategoriaById(Integer id) {
    CategoriaEntity catHija = findCategoriaByIdRepo(id);
    removeRelations(catHija);
    em.flush();
    em.remove(catHija);
  }

  @PreRemove
  private void removeRelations(CategoriaEntity catHija) {
    for (CategoriaEntity catsPadre : catHija.subcategorias) {
      catsPadre.subcategorias.remove(catHija);
    }
    catHija.subcategorias.clear();
  }

  public void persist(CategoriaEntity categoriaEntity) { PanacheRepository.super.persist(categoriaEntity); }

}
