package vista;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Aficion;
import clases.Empresa;
import clases.Correo;
import clases.Telefono;
import dao.AficionDAO;
import dao.ContactoDAO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroducirModificarEmpresa extends JFrame {

	private JPanel contentPane;
	
//text Empresa	
	private JTextField textFieldEmpresaNombre;
	private JTextField textFieldEmpresaDireccion;
	private JTextField textFieldEmpresaTelf1;
	private JTextField textFieldEmpresaCorreo1;
	private JTextField textFieldEmpresaNotas;
	private JRadioButton radioMovil1;
//Jlist Empresa	
	private DefaultListModel dlmTelefonoEmpresa;
	private DefaultListModel dlmCorreosEmpresa;

	private ContactoDAO cDAO;
	private Empresa e;
	private Telefono tlf;
	private Correo cor;
	private int id;
	
	private ArrayList<Telefono> altlf;
	private ArrayList<Correo> alcor;
	

	public IntroducirModificarEmpresa(int id) throws SQLException, ClassNotFoundException {
		
		cDAO = new ContactoDAO();
		this.id = id;
		e = new Empresa (id, "");
		
		altlf = new ArrayList<Telefono>();
		alcor = new ArrayList<Correo>();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 465, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelEmpresa();
		
	}
	
	public void panelEmpresa() throws SQLException, ClassNotFoundException{	
		
		JPanel panelEmpresa = new JPanel();
		panelEmpresa.setBounds(0, 0, 455, 496);
		contentPane.add(panelEmpresa);
		panelEmpresa.setLayout(null);
		panelEmpresa.setVisible(true);
		
	//TEXTOS Y LABELS - Empresa
		textFieldEmpresaNombre = new JTextField();
		textFieldEmpresaNombre.setBounds(85, 22, 343, 20);
		panelEmpresa.add(textFieldEmpresaNombre);
		textFieldEmpresaNombre.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setBounds(10, 24, 65, 17);
		panelEmpresa.add(labelNombre);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 52, 65, 17);
		panelEmpresa.add(lblDireccion);
		
		textFieldEmpresaDireccion = new JTextField();
		textFieldEmpresaDireccion.setColumns(10);
		textFieldEmpresaDireccion.setBounds(85, 53, 343, 20);
		panelEmpresa.add(textFieldEmpresaDireccion);
		
		textFieldEmpresaTelf1 = new JTextField();
		textFieldEmpresaTelf1.setColumns(10);
		textFieldEmpresaTelf1.setBounds(30, 102, 250, 20);
		panelEmpresa.add(textFieldEmpresaTelf1);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(20, 83, 148, 17);
		panelEmpresa.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(20, 223, 65, 17);
		panelEmpresa.add(lblCorreo);
		
		textFieldEmpresaCorreo1 = new JTextField();
		textFieldEmpresaCorreo1.setColumns(10);
		textFieldEmpresaCorreo1.setBounds(30, 243, 408, 20);
		panelEmpresa.add(textFieldEmpresaCorreo1);
		
		JLabel lblnotasEmpresa = new JLabel("Notas:");
		lblnotasEmpresa.setBounds(20, 363, 46, 14);
		panelEmpresa.add(lblnotasEmpresa);
		
		textFieldEmpresaNotas = new JTextField();
		textFieldEmpresaNotas.setBounds(40, 388, 398, 58);
		panelEmpresa.add(textFieldEmpresaNotas);
		textFieldEmpresaNotas.setColumns(10);
		
		radioMovil1 = new JRadioButton("Movil");
		radioMovil1.setBounds(306, 99, 65, 23);
		panelEmpresa.add(radioMovil1);
		
		JRadioButton radioFijo1 = new JRadioButton("Fijo");
		radioFijo1.setBounds(373, 99, 65, 23);
		panelEmpresa.add(radioFijo1);
		
		ButtonGroup btelefono = new ButtonGroup();
		btelefono.add(radioMovil1);
		btelefono.add(radioFijo1);
		
	//JLIST TELEFONO - Empresa		
		dlmTelefonoEmpresa = new DefaultListModel();
		
		JList listTelefonosEmpresa = new JList(dlmTelefonoEmpresa);
		listTelefonosEmpresa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listTelefonosEmpresa.setBounds(40, 136, 238, 75);
		
		panelEmpresa.add(listTelefonosEmpresa);
		
		listTelefonosEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tlf = (Telefono) listTelefonosEmpresa.getSelectedValue();		
			}
		});
		
		
	//JLIST CORREO - Empresa
		dlmCorreosEmpresa = new DefaultListModel();
		
		JList listCorreosEmpresa = new JList(dlmCorreosEmpresa);
		listCorreosEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cor = (Correo) listCorreosEmpresa.getSelectedValue();
			}
		});
		listCorreosEmpresa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listCorreosEmpresa.setBounds(40, 274, 238, 75);
		panelEmpresa.add(listCorreosEmpresa);
		
		
		/// TELEFONO ///
		JButton btnAniadirTelefono = new JButton("+Telefono");
		btnAniadirTelefono.setBounds(291, 135, 127, 23);
		panelEmpresa.add(btnAniadirTelefono);
		btnAniadirTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tlf = new Telefono (textFieldEmpresaTelf1.getText(), radioMovil1.isSelected());
				aniadirElemento(tlf);	
			}
		});
		
		JButton btnBorrarTelefono = new JButton("-Telefono");
		btnBorrarTelefono.setBounds(291, 188, 127, 23);
		panelEmpresa.add(btnBorrarTelefono);
		btnBorrarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarElemento(tlf);
			}
		});
		
		
		/// CORREO ///
		JButton btnAniadirCorreo = new JButton("+Correo");
		btnAniadirCorreo.setBounds(291, 275, 127, 23);
		panelEmpresa.add(btnAniadirCorreo);
		btnAniadirCorreo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cor = new Correo (textFieldEmpresaCorreo1.getText());
					aniadirElemento(cor);
			}
		});
		
		JButton btnBorrarCorreo = new JButton("-Correo");
		btnBorrarCorreo.setBounds(291, 329, 127, 23);
		panelEmpresa.add(btnBorrarCorreo);
		btnBorrarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarElemento(cor);
			}
		});
	
		
		/// AÑADIR Y CANCELAR ///
		JButton btnNewButton = new JButton("Introducir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					nuevaEmpresa();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(187, 457, 127, 23);
		panelEmpresa.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(326, 457, 112, 23);
		panelEmpresa.add(btnCancelar);
		
	// VARIACION DE CAMPOS CON MODIFICAR //	
		if (this.id != 0 && e instanceof Empresa){
			
			tlf = new Telefono("", null);
			cor = new Correo("");
			
			textFieldEmpresaNombre.setText(cDAO.imprimirPorId(this.id, e).getNombre());
			textFieldEmpresaNotas.setText(cDAO.imprimirPorId(this.id, e).getNotas());
			textFieldEmpresaDireccion.setText(cDAO.imprimirPorId(this.id, e).getDireccion());
			altlf = cDAO.imprimirPorId(this.id, e).getTelefono();
			alcor = cDAO.imprimirPorId(this.id, e).getCorreo();

			for (int i = 0; i < cDAO.imprimirPorId(this.id, e).getTelefono().size(); i++){
				dlmTelefonoEmpresa.addElement(cDAO.imprimirPorId(this.id, e).getTelefono().get(i));
			}
			for (int i = 0; i < cDAO.imprimirPorId(this.id, e).getCorreo().size(); i++){
				dlmCorreosEmpresa.addElement(cDAO.imprimirPorId(this.id, e).getCorreo().get(i));
			}
		}
	}

	
//AÑADE NUEVA EMPRESA A LA AGENDA
	private Boolean nuevaEmpresa() throws SQLException{
		ContactoDAO cDAO = new ContactoDAO();
		
		if(this.id != 0) {
			e.setIdentificador(this.id);
		}
		try{
			cDAO.borrarRegistro(e);
		} catch (Exception e){
			System.out.println("No se ha borrado nada");
		}
		
		e.setNombre(textFieldEmpresaNombre.getText());
		e.setDireccion(textFieldEmpresaDireccion.getText()); 
		e.setNotas(textFieldEmpresaNotas.getText());
		e.setCorreo(alcor);
		e.setTelefono(altlf);

		if (textFieldEmpresaNombre.getText().compareTo("") == 0){
			JOptionPane.showMessageDialog(null, "Debe introducir un nombre");
			return false;
		}

		cDAO.nuevoRegistro(e);
		setVisible(false);
		return true;
	}

	
//PERMITE AÑADIR TELEFONO, CORREO O AFICION AL CONTACTO
	private void aniadirElemento(Object o){
		
		if (o instanceof Telefono){
			textFieldEmpresaTelf1.setText("");
			dlmTelefonoEmpresa.addElement(tlf);
			altlf.add(tlf);	
		}
		
		if (o instanceof Correo){
			
			textFieldEmpresaCorreo1.setText("");
			dlmCorreosEmpresa.addElement(cor);
			alcor.add(cor);	
		}
	}
//ELIMINA TELEFONO, CORREO DEL CONTACTO	
	private void quitarElemento(Object o){

		if (o instanceof Telefono){
			dlmTelefonoEmpresa.removeElement(tlf);
			altlf.remove(tlf);
		}

		if (o instanceof Correo){
			dlmCorreosEmpresa.removeElement(cor);
			alcor.remove(cor);
		}
	}
}
