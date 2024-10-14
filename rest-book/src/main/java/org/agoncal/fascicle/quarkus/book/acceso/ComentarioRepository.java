package org.agoncal.fascicle.quarkus.book.acceso;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.modelo.ComentarioEntity;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class ComentarioRepository implements PanacheRepository<ComentarioEntity> {

  @Inject
  EntityManager em;

  public void createComentarioRepo(@Valid ComentarioEntity newComentario ) { persist(newComentario); }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ComentarioEntity> returnAllComentariosRepo() {
    return em.createQuery( "FROM ComentarioEntity", ComentarioEntity.class).getResultList();
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public Integer getNroLinea (Integer id_libro) {
    ComentarioEntity resultado = em.createQuery( "SELECT MAX(c.nro_linea) FROM ComentarioEntity c WHERE c.libro_id = :id_libro", ComentarioEntity.class).setParameter("id_libro", id_libro).getResultList().stream().findFirst().orElse(null);
    if (resultado == null) {
      return 1;
    } else {
      return resultado.nro_linea + 1;
    }
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public  ComentarioEntity findComentarioByIdRepo (Integer id) { return em.find(ComentarioEntity.class, id); }

  public ComentarioEntity updateComentarioRepo(@Valid ComentarioEntity comentario) {
    ComentarioEntity comentarioEntity = em.merge(comentario);
    return  comentarioEntity;
  }

  public void deleteComentarioById(Integer id) { em.remove(findComentarioByIdRepo(id)); }


}
