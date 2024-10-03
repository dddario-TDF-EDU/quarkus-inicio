package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;

import java.util.List;

public class AutorRepository implements PanacheRepository<AutorEntity> {

  @Inject
  EntityManager em;

  public void createNewAutorRepo(@Valid AutorEntity autorEntity) {
    persist(autorEntity);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<AutorEntity> returnAllAutoresRepo() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public AutorEntity findAutorByIdRepo(Long id) { return findById(id); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public boolean findAutorByName(@Valid AutorEntity autorEntity) {
    //query consulta
    return false;
  }

  public AutorEntity updateAutorRepo(@Valid AutorEntity book) {
    AutorEntity updatedEntity = em.merge(book); // AAAAA
    return updatedEntity;
  }

  public boolean deleteAutorByIdRepo(Long id) {
    return deleteById(id);
  }

  public void persist(AutorEntity autorEntity) {
    PanacheRepository.super.persist(autorEntity);
  }
}
