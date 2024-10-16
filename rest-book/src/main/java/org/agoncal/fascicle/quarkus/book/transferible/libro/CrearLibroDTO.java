package org.agoncal.fascicle.quarkus.book.transferible.libro;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CrearLibroDTO {
  public String titulo;
  public short yearOfPublication;
  public String isbn_13;
  public String isbn_10;
  public Set<Integer> id_autores = new HashSet<>();
  public short num_paginas;

  public BigDecimal precio;
  public Integer id_categoria;
  public Integer getId_categoria() {
    return this.id_categoria;
  }

  public void setId_categoria(Integer id_categoria) {
    this.id_categoria = id_categoria;
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

  public short getNum_paginas() {
    return num_paginas;
  }

  public void setNum_paginas(short num_paginas) {
    this.num_paginas = num_paginas;
  }

  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }



  public Set<Integer> getId_autores() {
    return this.id_autores;
  }

  public void setId_autores(Set<Integer> id_autores) {
    this.id_autores = id_autores;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public short getYearOfPublication() {
    return yearOfPublication;
  }

  public void setYearOfPublication(short yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
  }


}
