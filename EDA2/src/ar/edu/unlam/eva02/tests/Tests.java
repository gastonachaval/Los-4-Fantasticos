package ar.edu.unlam.eva02.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.eva02.dominio.Alumno;
import ar.edu.unlam.eva02.dominio.CentroEducativo;
import ar.edu.unlam.eva02.dominio.Curso;
import ar.edu.unlam.eva02.dominio.Docente;

public class Tests {

	@Test
	public void testQuePuedoAgregarPersonaAlAlumnadoA() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		assertTrue(centro1.agregarPersonaAlAlumnado(maxi));

	}

	@Test
	public void testQuPuedoAgregarDocenteAStaff() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		assertTrue(centro1.agregarDocenteAlStaff(andy));

	}

	@Test
	public void testQueAgregaNuevoCurso() {

		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, true, 001, andy);
		assertTrue(centro1.agregarCurso(poo, andy));
		Integer ve = centro1.getCursos().size();
		System.out.println(ve);
	}

	@Test
	public void testQuePuedoAgregarUnAlumnoPremiumACursoFree() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, false, 001, andy);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.pasarAlumnoAPremium(maxi);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));

	}

	@Test
	public void testQuePuedoAgregarUnAlumnoPremiumACursoPremium() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, true, 001, andy);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 800000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.pasarAlumnoAPremium(maxi));
		assertTrue(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));

	}

	@Test
	public void testQuePuedoAgregarUnAlumnoFreeAUnCursoFree() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, false, 001, andy);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertTrue(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));

	}

	@Test
	public void testQueNoPuedoAgregarAlumnoFreeACursoPremium() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 6, true, 001, andy);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		assertFalse(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));

	}

	@Test
	public void testQueNoPuedoAgregarAlumnoFreeACursoFreeSiCursoFreeEstaLLeno() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, true, 001, andy);
		centro1.agregarCurso(poo, andy);
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		Alumno gaston = new Alumno(40214554, "Gaston", 15647878, 8000.0);
		centro1.agregarPersonaAlAlumnado(gaston);
		assertFalse(centro1.asignarAlumnoACurso(poo.getId(), maxi.getDni()));

	}

	@Test
	public void testQueNoPuedaAgregarAlumnoFreeACursoSiALumnoEstaInscriptoEnTresCursos() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, false, 001, andy);
		Curso basica1 = new Curso("Basica1", 2, false, 002, andy);
		Curso ingles = new Curso("Ingles", 2, false, 003, andy);
		Curso mate = new Curso("Matematicas", 2, false, 004, andy);
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
	public void testQueCambioNumeroDeTelefonoAlumno() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		assertTrue(centro1.agregarPersonaAlAlumnado(maxi));
		assertTrue(centro1.cambioNumeroTelefonoDeAlumno(maxi.getDni(), 44570167));
		Integer ve = 44570167;
		Integer vo = maxi.getNroTelefono();
		assertEquals(ve, vo);
	}

	@Test
	public void testQueCambioNumeroDeTelefonoDocente() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		assertTrue(centro1.agregarDocenteAlStaff(andy));
		assertTrue(centro1.cambioNumeroTelefonoDeDocente(andy.getDni(), 44570167));
	}

	@Test
	public void testQueCuentoLaCantidadDeAlumnosConPremiumQueTieneAlumnado() {
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
		Integer ve = 3;
		Integer vo = centro1.totalDeAlumnosConPremium();
		assertEquals(ve, vo);

	}

	@Test
	public void testQueCuentoLaCantidadDeAlumnosFreeQueTieneElAlumnado() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		Alumno Gaston = new Alumno(4456545, "Gaston", 1547478, 8000.0);
		Alumno Ema = new Alumno(4021875, "Ema", 11947878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(Gaston);
		centro1.agregarPersonaAlAlumnado(Ema);
		centro1.pasarAlumnoAPremium(maxi);
		Integer ve = 2;
		Integer vo = centro1.totalDeAlumnosFree();
		assertEquals(ve, vo);

	}

	@Test
	public void testQueCuentoCantidadDeAlumnado() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 8000.0);
		Alumno Gaston = new Alumno(4456545, "Gaston", 1547478, 8000.0);
		Alumno Ema = new Alumno(4021875, "Ema", 11947878, 8000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(Gaston);
		centro1.agregarPersonaAlAlumnado(Ema);
		centro1.pasarAlumnoAPremium(maxi);
		Integer ve = 3;
		Integer vo = centro1.cuentoAlumnado();
		assertEquals(ve, vo);

	}

	@Test
	public void testQueVerificaQueDescuentaPlataDeBilleteraSiPagoPremium() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 3000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.pasarAlumnoAPremium(maxi);
		Double ve = 500.0;
		Double vo = maxi.getBilletera();
		assertEquals(ve, vo);

	}

	@Test
	public void testQueLePuedoAgregarDineroAlaBilletera() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Alumno maxi = new Alumno(40214545, "Maximiliano Davies", 11547878, 3000.0);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.pasarAlumnoAPremium(maxi);
		maxi.agregarDineroALaBilletera(500.0);
		Double ve = 1000.0;
		Double vo = maxi.getBilletera();
		assertEquals(ve, vo);

	}

	public void testQueFinalizaUnCurso() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, false, 001, andy);
		Curso basica1 = new Curso("Basica1", 2, false, 002, andy);
		Curso ingles = new Curso("Ingles", 2, false, 003, andy);
		Curso mate = new Curso("Matematicas", 2, false, 004, andy);
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
		Integer ve = 2;
		Integer vo = maxi.getCantidadDeCursosTomados();
		assertEquals(ve, vo);

	}

	@Test
	public void testQueAgregaCertificacionAAlumnosQueFinalizaronCurso() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitacion");
		Docente andy = new Docente(26654556, "Andreas", 1101454722);
		centro1.agregarDocenteAlStaff(andy);
		Curso poo = new Curso("Prog", 1, false, 001, andy);
		Curso basica1 = new Curso("Basica1", 2, false, 002, andy);
		Curso ingles = new Curso("Ingles", 2, false, 003, andy);
		Curso mate = new Curso("Matematicas", 2, false, 004, andy);
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

	@Test
	public void testQueMuetsreLosAlumnosConPremiumEnUnCurso() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitación");
		Docente andi = new Docente(12345678, "Andrés Borgeat", 1122334455);
		centro1.agregarDocenteAlStaff(andi);
		Curso poo = new Curso("Progamación O.O.", 1, false, 001, andi);
		Alumno maxi = new Alumno(43309952, "Max Davies", 1122332211, 3500.0);
		Alumno gaston = new Alumno(12345677, "Gaston Santos", 1122332233, 5000.0);

		centro1.agregarCurso(poo, andi);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(gaston);
		centro1.pasarAlumnoAPremium(maxi);
		centro1.pasarAlumnoAPremium(gaston);
		centro1.asignarAlumnoACurso(001, 43309952);
		centro1.asignarAlumnoACurso(001, 12345677);

		Integer ve = 2;
		assertEquals(ve, centro1.mostrarCantidadDeAlumnosPremiumEnCursoFree(poo));

	}

	@Test
	public void testQueMuetsreLosAlumnosConPremiumEnCursoFree() {
		CentroEducativo centro1 = new CentroEducativo("Centro de Capacitación");
		Docente andi = new Docente(12345678, "Andrés Borgeat", 1122334455);
		centro1.agregarDocenteAlStaff(andi);
		Curso poo = new Curso("Progamación O.O.", 1, false, 001, andi);
		Alumno maxi = new Alumno(43309952, "Max Davies", 1122332211, 3500.0);
		Alumno gaston = new Alumno(12345677, "Gaston Santos", 1122332233, 5000.0);

		centro1.agregarCurso(poo, andi);
		centro1.agregarPersonaAlAlumnado(maxi);
		centro1.agregarPersonaAlAlumnado(gaston);
		centro1.pasarAlumnoAPremium(maxi);
		centro1.asignarAlumnoACurso(001, 43309952);
		centro1.asignarAlumnoACurso(001, 12345677);

		Integer ve = 1;
		assertEquals(ve, centro1.mostrarCantidadDeAlumnosPremiumEnCursoFree());

	}

	@Test
	public void queNoSePuedaAgregarUnCursoConUnDoncenteFueraDelStaff() {
		CentroEducativo centro = new CentroEducativo("PLATZI");
		Docente nuevoDocente = new Docente(40136070, "juan monteagudo", 1154252158);
		Curso javaCurso = new Curso("java", 4, false, 100, nuevoDocente);
		Alumno nuevoAlumno = new Alumno(15412341, "emanuel arguello", 1245151472, 100000.0);
		Docente otro = new Docente(401360470, "andi andi", 1154428);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarPersonaAlAlumnado(nuevoAlumno);
		// centro.agregarCurso(javaCurso, otro);
		assertFalse(centro.agregarCurso(javaCurso, otro));
		// centro.asignarAlumnoACurso(100, 15412341)

	}

	@Test
	public void cantidadDeCursosConCupoLleno() {
		Integer valorEsperado = 2;
		CentroEducativo centro = new CentroEducativo("PLATZI");
		Docente nuevoDocente = new Docente(40136070, "juan monteagudo", 1154252158);
		Curso javaCurso = new Curso("java", 3, false, 100, nuevoDocente);
		Alumno emanuel = new Alumno(15412341, "emanuel arguello", 1245151472, 100000.0);
		Alumno gaston = new Alumno(45612, "Gaston achaval", 1424225, 200000.0);
		Alumno max = new Alumno(455612, "Maxi", 14245225, 200000.0);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarPersonaAlAlumnado(emanuel);
		centro.agregarPersonaAlAlumnado(gaston);
		centro.agregarPersonaAlAlumnado(max);
		centro.agregarCurso(javaCurso, nuevoDocente);
		centro.asignarAlumnoACurso(100, 15412341);
		centro.asignarAlumnoACurso(100, 45612);
		centro.asignarAlumnoACurso(100, 455612);
		Curso cursoPoo = new Curso("ppo", 3, false, 101, nuevoDocente);
		centro.agregarCurso(cursoPoo, nuevoDocente);
		centro.asignarAlumnoACurso(101, 45612);
		Curso c = new Curso("c", 2, false, 102, nuevoDocente);
		centro.agregarCurso(c, nuevoDocente);
		centro.asignarAlumnoACurso(102, 455612);
		centro.asignarAlumnoACurso(102, 15412341);
		Integer valorObtenido = centro.cantidadDeCursosConCapacidadLlena();

		assertEquals(valorObtenido, valorEsperado);

	}

}
