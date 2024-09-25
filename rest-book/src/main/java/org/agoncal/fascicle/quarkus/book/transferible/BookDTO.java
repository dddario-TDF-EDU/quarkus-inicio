package org.agoncal.fascicle.quarkus.book.transferible;


import java.math.BigDecimal;
import java.net.URL;

public class BookDTO {


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
