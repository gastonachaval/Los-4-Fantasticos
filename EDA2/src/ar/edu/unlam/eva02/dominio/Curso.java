package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class Curso {

	@Override
	public String toString() {
		return nombre;
	}

	private Integer id;
	private String nombre;
	private Integer cupo;
	private Docente docente;
	private HashSet<Alumno> alumnosInscriptos;
	private Boolean premium;

	public Curso(String nombre, Integer cupo, Boolean esPremium, Integer codigoCurso, Docente docenteQueDictaElCurso) {

		this.nombre = nombre;
		this.cupo = cupo;
		this.docente = docenteQueDictaElCurso;
		this.alumnosInscriptos = new HashSet<Alumno>();
		this.premium = esPremium;
		this.id = codigoCurso;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean agregarAlumnoACurso(Alumno alumnoAAgregar) {
		return alumnosInscriptos.add(alumnoAAgregar);
	}

	public Boolean eliminarAlumnoDelCurso(Alumno alumnoAEliminar) {
		return alumnosInscriptos.remove(alumnoAEliminar);
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getCupo() {
		return cupo;
	}

	public Docente getDocentePrincipal() {
		return docente;
	}

	public HashSet<Alumno> getAlumnosInscriptos() {
		return alumnosInscriptos;
	}

	public Integer contadorDeAlumnoEnCurso() {
		return alumnosInscriptos.size();
	}

	public Boolean getPremium() {
		return premium;
	}

	public Integer cantidadVacantesLibresEnElCurso() {
		return cupo - contadorDeAlumnoEnCurso();
	}

}
