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
									// vehículo
	private JTextField tFConsumo; // campo para poder introducir el consumo del
									// vehículo
	private JTextField tFEmisiones; // campo para poder introducir las emisiones
									// del vehículo
	private ArrayList<Marca> marcas = new ArrayList(); // ArrayList donde
														// almacenaremos todas
														// las marcas existentes
														// en la BBDD
	private ArrayList<String> eficiencias = new ArrayList();// ArrayList donde
															// almacenaremos
															// todas las
															// eficiencias de la
															// BBDD
	// Instanciación del Gestor de Vehículos para poder ralizar las consultas e
	// inserciones
	private GestorVehiculos gv = new GestorVehiculos();

	/**
	 * Constructor de la clase, donde se instancian todas los componentes
	 * necesarios para cumplir su función.
	 */
	public JPAgrega() {
		setLayout(null);
		setBounds(100, 100, 800, 600);
		
		// Barra de menú donde está colocado el botón(icono) de "Agregar"
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 61);
		add(menuBar);

		// Título del campor donde introduciremos el modelo que queremos agregar
		JLabel lblModelo = new JLabel("Modelo: ");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblModelo.setBounds(37, 173, 84, 43);
		add(lblModelo);
		
		// Campo donde se introducirá el modelo que se quiera agregar
		tFModelo = new JTextField();
		tFModelo.setBounds(209, 173, 462, 33);
		add(tFModelo);
		tFModelo.setColumns(10);
		
		// Título del combobox de marcas
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMarca.setBounds(37, 239, 84, 43);
		add(lblMarca);
		
		// Título del campo donde se introducirá el consumo del vehóculo que se
		// vaya a agrerar
		JLabel lblConsumo = new JLabel("Consumo (L/100Km): ");
		lblConsumo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConsumo.setBounds(37, 304, 162, 43);
		add(lblConsumo);
		
		// Título del campo donde se introducirá las emisiones del vehóculo que
		// se vaya a agrerar
		JLabel lblEmisiones = new JLabel("Emisiones (gCO2/km):");
		lblEmisiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmisiones.setBounds(37, 372, 162, 43);
		add(lblEmisiones);
		
		// Título del combobox de eficiencias
		JLabel lblEficiencia = new JLabel("Eficiencia");
		lblEficiencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEficiencia.setBounds(37, 434, 84, 43);
		add(lblEficiencia);
		
		// Instanciación del combobox de Marcas
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
											// conexión a la BBDD
			JOptionPane.showMessageDialog(JPAgrega.this, "Fallo de conexión con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			System.exit(0);// Salimos del programa si se produce un fallo
		} catch (SQLException ex) {// Error de tipo SQL que se puede producir
			switch (ex.getErrorCode()) {
			case 0:// Error que se produce si falla la conexión con la BBDD
				JOptionPane.showMessageDialog(JPAgrega.this,
						"ERROR EN LA CONEXIÓN CON EL SERVIDOR. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:// Fallo que se produce si la BBDD no existe
				JOptionPane.showMessageDialog(JPAgrega.this,
						"Bases de datos inexistente. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044: // Fallo que se produce si el usuario de la BBDD es
						// errónea
				JOptionPane.showMessageDialog(JPAgrega.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:// Fallo que se produce si la contraseña de la BBDD es
						// errónea
				JOptionPane.showMessageDialog(JPAgrega.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASEÑA DE BASES DE DATOS ERRONEA. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			default:// Cualquier otro fallo que pueda producirse
				JOptionPane.showMessageDialog(JPAgrega.this, "ERROR SQL. Error Nº " + ex.getErrorCode());
				System.exit(0);
			}
		}
		// Instanciación del campo donde se escribirá el consumo del vehículo a
		// agregar
		tFConsumo = new JTextField();
		tFConsumo.setBounds(209, 304, 120, 33);
		add(tFConsumo);
		tFConsumo.setColumns(10);

		// Instanciación del campo donde se introducirá las emisiones del
		// vehículo a agregar
		tFEmisiones = new JTextField();
		tFEmisiones.setColumns(10);
		tFEmisiones.setBounds(209, 379, 120, 33);
		add(tFEmisiones);

		// Intanciación del combobox de eficiencias
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
											// conexión a la BBDD
			JOptionPane.showMessageDialog(JPAgrega.this, "Fallo de conexión con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
			System.exit(0);// Salimos del programa si se produce un fallo
		} catch (SQLException ex) {// Error de tipo SQL que se puede producir
			switch (ex.getErrorCode()) {
			case 0:// Error que se produce si falla la conexión con la BBDD
				JOptionPane.showMessageDialog(JPAgrega.this,
						"ERROR EN LA CONEXIÓN CON EL SERVIDOR. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:// Fallo que se produce si la BBDD no existe
				JOptionPane.showMessageDialog(JPAgrega.this,
						"Bases de datos inexistente. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044: // Fallo que se produce si el usuario de la BBDD es
						// errónea
				JOptionPane.showMessageDialog(JPAgrega.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:// Fallo que se produce si la contraseña de la BBDD es
						// errónea
				JOptionPane.showMessageDialog(JPAgrega.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASEÑA DE BASES DE DATOS ERRONEA. Error Nº " + ex.getErrorCode());
				System.exit(0);
				break;
			default:// Cualquier otro fallo que pueda producirse
				JOptionPane.showMessageDialog(JPAgrega.this, "ERROR SQL. Error Nº " + ex.getErrorCode());
				System.exit(0);
			}
		}
		// Instanciación del botón de agregado
		JButton mnNewMenu = new JButton("");
		mnNewMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {// Cuando se pulsa el
															// botón de
															// agregado:
				try {
					if (tFModelo.getText().isEmpty()) { // Si el modelo se ha
														// dejado vacío:
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
					} else {// Si está todo OK(todos los campos rellenado
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

						// Llamamos la método del Gestor de Vehículos que nos
						// permitirá agregar el vehículo
						gv.addVehiculo(modelo, marca, consumo, emisiones, eficiencia);
						// Diálogo que nos informa del correcto agregado del
						// modelo
						JOptionPane.showMessageDialog(JPAgrega.this, "MODELO AGREGADO");
						// Reestablecemos los campos en blanco
						tFModelo.setText("");
						cBMarca.setSelectedIndex(0);
						tFConsumo.setText("");
						tFEmisiones.setText("");
						cBEficiencia.setSelectedIndex(0);
					}
					// Depués de agregar un vehículo, llamamos al método de la
					// clase JPConsulta que establece el
					// valor máximo del Slider al nuevo valor máximo de la BBDD
					JPConsulta.setValorMAximoEstatico();

				} catch (ClassNotFoundException e) {// Fallo que puede
													// producirse por la
													// conexión a la BBDD
					JOptionPane.showMessageDialog(JPAgrega.this, "Fallo de conexión con la Base de Datos", "",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
					System.exit(0);// Salimos del programa si se produce un
									// fallo
				} catch (SQLException ex) {// Error de tipo SQL que se puede
											// producir
					switch (ex.getErrorCode()) {
					case 0:// Error que se produce si falla la conexión con la
							// BBDD
						JOptionPane.showMessageDialog(JPAgrega.this,
								"ERROR EN LA CONEXIÓN CON EL SERVIDOR. Error Nº " + ex.getErrorCode());
						System.exit(0);
						break;
					case 1049:// Fallo que se produce si la BBDD no existe
						JOptionPane.showMessageDialog(JPAgrega.this,
								"Bases de datos inexistente. Error Nº " + ex.getErrorCode());
						System.exit(0);
						break;
					case 1044: // Fallo que se produce si el usuario de la BBDD
								// es errónea
						JOptionPane.showMessageDialog(JPAgrega.this,
								"USUARIO DE BASES DE DATOS ERRONEO. Error Nº " + ex.getErrorCode());
						System.exit(0);
						break;
					case 1045:// Fallo que se produce si la contraseña de la
								// BBDD es errónea
						JOptionPane.showMessageDialog(JPAgrega.this, ///////////////////////////////////////////////////////////////////////////
								"CONTRASEÑA DE BASES DE DATOS ERRONEA. Error Nº " + ex.getErrorCode());
						System.exit(0);
						break;
					default:// Cualquier otro fallo que pueda producirse
						JOptionPane.showMessageDialog(JPAgrega.this, "ERROR SQL. Error Nº " + ex.getErrorCode());
						System.exit(0);
					}
				} catch (NumberFormatException nf) {// Fallo que se produce por
													// la inserción de datos
													// numéricos en campos donde
													// solo admite texto
					JOptionPane.showMessageDialog(JPAgrega.this,
							"Debe introducir datos numéricos en Consumo y Emisiones");
				}

			}

		});
		// asignación del botón de agregado a un Icono
		mnNewMenu.setIcon(new ImageIcon(JPAgrega.class.getResource("/net/juanxxiii/imagenes/iconos/agregar.png")));
		menuBar.add(mnNewMenu);

	}
}
