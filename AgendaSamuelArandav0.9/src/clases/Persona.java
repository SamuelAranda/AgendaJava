package clases;

import java.util.ArrayList;

public class Persona extends Contacto {

	private String apellido;
	private String apodo;
	private Boolean genero;
	private ArrayList<Aficion> aficion;

	public Persona(int id, String nombre) {

		super(id, nombre);
		aficion = new ArrayList<Aficion>();
		genero = true;
		
	}

	public Persona(int id, String nombre, String apellido, Boolean genero, String direccion, String notas) {

		super(id, nombre);
		setApodo("");
		setApellido(apellido);
		setGenero(genero);
		setDireccion(direccion);
		setNotas(notas);
		aficion = new ArrayList<Aficion>();
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public Boolean getGenero() {
		return genero;
	}
	
	public String getGenero(Boolean boo){
		String generoR = "Mujer";
		
		if (this.genero == false) {
			generoR = "Hombre";
		}
		
		return generoR;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}
	
	public ArrayList<Aficion> getAficion() {
		return aficion;
	}
	
	//Igual que en los metodos super.setTelefono y super.setCorreo. Usamos dos posibles formas de introducir aficiones
	public void setAficion(ArrayList<Aficion> aficion) {
		this.aficion = aficion;	
	}
	public void setAficion(Aficion a) {
		this.aficion.add(a);
	}
	
	//El sexo sera un Boolean, para usarlo en forma de boton radial en el menu.
	@Override
	public String toString() {
		String generoR = "Mujer";
		
		if (this.genero == false) {
			generoR = "Hombre";
		}
		
		return nombre + " " + apellido + " " + generoR + " " + direccion
				+ " " + " " + telefono + " " + correo + " " + notas +" " + " " + aficion;
	}
	
}

