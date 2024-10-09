package org.agoncal.fascicle.quarkus.book.transferible.categoria;

public class UpdateNombreCategoriaDTO {
  public Integer id_categoria;
  public String nombre;

  public Integer getId_categoria() {
    return this.id_categoria;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
