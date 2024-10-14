package org.agoncal.fascicle.quarkus.book.transferible.comentario;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.agoncal.fascicle.quarkus.book.modelo.LibroEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ComentarioDTO {

  public Integer id_comentario;
  public String email;
  public String texto;
  public short puntuacion;
  public Integer libro_id;

  public Integer nro_linea;

  public Integer getNro_linea() {
    return nro_linea;
  }

  public void setNro_linea(Integer nro_linea) {
    this.nro_linea = nro_linea;
  }

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

  public Integer getLibro_id() {
    return libro_id;
  }

  public void setLibro_id(Integer libro_id) {
    this.libro_id = libro_id;
  }
}
