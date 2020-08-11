package vista;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Aficion;
import clases.Apodo;
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

public class IntroducirModificarApodo extends JFrame {

	private JPanel contentPane;
	
//text Apodo	
	private JTextField textFieldApodoApodo;
	private JTextField textFieldApodoDireccion;
	private JTextField textFieldApodoTelf1;
	private JTextField textFieldApodoCorreo1;
	private JTextField textFieldApodoNotas;
//radiobuttons Apodo	
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnHombre;
	private JRadioButton radioMovil1;
//Jlist Apodo	
	private DefaultListModel dlmAficionNoApodo;
	private JList list;
	private DefaultListModel dlmAficionTieneApodo;
	private JList listAficionTieneApodo;
	private DefaultListModel dlmTelefonoApodo;
	private DefaultListModel dlmCorreosApodo;

	private AficionDAO aDAO;
	private ContactoDAO cDAO;
	private Apodo ap;
	private Telefono tlf;
	private Correo cor;
	private Aficion afiTem;
	private Aficion aficionContacto;
	private int id;
	
	private ArrayList<Telefono> altlf;
	private ArrayList<Correo> alcor;
	private ArrayList<Aficion> alNoAfi;
	private ArrayList<Aficion> alSiAfi;
	

	public IntroducirModificarApodo(int id) throws SQLException, ClassNotFoundException {
		
		
		aDAO = new AficionDAO();
		cDAO = new ContactoDAO();
		
		ap = new Apodo (99999, "");
		aficionContacto = null;
		afiTem = new Aficion("");
		this.id = id;
		
		altlf = new ArrayList<Telefono>();
		alcor = new ArrayList<Correo>();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 464, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelApodo();
		
	}
	
	public void panelApodo() throws SQLException, ClassNotFoundException{	
		
		JPanel panelApodo = new JPanel();
		panelApodo.setBounds(0, 0, 455, 621);
		contentPane.add(panelApodo);
		panelApodo.setLayout(null);
		panelApodo.setVisible(true);
		alSiAfi = new ArrayList<Aficion>();
		
	//TEXTOS Y LABELS - Apodo
		textFieldApodoApodo = new JTextField();
		textFieldApodoApodo.setBounds(85, 22, 185, 20);
		panelApodo.add(textFieldApodoApodo);
		textFieldApodoApodo.setColumns(10);
		
		JLabel labelNombre = new JLabel("Apodo:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setBounds(10, 24, 65, 17);
		panelApodo.add(labelNombre);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 52, 65, 17);
		panelApodo.add(lblDireccion);
		
		textFieldApodoDireccion = new JTextField();
		textFieldApodoDireccion.setColumns(10);
		textFieldApodoDireccion.setBounds(85, 53, 343, 20);
		panelApodo.add(textFieldApodoDireccion);
		
		textFieldApodoTelf1 = new JTextField();
		textFieldApodoTelf1.setColumns(10);
		textFieldApodoTelf1.setBounds(30, 102, 250, 20);
		panelApodo.add(textFieldApodoTelf1);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(20, 83, 148, 17);
		panelApodo.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(20, 223, 65, 17);
		panelApodo.add(lblCorreo);
		
		textFieldApodoCorreo1 = new JTextField();
		textFieldApodoCorreo1.setColumns(10);
		textFieldApodoCorreo1.setBounds(30, 243, 408, 20);
		panelApodo.add(textFieldApodoCorreo1);
		
		JLabel lblAficionesNoApodo = new JLabel("Lista de aficiones:");
		lblAficionesNoApodo.setBounds(20, 362, 157, 17);
		panelApodo.add(lblAficionesNoApodo);
		
		JLabel labelAficionSiApodo = new JLabel("Aficion Apodo:");
		labelAficionSiApodo.setBounds(281, 363, 157, 17);
		panelApodo.add(labelAficionSiApodo);
		
		JLabel lblnotasApodo = new JLabel("Notas:");
		lblnotasApodo.setBounds(20, 489, 46, 14);
		panelApodo.add(lblnotasApodo);
		
		textFieldApodoNotas = new JTextField();
		textFieldApodoNotas.setBounds(40, 514, 398, 58);
		panelApodo.add(textFieldApodoNotas);
		textFieldApodoNotas.setColumns(10);
		
	//RADIOBUTTONS - Apodo	
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setBounds(281, 22, 80, 21);
		panelApodo.add(rdbtnHombre);
		
		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setBounds(363, 22, 65, 20);
		panelApodo.add(rdbtnMujer);
		
		ButtonGroup bgenero = new ButtonGroup();
		bgenero.add(rdbtnHombre);
		bgenero.add(rdbtnMujer);
		
		radioMovil1 = new JRadioButton("Movil");
		radioMovil1.setBounds(306, 99, 65, 23);
		panelApodo.add(radioMovil1);
		
		JRadioButton radioFijo1 = new JRadioButton("Fijo");
		radioFijo1.setBounds(373, 99, 65, 23);
		panelApodo.add(radioFijo1);
		
		ButtonGroup btelefono = new ButtonGroup();
		btelefono.add(radioMovil1);
		btelefono.add(radioFijo1);
		
	//JPANEL AFICIONES(Sin aniadir) - Apodo		
		JPanel panelAficionNoApodo = new JPanel();
		panelAficionNoApodo.setBounds(40, 391, 157, 82);
		panelApodo.add(panelAficionNoApodo);
		panelAficionNoApodo.setMaximumSize(new Dimension(3276, 3276));
		panelAficionNoApodo.setForeground(Color.LIGHT_GRAY);
		panelAficionNoApodo.setLayout(null);
		
		JScrollPane scrollPaneNoAficionApodo = new JScrollPane();
		scrollPaneNoAficionApodo.setBounds(0, 0, 156, 82);
		panelAficionNoApodo.add(scrollPaneNoAficionApodo);
	
		panelAficionNoApodo.setVisible(true);
		list = new JList();
	
		dlmAficionNoApodo = new DefaultListModel();
		list.setModel(dlmAficionNoApodo);
		dlmAficionNoApodo.clear();
		
		alNoAfi = aDAO.imprimirAfi();
		mostrarAficion();
		
		scrollPaneNoAficionApodo.setViewportView(list);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aficionContacto = (Aficion) list.getSelectedValue();
				System.out.println(aficionContacto);
			}
		});		
		
	//JPANEL AFICIONES(Aniadidas) - Apodo	
		JPanel panelAfcionTienePesona = new JPanel();
		panelAfcionTienePesona.setBounds(281, 391, 157, 82);
		panelApodo.add(panelAfcionTienePesona);
		panelAfcionTienePesona.setLayout(null);
		
		JScrollPane scrollPaneTieneApodo = new JScrollPane();
		scrollPaneTieneApodo.setBounds(0, 0, 157, 82);
		panelAfcionTienePesona.add(scrollPaneTieneApodo);
		
		scrollPaneTieneApodo.setVisible(true);
		
		listAficionTieneApodo = new JList();
		
		dlmAficionTieneApodo = new DefaultListModel();
		listAficionTieneApodo.setModel(dlmAficionTieneApodo);
	
		alNoAfi = new ArrayList<Aficion>();
		mostrarAficionInterior();
		
		scrollPaneTieneApodo.setViewportView(listAficionTieneApodo);
		
		listAficionTieneApodo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		listAficionTieneApodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				aficionContacto = (Aficion) listAficionTieneApodo.getSelectedValue();
			}
		});
		
	//JLIST TELEFONO - Apodo		
		dlmTelefonoApodo = new DefaultListModel();
		
		JList listTelefonosApodo = new JList(dlmTelefonoApodo);
		listTelefonosApodo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listTelefonosApodo.setBounds(40, 136, 238, 75);
		
		panelApodo.add(listTelefonosApodo);
		
		listTelefonosApodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tlf = (Telefono) listTelefonosApodo.getSelectedValue();
				
			}
		});
		
		
	//JLIST CORREO - Apodo
		dlmCorreosApodo = new DefaultListModel();
		
		JList listCorreosApodo = new JList(dlmCorreosApodo);
		listCorreosApodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cor = (Correo) listCorreosApodo.getSelectedValue();
			}
		});
		listCorreosApodo.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listCorreosApodo.setBounds(40, 274, 238, 75);
		panelApodo.add(listCorreosApodo);	
		
	// BOTONES  Apodo //
		/// AFICION ///
		JButton btnPonerAficionApodo = new JButton(">>");
		btnPonerAficionApodo.setBounds(209, 394, 60, 23);
		panelApodo.add(btnPonerAficionApodo);
		btnPonerAficionApodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aniadirElemento(aficionContacto);
			}
		});	

		JButton buttonQuitarAficionApodos = new JButton("<<");
		buttonQuitarAficionApodos.setBounds(209, 429, 60, 23);
		panelApodo.add(buttonQuitarAficionApodos);
		buttonQuitarAficionApodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarElemento(aficionContacto);
			}	
		});
		
		
		/// TELEFONO ///
		JButton btnAniadirTelefono = new JButton("+Telefono");
		btnAniadirTelefono.setBounds(291, 135, 127, 23);
		panelApodo.add(btnAniadirTelefono);
		btnAniadirTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tlf = new Telefono (textFieldApodoTelf1.getText(), radioMovil1.isSelected());
				aniadirElemento(tlf);	
			}
		});
		
		JButton btnBorrarTelefono = new JButton("-Telefono");
		btnBorrarTelefono.setBounds(291, 188, 127, 23);
		panelApodo.add(btnBorrarTelefono);
		btnBorrarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarElemento(tlf);
			}
		});
		
		
		/// CORREO ///
		JButton btnAniadirCorreo = new JButton("+Correo");
		btnAniadirCorreo.setBounds(291, 275, 127, 23);
		panelApodo.add(btnAniadirCorreo);
		btnAniadirCorreo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cor = new Correo (textFieldApodoCorreo1.getText());
					aniadirElemento(cor);
			}
		});
		
		JButton btnBorrarCorreo = new JButton("-Correo");
		btnBorrarCorreo.setBounds(291, 329, 127, 23);
		panelApodo.add(btnBorrarCorreo);
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
					nuevaApodo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(187, 583, 127, 23);
		panelApodo.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(326, 583, 112, 23);
		panelApodo.add(btnCancelar);
		
	// VARIACION DE CAMPOS CON MODIFICAR //	
		if (this.id != 0){
			
			aficionContacto = new Aficion("");
			tlf = new Telefono("", null);
			cor = new Correo("");
			
			textFieldApodoApodo.setText(cDAO.imprimirPorId(this.id, ap).getNombre());
			textFieldApodoNotas.setText(cDAO.imprimirPorId(this.id, ap).getNotas());
			textFieldApodoDireccion.setText(cDAO.imprimirPorId(this.id, ap).getDireccion());
			alSiAfi = ((Apodo) cDAO.imprimirPorId(this.id, ap)).getAficion();
			altlf = cDAO.imprimirPorId(this.id, ap).getTelefono();
			alcor = cDAO.imprimirPorId(this.id, ap).getCorreo();

			for(int i = 0; i < alSiAfi.size(); i++){
				aficionContacto = alSiAfi.get(i);
				dlmAficionNoApodo.removeElement(aficionContacto);
			}
			for (int i = 0; i < cDAO.imprimirPorId(this.id, ap).getTelefono().size(); i++){
				dlmTelefonoApodo.addElement(cDAO.imprimirPorId(this.id, ap).getTelefono().get(i));
			}
			for (int i = 0; i < cDAO.imprimirPorId(this.id, ap).getCorreo().size(); i++){
				dlmCorreosApodo.addElement(cDAO.imprimirPorId(this.id, ap).getCorreo().get(i));
			}
		}

		mostrarAficion();
		mostrarAficionInterior();
	}

	
//AÑADE NUEVO APODO A LA AGENDA
	private Boolean nuevaApodo() throws SQLException{
		ContactoDAO cDAO = new ContactoDAO();
		
		if(this.id != 0) {
			ap.setIdentificador(this.id);
		}
		try{
			cDAO.borrarRegistro(ap);
		} catch (Exception e){
			System.out.println("No se ha borrado nada");
		}
		
		
		ap.setNombre(textFieldApodoApodo.getText());
		ap.setApodo(textFieldApodoApodo.getText());
		ap.setGenero(rdbtnMujer.isSelected());
		ap.setDireccion(textFieldApodoDireccion.getText()); 
		ap.setNotas(textFieldApodoNotas.getText());
		ap.setCorreo(alcor);
		ap.setTelefono(altlf);
		ap.setAficion(alSiAfi);

		if (textFieldApodoApodo.getText().compareTo("") == 0){
			JOptionPane.showMessageDialog(null, "Debe introducir un nombre");
			return false;
		}
		if (!rdbtnMujer.isSelected() && !rdbtnHombre.isSelected()){
			JOptionPane.showMessageDialog(null, "No ha seleccionado genero");
			return false;
		}

		cDAO.nuevoRegistro(ap);
		setVisible(false);
		return true;
	}

	
//MUESTRA LISTA DE AFICIONES
	private void mostrarAficion() throws SQLException, ClassNotFoundException{
		
		for (int i = 0; i < alNoAfi.size(); i++){
			dlmAficionNoApodo.addElement(alNoAfi.get(i));
		}
	} 	
//MUESTRA LISTA DE AFICIONES DE APODO		
	private void mostrarAficionInterior(){
		
		for (int i = 0; i < alSiAfi.size(); i++){
				dlmAficionTieneApodo.addElement(alSiAfi.get(i));
		}
	}

	
//PERMITE AÑADIR TELEFONO, CORREO O AFICION AL CONTACTO
	private void aniadirElemento(Object o){	
		if (o instanceof Aficion){
			
			if (!(afiTem.compareTo(aficionContacto))){
				alNoAfi.remove(aficionContacto);
				alSiAfi.add(aficionContacto);
				
				dlmAficionTieneApodo.addElement(aficionContacto);
				dlmAficionNoApodo.removeElement(aficionContacto);
				afiTem = aficionContacto;
			}	
		}
		if (o instanceof Telefono){
			textFieldApodoTelf1.setText("");
			dlmTelefonoApodo.addElement(tlf);
			altlf.add(tlf);	
		}
		
		if (o instanceof Correo){
			
			textFieldApodoCorreo1.setText("");
			dlmCorreosApodo.addElement(cor);
			alcor.add(cor);	
		}
	}
//ELIMINA TELEFONO, CORREO O AFICION DEL CONTACTO	
	private void quitarElemento(Object o){
		if (o instanceof Aficion){

			if (!(afiTem.compareTo(aficionContacto))){
				alSiAfi.remove(aficionContacto);
				alNoAfi.add(aficionContacto);

				dlmAficionNoApodo.addElement(aficionContacto);
				dlmAficionTieneApodo.removeElement(aficionContacto);
				afiTem = aficionContacto;
			}
		}

		if (o instanceof Telefono){
			dlmTelefonoApodo.removeElement(tlf);
			altlf.remove(tlf);
		}

		if (o instanceof Correo){
			dlmCorreosApodo.removeElement(cor);
			alcor.remove(cor);
		}
	}
}
