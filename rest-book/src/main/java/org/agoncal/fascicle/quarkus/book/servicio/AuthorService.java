package org.agoncal.fascicle.quarkus.book.servicio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.agoncal.fascicle.quarkus.book.acceso.AutorRepository;
import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.CrearAutorDTO;
import org.agoncal.fascicle.quarkus.book.transformador.AutorMapper;

import java.util.List;

@ApplicationScoped
public class AuthorService {

  @Inject
  AutorRepository autorRepository;

  @Inject
  AutorMapper autorMapper;

  public AutorDTO persistAutor(@Valid CrearAutorDTO autor) {
    AutorEntity newAutor = autorMapper.toNewEntity(autor);
    if(autorRepository.findAutorByName(newAutor)) {
      autorRepository.persist(newAutor);
      return autorMapper.toDTO(newAutor);
    } else {
      return null;
    }
  }

  public List<AutorDTO> findAllAutores() {
    List<AutorEntity> autorEntities = autorRepository.returnAllAutoresRepo();
    return  autorMapper.toListDTO(autorEntities);
  }

  public AutorDTO findAutorById(Long id) {
    AutorEntity autorEntity = autorRepository.findAutorByIdRepo(id);
    if (autorEntity != null) {
      return autorMapper.toDTO(autorEntity);
    } else {
      return null;
    }
  }

  public AutorDTO updateAutor(@Valid AutorDTO autor) {
    if (autor.getId_author() > 0) {
      AutorEntity autorEntity = autorRepository.findAutorByIdRepo(autor.getId_author());
      if (autorEntity != null) {
        autorMapper.updateAutorFromDto(autor, autorEntity);
        autorRepository.persist(autorEntity);
      }
    }
    return null;
  }

  public boolean deleteAutor(Long id) { return autorRepository.deleteAutorByIdRepo(id); }

}
