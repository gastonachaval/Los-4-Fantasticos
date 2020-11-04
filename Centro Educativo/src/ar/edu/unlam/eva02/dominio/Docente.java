package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;
import java.util.Iterator;

public class Docente extends Persona {

	public Docente(Integer dni, String nombresYApellidos, Integer nroTelefono) {
		super(dni, nombresYApellidos, nroTelefono);
	}

	public Boolean aprobar(Alumno alumnoACalificar, Curso cursoACalificar, HashSet<Curso> curso) {
		Boolean sePudoAprobar = false;
		for (Iterator<Curso> iterator = curso.iterator(); iterator.hasNext();) {
			Curso cursito = (Curso) iterator.next();
			if (cursito.equals(cursoACalificar)) {
				cursito.getAlumnosInscriptos().contains(alumnoACalificar);
				alumnoACalificar.getCursosFinalizados().add(cursoACalificar);
				alumnoACalificar.restarUnCurso();
				sePudoAprobar = true;
				return sePudoAprobar;
			}

		}
		return sePudoAprobar;

	}

}
