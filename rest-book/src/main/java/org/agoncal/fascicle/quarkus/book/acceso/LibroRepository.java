package org.agoncal.fascicle.quarkus.book.acceso;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class LibroRepository implements PanacheRepository<LibroEntity> {

  @Inject
  EntityManager em;

  public void createNewBookRepo(@Valid LibroEntity newBook) {
    em.persist(newBook);
    System.out.println(newBook.id_libro + " mi ID nuevo? sss");
    em.flush();
  }

  public List<LibroEntity> returnByAutor(@Valid Integer id_autor) {
    return em.createNativeQuery("SELECT libros.id_libro," +
      "libros.titulo," +
      "libros.isbn_13," +
      "libros.isbn_10," +
      "libros.year_of_publication," +
      "libros.num_de_paginas," +
      "libros.ranking," +
      "libros.precio," +
      "libros.small_image_url," +
      "libros.medium_image_url," +
      "libros.descripcion," +
      "libros.nro_categoria " +
      "FROM libros JOIN autoria_de_libros ON libros.id_libro=autoria_de_libros.nro_libro WHERE nro_autor = :id_autor", LibroEntity.class).setParameter("id_autor", id_autor).getResultList();
  }

  public List<LibroEntity> returnByCategoria(@Valid Integer id_categoria) {
    List<LibroEntity> libroEntityList = em.createNativeQuery("SELECT * FROM libros WHERE nro_categoria = :id_categoria", LibroEntity.class)
      .setParameter("id_categoria", id_categoria).getResultList();
    List<Integer> cantSubcategorias = em.createNativeQuery("SELECT categoria_hija FROM categorias RIGHT JOIN subcategorias ON categorias.id_categoria = subcategorias.categoria_padre WHERE id_categoria = :id_categoria", Integer.class)
      .setParameter("id_categoria", id_categoria).getResultList();
    for (Integer id_subcategoria: cantSubcategorias
         ) {
      List<LibroEntity> nuevosLibros = em.createNativeQuery("SELECT * FROM libros WHERE nro_categoria = :id_categoria", LibroEntity.class)
        .setParameter("id_categoria", id_subcategoria).getResultList();
      if (nuevosLibros != null) {
        libroEntityList = Stream.concat(libroEntityList.stream(), nuevosLibros.stream()).toList();
      }
    }
    if (libroEntityList != null) {
      return libroEntityList;
    } else {
      return null;
    }
  }

  public List<LibroEntity> returnByRank(@Valid double rank) {
    return em.createNativeQuery("SELECT * FROM libros  l WHERE l.ranking > :rank", LibroEntity.class).setParameter("rank", rank).getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<LibroEntity> returnAllBooksRepo() {
    return em.createQuery("FROM LibroEntity", LibroEntity.class).getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public LibroEntity findBookByIdRepo(Integer id) {
    LibroEntity libroEntity = em.createQuery("SELECT l FROM LibroEntity l WHERE l.id_libro = :id", LibroEntity.class)
      .setParameter("id", id).getResultList().stream().findFirst().orElse(null);
    if (libroEntity == null) {
      return null;
    } else {
      return libroEntity;
    }
    //return em.createQuery("SELECT l FROM LibroEntity l WHERE l.id_libro = :id", LibroEntity.class).setParameter("id", id).getResultList().stream().findFirst().orElse(null);
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

  public void deleteBookByIdRepo(Integer id) {
    LibroEntity libroEntity = findBookByIdRepo(id);
    if (libroEntity != null) {
      em.remove(libroEntity);
    }
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
