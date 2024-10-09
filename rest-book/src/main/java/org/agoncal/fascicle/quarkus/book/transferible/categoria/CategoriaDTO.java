package org.agoncal.fascicle.quarkus.book.transferible.categoria;

import org.agoncal.fascicle.quarkus.book.modelo.CategoriaEntity;

import java.util.HashSet;
import java.util.Set;

public class CategoriaDTO {

  public Integer id_categoria;
  public String nombre;
  public Set<CategoriaDTO> subcategorias = new HashSet<>();

  public Integer getId_categoria() {
    return this.id_categoria;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Set<CategoriaDTO> getSubcategorias() {
    return this.subcategorias;
  }

  public void setSubcategorias(Set<CategoriaDTO> subcategorias) {
    this.subcategorias = subcategorias;
  }

  //public void addSubcategorias(CategoriaDTO categoriaDTO) { this.subcategorias.add(categoriaDTO); }

}
