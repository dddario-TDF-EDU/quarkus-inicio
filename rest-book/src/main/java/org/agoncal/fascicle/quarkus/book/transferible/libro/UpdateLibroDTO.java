package org.agoncal.fascicle.quarkus.book.transferible.libro;

import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorEnLibroDTO;
import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class UpdateLibroDTO {

  public Integer id_libro;
  public String titulo;
  public String isbn_13;
  public String isbn_10;
  public short yearOfPublication;
  public short num_paginas;
  public BigDecimal ranking;
  public BigDecimal precio;
  public URL smallImageUrl;
  public Set<Integer> id_autores = new HashSet<>();
  public Integer id_categoria;
  public URL mediumImageUrl;
  public String descripcion;

  public Set<Integer> getAutores() {
    return id_autores;
  }

  public void setAutores(Set<Integer> autores) {
    this.id_autores = autores;
  }

  public Integer getId_categoria() {
    return id_categoria;
  }

  public void setId_categoria(Integer id_categoria) {
    this.id_categoria = id_categoria;
  }

  public Integer getId_libro() {
    return id_libro;
  }

  public void setId_libro(Integer id_libro) {
    this.id_libro = id_libro;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
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

  public short getNum_paginas() {
    return num_paginas;
  }

  public void setNum_paginas(short num_paginas) {
    this.num_paginas = num_paginas;
  }

  public BigDecimal getRanking() {
    return ranking;
  }

  public void setRanking(BigDecimal ranking) {
    this.ranking = ranking;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
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

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }


}
