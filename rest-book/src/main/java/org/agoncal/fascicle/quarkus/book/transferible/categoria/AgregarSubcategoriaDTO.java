package org.agoncal.fascicle.quarkus.book.transferible.categoria;

public class AgregarSubcategoriaDTO {
  public int idPadre;
  public int idHija;

  public int getIdPadre() {
    return idPadre;
  }

  public void setIdPadre(int idPadre) {
    this.idPadre = idPadre;
  }

  public int getIdHija() {
    return idHija;
  }

  public void setIdHija(int idHija) {
    this.idHija = idHija;
  }
}
