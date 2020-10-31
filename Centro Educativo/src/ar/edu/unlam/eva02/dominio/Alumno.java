package ar.edu.unlam.eva02.dominio;

public class Alumno extends Persona {

	private boolean premium;
	private int cursosTomados;

	public Alumno(Integer dni, String nombresYApellidos) {
		super(dni, nombresYApellidos);
		premium = false;
		this.cursosTomados = 0;
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

	public Integer getCursosTomados() {
		return cursosTomados;
	}

	public void sumarUnCurso() {
		cursosTomados++;

	}
}
