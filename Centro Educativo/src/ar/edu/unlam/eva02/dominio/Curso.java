package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class Curso {

	private Integer id;
	private String nombre;
	private Integer cupo;
	private Docente docentePrincipal;
	private Docente docenteSecundario;
	private HashSet<Alumno> alumnosInscriptos;

	public Curso(String nombre, Integer cupo, Docente docentePrincipal, Integer id) {

		this.id = id;
		this.nombre = nombre;
		this.cupo = cupo;
		this.docentePrincipal = docentePrincipal;
		this.docenteSecundario = null;
		this.alumnosInscriptos = new HashSet<Alumno>();

	}

	public Curso(String nombre, Integer cupo, Docente docentePrincipal, Docente docenteSecundario, Integer id) {
		this.id = id;
		this.nombre = nombre;
		this.cupo = cupo;
		this.docentePrincipal = docentePrincipal;
		this.docenteSecundario = docenteSecundario;
		this.alumnosInscriptos = new HashSet<Alumno>();

	}

	// Contador DE ALUMNOS EN CURSO
	public Integer contadorDeAlumnoEnCurso() {
		return alumnosInscriptos.size();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public Docente getDocentePrincipal() {
		return docentePrincipal;
	}

	public void setDocentePrincipal(Docente docentePrincipal) {
		this.docentePrincipal = docentePrincipal;
	}

	public Docente getDocenteSecundario() {
		return docenteSecundario;
	}

	public void setDocenteSecundario(Docente docenteSecundario) {
		this.docenteSecundario = docenteSecundario;
	}

	public HashSet<Alumno> getAlumnosInscriptos() {
		return alumnosInscriptos;
	}

	public void setAlumnosInscriptos(HashSet<Alumno> alumnosInscriptos) {
		this.alumnosInscriptos = alumnosInscriptos;
	}

}
