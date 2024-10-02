package org.agoncal.fascicle.quarkus.book.transferible.libro;


import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;

import java.math.BigDecimal;

import java.net.URL;

import java.util.HashSet;
import java.util.Set;

public class LibroDTO {

  public long id_libro;
  public String titulo;
  public String isbn_13;
  public String isbn_10;
  public Set<AutorEntity> autores = new HashSet<>();
  public Integer yearOfPublication;
  public Integer num_paginas;
  public Integer ranking;
  public BigDecimal precio;
  public URL smallImageUrl;
  public URL mediumImageUrl;
  public String descripcion;

  public long getIdBook() {
    return id_libro;
  }

  public void setIdBook(long idBook) {
    this.id_libro = idBook;
  }

  public String getTitle() {
    return this.titulo;
  }

  public void setTitle(String title) {
    this.titulo = title;
  }

  public String getIsbn_13() {
    return isbn_13;
  }

  public void setIsbn_13(String isbn_13) {
    this.isbn_13 = isbn_13;
  }

  public String getIsbn_10() {
    return isbn_10;
  }

  public void setIsbn_10(String isbn_10) {
    this.isbn_10 = isbn_10;
  }

  public Set<AutorEntity> getAuthor() {
    return autores;
  }

  public void setAuthor(Set<AutorEntity> autores) {
    this.autores = autores;
  }

  public Integer getYearOfPublication() {
    return yearOfPublication;
  }

  public void setYearOfPublication(Integer yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
  }

  public Integer getNbOfPages() {
    return num_paginas;
  }

  public void setNbOfPages(Integer nbOfPages) {
    this.num_paginas = nbOfPages;
  }

  public Integer getRank() {
    return ranking;
  }

  public void setRank(Integer rank) {
    this.ranking = rank;
  }

  public BigDecimal getPrice() {
    return this.precio;
  }

  public void setPrice(BigDecimal price) {
    this.precio = price;
  }

  public URL getSmallImageUrl() {
    return smallImageUrl;
  }

  public void setSmallImageUrl(URL smallImageUrl) {
    this.smallImageUrl = smallImageUrl;
  }

  public URL getMediumImageUrl() {
    return mediumImageUrl;
  }

  public void setMediumImageUrl(URL mediumImageUrl) {
    this.mediumImageUrl = mediumImageUrl;
  }

  public String getDescription() {
    return descripcion;
  }

  public void setDescription(String description) {
    this.descripcion = description;
  }

}
