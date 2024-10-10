package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.AutorRepository;
import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;
import org.agoncal.fascicle.quarkus.book.transformador.AutorMapper;

import java.util.List;

@Transactional
@ApplicationScoped
public class AuthorService {

  @Inject
  AutorRepository autorRepository;

  @Inject
  AutorMapper autorMapper;

  public AutorDTO persistAutor(@Valid CrearAutorDTO autor) {
    AutorEntity newAutor = autorMapper.dtoToNewEntity(autor);
    if(autorRepository.findAutorByName(newAutor.nombre) == null) {
      autorRepository.persist(newAutor);
      return autorMapper.entityToDTO(newAutor);
    } else {
      return null;
    }
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public List<AutorSimpleDTO> findAllAutores() {
    List<AutorEntity> autorEntities = autorRepository.returnAllAutoresRepo();
    return  autorMapper.listEntityToListSimpleDTO(autorEntities);
  }
  @Transactional(Transactional.TxType.SUPPORTS)
  public AutorDTO findAutorById(Integer id) {
    AutorEntity autorEntity = autorRepository.findAutorByIdRepo(id);
    if (autorEntity != null) {
      return autorMapper.entityToDTO(autorEntity);
    } else {
      return null;
    }
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public AutorDTO findAutorByName(String name) {
    AutorEntity autorEntity = autorRepository.findAutorByName(name);
    if (autorEntity != null) {
      return autorMapper.entityToDTO(autorEntity);
    } else {
      return null;
    }
  }

  public AutorDTO updateAutor(@Valid AutorDTO autor) {
    if (autor.getId_autor() > 0) {
      AutorEntity autorEntity = autorRepository.findAutorByIdRepo(autor.getId_autor());
      if (autorEntity != null) {
        autorMapper.updateEntityFromDto(autor, autorEntity);
        autorRepository.persist(autorEntity);
      }
    }
    return null;
  }

  public void deleteAutor(Integer id) { autorRepository.deleteAutorByIdRepo(id); }

}
