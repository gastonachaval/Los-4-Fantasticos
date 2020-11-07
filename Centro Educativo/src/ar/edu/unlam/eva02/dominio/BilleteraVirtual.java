package ar.edu.unlam.eva02.dominio;

public class BilleteraVirtual {
private Double monto;

public Boolean agregarDinero(Double dineroAgregado) {
	
    monto = monto+dineroAgregado;
	return true;
	
}

public Double getMonto() {
	return monto;
}

public void setMonto(Double monto) {
	this.monto = monto;
	
		
	}

public BilleteraVirtual() {
	monto = 0.0;
}


	
	
}
