package org.agoncal.fascicle.quarkus.book.transferible.autor;

import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class AutorDTO {

  public Long idAuthor;
  public String name;
  public String surname;

  public Long getIdAuthor() {
    return idAuthor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String nationality;


}
