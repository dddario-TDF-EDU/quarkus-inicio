package org.agoncal.fascicle.quarkus.book.transferible.comentario;

public class CrearComentarioDTO {

  public String email;
  public String texto;
  public short puntuacion;
  public Integer libro_id;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public short getPuntuacion() {
    return puntuacion;
  }

  public void setPuntuacion(short puntuacion) {
    this.puntuacion = puntuacion;
  }

  public Integer getLibro_id() {
    return libro_id;
  }

  public void setLibro_id(Integer libro_id) {
    this.libro_id = libro_id;
  }
}
