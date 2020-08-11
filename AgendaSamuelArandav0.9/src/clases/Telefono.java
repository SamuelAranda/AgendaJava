package clases;

public class Telefono {
	
	private String numero;
	private Boolean movil;
	
	
	public Telefono(String numero, Boolean movil) {
		
		setNumero(numero);
		setMovil(movil);
		
	}


	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Boolean getMovil() {
		return movil;
	}
	public void setMovil(Boolean movil) {
		this.movil = movil;
	}

	// El m�todo no imprime True o False. Nos "transforma" esta informaci�n en una de ambas cadenas: "Fijo"/"Movil"
	// Nos sirve m�s adelante para establecer como un bot�n radial el tipo de telefono que desea introducir el usuario
	@Override
	public String toString() {
		
		String smovil = "Fijo";	
		if (this.getMovil() == true) {	
			smovil = "Movil";
		}
		return numero + " " + smovil;
	}

	public boolean equals(Object o) {
		if (o == this){
			return true;
		}
		if (o == null){
			return false;
		}
		return false;
	}

	public Boolean compareTo(Object o){
		if (o == this){
			return true;
		}
		if (o == null){
			return false;
		}
		return false;
	}
}
