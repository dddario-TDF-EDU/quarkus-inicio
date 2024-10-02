package org.agoncal.fascicle.quarkus.book.transferible.comentario;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ComentarioDTO {

  public Long id_comentario;
  public String email;
  public String texto;
  public Integer puntuacion;
  public Long libro_id;

  public Long getId_comentario() {
    return id_comentario;
  }

  public void setId_comentario(Long id_comentario) {
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
