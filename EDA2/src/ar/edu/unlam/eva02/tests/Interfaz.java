package ar.edu.unlam.eva02.tests;

import ar.edu.unlam.eva02.dominio.Alumno;
import ar.edu.unlam.eva02.dominio.CentroEducativo;
import ar.edu.unlam.eva02.dominio.Curso;
import ar.edu.unlam.eva02.dominio.Docente;

public class Interfaz {

	public static void main(String[] args) {
		CentroEducativo centro = new CentroEducativo("PLATZI");
		Docente nuevoDocente = new Docente(40136070, "Juan Monteagudo", 1154252158);
		Docente docente = new Docente(40136010, "Andres Borgeat", 1154252158);
		Curso javaCurso = new Curso("Java", 3, false, 100, nuevoDocente);
		Curso pythonCurso = new Curso("Pyhton", 1, false, 101, nuevoDocente);
		Curso pooCurso = new Curso("Poo", 1, false, 102, nuevoDocente);
		Curso ruby = new Curso("Ruby", 5, false, 106, docente);
		Alumno emanuel = new Alumno(15412341, "Emanuel Arguello", 1245151472, 100000.0);
		Alumno gaston = new Alumno(145612, "Gaston Achaval", 1424225, 200000.0);
		Alumno gastonS = new Alumno(14561233, "Gaston Santos", 1424225, 200000.0);
		Alumno ariel = new Alumno(456122, "Ariel Gomez", 1424225, 200000.0);
		Alumno jose = new Alumno(345612, "Jose Lopez", 14124225, 200000.0);
		Alumno max = new Alumno(4556152, "Maximiliano Davies", 54245225, 200000.0);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarDocenteAlStaff(docente);
		centro.agregarPersonaAlAlumnado(emanuel);
		centro.agregarPersonaAlAlumnado(gaston);
		centro.agregarPersonaAlAlumnado(max);
		centro.agregarPersonaAlAlumnado(jose);
		centro.agregarPersonaAlAlumnado(ariel);
		centro.agregarPersonaAlAlumnado(gastonS);
		centro.agregarCurso(javaCurso, nuevoDocente);
		centro.agregarCurso(pythonCurso, nuevoDocente);
		centro.agregarCurso(pooCurso, nuevoDocente);
		centro.agregarCurso(ruby, docente);
		centro.pasarAlumnoAPremium(max);
		centro.pasarAlumnoAPremium(gaston);
		centro.pasarAlumnoAPremium(emanuel);
		centro.asignarAlumnoACurso(100, 15412341);
		centro.asignarAlumnoACurso(100, 145612);
		centro.asignarAlumnoACurso(100, 4556152);
		centro.asignarAlumnoACurso(101, 15412341);
		centro.asignarAlumnoACurso(102, 15412341);
		centro.finalizarCurso(pythonCurso);

		System.out.println("Listado de alumnos premium en cursos free:\n");
		System.out.println(centro.listadoDeAlumnosPremiumEnCursosFree());
		System.out.println("\nListado de cursos con capacidad llena:\n");
		System.out.println(centro.listadoDeCursosConCapacidadLlena());
		System.out.println("\nListado de todos los cursos:\n");
		System.out.println(centro.getCursos());
		System.out.println("\nListado de docentes en el staff:\n");
		System.out.println(centro.getDocentes());
		System.out.println("\nListado del alumnado completo:\n");
		System.out.println(centro.getAlumnos());
		System.out.println("\nListado de alumnos que finalizaron al menos un curso:\n");
		System.out.println(centro.listadoDeAlumnosQueFinalizaronAlMenosUnCurso());
		System.out.println("\nListado de alumnos premium:\n");
		System.out.println(centro.listadoDeAlumnosPremium());
		System.out.println("\nListado de alumnos Free:\n");
		System.out.println(centro.listadoDeAlumnosFree());

	}

}
