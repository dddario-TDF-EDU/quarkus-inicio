package org.agoncal.fascicle.quarkus.book.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Comments representation")
@Entity
@Table(name = "comentarios")
public class ComentarioEntity {

  //añadido para sacar panache entity (id)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id_comentario;

  @NotNull
  @Schema(required = true)
  public String email;
  @NotNull
  @Schema(required = true)
  public String texto; //revisar
  @Min(1) @Max(10)
  public short puntuacion;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "libro_id", referencedColumnName = "id_libro")
  @NotNull
  @Schema(required = true)
  public LibroEntity libro;
  @NotNull
  @Schema(required = true)
  public Integer nro_linea;
}
