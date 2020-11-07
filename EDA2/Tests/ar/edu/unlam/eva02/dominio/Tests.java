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
		Curso poo = new Curso("Prog", 6, true,andy, 001);
		assertTrue(centro1.agregarCurso(poo, andy));
		Integer ve=centro1.getCursos().size();
		System.out.println(ve);
	}
	
	
	@Test
	public void testQuePuedoAgregarUnAlumnoPremiumACursoFree(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, false,andy,001);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.pasarAlumnoAPremium(maxi);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));
		
	}
	@Test
	public void testQuePuedoAgregarUnAlumnoPremiumACursoPremium(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, true,andy,001);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 800000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.pasarAlumnoAPremium(maxi));
		assertTrue(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));
		
		
	}
	
	@Test
	public void testQuePuedoAgregarUnAlumnoFreeAUnCursoFree(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, false,andy,001);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));
		
	}
	@Test
	public void testQueNoPuedoAgregarAlumnoFreeACursoPremium(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, true,andy,001);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertFalse(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));
		
	}
	@Test
	public void testQueNoPuedoAgregarAlumnoFreeACursoFreeSiCursoFreeEstaLLeno(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, true,andy,001);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		Alumno gaston = new Alumno(40214554, "Gaston", 15647878, 8000.0);
		centro1.agregarPersonaAlAlumnado(gaston);
		assertFalse(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));
		
	}
	@Test
	public void testQueNoPuedaAgregarAlumnoFreeACursoSiALumnoEstaInscriptoEnTresCursos(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, false,andy,001);
		Curso basica1 = new Curso("Basica1", 2, false, andy, 002);
		Curso ingles = new Curso("Ingles", 2, false, andy, 003);
		Curso mate = new Curso("Matematicas", 2, false, andy, 004);
		centro1.agregarCurso(poo, andy);
		centro1.agregarCurso(mate, andy);
		centro1.agregarCurso(ingles, andy);
		centro1.agregarCurso(basica1, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.asignarAlumnoACurso(basica1.getId(), maxi.getDni()));
		assertTrue(centro1.asignarAlumnoACurso(ingles.getId(), maxi.getDni()));
		assertTrue(centro1.asignarAlumnoACurso(mate.getId(), maxi.getDni()));
		assertFalse(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));
		
	}
	
	@Test
	public void testQueCambioNumeroDeTelefonoAlumno(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		assertTrue(centro1.agregarPersonaAlAlumnado(maxi));
		assertTrue(centro1.cambioNumeroTelefonoDeAlumno(maxi.getDni(), 44570167));
		Integer ve = 44570167;
		Integer vo= maxi.getNroTelefono();
		assertEquals(ve,vo);
	}
	
	@Test
	public void testQueCambioNumeroDeTelefonoDocente(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		assertTrue(centro1.agregarDocenteAlStaff(andy));
		assertTrue(centro1.cambioNumeroTelefonoDeDocente(andy.getDni(), 44570167));
	}
	
	@Test
	public void testQueCuentoLaCantidadDeAlumnosConPremiumQueTieneAlumnado(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		Alumno Gaston = new Alumno(4456545, "Gaston", 1547478, 8000.0);
		Alumno Ema = new Alumno(4021875, "Ema", 11947878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(Gaston);
		centro1.agregarPersonaAlAlumnado(Ema);
		centro1.pasarAlumnoAPremium(maxi);
		centro1.pasarAlumnoAPremium(Gaston);
		centro1.pasarAlumnoAPremium(Ema);
		Integer ve=3;
		Integer vo = centro1.totalDeAlumnosConPremium();
		assertEquals(ve,vo);
		
	}
	@Test
	public void testQueCuentoLaCantidadDeAlumnosFreeQueTieneElAlumnado(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		Alumno Gaston = new Alumno(4456545, "Gaston", 1547478, 8000.0);
		Alumno Ema = new Alumno(4021875, "Ema", 11947878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(Gaston);
		centro1.agregarPersonaAlAlumnado(Ema);
		centro1.pasarAlumnoAPremium(maxi);
		Integer ve=2;
		Integer vo = centro1.totalDeAlumnosFree();
		assertEquals(ve,vo);
		
	}
	@Test
	public void testQueCuentoCantidadDeAlumnado(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		Alumno Gaston = new Alumno(4456545, "Gaston", 1547478, 8000.0);
		Alumno Ema = new Alumno(4021875, "Ema", 11947878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(Gaston);
		centro1.agregarPersonaAlAlumnado(Ema);
		centro1.pasarAlumnoAPremium(maxi);
		Integer ve=3;
		Integer vo = centro1.cuentoAlumnado();
		assertEquals(ve,vo);
		
	}
	@Test
	public void testQueVerificaQueDescuentaPlataDeBilleteraSiPagoPremium(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 3000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.pasarAlumnoAPremium(maxi);
		Double ve=500.0;
		Double vo= maxi.getBilletera();
		assertEquals(ve,vo);
		
	}
	@Test
	public void testQueLePuedoAgregarDineroAlaBilletera(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 3000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.pasarAlumnoAPremium(maxi);
		maxi.agregarDineroALaBilletera(500.0);
		Double ve=1000.0;
		Double vo= maxi.getBilletera();
		assertEquals(ve,vo);
		
	}
	
	public void testQueFinalizaUnCurso(){
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, false,andy,001);
		Curso basica1 = new Curso("Basica1", 2, false, andy, 002);
		Curso ingles = new Curso("Ingles", 2, false, andy, 003);
		Curso mate = new Curso("Matematicas", 2, false, andy, 004);
		centro1.agregarCurso(poo, andy);
		centro1.agregarCurso(mate, andy);
		centro1.agregarCurso(ingles, andy);
		centro1.agregarCurso(basica1, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.pasarAlumnoAPremium(maxi);
		assertTrue(centro1.asignarAlumnoACurso(basica1.getId(), maxi.getDni()));
		assertTrue(centro1.asignarAlumnoACurso(ingles.getId(), maxi.getDni()));
		assertTrue(centro1.asignarAlumnoACurso(mate.getId(), maxi.getDni()));
		centro1.finalizarCurso(ingles);
		Integer ve=2;
		Integer vo= maxi.getCantidadDeCursosTomados();
		assertEquals(ve,vo);
		
	}
	@Test
	public void testQueAgregaCertificacionAAlumnosQueFinalizaronCurso() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente (26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, false,andy,001);
		Curso basica1 = new Curso("Basica1", 2, false, andy, 002);
		Curso ingles = new Curso("Ingles", 2, false, andy, 003);
		Curso mate = new Curso("Matematicas", 2, false, andy, 004);
		centro1.agregarCurso(poo, andy);
		centro1.agregarCurso(mate, andy);
		centro1.agregarCurso(ingles, andy);
		centro1.agregarCurso(basica1, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.pasarAlumnoAPremium(maxi);
		assertTrue(centro1.asignarAlumnoACurso(basica1.getId(), maxi.getDni()));
		assertTrue(centro1.asignarAlumnoACurso(ingles.getId(), maxi.getDni()));
		assertTrue(centro1.asignarAlumnoACurso(mate.getId(), maxi.getDni()));
		centro1.finalizarCurso(ingles);
		assertTrue(maxi.imprimirCertificacion(mate));
		assertFalse(maxi.imprimirCertificacion(ingles));
		assertTrue(maxi.imprimirCertificacion(basica1));
		
		
		
	}
	
	

}
