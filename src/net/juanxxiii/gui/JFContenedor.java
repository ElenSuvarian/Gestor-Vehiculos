package net.juanxxiii.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.juanxxiii.util.GestorProperties;

//import net.juanxxiii.util.GestorProperties;

import java.awt.CardLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase principal, que tiene la funci�n de hacer de contenedor para los
 * JPaneles que componen el programa. Contiene los componentes principales del
 * programa.
 * 
 * @author Elen Suvarian
 * @since 05-2017
 * @version V.1.0
 */
public class JFContenedor extends JFrame {
	private JPanel contentPane; // instanciaci�n del panel principal del JFrame

	/**
	 * Primer m�todo que se ejecuta cuando se inicia el programa.
	 */
	public static void main(String[] args) {
		GestorProperties.crearPropertiesXML(); //Inicializa los Properties del programa
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFContenedor frame = new JFContenedor();// Instanciaci�n del
															// JFrame
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la clase. Contendr� los componentes principales del
	 * programa.
	 */
	public JFContenedor() {
		// Asignaci�n de un icono para la ventana del JFrame
		Image icon = Toolkit.getDefaultToolkit()
				.getImage(getClass().getResource("/net/juanxxiii/imagenes/iconoVentana.png"));
		setIconImage(icon);
		setVisible(true);

		// Asignaci�n de la cruceta del cierre del programa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);// Tama�o de la ventana del JFrame

		// JPanel principal que contendr� los dem�s elementos
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("Gestion de Vehiculos");//T�tulo de la ventana principal
		setResizable(false);//Establecer un tama�o fijo que no se pueda redeimensionar

		// Barra de Men� con JMenuBar
		JMenuBar jMBarra = new JMenuBar();
		setJMenuBar(jMBarra);

		// Cada elemento de la barra de men�
		JMenu jMArchivo = new JMenu("Archivo");
		jMBarra.add(jMArchivo);

		JMenuItem jMISalir = new JMenuItem("Salir");
		jMISalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); //�tem del men� que permite salir del programa
			}
		});
		jMArchivo.add(jMISalir);

		JMenu jMVehiculos = new JMenu("Vehiculos");
		jMBarra.add(jMVehiculos);

		//�tem del men� Veh�culos que nos permite consultar y eliminar modelos
		JMenuItem jMIConsultar = new JMenuItem("Consultar/Eliminar Modelo");
		jMIConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Cuando se pulse en "Consultar/Eliminar Modelo" se ver� JPConsulta
				CardLayout cl = (CardLayout) contentPane.getLayout();
				cl.show(contentPane, "PantallaConsulta");
			}
		});
		jMVehiculos.add(jMIConsultar);
		
		
		JMenuItem jMIAgregar = new JMenuItem("Agregar Modelo");
		jMIAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Cuando se pulse en "Agregar Modelo" se ver� JPAgrega
				CardLayout cl = (CardLayout) contentPane.getLayout();
				cl.show(contentPane, "PantallaAgrega");
			}
		});
		jMVehiculos.add(jMIAgregar);
		//�tem de la barra de men�
		JMenu jMInformacion = new JMenu("Informacion");
		jMBarra.add(jMInformacion);

		//�tem del men� "Informaci�n" que nos proporciona informaci�n sobre el programa y el autor
		JMenuItem jMIInfo = new JMenuItem("Sobre mi...");
		jMIInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Al ser clickado, aparecer� un di�logo con el mensaje:
				JOptionPane.showMessageDialog(JFContenedor.this, "By Elen Suvarian 2017.", "Gestor de Veh�culos",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jMInformacion.add(jMIInfo);
		contentPane.setLayout(null);

		/////////////////////////////// Orden para mostrar las pantallas
		JPPortada portada = new JPPortada();
		portada.setBounds(5, 5, 784, 590);
		contentPane.add(portada);//Panel creado para poner una portada al principio
		JPConsulta consulta = new JPConsulta();
		consulta.setBounds(-10003, -10047, 784, 590);
		contentPane.add(consulta);
		JPAgrega agrega = new JPAgrega();
		agrega.setBounds(-10003, -10047, 784, 590);
		contentPane.add(agrega);
	}

}
