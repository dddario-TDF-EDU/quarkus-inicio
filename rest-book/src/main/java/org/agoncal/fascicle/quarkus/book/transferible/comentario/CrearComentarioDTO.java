package org.agoncal.fascicle.quarkus.book.transferible.comentario;

public class CrearComentarioDTO {

  public String email;
  public String texto;
  public Integer puntuacion;
  public Long libro_id;

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

  public Integer getPuntuacion() {
    return puntuacion;
  }

  public void setPuntuacion(Integer puntuacion) {
    this.puntuacion = puntuacion;
  }

  public Long getLibro_id() {
    return libro_id;
  }

  public void setLibro_id(Long libro_id) {
    this.libro_id = libro_id;
  }
}
