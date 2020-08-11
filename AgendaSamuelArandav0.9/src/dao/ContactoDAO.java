package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Aficion;
import clases.Apodo;
import clases.Contacto;
import clases.Correo;
import clases.Empresa;
import clases.Persona;
import clases.Telefono;

public class ContactoDAO {
	
	private static Connection conn = Conexion.getConn();
	private AficionDAO adao;

	public ContactoDAO() throws SQLException {
		adao = new AficionDAO();	
	}
	
//ID MAXIMA DE CONTACTO
	public int idMax() {
		try {
			String sql = "SELECT max(id) FROM contacto";
			ResultSet rs = conn.createStatement().executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			return 0;
		}
	}

//AGREGAR PARA IMPRIMIR LISTA DE TELEFONOS O CORREOS DE UN CONTACTO DADO
	public Contacto getArray(Contacto c) throws SQLException{

		ResultSet rs;

		String sql = "SELECT direccionC FROM correo "
				+ "WHERE id = " + c.getIdentificador();
		rs = conn.createStatement().executeQuery(sql);

		while (rs.next()){
			Correo cr = new Correo(rs.getString(1));
			c.setCorreo(cr);
		}

		sql = "SELECT numero, tipo FROM telefono WHERE id = " + c.getIdentificador();
		rs = conn.createStatement().executeQuery(sql);

		while (rs.next()){
			Telefono tlf = new Telefono(rs.getString(1), rs.getBoolean(2));
			c.setTelefono(tlf);
		}

		return c;
	}

//IMRPIMIR LISTA DE CONTACTO 
	public ArrayList<?> imprimir(Contacto c) throws SQLException{

		//COMPROVAMOS SI EL OBJETO ES DE TIPO PERSONA DE SER ASI:
			if (c instanceof Persona){
				ArrayList<Persona> al = new ArrayList<Persona>();

				String sql = "SELECT id, nombre, apellido, genero, direccion, notas "
						+ "FROM contacto WHERE tipo = \"persona\"";
				ResultSet rs = conn.createStatement().executeQuery(sql);

				while (rs.next()){
					Persona p = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), 
							rs.getBoolean(4), rs.getString(5), rs.getString(6));
					getArray(p);
					p.setAficion(adao.getAficion(p));
					al.add(p);
				}
				return al;
			}

			
		//COMPROVAMOS SI EL OBJETO ES DE TIPO EMPRESA DE SER ASI:
			if (c instanceof Empresa){
				ArrayList<Empresa> al = new ArrayList<Empresa>();

				String sql = "SELECT id, nombre, direccion, notas "
						+ "FROM contacto WHERE tipo = \"empresa\"";
				ResultSet rs = conn.createStatement().executeQuery(sql);

				while (rs.next()){
					Empresa e = new Empresa(rs.getInt(1), rs.getString(2), rs.getString(3), 
							rs.getString(4));
					getArray(e);
					al.add(e);
				}
				return al;
			}

			
		//COMPROVAMOS SI EL OBJETO ES DE TIPO APODO DE SER ASI:
			if (c instanceof Apodo){
				ArrayList<Apodo> al = new ArrayList<Apodo>();

				String sql = "SELECT id, nombre, genero, direccion, notas "
						+ "FROM contacto WHERE tipo = \"apodos\"";
				ResultSet rs = conn.createStatement().executeQuery(sql);

				while (rs.next()){
					Apodo ap = new Apodo(rs.getInt(1), rs.getString(2), 
							rs.getBoolean(3), rs.getString(4), rs.getString(5));
					getArray(ap);
					ap.setAficion(adao.getAficion(ap));

					al.add(ap);
				}
				return al;
			}
		return null;
	}
	
//FILTRAR LOS RESULTADOS DE CONTACTOS PARA MOSTRAR SOLO LOS QUE COINCIDAN CON UNA BUSQUEDA
	public ArrayList<?> filtrar(Contacto c, String busqueda) throws SQLException {
		
		if (c instanceof Persona){
			ArrayList<Persona> al = (ArrayList<Persona>) imprimir(c);
			ArrayList<Persona> ali = new ArrayList<Persona>();

			//SI POR CADA OBJETO PERSONA QUE TENGA EL ARRAY NO ESTA CONTENIDO EL STRING BUSQUEDA SE ELMINA ESE OBJETO	
			for (int i = (al.size()); i > 0; i--){

				if (((al.get(i-1).toString()).toLowerCase()).indexOf(busqueda.toLowerCase()) != -1){
					ali.add(al.get(i-1));
				}					
			}
			return ali;
		}
		
		if (c instanceof Apodo){
			
		ArrayList<Apodo> al = (ArrayList<Apodo>) imprimir(c);
		ArrayList<Apodo> ali = new ArrayList<Apodo>();

		//SI POR CADA OBJETO PERSONA QUE TENGA EL ARRAY NO ESTA CONTENIDO EL STRING BUSQUEDA SE ELMINA ESE OBJETO	
		for (int i = (al.size()); i > 0; i--){

			if (((al.get(i-1).toString()).toLowerCase()).indexOf(busqueda.toLowerCase()) != -1){
				ali.add(al.get(i-1));
			}					
		}
		return ali;
		}
		if (c instanceof Empresa){
			
			ArrayList<Empresa> al = (ArrayList<Empresa>) imprimir(c);
			ArrayList<Empresa> ali = new ArrayList<Empresa>();

			//SI POR CADA OBJETO PERSONA QUE TENGA EL ARRAY NO ESTA CONTENIDO EL STRING BUSQUEDA SE ELMINA ESE OBJETO	
			for (int i = (al.size()); i > 0; i--){

				if (((al.get(i-1).toString()).toLowerCase()).indexOf(busqueda.toLowerCase()) != -1){
					ali.add(al.get(i-1));
				}					
			}
			return ali;
		}
		return null;
	}

//IMPRIMIR CONTACTO POR ID
	public Contacto imprimirPorId(int id, Contacto c) throws SQLException{

		Persona p;
		Apodo ap;
		Empresa e;
		
	//COMPROVAMOS SI EL OBJETO ES DE TIPO PERSONA DE SER ASI:
		if (c instanceof Persona){
			String sql = "SELECT id, nombre, apellido, genero, direccion, notas "
					+ "FROM contacto WHERE id = " + id;
			ResultSet rs = conn.createStatement().executeQuery(sql);

			p = new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getBoolean(4), rs.getString(5), rs.getString(6));
			getArray(p);
			p.setAficion(adao.getAficion(p));
			return p;
		}

	//COMPROVAMOS SI EL OBJETO ES DE TIPO EMPRESA DE SER ASI:
		if (c instanceof Empresa){
			ArrayList<Empresa> al = new ArrayList<Empresa>();

			String sql = "SELECT id, nombre, direccion, notas "
					+ "FROM contacto WHERE id = " + id;
			ResultSet rs = conn.createStatement().executeQuery(sql);

			e = new Empresa(rs.getInt(1), rs.getString(2), rs.getString(3), 
					rs.getString(4));
			getArray(e);
			return e;
		}

	//COMPROVAMOS SI EL OBJETO ES DE TIPO APODO DE SER ASI:
		if (c instanceof Apodo){
			ArrayList<Apodo> al = new ArrayList<Apodo>();

			String sql = "SELECT id, nombre, genero, direccion, notas "
					+ "FROM contacto WHERE id = " + id;
			ResultSet rs = conn.createStatement().executeQuery(sql);

			ap = new Apodo(rs.getInt(1), rs.getString(2), 
					rs.getBoolean(3), rs.getString(4), rs.getString(5));
			getArray(ap);
			ap.setAficion(adao.getAficion(ap));
			return ap;
		}
		return null;
	}
	
//INTRODUCIR DATOS USANDO UNA CLASE CONTACTO(PERSONA, EMPRESA, APODO)
	public boolean nuevoRegistro(Contacto c) throws SQLException {

		try {
		//NUEVO REGISTRO PARA PERSONAS	
			if (c instanceof Persona) {

				Persona p = (Persona) c;
				p.setIdentificador(idMax()+1);

				String sql = "INSERT INTO contacto "
						+ " (id, nombre, apodo, apellido, genero, direccion, notas, tipo)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, p.getIdentificador());
				ps.setString(2, p.getNombre());
				ps.setString(3, p.getApodo());
				ps.setString(4, p.getApellido());
				ps.setBoolean(5, p.getGenero());
				ps.setString(6, p.getDireccion());
				ps.setString(7, p.getNotas());
				ps.setString(8, "persona");
				
				ps.executeUpdate();
				
			//RECORREMOS ARRAY DE TELEFONO E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				if (!(p.getTelefono() == null)){
					for (int i = p.getTelefono().size(); i > 0; i--){

						sql = "INSERT INTO telefono"
								+ " (numero, id, tipo)"
								+ " VALUES (?, ?, ?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, p.getTelefono().get(i-1).getNumero());
						ps.setInt(2, p.getIdentificador());
						ps.setBoolean(3, p.getTelefono().get(i-1).getMovil());
						ps.executeUpdate();
					}

				}

			//RECORREMOS ARRAY DE CORREO E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				for (int i = p.getCorreo().size(); i > 0; i--){

					sql = "INSERT INTO correo"
							+ " (direccionC, id)"
							+ " VALUES (?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, p.getCorreo().get(i-1).toString());
					ps.setInt(2, p.getIdentificador());
					ps.executeUpdate();
				}

			//RECORREMOS ARRAY DE AFICION E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				for (int i = p.getAficion().size(); i > 0; i--){

					sql = "INSERT INTO perAfi"
							+ " (id, actividad)"
							+ " VALUES (?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, p.getIdentificador());
					ps.setString(2, p.getAficion().get(i-1).getActividad());
					ps.executeUpdate();
				}
			}

		//NUEVO REGISTRO PARA EMPRESAS	
			if (c instanceof Empresa) {

				Empresa e = (Empresa) c;
				e.setIdentificador(idMax()+1);

				String sql = "INSERT INTO contacto"
						+ " (id, nombre, direccion, notas, tipo)"
						+ " VALUES (?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, e.getIdentificador());
				ps.setString(2, e.getNombre());
				ps.setString(3, e.getDireccion());
				ps.setString(4, e.getNotas());
				ps.setString(5, "empresa");

				ps.executeUpdate();

			//RECORREMOS ARRAY DE TELEFONO E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				for (int i = e.getTelefono().size(); i > 0; i--){

					sql = "INSERT INTO telefono"
							+ " (numero, id, tipo)"
							+ " VALUES (?, ?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, e.getTelefono().get(i-1).getNumero());
					ps.setInt(2, e.getIdentificador());
					ps.setBoolean(3, e.getTelefono().get(i-1).getMovil());

					ps.executeUpdate();
				}

			//RECORREMOS ARRAY DE CORREO E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				for (int i = e.getCorreo().size(); i > 0; i--){

					sql = "INSERT INTO correo"
							+ " (direccionC, id)"
							+ " VALUES (?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, e.getCorreo().get(i-1).toString());
					ps.setInt(2, e.getIdentificador());

					ps.executeUpdate();
				}
			}

		//NUEVO REGISTRO PARA APODOS	
			if (c instanceof Apodo) {

				Apodo ap = (Apodo) c;
				ap.setIdentificador(idMax()+1);

				String sql = "INSERT INTO contacto "
						+ " (id, nombre, genero, direccion, notas, tipo)"
						+ " VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, ap.getIdentificador());
				ps.setString(2, ap.getApodo());
				ps.setBoolean(3, ap.getGenero());
				ps.setString(4, ap.getDireccion());
				ps.setString(5, ap.getNotas());
				ps.setString(6, "apodos");

				ps.executeUpdate();

			//RECORREMOS ARRAY DE TELEFONO E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				for (int i = ap.getTelefono().size(); i > 0; i--){

					sql = "INSERT INTO telefono"
							+ " (numero, id, tipo)"
							+ " VALUES (?, ?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, ap.getTelefono().get(i-1).getNumero());
					ps.setInt(2, ap.getIdentificador());
					ps.setBoolean(3, ap.getTelefono().get(i-1).getMovil());

					ps.executeUpdate();
				}

			//RECORREMOS ARRAY DE CORREO E INTRODUCIMOS DATOS EN LA BASE DE DATOS
				for (int i = ap.getCorreo().size(); i > 0; i--){

					sql = "INSERT INTO correo"
							+ " (direccionC, id)"
							+ " VALUES (?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, ap.getCorreo().get(i-1).toString());
					ps.setInt(2, ap.getIdentificador());

					ps.executeUpdate();
				}

			//RECORREMOS ARRAY DE AFICION E INTRODUCIMOS DATOS EN LA BASE DE DATOS	
				for (int i = ap.getAficion().size(); i > 0; i--){

					sql = "INSERT INTO perAfi"
							+ " (id, actividad)"
							+ " VALUES (?, ?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, ap.getIdentificador());
					ps.setString(2, ap.getAficion().get(i-1).getActividad());
					ps.executeUpdate();
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

//BORRAR CONTACTO
	public boolean borrarRegistro(Contacto c) throws SQLException{
		
		String sql = "DELETE FROM contacto WHERE id = " + c.getIdentificador();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		
		sql = "DELETE FROM telefono WHERE id = " + c.getIdentificador();
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		
		sql = "DELETE FROM correo WHERE id = " + c.getIdentificador();
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		
		if(c instanceof Persona || c instanceof Apodo){
			sql = "DELETE FROM perAfi WHERE id = " + c.getIdentificador();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			}
		
		return true;
	}
	
//MODIFICAR UN REGISTRO CONTACTO
	public Boolean modificarRegistro(Contacto c){
		try{
		//GUARDANDO EL OBJETO ENTRANTE CON LOS DATOS RECIBIDOS DEL REGISTRO A MODIFICAR LLAMAMOS A LOS METODOS BORRAR
		//Y NUEVO. EN CASO DE QUE OBTENGAMOS FALSE (AL BORRAR AFICIONES POR ESTAR EN USO) NO NOS DEJARA
			
			if (borrarRegistro(c)){
				nuevoRegistro(c);
			} else {
				return false;
			}		
		}catch (Exception e){
			return false;
		}
		return true;
	}

//CREAMOS ALGUNOS REGISTROS INICIALES DE CADA TIPO
	public void registrosIniciales() throws SQLException{
		
		Aficion afi = new Aficion("Pesca");
		Telefono tlf = new Telefono("654321", true);
		Correo cor = new Correo("Correo123");
		
		Persona p = new Persona(0, "Juan", "Tres Cantos", false, "Calle Falsa Undostres", "Tiene un periquito");
		p.setCorreo(cor);
		p.setTelefono(tlf);
		p.setAficion(afi);
		afi = new Aficion("Lectura");
		tlf = new Telefono("987654", false);
		cor = new Correo("Correo456");
		p.setCorreo(cor);
		p.setTelefono(tlf);
		p.setAficion(afi);
		
		nuevoRegistro(p);
		
		p = new Persona(0, "Clara", "Tres Cantos", true, "Calle Falsa Undostres", "Tiene un koala");
		afi = new Aficion("Piano");
		tlf = new Telefono("789123", false);
		cor = new Correo("CorreoSegundo");
		p.setCorreo(cor);
		p.setTelefono(tlf);
		p.setAficion(afi);
		
		nuevoRegistro(p);
		
		///////////////////
		Apodo ap = new Apodo(0, "El Pelos", false, "Calle Falsa Undostres", "No sabe cantar");
		afi = new Aficion("Piano");
		tlf = new Telefono("147258", true);
		cor = new Correo("CorreoTercero");
		ap.setCorreo(cor);
		ap.setTelefono(tlf);
		ap.setAficion(afi);
		afi = new Aficion("Footing");
		tlf = new Telefono("951847", true);
		cor = new Correo("CorreoFalso");
		ap.setCorreo(cor);
		ap.setTelefono(tlf);
		ap.setAficion(afi);
		
		nuevoRegistro(ap);
		
		ap = new Apodo(0, "Perica", true, "Calle sin numero 5", "Viste de amarillo");
		afi = new Aficion("Lectura");
		tlf = new Telefono("357689", true);
		cor = new Correo("CorreoCuerto");
		ap.setCorreo(cor);
		ap.setTelefono(tlf);
		ap.setAficion(afi);
		afi = new Aficion("Footing");
		tlf = new Telefono("784951", false);
		cor = new Correo("CorreoSinDireccion");
		ap.setCorreo(cor);
		ap.setTelefono(tlf);
		ap.setAficion(afi);
		
		nuevoRegistro(ap);
		
		
		////////////////////////////
		Empresa e = new Empresa(0, "Agendas S.A.", "Nave industrial Z", "Venden abanicos con fecha");
		tlf = new Telefono("349761", true);
		cor = new Correo("CorreoEmpresa");
		e.setCorreo(cor);
		e.setTelefono(tlf);
		
		nuevoRegistro(e);
		
		e = new Empresa(0, "Globos Gitantes SL", "Nave empresarial X", "Venden bombas nucleares falsas");
		tlf = new Telefono("7614892", false);
		cor = new Correo("CorreoEmpresaSegundo");
		e.setCorreo(cor);
		e.setTelefono(tlf);
		
		nuevoRegistro(e);
		
	}

	

}
