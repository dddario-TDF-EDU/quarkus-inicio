package org.agoncal.fascicle.quarkus.book.transferible.autor;

import org.agoncal.fascicle.quarkus.book.transferible.libro.LibroDTO;

import java.util.HashSet;
import java.util.Set;

public class AutorSimpleDTO {

  public Integer id_autor;
  public String nombre;
  public String apellido;
  public String nacionalidad;

  public Integer getId_autor() {return id_autor; }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }
}
