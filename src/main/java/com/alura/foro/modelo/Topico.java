package com.alura.foro.modelo;

import com.alura.foro.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@Enumerated
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	//TODO: hacer con los tipos reales
//	@ManyToOne
//	@JoinColumn(name = "autor_id")
	private String autor;
//	@OneToOne
	private String curso;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(String titulo, String mensaje, Curso curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
//		this.curso = curso;
	}

	public Topico(DatosRegistroTopico datosRegistroTopico) {
		this.titulo = datosRegistroTopico.titulo();
		this.mensaje = datosRegistroTopico.mensaje();
		this.autor = datosRegistroTopico.autor();
		this.curso = datosRegistroTopico.curso();
	}
}
