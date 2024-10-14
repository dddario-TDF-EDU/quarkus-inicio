package org.agoncal.fascicle.quarkus.book.transferible.libro;

import org.agoncal.fascicle.quarkus.book.transferible.autor.AutorSimpleDTO;
import org.agoncal.fascicle.quarkus.book.transferible.categoria.CategoriaSimpleDTO;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CrearLibroDTO {
  public String title;
  public short yearOfPublication;
  public String isbn_13;
  public String isbn_10;
  public Set<AutorSimpleDTO> autores = new HashSet<>();
  public short num_paginas;
  public short ranking;
  public BigDecimal precio;

  public Integer getCategoriaId() {
    return categoria.getId_categoria();
  }

  public CategoriaSimpleDTO getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaSimpleDTO categoria) {
    this.categoria = categoria;
  }

  public CategoriaSimpleDTO categoria;

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

  public short getNum_paginas() {
    return num_paginas;
  }

  public void setNum_paginas(short num_paginas) {
    this.num_paginas = num_paginas;
  }

  public short getRanking() {
    return ranking;
  }

  public void setRanking(short ranking) {
    this.ranking = ranking;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }



  public Set<AutorSimpleDTO> getAutores() {
    return autores;
  }

  public void setAutores(Set<AutorSimpleDTO> autores) {
    this.autores = autores;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public short getYearOfPublication() {
    return yearOfPublication;
  }

  public void setYearOfPublication(short yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
  }


}
