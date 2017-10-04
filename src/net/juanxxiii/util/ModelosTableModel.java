package net.juanxxiii.util;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import net.juanxxiii.modelo.GestorVehiculos;
import net.juanxxiii.modelo.Modelo;

/**
 * Clase que implementa la clase TableModelo y permite darle formato a la tabla
 * existente en JPConsulta.
 * 
 * @author Elen Suvarian
 */
public class ModelosTableModel implements TableModel {
	////////////////// Añadimos:
	private ArrayList<Modelo> alModelos = new ArrayList(); // ArrayList que
															// almacena los
															// modelos de la
															// consulta de la
															// BBDD
	private GestorVehiculos gv = new GestorVehiculos(); // Instanciación de la
														// clase
														// GestorVehículos.
	private static final int NUM_COLUMNAS = 6; // Numero de columnas de la tabla

	/**
	 * Constructor de la clase, el cual recibe como parámetro un arrayList de
	 * tipo Modelo, que contiene los modelos a plasmar en la tabla.
	 * 
	 * @param modelos
	 *            ArrayList de tipo Modelo, el cual contiene los modelos a
	 *            agregar a la tabla.
	 */
	public ModelosTableModel(ArrayList<Modelo> modelos) {
		this.alModelos = modelos;
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// Según la columna que reciba el método, retorna una tipo de datos u
		// otro.
		if (columnIndex == 0) {
			return ImageIcon.class; // Para el logo de las marcas
		} else if (columnIndex == 1) {
			return String.class; // Para el nombre de la marca.
		} else if (columnIndex == 2) {
			return String.class; // Para el nombre del modelo.
		} else if (columnIndex == 3) {
			return Float.class; // Para el consumo
		} else if (columnIndex == 4) {
			return Float.class; // Para las emisiones
		} else {
			return ImageIcon.class; // Para la imagen de las eficiencias.
		}
	}

	@Override
	public int getColumnCount() {
		// El método retorna el número total de columnas de la tabla
		return NUM_COLUMNAS;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// Según la columna que reciba el método devuelve el título de la
		// columna
		if (columnIndex == 0) {
			return "LOGO";
		} else if (columnIndex == 1) {
			return "MARCA";
		} else if (columnIndex == 2) {
			return "MODELO";
		} else if (columnIndex == 3) {
			return "CONSUMO (L/100km)";
		} else if (columnIndex == 4) {
			return "EMISIONES (gCO2/km)";
		} else {
			return "EFICIENCIA";
		}
	}

	@Override
	public int getRowCount() {
		// El método devuelve las filas total que tendrá la tabla
		return alModelos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Modelo modelo = alModelos.get(rowIndex); // Instanciamos un objeto de tipo modelo, y recuperamos el modelo según la fila de la tabla
		switch (columnIndex) {
		//Recuperamos del modelo instanciado anteriormente los datos que necesitamos para la tabla según la columna de la tabla:
			case 0:
				
				ImageIcon imagenMarca = new ImageIcon(ModelosTableModel.class
						.getResource("/net/juanxxiii/imagenes/logos/" + modelo.getIdMarca() + ".gif"));
				return imagenMarca;
			case 1:
				return modelo.getMarca();
			case 2:
				return modelo.getNombre();
			case 3:
				return modelo.getConsumo();
			case 4:
				return modelo.getEmisiones();
			case 5:
				ImageIcon imagenEficiencia = new ImageIcon(
						ModelosTableModel.class.getResource("/net/juanxxiii/imagenes/eficiencias/" + modelo.getIcono()));
				return imagenEficiencia;
			default:
				return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	}
}
