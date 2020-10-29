package ar.edu.unlam.eva02.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.eva02.dominio.Alumno;
import ar.edu.unlam.eva02.dominio.CentroEducativo;
import ar.edu.unlam.eva02.dominio.Curso;
import ar.edu.unlam.eva02.dominio.Docente;
import ar.edu.unlam.eva02.dominio.Persona;

public class TestCentroEducativo {

	@Test
	public void queUnAlumnoFreeNoPuedaTomarUnCursoPremium() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente juanma = new Docente(30645987, "Juan Manuel Monteagudo");
		Docente andy = new Docente(20647451, "Andres Borgeat");
		Curso poo = new Curso("Programacion Orientada A Objetos", 50, juanma, andy);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval");
		los4Fantasticos.agregarDocenteAlStaff(andy);
		los4Fantasticos.agregarDocenteAlStaff(juanma);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.agregarCursoPremium(poo);
		assertFalse(los4Fantasticos.agregarAlumnoACursoPremium(gaston, poo));
	}

	@Test
	public void queUnAlumnoFreeNoPuedaTomarMasDeTresCursos() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente juanma = new Docente(30645987, "Juan Manuel Monteagudo");
		Docente andy = new Docente(20647451, "Andres Borgeat");
		Docente miriam = new Docente(18464147, "Miriam Barone");
		Docente ale = new Docente(18645312, "Alejandro Goitea");
		Curso poo = new Curso("Programacion Orientada A Objetos", 50, juanma, andy);
		Curso pb1 = new Curso("Programacion Basica 1", 100, juanma, ale);
		Curso infogral = new Curso("Informatica General", 110, miriam);
		Curso python = new Curso("Introduccion a Python", 30, juanma);
		Alumno gaston = new Alumno(36919350, "Gaston Rodriguez Achaval");
		los4Fantasticos.agregarDocenteAlStaff(andy);
		los4Fantasticos.agregarDocenteAlStaff(juanma);
		los4Fantasticos.agregarDocenteAlStaff(miriam);
		los4Fantasticos.agregarDocenteAlStaff(ale);
		los4Fantasticos.agregarNuevoAlumno(gaston);
		los4Fantasticos.agregarCursoFree(poo);
		los4Fantasticos.agregarCursoFree(pb1);
		los4Fantasticos.agregarCursoFree(python);
		los4Fantasticos.agregarCursoFree(infogral);
		los4Fantasticos.agregarAlumnoACursoFree(gaston, python);
		los4Fantasticos.agregarAlumnoACursoFree(gaston, poo);
		los4Fantasticos.agregarAlumnoACursoFree(gaston, pb1);
		assertFalse(los4Fantasticos.agregarAlumnoACursoFree(gaston, infogral));
	}

	@Test
	public void queUnAlumnoNoPuedaAnotarseEnUnCursoLleno() {
		CentroEducativo los4Fantasticos = new CentroEducativo("Los 4 Fantasticos");
		Docente juanma = new Docente(30645987, "Juan Manuel Monteagudo");
		Curso arduino = new Curso("Mini Curso Arduino", 3, juanma);
		Alumno gastonAchaval = new Alumno(36919350, "Gaston Rodriguez Achaval");
		Alumno gastonSantos = new Alumno(34564978, "Gaston Santos");
		Alumno emanuelArguello = new Alumno(38469132, "Emanuel Arguello");
		Alumno maximilianoDavies = new Alumno(41497315, "Maximiliano Davies");
		los4Fantasticos.agregarDocenteAlStaff(juanma);
		los4Fantasticos.agregarNuevoAlumno(maximilianoDavies);
		los4Fantasticos.agregarNuevoAlumno(gastonAchaval);
		los4Fantasticos.agregarNuevoAlumno(gastonSantos);
		los4Fantasticos.agregarNuevoAlumno(emanuelArguello);
		los4Fantasticos.agregarCursoFree(arduino);
		los4Fantasticos.agregarAlumnoACursoFree(maximilianoDavies, arduino);
		los4Fantasticos.agregarAlumnoACursoFree(emanuelArguello, arduino);
		los4Fantasticos.agregarAlumnoACursoFree(gastonSantos, arduino);

		assertFalse(los4Fantasticos.agregarAlumnoACursoFree(gastonAchaval, arduino));
	}

	/*
	 * 
	 * 
	 * Curso pb2 = new Curso("Programacion Basica 2",80,andy,ale);
	 * 
	 * 
	 * TODO VALIDAR TODO !!! SI ES PREMIUM QUE PUEDA ANOTARSE EN UN CURSO
	 * LLENO(SOBREVACANTE) falta registrar staff y validacion al instanciar curso
	 * FALTA VALIDAR QUE UN ALUMNO ESTE EN HASHSET ALUMNOS PARA INGRESARLO EN UN
	 * CURSO REESCRIBIR TESTS PARA QUE LOS ALUMNOS Y LOS DOCENTES ESTEN DENTRO DE
	 * SUS CORRESPONDIENTES HASH
	 */

}
