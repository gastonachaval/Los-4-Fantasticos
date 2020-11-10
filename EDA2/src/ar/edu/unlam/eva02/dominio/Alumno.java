package ar.edu.unlam.eva02.dominio;

import java.util.HashSet;

public class Alumno extends Persona {

	private Boolean premium;
	private Integer cantidadDeCursosTomados;
	private HashSet<Curso> cursosFinalizados;
	private Double billetera;

	public Alumno(Integer dni, String nombresYApellidos, Integer nroTelefono, Double platitaEnElBolsillo) {
		super(dni, nombresYApellidos, nroTelefono);
		premium = false;
		this.cantidadDeCursosTomados = 0;
		this.cursosFinalizados = new HashSet<Curso>();
		this.billetera = platitaEnElBolsillo;
	}

	public Boolean getPremium() {
		return premium;
	}

	public void cambiarEstadoPremium() {
		premium=!premium;
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

	public Double getBilletera() {
		return billetera;
	}

	public void agregarDineroALaBilletera(Double dineroAAgregar) {
		this.billetera += dineroAAgregar;
	}

	public Boolean imprimirCertificacion(Curso cursoACertificar) {
		restarUnCurso();
		return cursosFinalizados.add(cursoACertificar);
	}

	public void abonar(Double monto) {
		this.billetera -= monto;
	}
}
