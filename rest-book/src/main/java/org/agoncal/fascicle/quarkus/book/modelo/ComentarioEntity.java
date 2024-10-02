package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Comments representation")
@Entity
@Table(name = "Comments")
public class ComentarioEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue
  public Long id_comentario;

  @NotNull
  @Schema(required = true)
  public String email;
  @NotNull
  @Schema(required = true)
  public String texto; //revisar
  @Min(1) @Max(10)
  public Integer puntuacion;
  @ManyToOne
  @NotNull
  @Schema(required = true)
  public LibroEntity libro_id;
}
