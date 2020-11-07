package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;
import java.util.Iterator;

public class CentroEducativo {

	private final Double VALOR_MEMBRESIA_PREMIUM = 2500.0;
	private final Integer CANTIDAD_MAXIMA_DE_CURSOS_QUE_PUEDE_TOMAR_UN_ALUMNO_FREE = 3;
	private Double caja;
	private String nombre;
	private HashSet<Docente> docentes;
	private HashSet<Alumno> alumnado;
	private HashSet<Curso> cursos;

	public CentroEducativo(String nombre) {
		this.caja = 0.0;
		this.nombre = nombre;
		this.docentes = new HashSet<Docente>();
		this.alumnado = new HashSet<Alumno>();
		this.cursos = new HashSet<Curso>();
	}

	public Boolean agregarDocenteAlStaff(Docente docenteAAgregar) {
		return docentes.add(docenteAAgregar);
	}
	
	public Boolean agregarCurso(Curso cursoAAgregar, Docente docenteQueDictaElCurso) {
		if (docentes.contains(docenteQueDictaElCurso) == true) {
			return cursos.add(cursoAAgregar);
		}
		return false;
	}

	public Boolean agregarPersonaAlAlumnado(Alumno alumnoAAgregar) {
		return alumnado.add(alumnoAAgregar);

	}

	public Integer cuentoAlumnado() {
		return alumnado.size();
	}

	public Integer cuentoDocentesEnStaff() {
		return docentes.size();
	}

	
	public Boolean cambioNumeroTelefonoDeDocente(Integer dni, Integer telefonoNuevo) {
		Boolean cambio = false;
		for (Docente e : docentes) {
			if (e.getDni().equals(dni)) {
				e.setNroTelefono(telefonoNuevo);
				return cambio = true;
			}
		}
		return cambio;
	}

	

	public Boolean cambioNumeroTelefonoDeAlumno(Integer dni, Integer telefonoNuevo) {
		Boolean cambio = false;
		for (Alumno e : alumnado) {
			if (e.getDni().equals(dni)) {
				e.setNroTelefono(telefonoNuevo);
				return cambio = true;
			}
		}
		return cambio;
	}

	public Integer totalDeAlumnosConPremium() {
		Integer contador = 0;
		for (Alumno e : alumnado) {
			if (e.getPremium().equals(true)) {
				contador++;
			}
		}
		return contador;
	}

	public Integer totalDeAlumnosFree() {
		Integer contador = 0;
		for (Alumno e : alumnado) {
			if (e.getPremium().equals(false)) {
				contador++;
			}
		}
		return contador;
	}





	public Boolean asignarAlumnoACurso(Integer idCurso, Integer dniAlumno) {

		Alumno alumnoAAsignar = buscarAlumnoEnAlumnado(dniAlumno);
		Curso cursoAAsignarElAlumno = buscarCurso(idCurso);

		if (alumnoAAsignar != null && cursoAAsignarElAlumno != null) {

			if (alumnoAAsignar.getPremium().equals(true)) {
				for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext();) {
					Curso curso = (Curso) iterator.next();
					if (curso.getId().equals(idCurso)) {
						curso.agregarAlumnoACurso(alumnoAAsignar);
						alumnoAAsignar.sumarUnCurso();
						return true;
					}
				}
			} else if (alumnoAAsignar
					.getCantidadDeCursosTomados() < CANTIDAD_MAXIMA_DE_CURSOS_QUE_PUEDE_TOMAR_UN_ALUMNO_FREE
					&& cursoAAsignarElAlumno.cantidadVacantesLibresEnElCurso() > 0
					&& cursoAAsignarElAlumno.getPremium().equals(false)) {
				for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext();) {
					Curso curso = (Curso) iterator.next();
					if (curso.getId().equals(idCurso)) {
						curso.agregarAlumnoACurso(alumnoAAsignar);
						alumnoAAsignar.sumarUnCurso();
						return true;
					}
				}

			}

		}

		return false;

	}

	private Curso buscarCurso(Integer idCurso){
		for(Curso e: cursos){
			if(e.getId().equals(idCurso)){
				return e;
			}
		}
		return null;
	}
	
	/*private Curso buscarCurso(Integer idCurso) {
		for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext();) {
			Curso curso = (Curso) iterator.next();
			if (curso.getId().equals(idCurso)) {
				return curso;
			}
		}
		return null;
	}
*/
	private Alumno buscarAlumnoEnAlumnado(Integer dniABuscarEnElAlumnado) {
		for (Iterator<Alumno> iterator = alumnado.iterator(); iterator.hasNext();) {
			Alumno alumno = (Alumno) iterator.next();
			if (alumno.getDni().equals(dniABuscarEnElAlumnado)) {
				return alumno;
			}
		}
		return null;
	}

	public Boolean finalizarCurso(Curso cursoAFinalizar) {
		if (getCursos().contains(cursoAFinalizar) == true) {
			HashSet<Alumno> alumnosACertificar = cursoAFinalizar.getAlumnosInscriptos();
			for (Iterator<Alumno> iterator = alumnosACertificar.iterator(); iterator.hasNext();) {
				Alumno alumno = (Alumno) iterator.next();
				alumno.imprimirCertificacion(cursoAFinalizar);
			}
			getCursos().remove(cursoAFinalizar);
			return true;
		}
		return false;
	}

	public Double getCaja() {
		return caja;
	}

	public String getNombre() {
		return nombre;
	}

	public HashSet<Docente> getDocentes() {
		return docentes;
	}

	public HashSet<Alumno> getAlumnos() {
		return alumnado;
	}

	public HashSet<Curso> getCursos() {
		return cursos;
	}

	public Boolean eliminarAlumnoDelStaff(Alumno alumnoAEliminar) {
		return alumnado.remove(alumnoAEliminar);
	}

	public Boolean pasarAlumnoAPremium(Alumno alumnoAHacerPremium) {
		for (Iterator<Alumno> iterator = alumnado.iterator(); iterator.hasNext();) {
			Alumno alumno = (Alumno) iterator.next();
			if (alumno.equals(alumnoAHacerPremium) && alumno.getBilletera() >= VALOR_MEMBRESIA_PREMIUM) {
				alumno.abonar(VALOR_MEMBRESIA_PREMIUM);
				cobrar(VALOR_MEMBRESIA_PREMIUM);
				alumnoAHacerPremium.cambiarEstadoPremium();
				return true;
			}
		}
		return false;
	}

	public void cobrar(Double cobro) {
		caja += cobro;
	}

}
