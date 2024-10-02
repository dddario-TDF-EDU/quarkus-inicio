package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Authors representation")
@Entity
@Table(name = "Autores")
public class AutorEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue
  public Long idAuthor;

  @NotNull
  @Schema(required = true)
  public String name;
  @NotNull
  @Schema(required = true)
  public String surname;
  @NotNull
  @Schema(required = true)
  public String nationality;
  @ManyToMany(mappedBy = "autores_de_libros")
  private Set<LibroEntity> autorias = new HashSet<>();
}
