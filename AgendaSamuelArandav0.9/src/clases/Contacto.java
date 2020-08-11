package clases;
import java.util.ArrayList;

public abstract class Contacto {
	
	protected int id;
	protected String nombre;
	protected String direccion;
	protected String notas;
	protected ArrayList<Telefono> telefono;
	protected ArrayList<Correo> correo;


	public Contacto(int id, String nombre) {
		
		telefono = new ArrayList<Telefono>();
		correo = new ArrayList<Correo>();
		setIdentificador(id);
		setNombre(nombre);
		
	}
	
	public Contacto(int id, String nombre, String direccion, String notas) {
		
		telefono = new ArrayList<Telefono>();
		correo = new ArrayList<Correo>();
		setIdentificador(id);
		setNombre(nombre);
		setDireccion(direccion);
		setNotas(notas);
		
	}
	
	public int getIdentificador() {
		return id;
	}
	
	public void setIdentificador(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNotas() {
		return notas;
	}
	
	public void setNotas(String notas) {
		this.notas = notas;
	}

	public ArrayList<Telefono> getTelefono() {
		return telefono;
	}

	// El Set de telefono mantiene dos formatos, puedes incluir un String o Construir un nuevo ArrayList desde la base de datos.
	public void setTelefono(Telefono tlf) {		
		this.telefono.add(tlf);
	}
	public void setTelefono(ArrayList<Telefono> tlf) {	
		this.telefono= tlf;
	}

	public  ArrayList<Correo> getCorreo() {
		return correo;
	}
	
	// El Set de correos mantiene dos formatos, puedes incluir un String o Construir un nuevo ArrayList desde la base de datos.
	public void setCorreo(Correo correo) {
		this.correo.add(correo);
	}
	public void setCorreo(ArrayList<Correo> correo) {
		this.correo = correo;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nNombre: " + nombre + "\n" + "Dirección: " + direccion
				+ "\n" + "Telefono: " + telefono + "       Correo: " + correo + "\n" + notas;
	}


	
	
}
