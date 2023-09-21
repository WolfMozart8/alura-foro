package com.alura.foro.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@NoArgsConstructor
@Data
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;

	@OneToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@OneToOne
	@JoinColumn(name = "autor_id")
	private Usuario autor;
	private Boolean solucion = false;



}
