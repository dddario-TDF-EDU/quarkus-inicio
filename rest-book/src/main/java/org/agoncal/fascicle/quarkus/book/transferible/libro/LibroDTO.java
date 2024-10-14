package org.agoncal.fascicle.quarkus.book.transferible.libro;


import org.agoncal.fascicle.quarkus.book.modelo.AutorEntity;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;

import java.math.BigDecimal;

import java.net.URL;

import java.util.HashSet;
import java.util.Set;

public class LibroDTO {

  public Integer id_libro;
  public String titulo;
  public String isbn_13;
  public String isbn_10;

  public Set<AutorSimpleDTO> autores = new HashSet<>();

  public short yearOfPublication;
  public short num_paginas;
  public short ranking;
  public BigDecimal precio;
  public URL smallImageUrl;
  public URL mediumImageUrl;
  public String descripcion;
  public Integer id_categoria;

  public Integer getId_categoria() {
    return id_categoria;
  }

  public void setId_categoria(Integer id_categoria) {
    this.id_categoria = id_categoria;
  }

  public Integer getId_libro() {
    return this.id_libro;
  }

  public void setId_libro(Integer id_libro) {
    this.id_libro = id_libro;
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

  public Set<AutorSimpleDTO> getIdAutores() {
    return this.autores;
  }

  public void setIdAutores(Set<AutorSimpleDTO> autores) {
    this.autores = autores;
  }

  public short getYearOfPublication() {
    return yearOfPublication;
  }

  public void setYearOfPublication(short yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
  }

  public short getNbOfPages() {
    return num_paginas;
  }

  public void setNbOfPages(short nbOfPages) {
    this.num_paginas = nbOfPages;
  }

  public short getRank() {
    return ranking;
  }

  public void setRank(short rank) {
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
