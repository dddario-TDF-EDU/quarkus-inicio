package org.agoncal.fascicle.quarkus.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.BookEntity;

import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class BookRepository implements PanacheRepository<BookEntity> {

  @Inject
  EntityManager em;

  public void createNewBookRepo(@Valid BookEntity newBook) {
    persist(newBook);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<BookEntity> returnAllBooksRepo() {
    return listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public BookEntity findByIdRepo(Long id) {
    return findById(id);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public BookEntity findRandomBookRepo() {
    if (count() > 0) {
      BookEntity randomBook = null; // AAAAA
      while (randomBook == null) {
        randomBook = findRandomRepo();
      }
      return randomBook;
    }
    return null;
  }

  public BookEntity updateBookRepo(@Valid BookEntity book) {
    BookEntity updatedEntity = em.merge(book); // AAAAA
    return updatedEntity;
  }

  public boolean deleteByIdRepo(Long id) {
    return deleteById(id);
  }

  public BookEntity findRandomRepo() {
    long countBooks = count();
    int randomBook = new Random().nextInt((int) countBooks);
    return findAll().page(randomBook, 1).firstResult();
  }

  public void persist(BookEntity bookEntity) {
    PanacheRepository.super.persist(bookEntity);
  }
}
