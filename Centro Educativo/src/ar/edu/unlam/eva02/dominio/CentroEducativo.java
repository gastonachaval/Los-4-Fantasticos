package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class CentroEducativo {

	private final double valorMatricula = 2500.00;
	private Double caja;
	private String nombre;
	private HashSet<Docente> docentes;
	private HashSet<Alumno> alumnos;
	private HashSet<Curso> cursosFree;
	private HashSet<Curso> cursosPremium;

	public CentroEducativo(String nombre) {
		this.caja = 0.0;
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

	public Integer cuentoAlumnosEnStaff() {
		return alumnos.size();
	}

	public Integer cuentoDocentesEnStaff() {
		return docentes.size();
	}

	public Integer cuentoCantidadDeAlumnosEnTotal() {

		return cursosFree.size() + cursosPremium.size();

	}

	// Cuanto Cantidad DE CURSOS en la QUE ESta INscripto alumno
	/*
	 * public Integer cantidadDeCursosQueEstaAnotadoAlumno(Alumno alumno1) { Integer
	 * contador = 0; for(Curso i: cursosFree) {
	 * if(i.getAlumnosInscriptos().contains(alumno1)) { contador++; } } return
	 * contador; }
	 */
//Cuento Cantidad De Alumnos Con Premium
	public Integer totalDeAlumnosConPremium() {
		Integer contador = 0;
		for (Alumno e : alumnos) {
			if (e.getPremium().equals(true)) {
				contador++;
			}
		}
		return contador;
	}

//Cuento Cantidad De AlumnoFree
	public Integer totalDeAlumnosFree() {
		Integer contador = 0;
		for (Alumno e : alumnos) {
			if (e.getPremium().equals(false)) {
				contador++;
			}
		}
		return contador;
	}

//Cuento Total De Dinero Ganado Con alumnos Premium
	public Double totalDeDineroGanadoPorAlumnosPremium() {
		caja = totalDeAlumnosConPremium() * valorMatricula;
		return caja;

	}

	// Asgigna Alumno a CursosPremium

	public Boolean asignaAlumnoACursoPremium(Integer dni, Integer IdCurso) {
		Boolean asigno = false;
		for (Alumno e : alumnos) {
			if (e.getDni().equals(dni) && e.getPremium().equals(true)) {
				for (Curso i : cursosPremium) {
					if (i.getId().equals(IdCurso)) {
						i.agregarAlumnoACurso(e);
						e.sumarUnCurso();
						asigno = true;
					}

				}
			}
		}
		return asigno;
	}

	// Asigna Alumno a Curso Free si esta en Staff
	public Boolean asignarAlumnoACursoFree(Integer dni, Integer IdCurso) {
		Boolean asigno = false;
		for (Alumno e : alumnos) {
			if (e.getDni().equals(dni) && e.getPremium().equals(true)) {
				for (Curso i : cursosFree) {
					if (i.getId().equals(IdCurso)) {
						i.getAlumnosInscriptos().add(e);
						e.sumarUnCurso();
						asigno = true;
						break;
					}
				}
			} else if (e.getDni().equals(dni) && e.getPremium().equals(false)
					&& e.getCursosTomados() < 3 /* &&cantidadDeCursosQueEstaAnotadoAlumno(e)<3 */) {
				for (Curso i : cursosFree) {
					if (i.getId().equals(IdCurso) && i.contadorDeAlumnoEnCurso() < i.getCupo()) {
						i.getAlumnosInscriptos().add(e);
						e.sumarUnCurso();
						asigno = true;
						break;
					}
				}
			}

		}
		return asigno;

	}

	// Asigno Docente Principal a CursosFree si esta en Staff
	public Boolean asignarProfesorACurso(Integer dni, Integer IdCurso) {
		Boolean asigno = false;
		for (Docente e : docentes) {
			if (e.getDni().equals(dni)) {
				for (Curso i : cursosFree) {
					if (i.getId().equals(IdCurso)) {
						i.setDocentePrincipal(e);
						asigno = true;
					}
				}
			} else {
				asigno = false;
			}
		}
		return asigno;
	}
	// Asigno Docente Secundario a CursosFree si esta en Staff

	public Boolean asignarDocenteSecundarioACurso(Integer dni, Integer IdCurso) {
		Boolean asigno = false;
		for (Docente e : docentes) {
			if (e.getDni().equals(dni)) {
				for (Curso i : cursosFree) {
					if (i.getId().equals(IdCurso)) {
						i.setDocenteSecundario(e);
						asigno = true;
					}
				}
			} else {
				asigno = false;
			}
		}
		return asigno;
	}

	public Double getCaja() {
		return caja;
	}

	public void setCaja(Double caja) {
		this.caja = caja;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public HashSet<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(HashSet<Docente> docentes) {
		this.docentes = docentes;
	}

	public HashSet<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(HashSet<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public HashSet<Curso> getCursosFree() {
		return cursosFree;
	}

	public void setCursosFree(HashSet<Curso> cursosFree) {
		this.cursosFree = cursosFree;
	}

	public HashSet<Curso> getCursosPremium() {
		return cursosPremium;
	}

	public void setCursosPremium(HashSet<Curso> cursosPremium) {
		this.cursosPremium = cursosPremium;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * public Boolean agregarAlumnoACursoPremium(Alumno alumnoAAgregar, Curso
	 * cursoSeleccionado) { Boolean sePudoAgregar = false; if
	 * (alumnoAAgregar.getPremium() == true) { for (Iterator iterator =
	 * cursosPremium.iterator(); iterator.hasNext();) { Curso curso = (Curso)
	 * iterator.next(); if (curso.equals(cursoSeleccionado)) {
	 * curso.agregarAlumnoACurso(alumnoAAgregar); return sePudoAgregar = true; } } }
	 * return sePudoAgregar; }
	 * 
	 * public Boolean agregarAlumnoACursoFree(Alumno alumnoAAgregar, Curso
	 * cursoSeleccionado) { Boolean sePudoAgregar = false; if
	 * (alumnoAAgregar.getCursosTomados()<=3) { for (Iterator iterator =
	 * cursosPremium.iterator(); iterator.hasNext();) { Curso curso = (Curso)
	 * iterator.next(); if (curso.equals(cursoSeleccionado)) {
	 * curso.agregarAlumnoACurso(alumnoAAgregar); alumnoAAgregar.sumarUnCurso();
	 * return sePudoAgregar = true; } } } return sePudoAgregar; }
	 */

}
