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

  public void createComentarioRepo(@Valid ComentarioEntity newComentario ) {
    persist(newComentario); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ComentarioEntity> returnAllComentariosRepo() {
    return em.createQuery( "FROM ComentarioEntity", ComentarioEntity.class).getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ComentarioEntity> getLineas(Integer id_libro) {
    List<ComentarioEntity> comentarioEntities = em.createQuery( "SELECT c FROM ComentarioEntity c WHERE c.libro.id_libro = :id_libro", ComentarioEntity.class).setParameter("id_libro", id_libro).getResultList();
    if (comentarioEntities != null && comentarioEntities.size() > 0) {
      return comentarioEntities;
    } else {
      return null;
    }
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  short sumatoriaPuntuacion(Integer id_libro) {
    List<ComentarioEntity> comentarioEntities = em.createNativeQuery("SELECT * FROM comentarios c WHERE libro_id = :id_libro", ComentarioEntity.class)
    //List<ComentarioEntity> = em.createNativeQuery("SELECT SUM(puntuacion) FROM comentarios WHERE libro_id = :id_libro")
      .setParameter("id_libro", id_libro).getResultList();
    Integer sumatoria = 0;
    for (ComentarioEntity comentario: comentarioEntities
         ) {
      sumatoria += comentario.puntuacion;
    }
    return sumatoria.shortValue();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  short cantOpiniones(Integer id_libro) {
    List<ComentarioEntity> comentarioEntities = em.createNativeQuery("SELECT * FROM comentarios c WHERE libro_id = :id_libro", ComentarioEntity.class)
      .setParameter("id_libro", id_libro).getResultList();
    Integer sumatoria = 0;
    for (ComentarioEntity comentarioEntity: comentarioEntities
         ) {
      sumatoria += 1;
    }
    return sumatoria.shortValue();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  ComentarioEntity findComentarioByIdRepo (Integer id) { return em.find(ComentarioEntity.class, id); }

  public ComentarioEntity updateComentarioRepo(@Valid ComentarioEntity comentario) {
    ComentarioEntity comentarioEntity = em.merge(comentario);
    return  comentarioEntity;
  }

  public void deleteComentarioById(Integer id) { em.remove(findComentarioByIdRepo(id)); }


}
