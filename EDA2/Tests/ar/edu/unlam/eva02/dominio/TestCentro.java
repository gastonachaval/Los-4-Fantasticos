package ar.edu.unlam.eva02.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCentro {


	@Test
	public void queSePuedaCrearUnCentro() {
		CentroEducativo platzi=new CentroEducativo("PLATZI");
		assertNotNull(platzi);
	}
	@Test
	public void queSePuedaAsignarAlumnoACurso() {
		CentroEducativo centro=new CentroEducativo("PLATZI");
		Docente nuevoDocente=new Docente(40136070, "juan monteagudo", 1154252158);
		Curso javaCurso=new Curso("java", 4, false,100, nuevoDocente);
		Alumno nuevoAlumno=new Alumno(15412341, "emanuel arguello", 1245151472, 100000.0);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarPersonaAlAlumnado(nuevoAlumno);
		centro.agregarCurso(javaCurso,nuevoDocente);
		assertTrue(centro.asignarAlumnoACurso(100, 15412341));	
		
		
	}
	
	@Test
	public void queNoSePuedaAgregarUnCursoConUnDoncenteFueraDelStaff() {
		CentroEducativo centro=new CentroEducativo("PLATZI");
		Docente nuevoDocente=new Docente(40136070, "juan monteagudo", 1154252158);
		Curso javaCurso=new Curso("java", 4, false,100, nuevoDocente);
		Alumno nuevoAlumno=new Alumno(15412341, "emanuel arguello", 1245151472, 100000.0);
		Docente otro=new Docente(401360470, "andi andi", 1154428);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarPersonaAlAlumnado(nuevoAlumno);
		//centro.agregarCurso(javaCurso, otro);
		assertFalse(centro.agregarCurso(javaCurso, otro));	
		//centro.asignarAlumnoACurso(100, 15412341)
		
	}

}
