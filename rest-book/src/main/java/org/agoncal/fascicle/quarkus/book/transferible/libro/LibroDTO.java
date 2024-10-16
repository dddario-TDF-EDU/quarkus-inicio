package org.agoncal.fascicle.quarkus.book.transferible.libro;


import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorEnLibroDTO;

import java.math.BigDecimal;

import java.net.URL;

import java.util.HashSet;
import java.util.Set;

public class LibroDTO {

  public Integer id_libro;
  public String titulo;
  public String isbn_13;
  public String isbn_10;
  public Set<AutorEnLibroDTO> autores = new HashSet<>();
  public short yearOfPublication;
  public short num_paginas;
  public BigDecimal ranking;
  public BigDecimal precio;
  public URL smallImageUrl;
  public URL mediumImageUrl;
  public String descripcion;
  public String categoria;

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Integer getId_libro() {
    return this.id_libro;
  }

  public void setId_libro(Integer id_libro) {
    this.id_libro = id_libro;
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

  public short getYearOfPublication() {
    return yearOfPublication;
  }

  public void setYearOfPublication(short yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
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
