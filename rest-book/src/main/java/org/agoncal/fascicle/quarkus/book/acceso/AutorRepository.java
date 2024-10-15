package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;

import java.util.List;

@ApplicationScoped
public class AutorRepository implements PanacheRepository<AutorEntity> {

  @Inject
  EntityManager em;

  public void createNewAutorRepo(@Valid AutorEntity autorEntity) {
    persist(autorEntity);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<AutorEntity> returnAllAutoresRepo() {
    return em.createQuery("FROM AutorEntity", AutorEntity.class).getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public AutorEntity findAutorByIdRepo(Integer id) { return em.createQuery("SELECT a FROM AutorEntity a WHERE a.id_autor = :id", AutorEntity.class).setParameter("id", id).getResultList().stream().findFirst().orElse(null); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public AutorEntity findAutorByName(@Valid String autorName) {
    //query consulta
    return null;
  }

  public AutorEntity updateAutorRepo(@Valid AutorEntity book) {
    AutorEntity updatedEntity = em.merge(book);
    return updatedEntity;
  }

  public void deleteAutorByIdRepo(Integer id) {
    AutorEntity autorEntity = findAutorByIdRepo(id);
    em.remove(autorEntity);
  }

}
