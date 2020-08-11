package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import clases.Apodo;
import clases.Empresa;
import clases.Persona;

import dao.AficionDAO;
import dao.Conexion;
import dao.ContactoDAO;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Point;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField filtro;
	private final JPanel panelContactos = new JPanel();
	private JPanel contactosPersona;
	private JPanel contactosApodo;
	private JPanel contactosEmpresa;
	private JTable tRpersonas;
	private JTable tRapodos;
	private JTable tRempresas;
	private JButton btnNuevoPersona;
	private JButton btnNuevoApodo;
	private JButton btnNuevaEmpresa;
	
	private VistaAficiones afi;
	private IntroducirModificarPersona btnModPersona;
	private IntroducirModificarApodo btnModApodo;
	private IntroducirModificarEmpresa btnModEmpresa;
	private int contacto;
	private Persona p;
	private Apodo ap;
	private Empresa e;
	private int id;
	
	
	private DefaultTableModel dtmContactoPersona;
	private DefaultTableModel dtmContactoApodo;
	private DefaultTableModel dtmContactoEmpresa;
	
	private ContactoDAO cDAO;
	
	public VentanaPrincipal() throws SQLException, ClassNotFoundException{
		
		cDAO = new ContactoDAO();
		afi = new VistaAficiones();
		id = 0;
		
		principal();
		panelPersona();
		panelApodo();
		panelEmpresa();
		setVisible(true);
		contacto = 0;

	}
	
//PANEL PRINCIPAL	
	public void principal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 417);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setLocation(new Point(2, 2));
		contentPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 417);
		setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnContactos = new JMenu("Contactos");
		menuBar.add(mnContactos);
		
				
		
	//BOTONES PRINCIPALES
		JButton ButtonAficion = new JButton("Aficiones");
		ButtonAficion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				afi.setVisible(true);
			}
		});
		
		menuBar.add(ButtonAficion);
		btnNuevoPersona = new JButton("Nueva Persona");
		btnNuevoPersona.setBounds(72, 308, 141, 20);
		btnNuevoPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				introducirContacto();
			}
		});
		
		btnNuevoApodo = new JButton("Nuevo Apodo");
		btnNuevoApodo.setBounds(72, 308, 141, 20);
		btnNuevoApodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				introducirContacto();
			}
		});
	
		btnNuevaEmpresa = new JButton("Nueva Empresa");
		btnNuevaEmpresa.setBounds(72, 308, 141, 20);
		btnNuevaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				introducirContacto();
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(466, 308, 92, 21);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modificarContacto();
			}
		});
			
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(579, 307, 92, 21);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				p = new Persona(id, "");
				
				try {
					cDAO.borrarRegistro(p);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					updateBuscar("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnFiltrar = new JButton("Filtrar/Actualizar");
		btnFiltrar.setBounds(530, 24, 141, 23);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					updateBuscar(filtro.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	//TEXTOS Y LABELS	
		JLabel etiquetaBusqueda = new JLabel("Buscar: ");
		etiquetaBusqueda.setBounds(109, 28, 69, 14);
		etiquetaBusqueda.setHorizontalAlignment(SwingConstants.RIGHT);

		filtro = new JTextField();
		filtro.setBounds(188, 25, 316, 20);
		filtro.setColumns(10);
		
		JLabel etiquetaComentario = new JLabel("*Busca por nombres, apellidos, numeros o direcciones u otro "
				+ "campo que contenga el contacto");
		etiquetaComentario.setBounds(91, 49, 463, 14);
		etiquetaComentario.setPreferredSize(new Dimension(538, 200));
		etiquetaComentario.setVerticalTextPosition(SwingConstants.TOP);
		etiquetaComentario.setVerticalAlignment(SwingConstants.TOP);
		etiquetaComentario.setHorizontalTextPosition(SwingConstants.LEFT);
		etiquetaComentario.setHorizontalAlignment(SwingConstants.LEFT);
		etiquetaComentario.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		
		
		panelContactos.setBounds(0, 66, 706, 231);
		panelContactos.setLayout(null);
		
		contentPane.add(btnNuevoPersona);
		contentPane.add(btnNuevoApodo);
		contentPane.add(btnNuevaEmpresa);
		
		contentPane.add(btnModificar);
		contentPane.add(btnEliminar);
		contentPane.add(btnFiltrar);
		contentPane.add(etiquetaBusqueda);
		contentPane.add(panelContactos);
		contentPane.add(etiquetaComentario);
		contentPane.add(filtro);	
	}

	
//TABLA CONTACTOS/PERSONA	
	public void panelPersona() throws SQLException{
		contacto = 0;
		
		dtmContactoPersona = new DefaultTableModel();
		dtmContactoPersona.addColumn("id");
		dtmContactoPersona.addColumn("Nombre");
		dtmContactoPersona.addColumn("Apellidos");
		dtmContactoPersona.addColumn("Genero");
		dtmContactoPersona.addColumn("Telefonos");
		dtmContactoPersona.addColumn("Correos");
		dtmContactoPersona.addColumn("Aficiones");
		dtmContactoPersona.addColumn("Notas");
		
		contactosPersona = new JPanel();
		contactosPersona.setBounds(0, 0, 706, 231);
		contactosPersona.setMaximumSize(new Dimension(3276, 3276));
		contactosPersona.setForeground(Color.LIGHT_GRAY);
		contactosPersona.setVisible(true);
		
		tRpersonas = new JTable(dtmContactoPersona);
		tRpersonas.setPreferredScrollableViewportSize(new Dimension(450, 150));
		tRpersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tRpersonas.getColumnModel().getColumn(0).setMaxWidth(0);
		tRpersonas.getColumnModel().getColumn(0).setMinWidth(0);
		tRpersonas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tRpersonas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		contactosPersona.setLayout(null);
		
		JLabel lblNewLabelPersona = new JLabel("Persona");
		lblNewLabelPersona.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabelPersona.setBounds(10, 11, 181, 23);
		lblNewLabelPersona.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelPersona.setBackground(Color.LIGHT_GRAY);
		lblNewLabelPersona.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contactosPersona.add(lblNewLabelPersona);
		
	//SELECCION PERSONAS	
		tRpersonas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				id = (int) tRpersonas.getValueAt(tRpersonas.getSelectedRow(), 0);
			}
		});
			
		JScrollPane scrollPersonas = new JScrollPane(tRpersonas);
		scrollPersonas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPersonas.setBounds(10, 41, 696, 190);
		scrollPersonas.setMaximumSize(new Dimension(327, 327));
		scrollPersonas.setViewportView(tRpersonas);
		contactosPersona.add(scrollPersonas);
		
		panelContactos.add(contactosPersona);
		
		
	//BOTONES PARA CAMIAR TIPO DE CONTACTO	
		JButton btnPersonasEmpresa = new JButton("Empresa");
		btnPersonasEmpresa.setBounds(270, 11, 181, 23);
		contactosPersona.add(btnPersonasEmpresa);
		btnPersonasEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contacto = 2;
				contactosPersona.setVisible(false);
				contactosApodo.setVisible(false);
				contactosEmpresa.setVisible(true);
				btnNuevoPersona.setVisible(false);
				btnNuevoApodo.setVisible(false);
				btnNuevaEmpresa.setVisible(true);			
			}
		});
			
		JButton buttonPersonaApodo = new JButton("Apodo");
		buttonPersonaApodo.setBounds(514, 11, 181, 23);
		contactosPersona.add(buttonPersonaApodo);
		buttonPersonaApodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contacto = 1;
				contactosPersona.setVisible(false);
				contactosApodo.setVisible(true);
				contactosEmpresa.setVisible(false);
				btnNuevoPersona.setVisible(false);
				btnNuevoApodo.setVisible(true);
				btnNuevaEmpresa.setVisible(true);
			}
		});
		
		updateBuscar("");		
	}

	
//TABLA CONTACTOS/APODO
	public void panelApodo() throws SQLException{
		contacto = 1;
		
		dtmContactoApodo = new DefaultTableModel();
		dtmContactoApodo.addColumn("id");
		dtmContactoApodo.addColumn("Apodo");
		dtmContactoApodo.addColumn("Genero");
		dtmContactoApodo.addColumn("Telefonos");
		dtmContactoApodo.addColumn("Correos");
		dtmContactoApodo.addColumn("Aficiones");
		dtmContactoApodo.addColumn("Notas");
		
		contactosApodo = new JPanel();
		contactosApodo.setBounds(0, 0, 706, 231);
		contactosApodo.setMaximumSize(new Dimension(3276, 3276));
		contactosApodo.setForeground(Color.LIGHT_GRAY);
		contactosApodo.setVisible(false);
		contactosApodo.setLayout(null);
		
		tRapodos = new JTable(dtmContactoApodo);
		tRapodos.setPreferredScrollableViewportSize(new Dimension(450, 150));
		tRapodos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tRapodos.getColumnModel().getColumn(0).setMaxWidth(0);
		tRapodos.getColumnModel().getColumn(0).setMinWidth(0);
		tRapodos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tRapodos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		
	//SELECCION DE APODO	
		tRapodos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				id = (int) tRapodos.getValueAt(tRapodos.getSelectedRow(), 0);
			}
		});
	
		
		JScrollPane scrollApodos = new JScrollPane(tRapodos);
		scrollApodos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollApodos.setBounds(10, 41, 696, 190);
		scrollApodos.setMaximumSize(new Dimension(327, 327));
		scrollApodos.setViewportView(tRapodos);
		contactosApodo.setLayout(null);
		contactosApodo.add(scrollApodos);
		
		
		panelContactos.add(contactosApodo);
		
		
	//BOTONES EN APODO	
		JButton btnApodoPersona = new JButton("Persona");
		btnApodoPersona.setBounds(10, 11, 181, 23);
		contactosApodo.add(btnApodoPersona);
		btnApodoPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					contacto = 0;
					contactosPersona.setVisible(true);
					contactosApodo.setVisible(false);
					contactosEmpresa.setVisible(false);
					btnNuevoPersona.setVisible(true);
					btnNuevoApodo.setVisible(false);
					btnNuevaEmpresa.setVisible(true);
			}
		});
	
		JButton btnApodoEmpresa = new JButton("Empresa");
		btnApodoEmpresa.setAutoscrolls(true);
		btnApodoEmpresa.setBounds(270, 11, 181, 23);
		contactosApodo.add(btnApodoEmpresa);
		btnApodoEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					contacto = 2;
					contactosPersona.setVisible(false);
					contactosApodo.setVisible(false);
					contactosEmpresa.setVisible(true);
					btnNuevoPersona.setVisible(false);
					btnNuevoApodo.setVisible(false);
					btnNuevaEmpresa.setVisible(true);
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("Apodo");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(515, 11, 181, 23);
		contactosApodo.add(lblNewLabel);
		
		updateBuscar("");
		
	}

	
//TABLA CONTACTOS/EMPRESA
	public void panelEmpresa() throws SQLException{
		contacto = 2;
		
		dtmContactoEmpresa = new DefaultTableModel();
		dtmContactoEmpresa.addColumn("id");
		dtmContactoEmpresa.addColumn("Nombre");
		dtmContactoEmpresa.addColumn("Telefonos");
		dtmContactoEmpresa.addColumn("Correos");
		dtmContactoEmpresa.addColumn("Notas");
		
		contactosEmpresa = new JPanel();
		contactosEmpresa.setBounds(0, 0, 706, 231);
		contactosEmpresa.setMaximumSize(new Dimension(3276, 3276));
		contactosEmpresa.setForeground(Color.LIGHT_GRAY);
		contactosEmpresa.setVisible(false);
		contactosEmpresa.setLayout(null);
		
		tRempresas = new JTable(dtmContactoEmpresa);
		tRempresas.setPreferredScrollableViewportSize(new Dimension(450, 150));
		tRempresas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tRempresas.getColumnModel().getColumn(0).setMaxWidth(0);
		tRempresas.getColumnModel().getColumn(0).setMinWidth(0);
		tRempresas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tRempresas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresa.setBounds(273, 12, 181, 23);
		contactosEmpresa.add(lblEmpresa);
		
	//SELECION EN LA TABLA DE EMPRESAS	
		tRempresas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				id = (int) tRempresas.getValueAt(tRempresas.getSelectedRow(), 0);
			}
		});
		
		JScrollPane scrollEmpresa = new JScrollPane(tRempresas);
		scrollEmpresa.setForeground(Color.WHITE);
		scrollEmpresa.setBackground(Color.WHITE);
		scrollEmpresa.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollEmpresa.setBounds(10, 41, 696, 190);
		scrollEmpresa.setMaximumSize(new Dimension(327, 327));
		scrollEmpresa.setViewportView(tRempresas);
		contactosEmpresa.setLayout(null);
		contactosEmpresa.add(scrollEmpresa);
		
		panelContactos.add(contactosEmpresa);
		
	//BOTONES EN EMPRESA	
		JButton btnEmpresaApodo = new JButton("Apodo");
		btnEmpresaApodo.setBounds(514, 11, 181, 23);
		contactosEmpresa.add(btnEmpresaApodo);
		btnEmpresaApodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contacto = 1;
				contactosPersona.setVisible(false);
				contactosApodo.setVisible(true);
				contactosEmpresa.setVisible(false);
				btnNuevoPersona.setVisible(false);
				btnNuevoApodo.setVisible(true);
				btnNuevaEmpresa.setVisible(false);
			}
		});
		
		JButton btnEmpresaPersona = new JButton("Persona");
		btnEmpresaPersona.setBounds(10, 11, 181, 23);
		contactosEmpresa.add(btnEmpresaPersona);
		btnEmpresaPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contacto = 0;
				contactosPersona.setVisible(true);
				contactosApodo.setVisible(false);
				contactosEmpresa.setVisible(false);
				btnNuevoPersona.setVisible(true);
				btnNuevoApodo.setVisible(false);
				btnNuevaEmpresa.setVisible(false);
			}
		});
		
		updateBuscar("");

	}

	
//ACTUALIZAR TABLA Y/O FILTRAR RESULTADOS
	private void updateBuscar(String filtro) throws SQLException {

		if (contacto == 0){
			p = new Persona(0, "");
			ArrayList<Persona> lc = (ArrayList<Persona>) cDAO.filtrar(p, filtro);

			dtmContactoPersona.setRowCount(lc.size());
			for (int i = lc.size(); i > 0; i--) {
				dtmContactoPersona.setValueAt(lc.get(i-1).getIdentificador(), i-1, 0);
				dtmContactoPersona.setValueAt(lc.get(i-1).getNombre(), i-1, 1);
				dtmContactoPersona.setValueAt(lc.get(i-1).getApellido(), i-1, 2);
				dtmContactoPersona.setValueAt(lc.get(i-1).getGenero(true), i-1, 3);
				dtmContactoPersona.setValueAt(lc.get(i-1).getTelefono(), i-1, 4);
				dtmContactoPersona.setValueAt(lc.get(i-1).getCorreo(), i-1, 5);
				dtmContactoPersona.setValueAt(lc.get(i-1).getAficion(), i-1, 6);
				dtmContactoPersona.setValueAt(lc.get(i-1).getNotas(), i-1, 7);
			}
		}

		if (contacto == 1){
			ap = new Apodo(0, "");
			ArrayList<Apodo> lc = (ArrayList<Apodo>) cDAO.filtrar(ap, filtro);

			dtmContactoApodo.setRowCount(lc.size());
			for (int i = lc.size(); i > 0; i--) {
				dtmContactoApodo.setValueAt(lc.get(i-1).getIdentificador(), i-1, 0);
				dtmContactoApodo.setValueAt(lc.get(i-1).getNombre(), i-1, 1);
				dtmContactoApodo.setValueAt(lc.get(i-1).getGenero(true), i-1, 2);
				dtmContactoApodo.setValueAt(lc.get(i-1).getTelefono(), i-1, 3);
				dtmContactoApodo.setValueAt(lc.get(i-1).getCorreo(), i-1, 4);
				dtmContactoApodo.setValueAt(lc.get(i-1).getAficion(), i-1, 5);
				dtmContactoApodo.setValueAt(lc.get(i-1).getNotas(), i-1, 6);
			}
		}

		if (contacto == 2){
			e = new Empresa(0, "");
			ArrayList<Empresa> lc = (ArrayList<Empresa>) cDAO.filtrar(e, filtro);

			dtmContactoEmpresa.setRowCount(lc.size());
			for (int i = lc.size(); i > 0; i--) {
				dtmContactoEmpresa.setValueAt(lc.get(i-1).getIdentificador(), i-1, 0);
				dtmContactoEmpresa.setValueAt(lc.get(i-1).getNombre(), i-1, 1);
				dtmContactoEmpresa.setValueAt(lc.get(i-1).getTelefono(), i-1, 2);
				dtmContactoEmpresa.setValueAt(lc.get(i-1).getCorreo(), i-1, 3);
				dtmContactoEmpresa.setValueAt(lc.get(i-1).getNotas(), i-1, 4);

			}
		}
	}
	
//INTRODUCIR/MODIFICAR CONTACTOS
	private void introducirContacto() {
		
		if (contacto == 0){
			try {
				btnModPersona = new IntroducirModificarPersona(0);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnModPersona.setTitle("Persona");
			btnModPersona.setVisible(true);
		}
		else if (contacto == 1){
			try {
				btnModApodo = new IntroducirModificarApodo(0);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnModApodo.setTitle("Apodo");
			btnModApodo.setVisible(true);
		}
		else {
			try {
				btnModEmpresa = new IntroducirModificarEmpresa(0);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnModEmpresa.setTitle("Empresa");
			btnModEmpresa.setVisible(true);	
		}			
	}

//ACCION DE MODIFICAR CUALQUIER TIPO DE CONTACTO	
	private void modificarContacto() {
		if (contacto == 0){
			try {
				btnModPersona = new IntroducirModificarPersona(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnModPersona.setTitle("Persona");
			btnModPersona.setVisible(true);
		}
		else if (contacto == 1){
			try {
				btnModApodo = new IntroducirModificarApodo(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnModApodo.setTitle("Apodo");
			btnModApodo.setVisible(true);
		}
		else {
			try {
				btnModEmpresa = new IntroducirModificarEmpresa(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			btnModEmpresa.setTitle("Empresa");
			btnModEmpresa.setVisible(true);
		}	
	}
}


