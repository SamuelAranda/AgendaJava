package principal;

import java.sql.SQLException;

import dao.AficionDAO;
import dao.Conexion;
import dao.ContactoDAO;
import vista.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
			
		Conexion conn = new Conexion();
		AficionDAO aDAO = new AficionDAO();
		ContactoDAO cDAO = new ContactoDAO();
		VentanaPrincipal vp = new VentanaPrincipal();
		vp.isVisible();
		
		if (cDAO.idMax() == 0) {
			cDAO.registrosIniciales();
			aDAO.registrosIniciales();
		}
	}
}
