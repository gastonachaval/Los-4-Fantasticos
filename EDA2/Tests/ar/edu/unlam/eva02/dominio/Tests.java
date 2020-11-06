package ar.edu.unlam.eva02.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void testQuePuedoAgregarPersonaAlAlumnadoA(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		assertTrue(centro1.agregarPersonaAlAlumnado(maxi));
		
	}
	@Test
	public void testQuPuedoAgregarDocenteAStaff(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		assertTrue(centro1.agregarDocenteAlStaff(andy));
		
	}
	
	@Test
	public void testQueAgregaNuevoCurso(){
		
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, true,andy);
		assertTrue(centro1.agregarCurso(poo, andy));
		
		
	}
	

}
