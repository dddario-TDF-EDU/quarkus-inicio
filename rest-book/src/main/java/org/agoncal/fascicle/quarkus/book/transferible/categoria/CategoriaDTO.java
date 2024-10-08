package org.agoncal.fascicle.quarkus.book.transferible.categoria;

import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;

import java.util.Set;

public class CategoriaDTO {

  public Integer id_categoria;
  public String nombre;
  private Set<CategoriaEntity> subcategorias;

  public Integer getId_categoria() {
    return this.id_categoria;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Set<CategoriaEntity> getCategoriaPadre() {
    return this.subcategorias;
  }

  public void setCategoriaPadre(Set<CategoriaEntity> categoriaPadre) {
    this.subcategorias = categoriaPadre;
  }

  public void addCategoriaPadre(CategoriaEntity categoriaEntity) { this.subcategorias.add(categoriaEntity); }

}
