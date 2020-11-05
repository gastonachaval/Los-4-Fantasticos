package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class Alumno extends Persona {

	private Boolean premium;
	private Integer cantidadDeCursosTomados;
	private Double canditdadDeDineroGastado;
	private HashSet<Curso> cursosFinalizados;

	public Alumno(Integer dni, String nombresYApellidos, Integer nroTelefono, Boolean premium) {
		super(dni, nombresYApellidos, nroTelefono);
		this.premium = premium;
		this.cantidadDeCursosTomados = 0;
		this.cursosFinalizados = new HashSet<Curso>();
		canditdadDeDineroGastado=0.0;
	}

	public Double getCanditdadDeDineroGastado() {
		return canditdadDeDineroGastado;
	}

	public void setCanditdadDeDineroGastado(Double canditdadDeDineroGastado) {
		this.canditdadDeDineroGastado = canditdadDeDineroGastado;
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
