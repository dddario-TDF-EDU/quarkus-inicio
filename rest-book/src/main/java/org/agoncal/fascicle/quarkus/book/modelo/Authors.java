package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Authors representation")
@Entity
@Table(name = "Authors")
public class Authors {

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


}
