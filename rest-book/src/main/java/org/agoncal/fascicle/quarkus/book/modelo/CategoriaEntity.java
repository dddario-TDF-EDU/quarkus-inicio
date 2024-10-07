package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Category representation")
@Entity
@Table(name = "categorias", schema = "public")
public class CategoriaEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue()
  public Long id_categoria;

  @NotNull
  @Schema(required = true)
  public String nombre; //revisar

  @ManyToMany
  @JoinTable(name = "subcategorias",
    joinColumns = @JoinColumn(name = "categoria_padre"),
    inverseJoinColumns = @JoinColumn(name = "categoria_hija")
  )
  private Set<CategoriaEntity> subcategorias = new HashSet<>();

}
