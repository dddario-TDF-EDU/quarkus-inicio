package org.agoncal.fascicle.quarkus.book.transferible.categoria;

import jakarta.validation.constraints.NotNull;

public class CrearCategoriaDTO {

  @NotNull
  public String nombre;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

}
