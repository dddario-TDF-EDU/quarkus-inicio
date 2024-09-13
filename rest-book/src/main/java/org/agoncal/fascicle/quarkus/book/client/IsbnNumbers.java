package org.agoncal.fascicle.quarkus.book.client;

import javax.json.bind.annotation.JsonbProperty;

public class IsbnNumbers {
  @JsonbProperty("isbn_10")
  private String isbn10;
  @JsonbProperty("isbn_13")
  private String isbn13;

  public String getIsbn13() {
    return isbn13;
  }

  public String getIsbn10() {
    return isbn10;
  }
}
