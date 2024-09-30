package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Comments representation")
@Entity
@Table(name = "Comments")
public class Comments {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue
  public Long idComment;

  @NotNull
  @Schema(required = true)
  public String email;
  @NotNull
  @Schema(required = true)
  public String text; //revisar
  @Min(1) @Max(10)
  public Integer score;
}
