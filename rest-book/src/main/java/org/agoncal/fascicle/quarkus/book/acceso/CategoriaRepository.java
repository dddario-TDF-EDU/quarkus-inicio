package org.agoncal.fascicle.quarkus.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
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
  public CategoriaEntity findCategoriaByIdRepo (Integer id) { return findById((long)id); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public CategoriaEntity findCategoriaByNombre (String nombre) {
    return find("nombre", nombre).firstResult();
  }



  public CategoriaEntity updateCategoriaRepo(@Valid CategoriaEntity categoria) {
    return em.merge(categoria);
  }

  public void addSubcategoriaRepo(@Valid CategoriaEntity padre, @Valid CategoriaEntity hija) {
    em.createQuery( "INSERT INTO CategoriaEntity (" +
        "subcategorias INSERT INTO (categoria_padre, categoria_hija) VALUES (1,2)) " +
      "VALUES (1,2)")
      .setParameter(1,padre.id_categoria)
      .setParameter(2, hija.id_categoria)
      .executeUpdate();
    return  ;
  }

  public boolean deleteCategoriaById(Long id) { return deleteById(id); }

  public void persist(CategoriaEntity categoriaEntity) { PanacheRepository.super.persist(categoriaEntity); }

}
