package org.agoncal.fascicle.quarkus.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;

import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookRepository implements PanacheRepository<LibroEntity> {

  @Inject
  EntityManager em;

  public void createNewBookRepo(@Valid LibroEntity newBook) {
    persist(newBook);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<LibroEntity> returnAllBooksRepo() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public LibroEntity findBookByIdRepo(Integer id) {
    return findById(Long.valueOf(id));
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public LibroEntity findRandomBookRepo() {
    if (count() > 0) {
      LibroEntity randomBook = null; // AAAAA
      while (randomBook == null) {
        randomBook = findRandomRepo();
      }
      return randomBook;
    }
    return null;
  }

  public LibroEntity updateBookRepo(@Valid LibroEntity book) {
    LibroEntity updatedEntity = em.merge(book); // AAAAA
    return updatedEntity;
  }

  public boolean deleteBookByIdRepo(Long id) {
    return deleteById(id);
  }

  public LibroEntity findRandomRepo() {
    long countBooks = count();
    int randomBook = new Random().nextInt((int) countBooks);
    return findAll().page(randomBook, 1).firstResult();
  }

  public void persist(LibroEntity libroEntity) {
    PanacheRepository.super.persist(libroEntity);
  }
}
