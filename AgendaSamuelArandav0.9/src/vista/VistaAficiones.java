package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import clases.Aficion;
import clases.Contacto;
import clases.Persona;
import dao.AficionDAO;
import dao.ContactoDAO;

import javax.swing.DropMode;

public class VistaAficiones extends JFrame {
	
	private JPanel paneAficion;
	private DefaultTableModel dtmAficion;
	private AficionDAO aDAO;
	private ContactoDAO cDAO;
	private String seleccion;
	private JTable tRaficion;
	private Aficion afi;
	private String aficion;

	public	VistaAficiones() throws SQLException{
		
		aDAO = new AficionDAO();
		cDAO = new ContactoDAO();

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 606, 399);
		setVisible(false);

		JMenuBar menuBarAficion = new JMenuBar();
		setJMenuBar(menuBarAficion);

		JMenu mnNewMenu = new JMenu("Aficiones");
		menuBarAficion.add(mnNewMenu);

		paneAficion = new JPanel();
		paneAficion.setLocation(new Point(2, 2));
		paneAficion.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		paneAficion.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneAficion);
		paneAficion.setLayout(null);

		JLabel etiquetaAclaracion = new JLabel("Las aficiones se muestran por orden alfabetico. Recuerda que para poder eliminar o modificar");
		etiquetaAclaracion.setBounds(20, 20, 570, 20);
		etiquetaAclaracion.setMaximumSize(new Dimension(767, 60));
		etiquetaAclaracion.setLocale(new Locale("es"));
		etiquetaAclaracion.setAlignmentX(Component.CENTER_ALIGNMENT);
		paneAficion.add(etiquetaAclaracion);

		JLabel lblPuedeEstarSiendo = new JLabel("una aficion, esta no puede estar asiganda a ningun contacto.");
		lblPuedeEstarSiendo.setBounds(20, 43, 570, 20);
		lblPuedeEstarSiendo.setMaximumSize(new Dimension(767, 60));
		lblPuedeEstarSiendo.setLocale(new Locale("es"));
		lblPuedeEstarSiendo.setAlignmentX(0.5f);
		paneAficion.add(lblPuedeEstarSiendo);

		dtmAficion = new DefaultTableModel();
		dtmAficion.addColumn("Aficion");

		JTable tRaficion = new JTable(dtmAficion);
		tRaficion.setPreferredScrollableViewportSize(new Dimension(450, 150));
		tRaficion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	//SELECCION	
		tRaficion.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				aficion = (String) tRaficion.getValueAt(tRaficion.getSelectedRow(), 0);
			}
		});
		
		JPanel Aficiones = new JPanel();
		Aficiones.setBounds(0, 0, 594, 204);
		Aficiones.setMaximumSize(new Dimension(3276, 3276));
		Aficiones.setForeground(Color.LIGHT_GRAY);
		Aficiones.setVisible(true);
		Aficiones.setLayout(null);

		JScrollPane scrollPersonas = new JScrollPane(tRaficion);
		scrollPersonas.setBounds(27, 89, 541, 170);
		scrollPersonas.setMaximumSize(new Dimension(327, 327));
		scrollPersonas.setViewportView(tRaficion);

		paneAficion.add(scrollPersonas);
		
		
//BOTON CONTACTOS		
		JButton Contactos = new JButton("Contactos");
		Contactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		menuBarAficion.add(Contactos);
		
////BOTON NUEVO
			JButton btnNuevaAficion = new JButton("Nueva Aficion");
			btnNuevaAficion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nuevaAficion();
				}
			});
			btnNuevaAficion.setBounds(60, 293, 141, 20);
			paneAficion.add(btnNuevaAficion);

			
//BOTON MODIFICAR
			JButton btnModificar = new JButton("Modificar");
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					modificarAficion();	
				}
			});
			btnModificar.setBounds(315, 293, 92, 21);
			paneAficion.add(btnModificar);

			
//BOTON BORRAR		
			JButton btnBorrar = new JButton("Eliminar");
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarAficion();
				}
			});
			btnBorrar.setBounds(417, 293, 88, 20);
			paneAficion.add(btnBorrar);

		mostrarAficion();

	}
	
	
//MUESTRA LISTA COMPLETA DE AFICIONES	
	public void mostrarAficion() throws SQLException{

		AficionDAO aDAO = new AficionDAO();
		ArrayList<Aficion> lc = aDAO.imprimirAfi();

		dtmAficion.setRowCount(lc.size());
		for (int i = lc.size(); i > 0; i--) {
			dtmAficion.setValueAt(lc.get(i-1).getActividad(), i-1, 0);
		}
	}
	
	
//ACCION DE LOS BOTONES (ANIADIR, ELIMINAR Y MODIFICAR)
	//NUEVA	
	public void nuevaAficion(){

		afi = new Aficion(JOptionPane.showInputDialog(
				null,"Introduzca nueva aficion",
				"Nueva Aficion",
				JOptionPane.QUESTION_MESSAGE));
		try {
			aDAO.nuevaAficion(afi);
			mostrarAficion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	//BORRAR
	public void borrarAficion(){

		afi = new Aficion(aficion);
		int option = JOptionPane.showConfirmDialog(null, "Quiere borrar la aficion: " + aficion + "?");
		if (option == 0){
			try {
				if (!aDAO.borrarAficion(afi)){
					JOptionPane.showMessageDialog(null, "No se puede borrar. La aficion esta actualmente en uso");
				}
				mostrarAficion();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	//MODIFICAR	
	private void modificarAficion(){
		afi = new Aficion(aficion);
		Aficion afiMod = new Aficion(JOptionPane.showInputDialog(
				null,"Introduzca la nueva aficion que sustituir a " + aficion,
				"Modificar Aficion",
				JOptionPane.QUESTION_MESSAGE));
		int option = JOptionPane.showConfirmDialog(null, "Esta seguro de quere sustituir: " + aficion 
				+ " por " + afiMod.getActividad() + "?");
		if (option == 0){
			try {
				if (aDAO.borrarAficion(afi)){
					aDAO.nuevaAficion(afiMod);
				}else {
					JOptionPane.showMessageDialog(null, "No se puede modificar. La aficion esta actualmente en uso");
				}
				
				mostrarAficion();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
}