package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	final private String CONN_AGEN = "jdbc:sqlite:Agenda.sqlite";
	
	private static Connection conn = null;
	
	// Generamos la conexion a nuestra base de datos nombrada en CONN_AGEN
	public Conexion() throws ClassNotFoundException, SQLException {
		
		if (conn != null) {
			return;
		}
		
		try {
			conn = DriverManager.getConnection(CONN_AGEN);
			conn.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CreateContacto();
		CreateAficion();
		CreatePersonaAficion();
		CreateTelefono();
		CreateCorreo();
		
	}
	
	
	// Generamos tablas que vamos a tener dentro de la base de datos, cada una de las tablas será un método 
	//para tener un control de errores mas efectivo
	
	//PERSONA
	public Boolean CreateContacto() {
		String sql;
		try {
			sql = "CREATE TABLE IF NOT EXISTS contacto (\n" + 
					"id INTEGER(25) PRIMARY KEY NOT NULL,\n" + 
					"nombre VARCHAR(20),\n" + 
					"apodo VARCHAR(20) DEFAULT NULL,\n" + 
					"apellido VARCHAR(50) DEFAULT NULL,\n" + 
					"genero BOOLEAN DEFAULT NULL,\n" + 
					"direccion VARCHAR(50),\n" + 
					"notas VARCHAR(320) DEFAULT NULL,\n" +
					"tipo VARCHAR (25))";
			conn.createStatement().executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}
	
	//AFICIONES
	public Boolean CreateAficion() {
		String sql;
		try {
			sql = "CREATE TABLE IF NOT EXISTS aficion (\n" + 
					"actividad VARCHAR(25) PRIMARY KEY NOT NULL);";
			conn.createStatement().executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	//PERSONAS-AFICIONES
	public Boolean CreatePersonaAficion() {
		String sql;
		try {
			sql = "CREATE TABLE IF NOT EXISTS perAfi (\n" + 
					"id INTEGER(25) NOT NULL,\n" + 
					"actividad VARCHAR(25) NOT NULL,\n" + 
					"FOREIGN KEY (id) REFERENCES contacto (id)\n" +
					"ON DELETE CASCADE,\n" + 
					"FOREIGN KEY (actividad) REFERENCES aficion (actividad)\n" +
					"ON DELETE CASCADE)";
			conn.createStatement().executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	// TELEFONO
	public Boolean CreateTelefono() {
		String sql;
		try {
			sql = "CREATE TABLE IF NOT EXISTS telefono (\n" + 
					"numero VARCHAR(25) PRIMARY KEY NOT NULL,\n" + 
					"id INTEGER(25),\n" + 
					"tipo BOOLEAN,\n" +
					"FOREIGN KEY (id) REFERENCES contacto (id)\n" +
					"ON DELETE CASCADE)";
			conn.createStatement().executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	// CORREO
	public Boolean CreateCorreo() {
		String sql;
		try {
			sql = "CREATE TABLE IF NOT EXISTS correo (\n" + 
					"direccionC VARCHAR(25) PRIMARY KEY NOT NULL,\n" + 
					"id INTEGER(20),\n" + 
					"FOREIGN KEY (id) REFERENCES contacto (id)\n" +
					"ON DELETE CASCADE)";
			conn.createStatement().executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public Boolean BorrarTodo() {
		String sql;
		try {
			sql = "DROP TABLE correo;";
			conn.createStatement().executeUpdate(sql);

			sql = "DROP TABLE contacto;";
			conn.createStatement().executeUpdate(sql);

			sql = "DROP TABLE telefono;";
			conn.createStatement().executeUpdate(sql);

			sql = "DROP TABLE perAfi;";
			conn.createStatement().executeUpdate(sql);

			sql = "DROP TABLE aficion;";
			conn.createStatement().executeUpdate(sql);

			return true;

		} catch (Exception e){
			e.printStackTrace();

			return false;
		}
	}

	//Para llamar a la conexion
	public static Connection getConn() { 
		return conn;

	}
}
