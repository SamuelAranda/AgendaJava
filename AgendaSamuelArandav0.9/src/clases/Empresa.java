package clases;
public class Empresa extends Contacto{

	public Empresa(int id, String nombre) {
		super(id, nombre);	
	}
	
	public Empresa(int id, String nombre, String direccion, String notas) {
		super(id, nombre, direccion, notas);
	}
	
	@Override
	public String toString() {
		return id + " " + nombre + " " + " " + direccion
				+ " " + telefono + " " + correo + " " + notas;
	}
}
