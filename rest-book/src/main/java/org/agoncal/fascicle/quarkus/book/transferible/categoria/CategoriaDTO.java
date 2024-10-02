package org.agoncal.fascicle.quarkus.book.transferible.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class CategoriaDTO {

  public Long id_categoria;
  public String nombre;
  public CategoriaEntity categoriaPadre;

  public Long getId_categoria() {
    return id_categoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public CategoriaEntity getCategoriaPadre() {
    return categoriaPadre;
  }

  public void setCategoriaPadre(CategoriaEntity categoriaPadre) {
    this.categoriaPadre = categoriaPadre;
  }
}
