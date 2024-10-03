package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;

import java.util.List;

public class ComentarioRepository implements PanacheRepository<ComentarioEntity> {

  @Inject
  EntityManager em;

  public void createComentarioRepo(@Valid ComentarioEntity newComentario ) { persist(newComentario); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ComentarioEntity> returnAllComentariosRepo() { return listAll(); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  ComentarioEntity findComentarioByIdRepo (Long id) { return findById(id); }

  public ComentarioEntity updateComentarioRepo(@Valid ComentarioEntity comentario) {
    ComentarioEntity comentarioEntity = em.merge(comentario);
    return  comentarioEntity;
  }

  public boolean deleteComentarioById(Long id) { return deleteById(id); }

  public void persist(ComentarioEntity comentarioEntity) { PanacheRepository.super.persist(comentarioEntity); }

}
