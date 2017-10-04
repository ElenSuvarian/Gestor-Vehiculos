package net.juanxxiii.gui;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.juanxxiii.modelo.GestorVehiculos;
import net.juanxxiii.modelo.Marca;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase de tipo JPanel, que contiene los componentes principales para poder
 * agregar modelos.
 * @author Elen Suvarian
 */
public class JPAgrega extends JPanel {
	private JTextField tFModelo; // campo para poder escribir el modelo del
									// veh�culo
	private JTextField tFConsumo; // campo para poder introducir el consumo del
									// veh�culo
	private JTextField tFEmisiones; // campo para poder introducir las emisiones
									// del veh�culo
	private ArrayList<Marca> marcas = new ArrayList(); // ArrayList donde
														// almacenaremos todas
														// las marcas existentes
														// en la BBDD
	private ArrayList<String> eficiencias = new ArrayList();// ArrayList donde
															// almacenaremos
															// todas las
															// eficiencias de la
															// BBDD
	// Instanciaci�n del Gestor de Veh�culos para poder ralizar las consultas e
	// inserciones
	private GestorVehiculos gv = new GestorVehiculos();

	/**
	 * Constructor de la clase, donde se instancian todas los componentes
	 * necesarios para cumplir su funci�n.
	 */
	public JPAgrega() {
		setLayout(null);
		setBounds(100, 100, 800, 600);
		
		// Barra de men� donde est� colocado el bot�n(icono) de "Agregar"
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 61);
		add(menuBar);

		// T�tulo del campor donde introduciremos el modelo que queremos agregar
		JLabel lblModelo = new JLabel("Modelo: ");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModelo.setBounds(37, 173, 84, 43);
		add(lblModelo);
		
		// Campo donde se introducir� el modelo que se quiera agregar
		tFModelo = new JTextField();
		tFModelo.setBounds(209, 173, 462, 33);
		add(tFModelo);
		tFModelo.setColumns(10);
		
		// T�tulo del combobox de marcas
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMarca.setBounds(37, 239, 84, 43);
		add(lblMarca);
		
		// T�tulo del campo donde se introducir� el consumo del veh�culo que se
		// vaya a agrerar
		JLabel lblConsumo = new JLabel("Consumo (L/100Km): ");
		lblConsumo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConsumo.setBounds(37, 304, 162, 43);
		add(lblConsumo);
		
		// T�tulo del campo donde se introducir� las emisiones del veh�culo que
		// se vaya a agrerar
		JLabel lblEmisiones = new JLabel("Emisiones (gCO2/km):");
		lblEmisiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmisiones.setBounds(37, 372, 162, 43);
		add(lblEmisiones);
		
		// T�tulo del combobox de eficiencias
		JLabel lblEficiencia = new JLabel("Eficiencia");
		lblEficiencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEficiencia.setBounds(37, 434, 84, 43);
		add(lblEficiencia);
		
		// Instanciaci�n del combobox de Marcas
		JComboBox cBMarca = new JComboBox();
		cBMarca.setBounds(209, 239, 290, 33);
		add(cBMarca);

		try {
			marcas = gv.getMarcas();// Recogemos las marcas existentes en la
									// BBDD
			for (Marca marca : marcas) {// Recorremos el ArrayList marcas
				cBMarca.addItem(marca.getNombre());// Agregamos el nombre de la
													// marca como elemento del
													// combobox
			}
		} catch (ClassNotFoundException e) {// Fallo que puede producirse por la
											// conexi�n a la BBDD
			JOptionPane.showMessageDialog(JPAgrega.this, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			System.exit(0);// Salimos del programa si se produce un fallo
		} catch (SQLException ex) {// Error de tipo SQL que se puede producir
			switch (ex.getErrorCode()) {
			case 0:// Error que se produce si falla la conexi�n con la BBDD
				JOptionPane.showMessageDialog(JPAgrega.this,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:// Fallo que se produce si la BBDD no existe
				JOptionPane.showMessageDialog(JPAgrega.this,
						"Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044: // Fallo que se produce si el usuario de la BBDD es
						// err�nea
				JOptionPane.showMessageDialog(JPAgrega.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:// Fallo que se produce si la contrase�a de la BBDD es
						// err�nea
				JOptionPane.showMessageDialog(JPAgrega.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:// Cualquier otro fallo que pueda producirse
				JOptionPane.showMessageDialog(JPAgrega.this, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
		}
		// Instanciaci�n del campo donde se escribir� el consumo del veh�culo a
		// agregar
		tFConsumo = new JTextField();
		tFConsumo.setBounds(209, 304, 120, 33);
		add(tFConsumo);
		tFConsumo.setColumns(10);

		// Instanciaci�n del campo donde se introducir� las emisiones del
		// veh�culo a agregar
		tFEmisiones = new JTextField();
		tFEmisiones.setColumns(10);
		tFEmisiones.setBounds(209, 379, 120, 33);
		add(tFEmisiones);

		// Intanciaci�n del combobox de eficiencias
		JComboBox cBEficiencia = new JComboBox();
		cBEficiencia.setBounds(209, 434, 290, 33);
		add(cBEficiencia);

		try {
			eficiencias = gv.getEficiencias(); // Recogemos las eficiencias
												// existentes de la BBDD
			for (String eficiencia : eficiencias) {// recorremos el ArrayList de
													// eficiencias
				cBEficiencia.addItem(eficiencia); // Agregamos el nombre de la
													// eficiencia en el combobox
			}
		} catch (ClassNotFoundException e) {// Fallo que puede producirse por la
											// conexi�n a la BBDD
			JOptionPane.showMessageDialog(JPAgrega.this, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			System.exit(0);// Salimos del programa si se produce un fallo
		} catch (SQLException ex) {// Error de tipo SQL que se puede producir
			switch (ex.getErrorCode()) {
			case 0:// Error que se produce si falla la conexi�n con la BBDD
				JOptionPane.showMessageDialog(JPAgrega.this,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:// Fallo que se produce si la BBDD no existe
				JOptionPane.showMessageDialog(JPAgrega.this,
						"Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044: // Fallo que se produce si el usuario de la BBDD es
						// err�nea
				JOptionPane.showMessageDialog(JPAgrega.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:// Fallo que se produce si la contrase�a de la BBDD es
						// err�nea
				JOptionPane.showMessageDialog(JPAgrega.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:// Cualquier otro fallo que pueda producirse
				JOptionPane.showMessageDialog(JPAgrega.this, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
		}
		// Instanciaci�n del bot�n de agregado
		JButton mnNewMenu = new JButton("");
		mnNewMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {// Cuando se pulsa el
															// bot�n de
															// agregado:
				try {
					if (tFModelo.getText().isEmpty()) { // Si el modelo se ha
														// dejado vac�o:
						JOptionPane.showMessageDialog(JPAgrega.this, "DEBE INTRODUCIR EL MODELO", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (cBMarca.getSelectedItem() == null) {// Si no se
																	// ha
																	// seleccionado
																	// una
																	// marca:
						JOptionPane.showMessageDialog(JPAgrega.this, "DEBE SELECCIONAR LA MARCA", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (tFConsumo.getText().isEmpty()) {// Si el consumo
																// se ha dejado
																// en blanco:
						JOptionPane.showMessageDialog(JPAgrega.this, "DEBE INTRODUCIR EL CONSUMO", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (tFEmisiones.getText().isEmpty()) {// Si las
																	// emisiones
																	// se han
																	// dejado en
																	// blanco
						JOptionPane.showMessageDialog(JPAgrega.this, "DEBE INTRODUCIR LAS EMISIONES", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (cBEficiencia.getSelectedItem() == null) {// Si no
																		// se ha
																		// seleccionado
																		// una
																		// eficiencia
						JOptionPane.showMessageDialog(JPAgrega.this, "DEBE SELECCIONAR LA EFICIENCIA", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {// Si est� todo OK(todos los campos rellenado
							// correctamente) se procedde a agreagr:
						String modelo = tFModelo.getText();// REcogemos el valos
															// del modelo
						String marca = (String) cBMarca.getSelectedItem(); // recogemos
																			// el
																			// valor
																			// de
																			// la
																			// marca
						String consumo = tFConsumo.getText();// recogemos el
																// valor del
																// consumo
						String emisiones = tFEmisiones.getText();// recogemos el
																	// valor de
																	// las
																	// emisiones
						String eficiencia = (String) cBEficiencia.getSelectedItem();// recogemos
																					// el
																					// valor
																					// de
																					// la
																					// eficiencia

						// Llamamos la m�todo del Gestor de Veh�culos que nos
						// permitir� agregar el veh�culo
						gv.addVehiculo(modelo, marca, consumo, emisiones, eficiencia);
						// Di�logo que nos informa del correcto agregado del
						// modelo
						JOptionPane.showMessageDialog(JPAgrega.this, "MODELO AGREGADO");
						// Reestablecemos los campos en blanco
						tFModelo.setText("");
						cBMarca.setSelectedIndex(0);
						tFConsumo.setText("");
						tFEmisiones.setText("");
						cBEficiencia.setSelectedIndex(0);
					}
					// Depu�s de agregar un veh�culo, llamamos al m�todo de la
					// clase JPConsulta que establece el
					// valor m�ximo del Slider al nuevo valor m�ximo de la BBDD
					JPConsulta.setValorMAximoEstatico();

				} catch (ClassNotFoundException e) {// Fallo que puede
													// producirse por la
													// conexi�n a la BBDD
					JOptionPane.showMessageDialog(JPAgrega.this, "Fallo de conexi�n con la Base de Datos", "",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
					System.exit(0);// Salimos del programa si se produce un
									// fallo
				} catch (SQLException ex) {// Error de tipo SQL que se puede
											// producir
					switch (ex.getErrorCode()) {
					case 0:// Error que se produce si falla la conexi�n con la
							// BBDD
						JOptionPane.showMessageDialog(JPAgrega.this,
								"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
						System.exit(0);
						break;
					case 1049:// Fallo que se produce si la BBDD no existe
						JOptionPane.showMessageDialog(JPAgrega.this,
								"Bases de datos inexistente. Error N� " + ex.getErrorCode());
						System.exit(0);
						break;
					case 1044: // Fallo que se produce si el usuario de la BBDD
								// es err�nea
						JOptionPane.showMessageDialog(JPAgrega.this,
								"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
						System.exit(0);
						break;
					case 1045:// Fallo que se produce si la contrase�a de la
								// BBDD es err�nea
						JOptionPane.showMessageDialog(JPAgrega.this, ///////////////////////////////////////////////////////////////////////////
								"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
						System.exit(0);
						break;
					default:// Cualquier otro fallo que pueda producirse
						JOptionPane.showMessageDialog(JPAgrega.this, "ERROR SQL. Error N� " + ex.getErrorCode());
						System.exit(0);
					}
				} catch (NumberFormatException nf) {// Fallo que se produce por
													// la inserci�n de datos
													// num�ricos en campos donde
													// solo admite texto
					JOptionPane.showMessageDialog(JPAgrega.this,
							"Debe introducir datos num�ricos en Consumo y Emisiones");
				}

			}

		});
		// asignaci�n del bot�n de agregado a un Icono
		mnNewMenu.setIcon(new ImageIcon(JPAgrega.class.getResource("/net/juanxxiii/imagenes/iconos/agregar.png")));
		menuBar.add(mnNewMenu);

	}
}
