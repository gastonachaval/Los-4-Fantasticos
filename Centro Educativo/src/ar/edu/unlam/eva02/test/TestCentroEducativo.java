package ar.edu.unlam.eva02.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.edu.unlam.eva02.dominio.Alumno;
import ar.edu.unlam.eva02.dominio.BilleteraVirtual;
import ar.edu.unlam.eva02.dominio.CentroEducativo;
import ar.edu.unlam.eva02.dominio.Curso;
import ar.edu.unlam.eva02.dominio.Docente;

public class TestCentroEducativo {

	@Test
	public void testQueSePuedaCrearUnCentroConCursosAlumnosYProfesores() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		Alumno maxi = new Alumno(43309952, "Davies Maximiliano", 112233455, false,billetera1);
		Docente andy = new Docente(7123321, "Andres Borgeat", 112233456);
		Docente juanma = new Docente(8123321, "Juan Monteagudo", 112233457);
		Curso java = new Curso("Java", 15, andy, juanma, 001, 50.0, false);
		assertTrue(los4Fantasticos.agregarCursoPremium(java));
		assertTrue(los4Fantasticos.agregarDocenteAlStaff(andy));
		assertTrue(los4Fantasticos.agregarDocenteAlStaff(juanma));
		assertTrue(los4Fantasticos.agregarNuevoAlumno(maxi));
	}

	@Test
	public void testQueCuentoCantidadDeAlumnosPorCursoSeanFreeOPremium() {

		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
		BilleteraVirtual billetera4 = new BilleteraVirtual();
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		billetera4.agregarDinero(6000.0);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233452, false, billetera1);
		Alumno emanuelArguello = new Alumno(38469132, "Emanuel Arguello", 112233459, true, billetera2);
		Alumno maximilianoDavies = new Alumno(41497315, "Maximiliano Davies", 112233455, false, billetera3);
		Alumno gaston1 = new Alumno(33022376, "Gaston Tomas Santos", 1158476201, true, billetera4);

		Docente andy = new Docente(20647451, "Andres Borgeat", 112233456);
		Curso poo = new Curso("Programacion Orientada A Objetos", 5, andy, 998, 250.0, false);

		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarDocenteAlStaff(andy);
		los4Fantasticos.asignarProfesorACurso(20647451, 998);

		los4Fantasticos.agregarNuevoAlumno(gaston1);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.agregarNuevoAlumno(emanuelArguello);
		los4Fantasticos.agregarNuevoAlumno(maximilianoDavies);

		gaston1.cambiarEstadoPremium();
		gaston.cambiarEstadoPremium();

		los4Fantasticos.asignarAlumnoACurso(36919350, 998);
		los4Fantasticos.asignarAlumnoACurso(38469132, 998);
		los4Fantasticos.asignarAlumnoACurso(41497315, 998);
		los4Fantasticos.asignarAlumnoACurso(33022376, 998);
		Integer vo = poo.contadorDeAlumnoEnCurso();
		Integer ve = 4;
		assertEquals(ve, vo);
	}

	@Test
	public void queAlumnoPuedaInscribirseAlStaff() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(6000.0);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233458, false, billetera1);
		assertTrue(los4Fantasticos.agregarNuevoAlumno(gaston));
		Integer ve = 1;
		Integer vo = los4Fantasticos.cuentoAlumnosEnStaff();
		assertEquals(ve, vo);
	}

	@Test
	public void testQueUnAlumnoDelStaffSeInscribaAUnCursoFree() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(4000.0);
		Alumno max = new Alumno(43309952, "Davies Max", 112233455, false, billetera1);
		Docente andi = new Docente(28004443, "Andres Borgeat", 112233457);
		Curso poo = new Curso("POO", 3, andi, 001, 500.0, false);

		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarNuevoAlumno(max);
		//max.cambiarEstadoPremium();

		assertTrue(los4Fantasticos.asignarAlumnoACurso(max.getDni(), poo.getId()));
	}
	
	

	@Test
	public void testQueAlumnoNoPremiumSePuedeInscribirACursoFree() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(6000.0);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233458, false, billetera1);
		Docente andy = new Docente(20647451, "Andres Borgeat", 112233459);
		los4Fantasticos.agregarDocenteAlStaff(andy);
		los4Fantasticos.asignarProfesorACurso(20647451, 02);
		Curso poo = new Curso("Programacion Orientada A Objetos", 50, andy, 02, 500.0, false);
		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.asignarAlumnoACurso(36919350, 02);
		Integer ve = 1;
		Integer vo = poo.contadorDeAlumnoEnCurso();
		assertEquals(ve, vo);

	}

	@Test
	public void testQueAlumnoPremiumSePuedaAnotarEnUnCursoAunqueEsteLLeno() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
	
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233452, false, billetera1);
		Alumno emanuelArguello = new Alumno(38469132, "Emanuel Arguello", 112233459, true, billetera2);
		Alumno maximilianoDavies = new Alumno(41497315, "Maximiliano Davies", 112233455, true, billetera3);
		Docente andy = new Docente(20647451, "Andres Borgeat", 112233456);
		Curso poo = new Curso("Programacion Orientada A Objetos", 2, andy, 998, 200.0, false);

		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarDocenteAlStaff(andy);
		los4Fantasticos.asignarProfesorACurso(20647451, 998);

		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.agregarNuevoAlumno(emanuelArguello);
		los4Fantasticos.agregarNuevoAlumno(maximilianoDavies);

		assertTrue(los4Fantasticos.asignarAlumnoACurso(38469132, 998));
		assertTrue(los4Fantasticos.asignarAlumnoACurso(41497315, 998));
		gaston.cambiarEstadoPremium();
		assertTrue(los4Fantasticos.asignarAlumnoACurso(36919350, 998));

	}

	@Test
	public void testQueVerificoQueCambiaEstadoPremiumEnAlumno() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(4000.0);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233455, false,billetera1);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		assertTrue(los4Fantasticos.cambioEstadoDeAlumnoDeFreeAPremium(gaston));
		//gaston.cambiarEstadoPremium();
		

	}

	@Test
	public void testQueAlumnoFreeNoSePuedeAnotarEnCursoFreeSiELCursoEstaLLeno() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
	
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233459, false, billetera1);
		Alumno emanuelArguello = new Alumno(38469132, "Emanuel Arguello", 112233452, false, billetera2);
		Alumno maximilianoDavies = new Alumno(41497315, "Maximiliano Davies", 112233455, false, billetera3);

		Docente andy = new Docente(20647451, "Andres Borgeat", 112233458);

		Curso poo = new Curso("Programacion Orientada A Objetos", 2, andy, 998, 250.0, false);
		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarDocenteAlStaff(andy);
		los4Fantasticos.asignarProfesorACurso(20647451, 998);

		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.agregarNuevoAlumno(emanuelArguello);
		los4Fantasticos.agregarNuevoAlumno(maximilianoDavies);

		los4Fantasticos.asignarAlumnoACurso(36919350, 998);
		los4Fantasticos.asignarAlumnoACurso(38469132, 998);
		los4Fantasticos.asignarAlumnoACurso(41497315, 998);
		Integer vo = poo.contadorDeAlumnoEnCurso();
		Integer ve = 2;
		assertEquals(ve, vo);
	}

	@Test
	public void testQueCuentaCantidadDeCursosQueEstaAnotadoAlumnoFree() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(6000.0);
		Docente andy = new Docente(20647451, "Andres Borgeat", 112233452);
		Curso poo = new Curso("Programacion Orientada A Objetos", 2, andy, 997, 20.0, false);
		Curso basica2 = new Curso("Basica 2", 2, andy, 998, 50.0, false);
		Curso tics = new Curso("Tics", 2, andy, 999, 540.0, false);
		los4Fantasticos.asignarProfesorACurso(20647451, 997);
		los4Fantasticos.asignarProfesorACurso(20647451, 998);
		los4Fantasticos.asignarProfesorACurso(20647451, 999);
		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarCursoPremium(basica2);
		los4Fantasticos.agregarCursoPremium(tics);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233451, false,billetera1);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.asignarAlumnoACurso(36919350, 997);
		los4Fantasticos.asignarAlumnoACurso(36919350, 998);
		los4Fantasticos.asignarAlumnoACurso(36919350, 999);
		Integer ve = 3;
		Integer vo = gaston.getCantidadDeCursosTomados();
		assertEquals(ve, vo);
	}

	@Test
	public void testQueAlumnoFreeNoSePuedeAnotarEnEl4toCurso() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(6000.0);
		Docente andy = new Docente(20647451, "Andres Borgeat", 112233456);

		Curso ingles = new Curso("Ingles", 3, andy, 996, 20.0, false);
		Curso poo = new Curso("Programacion Orientada A Objetos", 3, andy, 997, 520.0, false);
		Curso basica2 = new Curso("Basica 2", 2, andy, 998, 40.0, false);
		Curso tics = new Curso("Tics", 2, andy, 999, 870.0, false);

		los4Fantasticos.asignarProfesorACurso(20647451, 996);
		los4Fantasticos.asignarProfesorACurso(20647451, 997);
		los4Fantasticos.asignarProfesorACurso(20647451, 998);
		los4Fantasticos.asignarProfesorACurso(20647451, 999);

		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarCursoPremium(basica2);
		los4Fantasticos.agregarCursoPremium(tics);
		los4Fantasticos.agregarCursoPremium(ingles);

		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233458, false,billetera1);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.asignarAlumnoACurso(36919350, 997);
		los4Fantasticos.asignarAlumnoACurso(36919350, 998);
		assertTrue(los4Fantasticos.asignarAlumnoACurso(36919350, 999));
		assertFalse(los4Fantasticos.asignarAlumnoACurso(36919350, 996));
	}

	@Test
	public void testQueCuentaCantidadDeAlumnosFreeHayEnCursoPremium(){
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
	
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		Docente andy = new Docente(20647451, "Andres Borgeat", 112233456);
		Curso ingles = new Curso("Ingles", 3, andy, 996, 20.0, true);
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233455, false, billetera1);
		Alumno gaston = new Alumno(36919350, "Rodriguez Achaval Gaston", 112233451, false,billetera2);
		Alumno emanuel = new Alumno(38469132, "Arguello Emanuel", 112233458, false, billetera3);
		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.agregarNuevoAlumno(emanuel);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		
		los4Fantasticos.asignarAlumnoACurso(43309952, 996);
		los4Fantasticos.asignarAlumnoACurso(38469132, 996);
		los4Fantasticos.asignarAlumnoACurso(36919350, 996);
		Integer ve=0;
		Integer vo= ingles.cuentoCantidadDeAlumnosFreeEnCurso();
		assertEquals(ve,vo);
		
	}
	
	@Test
	public void testQueCuentaCantidadDeAlumnosFreeHayEnCursoFree(){
			CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
			BilleteraVirtual billetera1 = new BilleteraVirtual();
			BilleteraVirtual billetera2 = new BilleteraVirtual();
			BilleteraVirtual billetera3 = new BilleteraVirtual();
			BilleteraVirtual billetera4 = new BilleteraVirtual();
			billetera1.agregarDinero(6000.0);
			billetera2.agregarDinero(6000.0);
			billetera3.agregarDinero(6000.0);
			billetera4.agregarDinero(6000.0);
			Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval", 112233452, false,billetera1);
			Alumno emanuelArguello = new Alumno(38469132, "Emanuel Arguello", 112233459, true,billetera2);
			Alumno maximilianoDavies = new Alumno(41497315, "Maximiliano Davies", 112233455, false,billetera3);
			Alumno gaston1 = new Alumno(33022376, "Gaston Tomas Santos", 1158476201, true,billetera4);

			Docente andy = new Docente(20647451, "Andres Borgeat", 112233456);
			Curso poo = new Curso("Programacion Orientada A Objetos", 5, andy, 998, 250.0, false);

			los4Fantasticos.agregarCursoPremium(poo);
			los4Fantasticos.agregarDocenteAlStaff(andy);
			los4Fantasticos.asignarProfesorACurso(20647451, 998);

			los4Fantasticos.agregarNuevoAlumno(gaston1);
			los4Fantasticos.agregarNuevoAlumno(gaston);
			los4Fantasticos.agregarNuevoAlumno(emanuelArguello);
			los4Fantasticos.agregarNuevoAlumno(maximilianoDavies);

			gaston1.cambiarEstadoPremium();
			gaston.cambiarEstadoPremium();

			los4Fantasticos.asignarAlumnoACurso(36919350, 998);
			los4Fantasticos.asignarAlumnoACurso(38469132, 998);
			los4Fantasticos.asignarAlumnoACurso(41497315, 998);
			los4Fantasticos.asignarAlumnoACurso(33022376, 998);
			Integer vo = poo.cuentoCantidadDeAlumnosFreeEnCurso();
			Integer ve = 2;
			assertEquals(ve, vo);
		}
		
	
	
	@Test
	public void testQueAlumnoFreeSePuedaAnotarEnCursoFree(){
		
		
	}
	
	@Test
	public void testQueCalculaElDineroGanadoConTodosLosCursos(){
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
		
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		
		Docente andi = new Docente(8987654, "Andres Borgeat", 112233453);
		
		
		los4Fantasticos.agregarDocenteAlStaff(andi);
		Curso poo = new Curso("Programacion Orientada A Objetos",5, andi, 996, 50.0, false);
		Curso ingles = new Curso("Ingkes", 5, andi, 003, 150.0, false);
		
		los4Fantasticos.agregarCursoPremium(ingles);
		los4Fantasticos.agregarCursoPremium(poo);
		
		
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233455, true,billetera1);
		Alumno gaston = new Alumno(36919350, "Rodriguez Achaval Gaston", 112233451, true,billetera2);
		Alumno emanuel = new Alumno(38469132, "Arguello Emanuel", 112233458, true,billetera3);
		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.agregarNuevoAlumno(emanuel);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		
		los4Fantasticos.asignarAlumnoACurso(43309952, 996);
		los4Fantasticos.asignarAlumnoACurso(38469132, 996);
		los4Fantasticos.asignarAlumnoACurso(36919350, 003);
		
		Double ve= 250.0;
		Double vo= los4Fantasticos.dineroGanadoEnCursos();
		
		assertEquals(ve, vo);
		
		
	}
	@Test
	public void testQueCalculeElDineroRecaudadoConSubsPremium() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
		
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		
		Docente andi = new Docente(8987654, "Andres Borgeat", 112233453);
		Curso poo = new Curso("Programacion Orientada A Objetos", 3, andi, 002, 50.0, true);

		Alumno max = new Alumno(43309952, "Davies Maxi", 112233455, false,billetera1);
		Alumno gaston = new Alumno(36919350, "Rodriguez Achaval Gaston", 112233451, false,billetera2);
		Alumno emanuel = new Alumno(38469132, "Arguello Emanuel", 112233458, false,billetera3);

		max.cambiarEstadoPremium();
		gaston.cambiarEstadoPremium();
		emanuel.cambiarEstadoPremium();

		los4Fantasticos.agregarCursoPremium(poo);

		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.agregarNuevoAlumno(emanuel);

		Double ve = 7500.00;
		Double vo = los4Fantasticos.totalDeDineroGanadoPorAlumnosConSubPremium();
		
		assertEquals(ve, vo);

	}
	@Test
	public void testQueCalculaDineroGanadoEnTotalConSubsPremiumYCursos(){
		
		
		
	}
	@Test
	public void testQueCalculaDineroRecaudadoConUnCurso(){
		
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		BilleteraVirtual billetera3 = new BilleteraVirtual();
		
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		billetera3.agregarDinero(6000.0);
		
		Docente andy = new Docente(20647451, "Andres Borgeat", 112233456);

		Curso ingles = new Curso("Ingles", 3, andy, 996, 20.0, false);
		
		los4Fantasticos.asignarProfesorACurso(20647451, 996);
		los4Fantasticos.agregarCursoPremium(ingles);
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233455, false,billetera1);
		Alumno gaston = new Alumno(36919350, "Rodriguez Achaval Gaston", 112233451, false,billetera2);
		Alumno emanuel = new Alumno(38469132, "Arguello Emanuel", 112233458, false,billetera3);
		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.agregarNuevoAlumno(emanuel);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		
		los4Fantasticos.asignarAlumnoACurso(43309952, 996);
		los4Fantasticos.asignarAlumnoACurso(38469132, 996);
		los4Fantasticos.asignarAlumnoACurso(36919350, 996);
		Double ve= 60.0;
		Double vo= ingles.cuentoCantidadDeDineroGaneEnCurso();
		assertEquals(ve, vo);
		
	}

	@Test
	public void testQueSePuedaElimarUnAlumnoDelStaff() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233455, true,billetera1);
		Alumno emanuel = new Alumno(38469132, "Arguello Emanuel", 112233456, false,billetera2);

		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.agregarNuevoAlumno(emanuel);

		Integer ve = 2;
		assertEquals(ve, los4Fantasticos.cuentoAlumnosEnStaff());

		los4Fantasticos.eliminarAlumnoDelStaff(max);

		Integer vo = 1;
		assertEquals(vo, los4Fantasticos.cuentoAlumnosEnStaff());
	}

	@Test
	public void testQueSePuedaElimnarUnAlumnoDeUnCurso() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		BilleteraVirtual billetera2 = new BilleteraVirtual();
		
		billetera1.agregarDinero(6000.0);
		billetera2.agregarDinero(6000.0);
		
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233452, false,billetera1);
		Alumno emanuel = new Alumno(38469132, "Arguello Emanuel", 112233455, false,billetera2);
		Docente andi = new Docente(234675232, "Andres Borgeat", 112233457);
		Curso poo = new Curso("POO", 3, andi, 001, 540.0, false);

		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.agregarNuevoAlumno(emanuel);
		los4Fantasticos.agregarDocenteAlStaff(andi);
		los4Fantasticos.agregarCursoPremium(poo);

		los4Fantasticos.asignarAlumnoACurso(43309952, 001);
		los4Fantasticos.asignarAlumnoACurso(38469132, 001);

		poo.eliminarAlumnoDelCurso(max);

		Integer ve = 1;
		assertEquals(ve, poo.contadorDeAlumnoEnCurso());

	}

	@Test
	public void testQueCuentoLaCantidadDeDineroGastadoQueLLevaALumnoEnCursos(){
		BilleteraVirtual billetera1 = new BilleteraVirtual();
	
		billetera1.agregarDinero(6000.0);
		
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente andi = new Docente(8987654, "Andres Borgeat", 112233453);
		
		
		los4Fantasticos.agregarDocenteAlStaff(andi);
		Curso poo = new Curso("Programacion Orientada A Objetos",5, andi, 996, 50.0, false);
		Curso ingles = new Curso("Ingkes", 5, andi, 997, 150.0, false);
		
		los4Fantasticos.agregarCursoPremium(ingles);
		los4Fantasticos.agregarCursoPremium(poo);
		
		
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233455, false,billetera1);
		assertTrue(los4Fantasticos.agregarNuevoAlumno(max));
		
		assertTrue(los4Fantasticos.asignarAlumnoACurso(43309952, 996));
		assertTrue(los4Fantasticos.asignarAlumnoACurso(43309952, 997));
		Double ve = 200.0;
		Double vo = max.getCanditdadDeDineroGastado();
		assertEquals(ve, vo);
		
		
	}


		
	@Test
	public void testQueCambiaNumeroDeTelefonoAlumno() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		
		billetera1.agregarDinero(6000.0);
		
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233452, true,billetera1);
		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.cambioNumeroTelefonoDeAlumno(43309952, 111111111);
		Integer ve = 111111111;
		Integer vo = max.getNroTelefono();
		assertEquals(ve, vo);
	}

	@Test
	public void testQueCambiaNumeroDeTelefonoDocente() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente andi = new Docente(234675232, "Andres Borgeat", 112233457);
		los4Fantasticos.agregarDocenteAlStaff(andi);
		los4Fantasticos.cambioNumeroTelefonoDeDocente(234675232, 111111111);
		Integer ve = 111111111;
		Integer vo = andi.getNroTelefono();
		assertEquals(ve, vo);
	}
	
	//SI USAMOS ASIGNACION MANUAL DE ID, HABRIA QUE TENER EL SIGUIENTE TEST
	//EL TEST DA BIEN, PERO TENER EN CUENTA QUE UN DOCENTE PUEDE DICTAR UN CURSO
	//SIN SER PARTE DEL STAFF (DEBERIAMOS VALIDARLO EN LA INSTANCIACION DE CURSO ?)
	
	@Test
	public void queNoSePuedaAgregarUnCursoConElMismoId() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente andi = new Docente(234675232, "Andres Borgeat", 112233457);
		//los4Fantasticos.agregarDocenteAlStaff(andi);
		Curso java= new Curso("Programacion Java", 6, andi, 1, 200.0, true);
		Curso java2= new Curso("Programacion Java2", 6, andi, 1, 500.0, false);
		los4Fantasticos.agregarCursoPremium(java);
		
		assertFalse(los4Fantasticos.agregarCursoPremium(java2));

	}
	
	@Test
	public void testQueVerificoQueBilleteraVirutalFuncionaBien() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		BilleteraVirtual billetera1 = new BilleteraVirtual();
		billetera1.agregarDinero(6000.0);
		
		Docente andi = new Docente(8987654, "Andres Borgeat", 112233453);
		los4Fantasticos.agregarDocenteAlStaff(andi);
		Curso poo = new Curso("Programacion Orientada A Objetos",5, andi, 996, 2000.0, false);
		Alumno max = new Alumno(43309952, "Davies Maxi", 112233452, false,billetera1);
		los4Fantasticos.agregarCursoPremium(poo);
		los4Fantasticos.agregarNuevoAlumno(max);
		los4Fantasticos.asignarAlumnoACurso(max.getDni(),poo.getId());
		los4Fantasticos.cambioEstadoDeAlumnoDeFreeAPremium(max);
		
		Double ve= 1500.0;
		Double vo = max.getBilletera1().getMonto();
		assertEquals(ve,vo);
		assertTrue(max.getPremium());
		
	}
	
	/*@Test
	public void queSePuedaCalificarUnAlumnoEnUnCursoFree() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente andi = new Docente(234675232, "Andres Borgeat", 112233457);
		los4Fantasticos.agregarDocenteAlStaff(andi);
		Curso java= new Curso("Programacion Java", 6, andi, 1, 200.0, false);
		los4Fantasticos.agregarCursoPremium(java);
		Alumno gaston= new Alumno (36919350, "Gaston Rodriguez Achaval",1522516656);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.asignarAlumnoACursoFree(gaston.getDni(), java.getId());
		
		
		assertTrue(los4Fantasticos.aprobarAlumnoEnCursoFree(gaston, java,andi));

	}
	*/
	/*@Test
	public void queSePuedaCalificarUnAlumnoEnUnCursoPremium() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente andi = new Docente(234675232, "Andres Borgeat", 112233457);
		los4Fantasticos.agregarDocenteAlStaff(andi);
		Curso java= new Curso("Programacion Java", 6, andi, 1, 20.0, true);
		los4Fantasticos.agregarCursoPremium(java);
		Alumno gaston= new Alumno (36919350, "Gaston Rodriguez Achaval",1522516656);	
		los4Fantasticos.agregarNuevoAlumno(gaston);
		gaston.cambiarEstadoPremium();
		los4Fantasticos.asignaAlumnoACursoPremium(gaston.getDni(), java.getId());
		
		
		assertTrue(los4Fantasticos.aprobarAlumnoEnCursoPremium(gaston, java, andi));

	}
*/
	/*
	 * @Test public void queUnAlumnoFreeNoPuedaTomarUnCursoPremium() {
	 * CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
	 * Docente juanma = new Docente(30645987, "Juan Manuel Monteagudo"); Docente
	 * andy = new Docente(20647451, "Andres Borgeat"); Curso poo = new
	 * Curso("Programacion Orientada A Objetos", 50, juanma, andy); Alumno gaston =
	 * new Alumno(36919350, "Gaston Rodriguez Achaval");
	 * los4Fantasticos.agregarDocenteAlStaff(andy);
	 * los4Fantasticos.agregarDocenteAlStaff(juanma);
	 * los4Fantasticos.agregarNuevoAlumno(gaston);
	 * los4Fantasticos.agregarCursoPremium(poo);
	 * assertFalse(los4Fantasticos.agregarAlumnoACursoPremium(gaston, poo)); }
	 * 
	 * @Test public void queUnAlumnoFreeNoPuedaTomarMasDeTresCursos() {
	 * CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
	 * Docente juanma = new Docente(30645987, "Juan Manuel Monteagudo"); Docente
	 * andy = new Docente(20647451, "Andres Borgeat"); Docente miriam = new
	 * Docente(18464147, "Miriam Barone"); Docente ale = new Docente(18645312,
	 * "Alejandro Goitea"); Curso poo = new
	 * Curso("Programacion Orientada A Objetos", 50, juanma, andy); Curso pb1 = new
	 * Curso("Programacion Basica 1", 100, juanma, ale); Curso infogral = new
	 * Curso("Informatica General", 110, miriam); Curso python = new
	 * Curso("Introduccion a Python", 30, juanma); Alumno gaston = new
	 * Alumno(36919350, "Gaston Rodriguez Achaval");
	 * los4Fantasticos.agregarDocenteAlStaff(andy);
	 * los4Fantasticos.agregarDocenteAlStaff(juanma);
	 * los4Fantasticos.agregarDocenteAlStaff(miriam);
	 * los4Fantasticos.agregarDocenteAlStaff(ale);
	 * los4Fantasticos.agregarNuevoAlumno(gaston);
	 * los4Fantasticos.agregarCursoFree(poo); los4Fantasticos.agregarCursoFree(pb1);
	 * los4Fantasticos.agregarCursoFree(python);
	 * los4Fantasticos.agregarCursoFree(infogral);
	 * los4Fantasticos.agregarAlumnoACursoFree(gaston, python);
	 * los4Fantasticos.agregarAlumnoACursoFree(gaston, poo);
	 * los4Fantasticos.agregarAlumnoACursoFree(gaston, pb1);
	 * assertFalse(los4Fantasticos.agregarAlumnoACursoFree(gaston, infogral)); }
	 * 
	 * @Test public void queUnAlumnoNoPuedaAnotarseEnUnCursoLleno() {
	 * CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
	 * Docente juanma = new Docente(30645987, "Juan Manuel Monteagudo"); Curso
	 * arduino = new Curso("Mini Curso Arduino", 3, juanma); Alumno gastonAchaval =
	 * ; Alumno gastonSantos = new
	 * Alumno(34564978, "Gaston Santos"); Alumno emanuelArguello = new
	 * Alumno(38469132, "Emanuel Arguello"); Alumno maximilianoDavies = new
	 * Alumno(41497315, "Maximiliano Davies");
	 * los4Fantasticos.agregarDocenteAlStaff(juanma);
	 * los4Fantasticos.agregarNuevoAlumno(maximilianoDavies);
	 * los4Fantasticos.agregarNuevoAlumno(gastonAchaval);
	 * los4Fantasticos.agregarNuevoAlumno(gastonSantos);
	 * los4Fantasticos.agregarNuevoAlumno(emanuelArguello);
	 * los4Fantasticos.agregarCursoFree(arduino);
	 * los4Fantasticos.agregarAlumnoACursoFree(maximilianoDavies, arduino);
	 * los4Fantasticos.agregarAlumnoACursoFree(emanuelArguello, arduino);
	 * los4Fantasticos.agregarAlumnoACursoFree(gastonSantos, arduino);
	 * 
	 * assertFalse(los4Fantasticos.agregarAlumnoACursoFree(gastonAchaval, arduino));
	 * }
	 */
	/*
	 * 
	 * 
	 * Curso pb2 = new Curso("Programacion Basica 2",80,andy,ale);
	 * 
	 * Empresa Capacitacion
	 * TODO VALIDAR TODO !!! SI ES PREMIUM QUE PUEDA ANOTARSE EN UN CURSO
	 * LLENO(SOBREVACANTE) falta registrar staff y validacion al instanciar curso
	 * FALTA VALIDAR QUE UN ALUMNO ESTE EN HASHSET ALUMNOS PARA INGRESARLO EN UN
	 * CURSO REESCRIBIR TESTS PARA QUE LOS ALUMNOS Y LOS DOCENTES ESTEN DENTRO DE
	 * SUS CORRESPONDIENTES HASH
	 */

}
