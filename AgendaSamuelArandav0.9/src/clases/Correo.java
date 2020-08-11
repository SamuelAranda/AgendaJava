package clases;

public class Correo {
	
	private String correo;
	
	public Correo(String correo){
		this.correo = correo;

	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	@Override
	public String toString() {
		return correo;
	}

	public boolean equals(Object o) {
		if (o.toString().compareTo(this.toString()) == 0){
			return true;
		}
		
		return false;
	}

	public Boolean compareTo(Object o){
		if (o.toString().compareTo(this.toString()) == 0){
			return true;
		}
		
		return false;
	}
}
