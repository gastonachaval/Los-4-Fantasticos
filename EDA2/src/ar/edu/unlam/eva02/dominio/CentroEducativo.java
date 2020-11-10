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

	private Curso buscarCurso(Integer idCurso) {
		for (Curso e : cursos) {
			if (e.getId().equals(idCurso)) {
				return e;
			}
		}
		return null;
	}

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
		if (getCursos().contains(cursoAFinalizar)) {
			HashSet<Alumno> alumnosACertificar = cursoAFinalizar.getAlumnosInscriptos();
			for (Iterator<Alumno> iterator = alumnosACertificar.iterator(); iterator.hasNext();) {
				Alumno alumnoACertificar = (Alumno) iterator.next();
				for (Iterator<Alumno> iterator2 = alumnado.iterator(); iterator2.hasNext();) {
					Alumno alumnado = (Alumno) iterator2.next();
					if (alumnado.equals(alumnoACertificar)) {
						alumnado.imprimirCertificacion(cursoAFinalizar);
						break;
					}
				}
			}
			return getCursos().remove(cursoAFinalizar);
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

	public HashSet<Curso> listadoDeCursosConCapacidadLlena() {
		HashSet<Curso> cursosConCupoLleno = new HashSet<Curso>();
		for (Curso cursito : cursos) {
			if (cursito.contadorDeAlumnoEnCurso() >= cursito.getCupo()) {

				cursosConCupoLleno.add(cursito);
			}
		}
		return cursosConCupoLleno;

	}

	public Integer cantidadDeCursosConCapacidadLlena() {
		Integer contadorDeCursos = 0;
		for (Curso cursito : cursos) {
			if (cursito.contadorDeAlumnoEnCurso() >= cursito.getCupo()) {

				contadorDeCursos++;
			}
		}
		return contadorDeCursos;
	}

	public Integer mostrarCantidadDeAlumnosPremiumEnCursoFree() {
		Integer cantidad = 0;
		for (Curso cursito : cursos) {
			if (cursito.getPremium() == false) {
				for (Alumno alumno : cursito.getAlumnosInscriptos()) {
					if (alumno.getPremium()) {
						cantidad += 1;
					}
				}
			}
		}
		return cantidad;
	}

	public Integer mostrarCantidadDeAlumnosPremiumEnCursoFree(Curso cursoAContar) {
		Integer cantidad = 0;

		if (cursoAContar.getPremium().equals(false)) {
			for (Alumno alumno : cursoAContar.getAlumnosInscriptos()) {
				if (alumno.getPremium().equals(true)) {
					cantidad += 1;
				}
			}
		}

		return cantidad;
	}

	public HashSet<Alumno> listadoDeAlumnosPremiumEnCursosFree() {
		HashSet<Alumno> listado = new HashSet<Alumno>();
		for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext();) {
			Curso curso = (Curso) iterator.next();
			if (curso.getPremium().equals(false)) {
				for (Iterator<Alumno> iterator2 = curso.getAlumnosInscriptos().iterator(); iterator2.hasNext();) {
					Alumno alumno = (Alumno) iterator2.next();
					if (alumno.getPremium().equals(true)) {
						listado.add(alumno);
					}
				}
			}
		}
		return listado;
	}
	
	public HashSet<Alumno> listadoDeAlumnosQueFinalizaronAlMenosUnCurso(){
		HashSet<Alumno> listado=new HashSet<Alumno>();
		for (Iterator<Alumno> iterator = alumnado.iterator(); iterator.hasNext();) {
			Alumno alumno = (Alumno) iterator.next();
			if (alumno.getCursosFinalizados().size()!=0) {
				listado.add(alumno);
			}
		}
		return listado;
	}

	/*public HashSet<Docente> listadoDeDocentesEnElStaff1() {
		HashSet<Docente> listado = new HashSet<Docente>();
		for (Iterator<Docente> iterator = docentes.iterator(); iterator.hasNext();) {
			Docente docente = (Docente) iterator.next();
			listado.add(docente);
		}
		return listado;
	}

	public HashSet<Alumno> listadoAlumnado1() {
		HashSet<Alumno> listado = new HashSet<Alumno>();
		for (Iterator<Alumno> iterator = alumnado.iterator(); iterator.hasNext();) {
			Alumno alumno = (Alumno) iterator.next();
			listado.add(alumno);
		}
		return listado;
	}*/
}
