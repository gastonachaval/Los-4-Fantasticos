package ar.edu.unlam.eva02.dominio;

public class Interfaz {

	public static void main(String[] args) {
		CentroEducativo centro=new CentroEducativo("PLATZI");
		Docente nuevoDocente=new Docente(40136070, "juan monteagudo", 1154252158);
		Curso javaCurso=new Curso("java", 3, false,100, nuevoDocente);
		Alumno emanuel=new Alumno(15412341, "emanuel arguello", 1245151472, 100000.0);
		Alumno gaston=new Alumno(45612, "Gaston achaval", 1424225, 200000.0);
		Alumno max=new Alumno(455612, "Maxi", 14245225, 200000.0);
		centro.agregarDocenteAlStaff(nuevoDocente);
		centro.agregarPersonaAlAlumnado(emanuel);
		centro.agregarPersonaAlAlumnado(gaston);
		centro.agregarPersonaAlAlumnado(max);
		centro.agregarCurso(javaCurso, nuevoDocente);
		centro.asignarAlumnoACurso(100, 15412341);
		centro.asignarAlumnoACurso(100, 45612);
		centro.asignarAlumnoACurso(100, 455612);
		System.out.println(centro.cursosConCapacidadLlena());
	}

}
