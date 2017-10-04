package net.juanxxiii.gui;

import javax.swing.JPanel;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.juanxxiii.modelo.GestorVehiculos;
import net.juanxxiii.modelo.Marca;
import net.juanxxiii.modelo.Modelo;
import net.juanxxiii.util.ModelosTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

/**
 * Clase de tipo JPanel, que contiene los componentes principales para poder
 * realizar b�squedas de veh�culos, y adem�s poder eliminarlos.
 * 
 * @author Elen Suvarian
 */
public class JPConsulta extends JPanel {
	private JTable jTModelos;// Tabla que contendr� los modelos y sus
								// caracter�sticas
	private GestorVehiculos gv = new GestorVehiculos(); // Instanciaci�n del
														// Gestor de Ve�culos
														// para poder realizar
														// consultas y borrado
														// de la BBDD
	private ArrayList<Modelo> modelos = new ArrayList();// ArrayList donde
														// recogemos los modelos
														// de la consulta
	private ArrayList<Marca> marcas = new ArrayList();// ArrayList donde
														// recogemos las marcas
														// existentes en la BBDD
	private JPanel jPanelModelos; // Panel que ocupa la parte inferior del Panel
									// principal,y al cual se le agregar� el
									// ScrollPane con la tabla de modelos
	private JScrollPane sPaneModelos;// Panel con barra scroll al que se le
										// a�adir� la tabla de modelos
	private static JSlider sConsumo; // Barra desplazadora para poder
										// seleccionar el consumo m�ximo
	private JCheckBox chMarca; // Una de las dos opciones de b�squeda
	private JCheckBox chConsumo; // Otra de las dos opciones de b�squeda
	private JComboBox cBMarcas; // combobox que contiene todas las marcas
								// existentes en la base de datos
	private float consumoMax; // Consumo m�ximo recibido de la BBDD
	private String consumoMaximo; // Consumo m�ximo anterior parseado a String
	private int consumoMx = 0; // Consumo m�ximo anterior parseado a int para
								// poder establecerlo en el Slider

	/**
	 * Constructor de la clase, el cual inicia todos los componentes que son
	 * propios del agregado y borrado de modelos de veh�culos.
	 */
	public JPConsulta() {
		setLayout(null);
		setBounds(100, 100, 800, 600);
		// Barra de men� donde se situan los botones de b�squeda y eliminado
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 800, 56);
		add(menuBar);

		// Checkbox que podra ser tickado para considerar la opci�n el buscado
		chMarca = new JCheckBox("Seleccione para buscar por Marca:");
		chMarca.setSelected(true);
		chMarca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chMarca.setBounds(43, 91, 316, 33);
		add(chMarca);
		//// Checkbox que podra ser tickado para considerar la opci�n el buscado
		chConsumo = new JCheckBox("Seleccione para buscar por Consumo:");
		chConsumo.setSelected(true);
		chConsumo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chConsumo.setBounds(43, 150, 293, 33);
		add(chConsumo);
		// Combobox que contiene todas las marcas de la BBDD
		cBMarcas = new JComboBox();
		cBMarcas.setBounds(384, 93, 288, 33);
		add(cBMarcas);

		try {
			marcas = gv.getMarcas(); // recogemos todas las marcas de la BBDD
			for (Marca marca : marcas) {// recorremos el array de marcas:
				cBMarcas.addItem(marca.getNombre());// Agregamos el nombre de la
													// marca al combobos
			}
		} catch (ClassNotFoundException e1) {// Fallo que puede producirse por
												// la conexi�n a la BBDD
			JOptionPane.showMessageDialog(JPConsulta.this, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e1.printStackTrace();
			System.exit(0);// Salimos del programa si se produce un fallo
		} catch (SQLException ex) {// Error de tipo SQL que se puede producir
			switch (ex.getErrorCode()) {
			case 0:// Error que se produce si falla la conexi�n con la BBDD
				JOptionPane.showMessageDialog(JPConsulta.this,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:// Fallo que se produce si la BBDD no existe
				JOptionPane.showMessageDialog(JPConsulta.this,
						"Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044:// Fallo que se produce si el usuario de la BBDD es err�nea
				JOptionPane.showMessageDialog(JPConsulta.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:// Fallo que se produce si la contrase�a de la BBDD es err�nea
				JOptionPane.showMessageDialog(JPConsulta.this,
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:// Cualquier otro fallo que pueda producirse
				JOptionPane.showMessageDialog(JPConsulta.this, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
		}
		//Cuadro de texto donde se mostrar� el valor de Slider a tiempo real
		JLabel jlConsumo = new JLabel();
		jlConsumo.setBounds(650, 140, 100, 43);
		add(jlConsumo);

		//Asignaci�n de los valores del Slider
		sConsumo = new JSlider(SwingConstants.HORIZONTAL, 0, getMaximoConsumo(), 0);//Para establecer el valor m�ximo del Slider, lo reocgemos del m�todo getMaximoConsumo()
		sConsumo.setPaintLabels(true); 
		sConsumo.setSnapToTicks(true);
		sConsumo.setPaintTicks(true);
		sConsumo.setBounds(384, 151, 257, 45);
		add(sConsumo);
		sConsumo.setMajorTickSpacing(5000);
		sConsumo.setMinorTickSpacing(10);

		//Agregamos un escuchador al Slider para cuando se mueva nos muetre el un JLabel el valor en tiempo real.
		sConsumo.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int valor = sConsumo.getValue(); //Asignamos a la variable el valor del Slider
				jlConsumo.setText(String.valueOf(valor + " ml/100Km")); //convertimos el valor recogido en String y lo mostramos en el JLabel
			}
		});
		//Creaci�n del bot�n buscar
		JButton mBotonBuscar = new JButton("");
		mBotonBuscar.setBackground(SystemColor.control);
		mBotonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {//Cuando se pulsa el bot�n de Buscar:

				if (modelos.size() != 0) {
					jPanelModelos.removeAll(); // Para limpiar el contenido de
												// la tabla cada vez que
												// buscamos
				}
				cargarTabla(); //Llamamos al m�todo que nos cargar� la tabla seg�n los datos seleccionados anteriormente
			}
		});
		//Asignamos la bot�n de busar un icono
		mBotonBuscar.setIcon(new ImageIcon(JPConsulta.class.getResource("/net/juanxxiii/imagenes/iconos/buscar.png")));
		menuBar.add(mBotonBuscar);

		//Creamos el bot�n de Eliminar
		JButton jBEliminar = new JButton("");
		jBEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//Cuando se pula el bot�n de Eliminar:
				if (modelos.size() == 0) { //Si el tama�o del arrayList  recibido de modelos es 0 es que no se ha seleccionado ninguna opcion de b�squeda
					if (jTModelos == null) {//Si la tabla es igual a null, es decir que no se ha relizado ninguna b�squeda de momento:
						JOptionPane.showMessageDialog(JPConsulta.this, "NO HA SELECCIONADO NING�N VEH�CULO", "",
								JOptionPane.WARNING_MESSAGE);
					} else {//Si la tabla tiene modelos de una b�squeda anterior:
						if (jTModelos.getSelectedRow() != -1) {//Si nos devuelve un numero diferente a -1 es porque hay una fila seleccionada de la tabla
							//Preguntamos con un di�logo si est� seguro de borrarlo y lo recogemos en una variable
							int confirmado = JOptionPane.showConfirmDialog(JPConsulta.this,
									"�EST� SEGURO QUE LO QUIERE ELIMINAR?");
							if (JOptionPane.OK_OPTION == confirmado) { //Si es que ha presionado SI:
								eliminarModelo();//Procedemos a Eliminar llamando al metodo eliminarModelo()
							}//Si ha seleccionado NO, no relizamos nada. Igual con la opcion de Cancelar
						} else {//Si nos devuelve un numero igual a -1 es porque no ha seleccionado ninguna fila de la tabla:
							JOptionPane.showMessageDialog(JPConsulta.this, "NO HA SELECCIONADO NING�N VEH�CULO", "",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				} else {//Si el tama�o del ArrayList de modelos no es 0, es porque se ha relizado una b�squeda con resultados
					if (jTModelos.getSelectedRow() == -1) { //Si no se ha seleccionado ninguna fila de la tabla:
						JOptionPane.showMessageDialog(JPConsulta.this, "NO HA SELECCIONADO NING�N VEH�CULO", "",
								JOptionPane.WARNING_MESSAGE);
					} else { //Si se ha seleccionado una fila de la tabla:
						//Preguntamos si est� seguro de borrar
						int confirmado = JOptionPane.showConfirmDialog(JPConsulta.this,
								"�EST� SEGURO QUE LO QUIERE ELIMINAR?");

						if (JOptionPane.OK_OPTION == confirmado) {
							eliminarModelo();//Borramos finalmente

						}
					}
				}
				//Actualizamos el valor m�ximo del Slider despu�s del borrado.ya que podr�a haber sido el valor m�ximo
				sConsumo.setMaximum(getMaximoConsumo());
			}

		});
		//Asignamos un icono al bot�n de eliminar:
		jBEliminar.setBackground(SystemColor.control);
		jBEliminar.setIcon(new ImageIcon(JPConsulta.class.getResource("/net/juanxxiii/imagenes/iconos/borrar.png")));
		menuBar.add(jBEliminar);

	}

	/**
	 * M�todo que cambia el cursor actual al cursor Flecha habitual.
	 */
	public void cursorNormal() {
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		setCursor(cursor);
	}
	/**
	 * M�todo que cambia el cursor actual al cursor de espera (Reloj de arena).
	 */
	public void cursorEspera() {
		Cursor cursor = new Cursor(Cursor.WAIT_CURSOR);
		setCursor(cursor);
	}
	/**
	 * M�todo est�tico que permite asignarle el nuevo valor m�ximo al Slider
	 * (Utilizado por Clases externas)
	 */
	public static void setValorMAximoEstatico() {
		sConsumo.setMaximum(maxConsumEstatico());
	}
	/**
	 * M�todo para consultar de la BBDD el valor m�ximo del consumo de los veh�culos
	 * (Utilizado por clases externas)
	 * @return el valor m�ximo del consumo de la BBDD
	 */
	public static int maxConsumEstatico() {
		int maximo = 0; //variable para recoger el consumo m�ximo
		try {
			GestorVehiculos gv = new GestorVehiculos(); //Utilizamos el Gestor de Veh�culos como intermediario con la BBDD
			String consumoMaximo = gv.getConsumoMax(); //recogemos el consumo m�ximo 
			float consumoMax = Float.parseFloat(consumoMaximo); //parseamo el consumo a float
			maximo = (int) (consumoMax * 1000); //Lo multiplicamos por 100 y lo convertimos en int para poder usarlo en el Slider
			
/********************* Tratamos los errores que se puedan ocasionar como en las anteriores ocasiones****************/		
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e1.printStackTrace();
			System.exit(0);
		} catch (SQLException ex) {
			switch (ex.getErrorCode()) {
			case 0:
				JOptionPane.showMessageDialog(null,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:
				JOptionPane.showMessageDialog(null, "Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044:
				JOptionPane.showMessageDialog(null, "USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:
				JOptionPane.showMessageDialog(null,
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
		}
/*********************************************************************************************************************************/		
		return maximo;//Devolvemos el valor que hemos obtenido del consumo m�ximo
	}

	/**
	 * M�todo para consultar de la BBDD el valor m�ximo del consumo de los veh�culos.
	 * (Utilizado por la propia clase)
	 * @return el valor m�ximo del consumo de la BBDD
	 */
	public int getMaximoConsumo() {
		try {
			consumoMaximo = gv.getConsumoMax(); //recogemos el valor m�ximo de la BBDD
			consumoMax = Float.parseFloat(consumoMaximo); //lo parseamos a float
			consumoMx = (int) (consumoMax * 1000); //lo multiplicamos por 100 y lo parseamos a int para poder utilizarlo en el Slider
			
/********************* Tratamos los errores que se puedan ocasionar como en las anteriores ocasiones****************/		
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(JPConsulta.this, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			e1.printStackTrace();
			System.exit(0);
		} catch (SQLException ex) {
			switch (ex.getErrorCode()) {
			case 0:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:
				JOptionPane.showMessageDialog(JPConsulta.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(JPConsulta.this, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
		}
/*********************************************************************************************************************************/		
		return consumoMx; //Devolvemos el valor del consumo m�ximo que hemos obtenido 
	}
	
	/**
	 * M�todo que nos crea una Tabla con los datos de los veh�culos dependiendo de los criterios de b�squeda realizada.
	 */
	public void cargarTabla() {
		int cantidadModelosDevueltos = 0; //variable que recoge la cantidad de registros seg�n la b�squeda
		cursorEspera();//Llamamos al m�todo que cambia el cursor por el cursor de espera
		if (modelos.size() != 0) {//Si la tabla ya estaba llena con datos:
			jPanelModelos.removeAll(); // Elimina todo componente que lo
										// componga(Tabla con datos de la
										// anterior b�squeda)
			jTModelos.removeAll();//elimina los datos de la tabla
		}

/********************* Tratamos los errores que se puedan ocasionar como en las anteriores ocasiones****************/	
		try {
			int consumo = sConsumo.getValue(); //Recogemos el consumo del Slider
			String marca = (String) cBMarcas.getSelectedItem();//Recogemos la marca seleccionada
			int idMarca = gv.getIDMarca(marca); //recogemos la id de la marca seleccionada

			//si est� seleccionada la b�squeda solo por marca:
			if (chMarca.isSelected() == true && chConsumo.isSelected() == false) {
				modelos = gv.getModelos(idMarca);
				cantidadModelosDevueltos = gv.getNumModelos(idMarca);
			//si est� seleccionada la b�squeda solo por consumo:
			} else if (chMarca.isSelected() == false && chConsumo.isSelected() == true) {
				modelos = gv.getModelos(consumo / 1000f);
				cantidadModelosDevueltos = gv.getNumModelos(consumo / 1000f);
			//si est� seleccionada la b�squeda por marca y consumo:
			} else if (chMarca.isSelected() == true && chConsumo.isSelected() == true) {
				modelos = gv.getModelos(idMarca, consumo / 1000f);
				cantidadModelosDevueltos = gv.getNumModelos(idMarca, consumo / 1000f);
			//Si no est� seleccionada ninguna opci�n:
			} else {
				JOptionPane.showMessageDialog(JPConsulta.this, "SELECCIONE UNA OPCI�N PARA BUSCAR");
			}

		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(JPConsulta.this, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
			System.exit(0);
		} catch (SQLException ex) {
			switch (ex.getErrorCode()) {
			case 0:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:
				JOptionPane.showMessageDialog(JPConsulta.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(JPConsulta.this, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
/*********************************************************************************************************************************/				
		} finally {
			cursorNormal();//Finalmente volver a establecer el vursor al cursor Normal
		}

		if (modelos.size() == 0) {//Si la consulta no devuelve ning�n modelo:
			JOptionPane.showMessageDialog(JPConsulta.this, "NO EXISTEN VEH�CULOS CON LOS DATOS SELECCIONADOS");
		} else {//Si la consulta nos devuelve datos:
			//Creamos el Panel que contendr� la tabla
			jPanelModelos = new JPanel();
			jPanelModelos.setBounds(10, 246, 780, 343);
			add(jPanelModelos);
			jPanelModelos.setLayout(null);
			//Creamos el Panel con scroll que contendr� la tabla
			sPaneModelos = new JScrollPane();
			sPaneModelos.setBounds(10, 11, 760, 321);
			jPanelModelos.add(sPaneModelos);
			//Cramos la tabla que contendr� los modelos
			jTModelos = new JTable();
			sPaneModelos.setViewportView(jTModelos);
			jTModelos.setModel(new ModelosTableModel(modelos));
			jTModelos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			//Le damos tama�os a los campos de la tabla
			jTModelos.getColumnModel().getColumn(0).setPreferredWidth(70);
			jTModelos.setRowHeight(50);
			jTModelos.getColumnModel().getColumn(1).setPreferredWidth(100);
			jTModelos.getColumnModel().getColumn(2).setPreferredWidth(350);
			jTModelos.getColumnModel().getColumn(5).setPreferredWidth(100);
			jTModelos.getColumnModel().getColumn(3).setPreferredWidth(5);
			jTModelos.getColumnModel().getColumn(4).setPreferredWidth(5);

			//Si la consulta nos devuelve m�s de 100 resultados, emitimos un mensaje:
			if (cantidadModelosDevueltos >= 100) {
				JOptionPane.showMessageDialog(null, "PRIMEROS 100 RESULTADOS, REFINA SU B�SQUEDA",
						"NUMERO DE RESULTADOS", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	/**
	 * M�todo que recoge la fila seleccionada de la tabla de modelos y lo elimina de la BBDD.
	 */
	public void eliminarModelo() {
		int filaSeleccionada = jTModelos.getSelectedRow(); //Numero de Fila seleccionada de la tabla
		String modelo = (String) jTModelos.getValueAt(filaSeleccionada, 2); //asignamos el objeto de la fila seleccionada a un objeto Modelo
		try {
			gv.eliminarModelo(modelo); //Procedemos a eliminar el modelo de la BBDD
			
/********************* Tratamos los errores que se puedan ocasionar como en las anteriores ocasiones****************/			
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(JPConsulta.this, "Fallo de conexi�n con la Base de Datos", "",
					JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
			System.exit(0);
		} catch (SQLException ex) {
			switch (ex.getErrorCode()) {
			case 0:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"ERROR EN LA CONEXI�N CON EL SERVIDOR. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1049:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"Bases de datos inexistente. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1044:
				JOptionPane.showMessageDialog(JPConsulta.this,
						"USUARIO DE BASES DE DATOS ERRONEO. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			case 1045:
				JOptionPane.showMessageDialog(JPConsulta.this, ///////////////////////////////////////////////////////////////////////////
						"CONTRASE�A DE BASES DE DATOS ERRONEA. Error N� " + ex.getErrorCode());
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(JPConsulta.this, "ERROR SQL. Error N� " + ex.getErrorCode());
				System.exit(0);
			}
		}
/*********************************************************************************************************************************/	
		JOptionPane.showMessageDialog(JPConsulta.this, "MODELO ELIMINADO"); //Al eliminar le modelo emitimos un mesnaje de confirmaci�n	
		cargarTabla(); //Volvemos a cargar la tabla con los datos nuevos de la BBDD
	}
}
