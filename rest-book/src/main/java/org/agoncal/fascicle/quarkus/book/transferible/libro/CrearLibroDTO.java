package org.agoncal.fascicle.quarkus.book.transferible.libro;

import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;

import java.util.Set;

public class CrearLibroDTO {
  public String title;
  public Set<AutorEntity> autores;
  public Integer yearOfPublication;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<AutorEntity> getAuthor() {
    return this.autores;
  }

  public void setAuthor(Set<AutorEntity> autor) {
    this.autores = autores;
  }

  public void addAuthor(AutorEntity autor) { this.autores.add(autor); }

  public Integer getYearOfPublication() {
    return yearOfPublication;
  }

  public void setYearOfPublication(Integer yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
  }


}
