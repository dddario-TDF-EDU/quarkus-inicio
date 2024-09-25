package org.agoncal.fascicle.quarkus.book.modelo;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;

@Schema(description = "Book representation")
public class Book {
  public String title;
  public String isbn13;
  public String isbn10;
  public String author;
  public Integer yearOfPublication;
  public Integer nbOfPages;
  public Integer rank;
  public BigDecimal price;
  public URL smallImageUrl;
  public URL mediumImageUrl;
  public String description;


}
