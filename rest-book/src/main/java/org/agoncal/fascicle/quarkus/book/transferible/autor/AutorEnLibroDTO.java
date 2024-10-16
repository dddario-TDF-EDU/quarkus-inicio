package org.agoncal.fascicle.quarkus.book.transferible.autor;

public class AutorEnLibroDTO {
  public String nombre;
  public String apellido;


  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
