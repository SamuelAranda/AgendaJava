package vista;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Aficion;
import clases.Correo;
import clases.Persona;
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

public class IntroducirModificarPersona extends JFrame {

	private JPanel contentPane;
	
//text Persona	
	private JTextField textFieldPersonaNombre;
	private JTextField textField_1Persona;
	private JTextField textFieldPersonaDireccion;
	private JTextField textFieldPersonaApellido;
	private JTextField textFieldPersonaTelf1;
	private JTextField textFieldPersonaCorreo1;
	private JTextField textFieldPersonaNotas;
//radiobuttons Persona	
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnHombre;
	private JRadioButton radioMovil1;
//Jlist Persona	
	private DefaultListModel dlmAficionNoPersona;
	private JList list;
	private DefaultListModel dlmAficionTienePersona;
	private JList listAficionTienePersona;
	private DefaultListModel dlmTelefonoPersona;
	private DefaultListModel dlmCorreosPersona;

	private AficionDAO aDAO;
	private ContactoDAO cDAO;
	private Persona p;
	private Telefono tlf;
	private Correo cor;
	private Aficion afiTem;
	private Aficion aficionContacto;
	private int id;
	
	private ArrayList<Telefono> altlf;
	private ArrayList<Correo> alcor;
	private ArrayList<Aficion> alNoAfi;
	private ArrayList<Aficion> alSiAfi;
	

	public IntroducirModificarPersona(int id) throws SQLException, ClassNotFoundException {
		
		aDAO = new AficionDAO();
		cDAO = new ContactoDAO();
		
		p = new Persona (99999, "");
		aficionContacto = null;
		afiTem = new Aficion("");
		this.id = id;
		
		altlf = new ArrayList<Telefono>();
		alcor = new ArrayList<Correo>();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 464, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelPersona();
		
	}
	
	public void panelPersona() throws SQLException, ClassNotFoundException{	
		
		JPanel panelPersona = new JPanel();
		panelPersona.setBounds(0, 0, 455, 671);
		contentPane.add(panelPersona);
		panelPersona.setLayout(null);
		panelPersona.setVisible(true);
		ArrayList<Aficion> alTAfi;
		alSiAfi = new ArrayList<Aficion>();
		
	//TEXTOS Y LABELS - PERSONA
		textFieldPersonaNombre = new JTextField();
		textFieldPersonaNombre.setBounds(85, 22, 185, 20);
		panelPersona.add(textFieldPersonaNombre);
		textFieldPersonaNombre.setColumns(10);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setBounds(10, 24, 65, 17);
		panelPersona.add(labelNombre);
		
		textFieldPersonaApellido = new JTextField();
		textFieldPersonaApellido.setColumns(10);
		textFieldPersonaApellido.setBounds(85, 54, 343, 20);
		panelPersona.add(textFieldPersonaApellido);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 55, 65, 17);
		panelPersona.add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 86, 65, 17);
		panelPersona.add(lblDireccion);
		
		textFieldPersonaDireccion = new JTextField();
		textFieldPersonaDireccion.setColumns(10);
		textFieldPersonaDireccion.setBounds(85, 85, 343, 20);
		panelPersona.add(textFieldPersonaDireccion);
		
		textFieldPersonaTelf1 = new JTextField();
		textFieldPersonaTelf1.setColumns(10);
		textFieldPersonaTelf1.setBounds(20, 134, 250, 20);
		panelPersona.add(textFieldPersonaTelf1);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 115, 148, 17);
		panelPersona.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 255, 65, 17);
		panelPersona.add(lblCorreo);
		
		textFieldPersonaCorreo1 = new JTextField();
		textFieldPersonaCorreo1.setColumns(10);
		textFieldPersonaCorreo1.setBounds(20, 275, 408, 20);
		panelPersona.add(textFieldPersonaCorreo1);
		
		JLabel lblAficionesNoPersona = new JLabel("Lista de aficiones:");
		lblAficionesNoPersona.setBounds(10, 394, 157, 17);
		panelPersona.add(lblAficionesNoPersona);
		
		JLabel labelAficionSiPersona = new JLabel("Aficion persona:");
		labelAficionSiPersona.setBounds(271, 395, 157, 17);
		panelPersona.add(labelAficionSiPersona);
		
		JLabel lblnotasPersona = new JLabel("Notas:");
		lblnotasPersona.setBounds(10, 521, 46, 14);
		panelPersona.add(lblnotasPersona);
		
		textFieldPersonaNotas = new JTextField();
		textFieldPersonaNotas.setBounds(30, 546, 398, 58);
		panelPersona.add(textFieldPersonaNotas);
		textFieldPersonaNotas.setColumns(10);
		
	//RADIOBUTTONS - PERSONA	
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(281, 22, 80, 21);
		panelPersona.add(rdbtnHombre);
		
		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(363, 22, 65, 20);
		panelPersona.add(rdbtnMujer);
		
		ButtonGroup bgenero = new ButtonGroup();
		bgenero.add(rdbtnHombre);
		bgenero.add(rdbtnMujer);
		
		radioMovil1 = new JRadioButton("Movil");
		radioMovil1.setBounds(296, 131, 65, 23);
		panelPersona.add(radioMovil1);
		
		JRadioButton radioFijo1 = new JRadioButton("Fijo");
		radioFijo1.setBounds(363, 131, 65, 23);
		panelPersona.add(radioFijo1);
		
		ButtonGroup btelefono = new ButtonGroup();
		btelefono.add(radioMovil1);
		btelefono.add(radioFijo1);
		
		ButtonGroup btelefono2 = new ButtonGroup();
		
		ButtonGroup btelefono3 = new ButtonGroup();
		
		
	//JPANEL AFICIONES(Sin aniadir) - PERSONA		
		JPanel panelAficionNoPersona = new JPanel();
		panelAficionNoPersona.setBounds(30, 423, 157, 82);
		panelPersona.add(panelAficionNoPersona);
		panelAficionNoPersona.setMaximumSize(new Dimension(3276, 3276));
		panelAficionNoPersona.setForeground(Color.LIGHT_GRAY);
		panelAficionNoPersona.setLayout(null);
		
		JScrollPane scrollPaneNoAficionPersona = new JScrollPane();
		scrollPaneNoAficionPersona.setBounds(0, 0, 156, 82);
		panelAficionNoPersona.add(scrollPaneNoAficionPersona);
	
		panelAficionNoPersona.setVisible(true);
		list = new JList();
	
		dlmAficionNoPersona = new DefaultListModel();
		list.setModel(dlmAficionNoPersona);
		dlmAficionNoPersona.clear();
		
		alNoAfi = aDAO.imprimirAfi();
		mostrarAficion();
		
		scrollPaneNoAficionPersona.setViewportView(list);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aficionContacto = (Aficion) list.getSelectedValue();
				System.out.println(aficionContacto);
			}
		});		
		
	//JPANEL AFICIONES(Aniadidas) - PERSONA	
		JPanel panelAfcionTienePesona = new JPanel();
		panelAfcionTienePesona.setBounds(271, 423, 157, 82);
		panelPersona.add(panelAfcionTienePesona);
		panelAfcionTienePesona.setLayout(null);
		
		JScrollPane scrollPaneTienePersona = new JScrollPane();
		scrollPaneTienePersona.setBounds(0, 0, 157, 82);
		panelAfcionTienePesona.add(scrollPaneTienePersona);
		
		scrollPaneTienePersona.setVisible(true);
		
		listAficionTienePersona = new JList();
		
		dlmAficionTienePersona = new DefaultListModel();
		listAficionTienePersona.setModel(dlmAficionTienePersona);
	
		alNoAfi = new ArrayList<Aficion>();
		mostrarAficionInterior();
		
		scrollPaneTienePersona.setViewportView(listAficionTienePersona);
		
		listAficionTienePersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		listAficionTienePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aficionContacto = (Aficion) listAficionTienePersona.getSelectedValue();
			}
		});
		
	//JLIST TELEFONO - PERSONA		
		dlmTelefonoPersona = new DefaultListModel();
		
		JList listTelefonosPersona = new JList(dlmTelefonoPersona);
		listTelefonosPersona.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listTelefonosPersona.setBounds(30, 168, 238, 75);
		
		panelPersona.add(listTelefonosPersona);
		
		listTelefonosPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tlf = (Telefono) listTelefonosPersona.getSelectedValue();
				
			}
		});
		
		
	//JLIST CORREO - PERSONA
		dlmCorreosPersona = new DefaultListModel();
		
		JList listCorreosPersona = new JList(dlmCorreosPersona);
		listCorreosPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cor = (Correo) listCorreosPersona.getSelectedValue();
			}
		});
		listCorreosPersona.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listCorreosPersona.setBounds(30, 306, 238, 75);
		panelPersona.add(listCorreosPersona);	
		
	// BOTONES  PERSONA //
		/// AFICION ///
		JButton btnPonerAficionPersona = new JButton(">>");
		btnPonerAficionPersona.setBounds(199, 426, 60, 23);
		panelPersona.add(btnPonerAficionPersona);
		btnPonerAficionPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aniadirElemento(aficionContacto);
			}
		});	

		JButton buttonQuitarAficionPersonas = new JButton("<<");
		buttonQuitarAficionPersonas.setBounds(199, 461, 60, 23);
		panelPersona.add(buttonQuitarAficionPersonas);
		buttonQuitarAficionPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarElemento(aficionContacto);
			}	
		});
		
		
		/// TELEFONO ///
		JButton btnAniadirTelefono = new JButton("+Telefono");
		btnAniadirTelefono.setBounds(281, 167, 127, 23);
		panelPersona.add(btnAniadirTelefono);
		btnAniadirTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tlf = new Telefono (textFieldPersonaTelf1.getText(), radioMovil1.isSelected());
				aniadirElemento(tlf);	
			}
		});
		
		JButton btnBorrarTelefono = new JButton("-Telefono");
		btnBorrarTelefono.setBounds(281, 220, 127, 23);
		panelPersona.add(btnBorrarTelefono);
		btnBorrarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarElemento(tlf);
			}
		});
		
		
		/// CORREO ///
		JButton btnAniadirCorreo = new JButton("+Correo");
		btnAniadirCorreo.setBounds(281, 307, 127, 23);
		panelPersona.add(btnAniadirCorreo);
		btnAniadirCorreo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cor = new Correo (textFieldPersonaCorreo1.getText());
					aniadirElemento(cor);
			}
		});
		
		JButton btnBorrarCorreo = new JButton("-Correo");
		btnBorrarCorreo.setBounds(281, 361, 127, 23);
		panelPersona.add(btnBorrarCorreo);
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
					nuevaPersona();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(177, 615, 127, 23);
		panelPersona.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(316, 615, 127, 23);
		panelPersona.add(btnCancelar);
		
	// VARIACION DE CAMPOS CON MODIFICAR //	
		if (this.id != 0){
			
			aficionContacto = new Aficion("");
			tlf = new Telefono("", null);
			cor = new Correo("");
			
			textFieldPersonaNombre.setText(cDAO.imprimirPorId(this.id, p).getNombre());
			textFieldPersonaApellido.setText(((Persona) cDAO.imprimirPorId(this.id, p)).getApellido());
			textFieldPersonaNotas.setText(cDAO.imprimirPorId(this.id, p).getNotas());
			textFieldPersonaDireccion.setText(cDAO.imprimirPorId(this.id, p).getDireccion());
			alSiAfi = ((Persona) cDAO.imprimirPorId(this.id, p)).getAficion();
			altlf = cDAO.imprimirPorId(this.id, p).getTelefono();
			alcor = cDAO.imprimirPorId(this.id, p).getCorreo();

			for(int i = 0; i < alSiAfi.size(); i++){
				aficionContacto = alSiAfi.get(i);
				dlmAficionNoPersona.removeElement(aficionContacto);
			}
			for (int i = 0; i < cDAO.imprimirPorId(this.id, p).getTelefono().size(); i++){
				dlmTelefonoPersona.addElement(cDAO.imprimirPorId(this.id, p).getTelefono().get(i));
			}
			for (int i = 0; i < cDAO.imprimirPorId(this.id, p).getCorreo().size(); i++){
				dlmCorreosPersona.addElement(cDAO.imprimirPorId(this.id, p).getCorreo().get(i));
			}
		}

		mostrarAficion();
		mostrarAficionInterior();
	}

	
//AÑADE NUEVA PERSONA A LA AGENDA
	private Boolean nuevaPersona() throws SQLException{
		ContactoDAO cDAO = new ContactoDAO();
		
		if(this.id != 0) {
			p.setIdentificador(this.id);
		}
		try{
			cDAO.borrarRegistro(p);
		} catch (Exception e){
			System.out.println("No se ha borrado nada");
		}
		
		
		p.setNombre(textFieldPersonaNombre.getText());
		p.setApellido(textFieldPersonaApellido.getText());
		p.setGenero(rdbtnMujer.isSelected());
		p.setDireccion(textFieldPersonaDireccion.getText()); 
		p.setNotas(textFieldPersonaNotas.getText());
		p.setCorreo(alcor);
		p.setTelefono(altlf);
		p.setAficion(alSiAfi);

		if (textFieldPersonaApellido.getText().compareTo("") == 0){
			JOptionPane.showMessageDialog(null, "Debe introducir, al menos, un apellido");
			return false;
		}
		if (textFieldPersonaNombre.getText().compareTo("") == 0){
			JOptionPane.showMessageDialog(null, "Debe introducir un nombre");
			return false;
		}
		if (!rdbtnMujer.isSelected() && !rdbtnHombre.isSelected()){
			JOptionPane.showMessageDialog(null, "No ha seleccionado genero");
			return false;
		}

		cDAO.nuevoRegistro(p);
		setVisible(false);
		return true;
	}

	
//MUESTRA LISTA DE AFICIONES
	private void mostrarAficion() throws SQLException, ClassNotFoundException{
		
		for (int i = 0; i < alNoAfi.size(); i++){
			dlmAficionNoPersona.addElement(alNoAfi.get(i));
		}
	} 	
//MUESTRA LISTA DE AFICIONES DE LA PERSONA		
	private void mostrarAficionInterior(){
		
		for (int i = 0; i < alSiAfi.size(); i++){
				dlmAficionTienePersona.addElement(alSiAfi.get(i));
		}
	}

	
//PERMITE AÑADIR TELEFONO, CORREO O AFICION AL CONTACTO
	private void aniadirElemento(Object o){
		
		if (o instanceof Aficion){
			
			if (!(afiTem.compareTo(aficionContacto))){
				alNoAfi.remove(aficionContacto);
				alSiAfi.add(aficionContacto);
				
				dlmAficionTienePersona.addElement(aficionContacto);
				dlmAficionNoPersona.removeElement(aficionContacto);
				afiTem = aficionContacto;
			}
			
		}
		if (o instanceof Telefono){
			textFieldPersonaTelf1.setText("");
			dlmTelefonoPersona.addElement(tlf);
			altlf.add(tlf);	
		}
		
		if (o instanceof Correo){
			
			textFieldPersonaCorreo1.setText("");
			dlmCorreosPersona.addElement(cor);
			alcor.add(cor);	
		}
	}
//ELIMINA TELEFONO, CORREO O AFICION DEL CONTACTO	
	private void quitarElemento(Object o){
		
		if (o instanceof Aficion){

			if (!(afiTem.compareTo(aficionContacto))){
				alSiAfi.remove(aficionContacto);
				alNoAfi.add(aficionContacto);

				dlmAficionNoPersona.addElement(aficionContacto);
				dlmAficionTienePersona.removeElement(aficionContacto);
				afiTem = aficionContacto;
			}
		}

		if (o instanceof Telefono){
			dlmTelefonoPersona.removeElement(tlf);
			altlf.remove(tlf);
		}

		if (o instanceof Correo){
			dlmCorreosPersona.removeElement(cor);
			alcor.remove(cor);
		}
	}
}
