package org.agoncal.fascicle.quarkus.book.modelo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;

@Schema(description = "Book representation")
@Entity
@Table(name = "Books")
public class BookEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue
  public Long idBook;

  @NotNull
  @Schema(required = true)
  public String title;
  @Column(name = "isbn_13")
  public String isbn13;
  @Column(name = "isbn_10")
  public String isbn10;
  public String author;
  @Column(name = "year_of_publication")
  public Integer yearOfPublication;
  @Column(name = "nb_of_pages")
  public Integer nbOfPages;
  @Min(1) @Max(10)
  public Integer rank;
  public BigDecimal price;
  @Column(name = "small_image_url")
  public URL smallImageUrl;
  @Column(name = "medium_image_url")
  public URL mediumImageUrl;
  @Column(length = 10000)
  @Size(min = 1, max = 10000)
  public String description;





}
