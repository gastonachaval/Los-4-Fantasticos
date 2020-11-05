package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;
import java.util.Iterator;

public class CentroEducativo {

	private final double valorMatricula = 2500.00;
	private Double caja;
	private String nombre;
	private HashSet<Docente> docentes;
	private HashSet<Alumno> alumnos;
	//private HashSet<Curso> cursosFree;
	private HashSet<Curso> cursos;

	public CentroEducativo(String nombre) {
		this.caja = 0.0;
		this.nombre = nombre;
		this.docentes = new HashSet<Docente>();
		this.alumnos = new HashSet<Alumno>();
		//this.cursosFree = new HashSet<Curso>();
		this.cursos = new HashSet<Curso>();
	}

	
	
	public Boolean agregarDocenteAlStaff(Docente docenteAAgregar) {
		return docentes.add(docenteAAgregar);
	}

	//public Boolean agregarCursoFree(Curso cursoAAgregar) {
	//	return cursosFree.add(cursoAAgregar);

	//}

	public Boolean agregarCursoPremium(Curso cursoAAgregar) {
		return cursos.add(cursoAAgregar);

	}

	public Boolean agregarNuevoAlumno(Alumno alumnoAAgregar) {
		return alumnos.add(alumnoAAgregar);

	}

	//Cuento Cantidad de Alumnos en Staff
	public Integer cuentoAlumnosEnStaff() {
		return alumnos.size();
	}
//Cuento Cantidad de Docentes en Staff
	public Integer cuentoDocentesEnStaff() {
		return docentes.size();
	}
//Cuento totalidad Estudiantes En Cursos
	//public Integer cuentoCantidadDeAlumnosEnTotal() {

	//	return cursosFree.size() + cursos.size();

	//}

	// Cuanto Cantidad DE CURSOS en la QUE ESta INscripto alumno
	/*
	 * public Integer cantidadDeCursosQueEstaAnotadoAlumno(Alumno alumno1) { Integer
	 * contador = 0; for(Curso i: cursosFree) {
	 * if(i.getAlumnosInscriptos().contains(alumno1)) { contador++; } } return
	 * contador; }
	 */
	// Cambio Numero de Telefono Docente
	public Boolean cambioNumeroTelefonoDeDocente(Integer dni, Integer telefonoNuevo) {
		Boolean cambio = false;
		for (Docente e : docentes) {
			if (e.getDni().equals(dni)) {
				e.setNroTelefono(telefonoNuevo);
				cambio = true;
			}
		}
		return cambio;
	}

	// Cambio Numero de Telefono de Alumno

	public Boolean cambioNumeroTelefonoDeAlumno(Integer dni, Integer telefonoNuevo) {
		Boolean cambio = false;
		for (Alumno e : alumnos) {
			if (e.getDni().equals(dni)) {
				e.setNroTelefono(telefonoNuevo);
				cambio = true;
			}
		}
		return cambio;
	}

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
	public Double totalDeDineroGanadoPorAlumnosConSubPremium() {
		caja = totalDeAlumnosConPremium() * valorMatricula;
		return caja;

	}
	//TOTAL DE DINERO GANADO CON CURSOS
		public Double dineroGanadoEnCursos(){
			Double contador= 0.0;
			for(Curso e: cursos){
				contador += e.cuentoCantidadDeDineroGaneEnCurso();
			}
			return contador;
			
		}
		
	//TOTAL DE DINERO GANADO EN TOTAL
		public Double totalDineroGanadoEnTotal(){
			Double total= 0.0;
			total= totalDeDineroGanadoPorAlumnosConSubPremium()+dineroGanadoEnCursos();
		return	total;
			
		}
		
		
	// Asigna Alumno a CursosPremium

	/*public Boolean asignaAlumnoACursoPremium(Integer dni, Integer IdCurso) {
		Boolean asigno = false;
		for (Alumno e : alumnos) {
			if (e.getDni().equals(dni) && e.getPremium().equals(true)) {
				for (Curso i : cursos) {
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
*/
	// Asigna Alumno a Curso Free si esta en Staff
	
	public Boolean asignarAlumnoACurso(Integer dni, Integer IdCurso) {
		Boolean asigno = false;
		for (Alumno e : alumnos) {
			if (e.getDni().equals(dni) && e.getPremium().equals(true)) {
				for (Curso i : cursos) {
					if (i.getId().equals(IdCurso) ) {
						i.getAlumnosInscriptos().add(e);
						e.sumarUnCurso();
						e.setCanditdadDeDineroGastado(e.getCanditdadDeDineroGastado()+i.getCosto());
						asigno = true;
						break;
					}
				}
			} else if (e.getDni().equals(dni) && e.getPremium().equals(false)&& e.getCantidadDeCursosTomados() < 3 ) {
				for (Curso i : cursos) {
					if (i.getId().equals(IdCurso) && i.contadorDeAlumnoEnCurso() < i.getCupo() && i.getPremium().equals(false)) {
						i.getAlumnosInscriptos().add(e);
						e.sumarUnCurso();
						e.setCanditdadDeDineroGastado(e.getCanditdadDeDineroGastado()+i.getCosto());
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
				for (Curso i : cursos) {
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
				for (Curso i : cursos) {
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

	public Boolean aprobarAlumnoEnCurso(Alumno alumnoACalificar, Curso cursoACalificar,Docente docenteAprobador) {
		Boolean sePudoCalificar = false;
		for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext();) {
			Curso curso = (Curso) iterator.next();
			if (curso.getDocentePrincipal().equals(docenteAprobador)) {
				return sePudoCalificar = docenteAprobador.aprobar(alumnoACalificar, cursoACalificar, cursos);
			}
		}

		return sePudoCalificar;
	}
/*
	public Boolean aprobarAlumnoEnCursoFree(Alumno alumnoACalificar, Curso cursoACalificar, Docente docenteAprobador) {
		Boolean sePudoCalificar = false;
		for (Iterator<Curso> iterator = cursosFree.iterator(); iterator.hasNext();) {
			Curso curso = (Curso) iterator.next();
			if (curso.getDocentePrincipal().equals(docenteAprobador)) {
				sePudoCalificar = docenteAprobador.aprobar(alumnoACalificar, cursoACalificar, cursosFree);
				return sePudoCalificar;
			}
		}

		return sePudoCalificar;
	}
	*/

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

//	public HashSet<Curso> getCursosFree() {
//		return cursosFree;
//	}

//	public void setCursosFree(HashSet<Curso> cursosFree) {
//		this.cursosFree = cursosFree;
//	}

	public HashSet<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(HashSet<Curso> cursosPremium) {
		this.cursos = cursosPremium;
	}

	public Boolean eliminarAlumnoDelStaff(Alumno alumnoAEliminar) {
		return alumnos.remove(alumnoAEliminar);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * public Boolean agregarAlumnoACursoPremium(Alumno alumnoAAgregar, Curso
	 * cursoSeleccionado) { Boolean sePudoAgregar = false; if
	 * (alumnoAAgregar.getPremium() == true) { for (Iterator iterator =
	 * cursos.iterator(); iterator.hasNext();) { Curso curso = (Curso)
	 * iterator.next(); if (curso.equals(cursoSeleccionado)) {
	 * curso.agregarAlumnoACurso(alumnoAAgregar); return sePudoAgregar = true; } } }
	 * return sePudoAgregar; }
	 * 
	 * public Boolean agregarAlumnoACursoFree(Alumno alumnoAAgregar, Curso
	 * cursoSeleccionado) { Boolean sePudoAgregar = false; if
	 * (alumnoAAgregar.getCursosTomados()<=3) { for (Iterator iterator =
	 * cursos.iterator(); iterator.hasNext();) { Curso curso = (Curso)
	 * iterator.next(); if (curso.equals(cursoSeleccionado)) {
	 * curso.agregarAlumnoACurso(alumnoAAgregar); alumnoAAgregar.sumarUnCurso();
	 * return sePudoAgregar = true; } } } return sePudoAgregar; }
	 */
// Elimino un alumno de un Curso
//
//		public Boolean eliminarAlumnoDeUnCurso(Integer dni, Integer idCurso) {
//			Boolean elimino = false;
//			for (Alumno a : alumnos) {
//				if (a.getDni().equals(dni)) {
//					for (Curso e : cursosFree) {
//						if (e.getId().equals(idCurso)) {
//							e.eliminarAlumnoDelCurso(a);
//							elimino = true;
//						}
//					}
//				}
//			}
//			return elimino;
//		}
}
