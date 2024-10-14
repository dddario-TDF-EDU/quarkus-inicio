package org.agoncal.fascicle.quarkus.book.transferible.comentario;

public class UpdateComentarioDTO {
  public Integer id_comentario;
  public String email;
  public String texto;
  public short puntuacion;


  public Integer getId_comentario() {
    return id_comentario;
  }

  public void setId_comentario(Integer id_comentario) {
    this.id_comentario = id_comentario;
  }

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

}
