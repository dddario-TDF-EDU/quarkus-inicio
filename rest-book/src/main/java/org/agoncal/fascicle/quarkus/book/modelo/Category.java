package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Category representation")
@Entity
@Table(name = "Category")
public class Category {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue
  public Long idCategory;

  @NotNull
  @Schema(required = true)
  public String name; //revisar

}
