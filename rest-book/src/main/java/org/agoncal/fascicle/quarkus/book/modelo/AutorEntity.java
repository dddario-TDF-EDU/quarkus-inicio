package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

@Schema(description = "Authors representation")
@Entity
@Table(name = "autores")
public class AutorEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue
  public Long id_autor;
  @NotNull
  @Schema(required = true)
  public String nombre;
  @NotNull
  @Schema(required = true)
  public String apellido;
  @NotNull
  @Schema(required = true)
  public String nacionalidad;
  @ManyToMany(mappedBy = "autores_de_libros")
  public Set<LibroEntity> autorias = new HashSet<>();
}
