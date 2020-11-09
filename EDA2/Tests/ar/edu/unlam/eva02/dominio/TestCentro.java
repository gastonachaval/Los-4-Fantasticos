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
	@Test
	public void cantidadDeCursosConCupoLleno() {
		Integer valorEsperado=2;
		CentroEducativo centro=new CentroEducativo("PLATZI");
		Docente nuevoDocente=new Docente(40136070, "juan monteagudo", 1154252158);
		Curso javaCurso=new Curso("java", 3, false,100, nuevoDocente);
		Alumno emanuel=new Alumno(15412341, "emanuel arguello", 1245151472, 100000.0);
		Alumno gaston=new Alumno(45612, "Gaston achaval", 1424225, 200000.0);
		Alumno max=new Alumno(455612, "Maxi", 14245225, 200000.0);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarPersonaAlAlumnado(emanuel);
		centro.agregarPersonaAlAlumnado(gaston);
		centro.agregarPersonaAlAlumnado(max);
		centro.agregarCurso(javaCurso, nuevoDocente);
		centro.asignarAlumnoACurso(100, 15412341);
		centro.asignarAlumnoACurso(100, 45612);
		centro.asignarAlumnoACurso(100, 455612);
		Curso cursoPoo=new Curso("ppo", 3, false, 101, nuevoDocente);
		centro.agregarCurso(cursoPoo, nuevoDocente);
		centro.asignarAlumnoACurso(101, 45612);
		Curso c=new Curso("c", 2, false, 102, nuevoDocente);
		centro.agregarCurso(c, nuevoDocente);
		centro.asignarAlumnoACurso(102,455612 );
		centro.asignarAlumnoACurso(102, 15412341);
		Integer valorObtenido=centro.cantidadDeCursosConCapacidadLlena();

		
		assertEquals(valorObtenido, valorEsperado);
		
		
		
	}

}
