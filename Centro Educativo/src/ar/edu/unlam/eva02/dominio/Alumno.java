package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class Alumno extends Persona {

	private boolean premium;
	private int cantidadDeCursosTomados;
	private HashSet<Curso> cursosFinalizados;

	public Alumno(Integer dni, String nombresYApellidos, Integer nroTelefono) {
		super(dni, nombresYApellidos, nroTelefono);
		premium = false;
		this.cantidadDeCursosTomados = 0;
		this.cursosFinalizados = new HashSet<Curso>();
	}

	public Boolean getPremium() {
		return premium;
	}

	public void cambiarEstadoPremium() {
		if (premium == false) {
			premium = true;
		} else {
			premium = false;
		}
	}

	public Integer getCantidadDeCursosTomados() {
		return cantidadDeCursosTomados;
	}

	public void sumarUnCurso() {
		cantidadDeCursosTomados++;
	}
	
	public void restarUnCurso() {
		cantidadDeCursosTomados--;
	}

	public HashSet<Curso> getCursosFinalizados() {
		return cursosFinalizados;
	}
}
