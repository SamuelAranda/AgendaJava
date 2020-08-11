package clases;

import java.util.ArrayList;

//Aunque en esta clase definamos personas pueden no contener apellidos

public class Apodo extends Contacto {
	
	private String apodo;
	private Boolean genero;
	private ArrayList<Aficion> aficion;
	
	public Apodo(int id, String apodo) {
		
		super(id, apodo);
		aficion = new ArrayList<Aficion>();
		setApodo(apodo);
		genero = true;
		
	}

	public Apodo(int id, String apodo, Boolean genero, String direccion, String notas) {

		super(id, apodo);	
		setGenero(genero);
		setDireccion(direccion);
		setNotas(notas);
		setApodo(apodo);
		aficion = new ArrayList<Aficion>();

	}
	public ArrayList<Aficion> getAficion() {
		return aficion;
	}
	
	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	//Usamos dos posibles formas de introducir aficiones
	public void setAficion(ArrayList<Aficion> aficion) {
		this.aficion = aficion;
	}
	public void setAficion(Aficion afi) {
		this.aficion.add(afi);
	}

	public Boolean getGenero() {
		return genero;
	}
	
	public String getGenero(Boolean boo) {
			String generoR = "Mujer";
			
			if (this.genero == false) {
				generoR = "Hombre";
			}
		
		return generoR;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}


		//El sexo ser� un Boolean, para usarlo en forma de bot�n radial en le men�.
		@Override
		public String toString() {
			String generoR = "Mujer";
			
			if (this.genero == false) {
				generoR = "Hombre";
			}
			
			return apodo + " " + generoR  + " " + direccion
					+ " " + telefono + " " + " " + correo + " " + notas + " " + aficion;
		}
}
