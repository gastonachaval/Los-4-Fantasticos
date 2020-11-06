package ar.edu.unlam.eva02.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void testQuePuedoAgregarUnCentroConCursoYProfesor(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(24547845, "Andres", 1557878454);
		centro1.agregarDocenteAlStaff(andy);
		
		Curso curso1 = new Curso("Basica1", 20, false, andy);
		centro1.agregarCurso(curso1, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		
		assertTrue(centro1.agregarPersonaAlAlumnado(maxi));
		assertTrue(centro1.asignarAlumnoACurso(maxi.getDni(), curso1.getId()));
		
	}
	

}
