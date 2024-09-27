package org.agoncal.fascicle.quarkus.book.acceso;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.BookEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class DBAccess {

  @Inject
  EntityManager em;

  public void createNewBook(@Valid BookEntity newBook) {
    BookEntity.persist(newBook);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<BookEntity> returnAllBooks() {
    return BookEntity.listAll();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Optional <BookEntity> findById(Long id) {
    return BookEntity.findByIdOptional(id);
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public BookEntity findRandomBook() {
    if (BookEntity.count() > 0) {
      BookEntity randomBook = null; // AAAAA
      while (randomBook == null) {
        randomBook = BookEntity.findRandom();
      }
      return randomBook;
    }
    return null;
  }

  public BookEntity updateBook(@Valid BookEntity book) {
    BookEntity updatedEntity = em.merge(book); // AAAAA
    return updatedEntity;
  }

  public void deleteById(Long id) {
    BookEntity.deleteById(id);
  }

}
