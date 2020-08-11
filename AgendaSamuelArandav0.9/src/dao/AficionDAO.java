package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Aficion;
import clases.Contacto;

public class AficionDAO {
	
	private static Connection conn = Conexion.getConn();

	public AficionDAO() throws SQLException {
	}
	
//OBTENER LISTA COMPLETA DE AFICIONES	
	public ArrayList<Aficion> imprimirAfi() throws SQLException{
		ArrayList<Aficion> al = new ArrayList<Aficion>();

		String sql = "SELECT actividad FROM aficion ORDER BY actividad";
		ResultSet rs = conn.createStatement().executeQuery(sql);

		while (rs.next()){
			Aficion afi = new Aficion(rs.getString(1));
			al.add(afi);
		}
		return al;
	}

//ANIADIR NUEVA ACTIVIDAD A LA BBDD	
	public Boolean nuevaAficion(Aficion afi) throws SQLException{

		String sql = "INSERT INTO aficion"
				+ " (actividad)"
				+ " VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, afi.getActividad());
		
		return ps.executeUpdate()>0;
		
	}
	
//PEDIR ARRAYLIST DE AFICIONES PARA CONTACTO DADO	
	public ArrayList<Aficion> getAficion(Contacto c) throws SQLException{

		ArrayList<Aficion> al = new ArrayList<Aficion>();

		String sql = "SELECT actividad FROM perAfi "
				+ "WHERE id = " + c.getIdentificador();
		ResultSet rs = conn.createStatement().executeQuery(sql);

		while (rs.next()){
			Aficion afi = new Aficion(rs.getString(1));
			al.add(afi);
		}
		return al;
	}
	
//COMPROBAR SI LAS AFICIONES ESTAN SIENDO USADAS POR ALGUN CONTACTO
	public Boolean proveAfi(Aficion afi){
		
		try {
			String sql = "SELECT actividad FROM perAfi";
			ResultSet rs = conn.createStatement().executeQuery(sql);

			while (rs.next()){
				Aficion enUso = new Aficion(rs.getString(1));
				Boolean usado = (enUso.getActividad().compareTo(afi.getActividad()) == 0);
				if (usado){
					return true;
				}
			}
		} catch (Exception e){
			return null;
		}
		return false;
	}
	
//BORRAR AFICIONES (COMPROBANDO ANTES SI EST�N EN USO)
	public Boolean borrarAficion(Aficion afi) throws SQLException{
		
		if (proveAfi(afi)){
			return false;
		} else {

			String sql = "DELETE FROM aficion WHERE actividad = \"" + afi.getActividad() + "\"";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();	
		}	
		return true;
	}


//ANIADIR ALGUNOS REGISTROS AL CREAR LA BBDD
	public void registrosIniciales() throws SQLException{
		
		Aficion afi; 
		
		afi = new Aficion("Footing");
		nuevaAficion(afi);
		
		afi = new Aficion("Danza");
		nuevaAficion(afi);
		
		afi = new Aficion("Pesca");
		nuevaAficion(afi);
		
		afi = new Aficion("Lectura");
		nuevaAficion(afi);
		
		afi = new Aficion("Piano");
		nuevaAficion(afi);
		
		
	}

//VER TODA TABLA PERAFI
	public ArrayList<String> pedirPerAfi() throws SQLException{
		
		ArrayList<String> al = new ArrayList<String>();

		String sql = "SELECT id, actividad FROM perAfi";
		ResultSet rs = conn.createStatement().executeQuery(sql);

		while (rs.next()){
			String afi = rs.getInt(1) + rs.getString(2);
			al.add(afi);
		}
		return al;
	}
}