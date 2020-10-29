package ar.edu.unlam.eva02.dominio;

public abstract class Persona {

	private Integer dni;
	private String nombresYApellidos;

	public Persona(Integer dni, String nombresYApellidos) {
		super();
		this.dni = dni;
		this.nombresYApellidos = nombresYApellidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	public Integer getDni() {
		return dni;
	}

	public String getNombresYApellidos() {
		return nombresYApellidos;
	}

}
