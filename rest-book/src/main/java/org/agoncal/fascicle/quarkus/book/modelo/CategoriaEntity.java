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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id_categoria;

  @NotNull
  @Schema(required = true)
  public String nombre; //revisar

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "subcategorias",
    joinColumns = @JoinColumn(name = "categoria_padre"),
    inverseJoinColumns = @JoinColumn(name = "categoria_hija")
  )
  public Set<CategoriaEntity> subcategorias = new HashSet<CategoriaEntity>();

}
