package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;
import java.util.Iterator;

public class CentroEducativo {

	private final Double valorMatricula = 2500.00;
	private Double caja;
	private String nombre;
	private HashSet<Docente> docentes;
	private HashSet<Alumno> alumnos;
	private HashSet<Curso> cursosFree;
	private HashSet<Curso> cursosPremium;

	public CentroEducativo(String nombre) {
		this.caja=0.0;
		this.nombre = nombre;
		this.docentes = new HashSet<Docente>();
		this.alumnos = new HashSet<Alumno>();
		this.cursosFree = new HashSet<Curso>();
		this.cursosPremium = new HashSet<Curso>();
	}

	public Boolean agregarDocenteAlStaff(Docente docenteAAgregar) {
		return docentes.add(docenteAAgregar);
	}

	public Boolean agregarCursoFree(Curso cursoAAgregar) {
		return cursosFree.add(cursoAAgregar);

	}

	public Boolean agregarCursoPremium(Curso cursoAAgregar) {
		return cursosPremium.add(cursoAAgregar);

	}

	public Boolean agregarNuevoAlumno(Alumno alumnoAAgregar) {
		return alumnos.add(alumnoAAgregar);

	}

	public Boolean agregarAlumnoACursoPremium(Alumno alumnoAAgregar, Curso cursoSeleccionado) {
		Boolean sePudoAgregar = false;
		if (alumnoAAgregar.getPremium() == true) {
			for (Iterator iterator = cursosPremium.iterator(); iterator.hasNext();) {
				Curso curso = (Curso) iterator.next();
				if (curso.equals(cursoSeleccionado)) {
					curso.agregarAlumnoACurso(alumnoAAgregar);
					return sePudoAgregar = true;
				}
			}
		}
		return sePudoAgregar;
	}

	public Boolean agregarAlumnoACursoFree(Alumno alumnoAAgregar, Curso cursoSeleccionado) {
		Boolean sePudoAgregar = false;
		if (alumnoAAgregar.getCursosTomados()<=3) {
			for (Iterator iterator = cursosPremium.iterator(); iterator.hasNext();) {
				Curso curso = (Curso) iterator.next();
				if (curso.equals(cursoSeleccionado)) {
					curso.agregarAlumnoACurso(alumnoAAgregar);
					alumnoAAgregar.sumarUnCurso();
					return sePudoAgregar = true;
				}
			}
		}
	return sePudoAgregar;
	}

}
