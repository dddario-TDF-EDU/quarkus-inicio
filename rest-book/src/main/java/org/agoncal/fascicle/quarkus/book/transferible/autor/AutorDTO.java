package org.agoncal.fascicle.quarkus.book.transferible.autor;

public class AutorDTO {

  public Integer id_author;
  public String nombre;
  public String apellido;
  public String nacionalidad;


  public Integer getId_author() {return id_author; }

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
