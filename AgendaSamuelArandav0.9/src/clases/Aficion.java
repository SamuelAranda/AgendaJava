package clases;

public class Aficion {

	private String actividad;

	public Aficion(String actividad) {
		setActividad(actividad);
	}
	
	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	@Override
	public String toString() {
		return actividad;
	}
	
	
	@Override
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