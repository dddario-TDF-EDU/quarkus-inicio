package org.agoncal.fascicle.quarkus.book.transferible.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class CrearCategoriaDTO {

  public String nombre;
  public String categoriaPadre;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCategoriaPadre() {
    return categoriaPadre;
  }

  public void setCategoriaPadre(String categoriaPadre) {
    this.categoriaPadre = categoriaPadre;
  }
}
