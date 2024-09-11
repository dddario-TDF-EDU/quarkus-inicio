package org.agoncal.fascicle.quarkus.number.health;

import java.time.Instant;

public class BookNumbers {
  private String isbn10;
  private String isbn13;
  private String asin;
  private String ean8;
  private String ean13;
  private Instant generationDate;

  public String getIsbn10() {
    return isbn10;
  }

  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }

  public String getIsbn13() {
    return isbn13;
  }

  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }

  public String getAsin() {
    return asin;
  }

  public void setAsin(String asin) {
    this.asin = asin;
  }

  public String getEan8() {
    return ean8;
  }

  public void setEan8(String ean8) {
    this.ean8 = ean8;
  }

  public String getEan13() {
    return ean13;
  }

  public void setEan13(String ean13) {
    this.ean13 = ean13;
  }

  public void setGenerationDate(Instant date) {
    this.generationDate = date;
  }
}
