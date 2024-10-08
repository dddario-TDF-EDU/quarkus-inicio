package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Schema(description = "Book representation")
@Entity
@Table(name = "libros")
public class LibroEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id_libro;

  @NotNull
  @Schema(required = true)
  public String titulo;
  @Column(name = "isbn_13")
  public String isbn13;
  @Column(name = "isbn_10")
  public String isbn10;
  @Column(name = "year_of_publication")
  public short yearOfPublication;
  @Column(name = "num_de_paginas")
  public short num_paginas;
  @Min(1) @Max(10)
  public short ranking;
  public BigDecimal precio;
  @Column(name = "small_image_url")
  public URL smallImageUrl;
  @Column(name = "medium_image_url")
  public URL mediumImageUrl;
  @Column(length = 10000)
  @Size(min = 1, max = 10000)
  public String descripcion;

  @NotNull
  @ManyToOne()
  @JoinColumn(name = "nro_categoria", referencedColumnName = "id_categoria", nullable = false)
  public CategoriaEntity categoriaEntity;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "autoria_de_libros",
    joinColumns = @JoinColumn(name = "nro_libro"),
    inverseJoinColumns = @JoinColumn(name = "nro_autor")
  )
  public Set<AutorEntity> autores_de_libros = new HashSet<>();


}
