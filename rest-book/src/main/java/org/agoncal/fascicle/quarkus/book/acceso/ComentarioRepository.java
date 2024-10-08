package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;

import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ComentarioRepository implements PanacheRepository<ComentarioEntity> {

  @Inject
  EntityManager em;

  public void createComentarioRepo(@Valid ComentarioEntity newComentario ) { persist(newComentario); }

//  @Transactional(Transactional.TxType.SUPPORTS)
//  public List<ComentarioEntity> returnAllComentariosRepo() {
//    return em.createQuery( "FROM ComentarioEntity", ComentarioEntity.class).getResultList();
//  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ComentarioEntity> returnAllComentariosRepo() {
    return em.createQuery( "FROM ComentarioEntity", ComentarioEntity.class).getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  ComentarioEntity findComentarioByIdRepo (Integer id) { return em.find(ComentarioEntity.class, id); }

  public ComentarioEntity updateComentarioRepo(@Valid ComentarioEntity comentario) {
    ComentarioEntity comentarioEntity = em.merge(comentario);
    return  comentarioEntity;
  }

  public void deleteComentarioById(Integer id) { em.remove(findComentarioByIdRepo(id)); }


}
