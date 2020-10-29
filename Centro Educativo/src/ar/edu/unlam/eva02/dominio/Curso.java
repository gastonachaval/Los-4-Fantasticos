package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class Curso {
	
	private static Integer contadorId=0;
	private Integer id;
	private String nombre;
	private Integer cupo;
	private Docente docentePrincipal;
	private Docente docenteSecundario;
	private HashSet<Alumno> alumnosInscriptos;

	public Curso(String nombre, Integer cupo, Docente docentePrincipal) {
		contadorId++;
		this.id=contadorId;
		this.nombre = nombre;
		this.cupo = cupo;
		this.docentePrincipal = docentePrincipal;
		this.docenteSecundario = null;
		this.alumnosInscriptos=new HashSet<Alumno>();

	}

	public Curso(String nombre, Integer cupo, Docente docentePrincipal, Docente docenteSecundario) {
		contadorId++;
		this.id=contadorId;
		this.nombre = nombre;
		this.cupo = cupo;
		this.docentePrincipal = docentePrincipal;
		this.docenteSecundario = docenteSecundario;
		this.alumnosInscriptos=new HashSet<Alumno>();

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
	
}
