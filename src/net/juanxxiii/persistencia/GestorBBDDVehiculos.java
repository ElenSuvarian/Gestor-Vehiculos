package net.juanxxiii.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.juanxxiii.modelo.Marca;
import net.juanxxiii.modelo.Modelo;

/**
 * Clase que extiende de la clase GestorBBDD. Contiene los métodos apropiados
 * para la gestión de alta, eliminación y amplia recuperación de datos sobre
 * Vehículos.
 * 
 * @author Elen Suvarian
 */
public class GestorBBDDVehiculos extends GestorBBDD {
	private static final String TABLA_MODELOS = "modelos"; // Nombre de la tabla
															// que hace
															// referencia a la
															// tabla Modelos de
															// la BBDD.
	private static final String TABLA_MARCAS = "marcas"; // Nombre de la tabla
															// que hace
															// referencia a la
															// tabla Marcas de
															// la BBDD.
	private static final String TABLA_EFICIENCIAS = "eficiencias"; // Nombre de
																	// la tabla
																	// que hace
																	// referencia
																	// a la
																	// tabla
																	// Eficiencias
																	// de la
																	// BBDD.

	/**
	 * Constructor de la clase que al heredar de la clase GestorBBDD, recibe los
	 * datos y los asigna a su instanciación. No incluye el puerto, ya que
	 * utiliza el de por defecto(3360).
	 * 
	 * @param usuario
	 *            Usuario de la BBDD.
	 * @param password
	 *            Contraseña de la BBDD.
	 * @param ip
	 *            IP de la BBDD.
	 * @param nombreBBDD
	 *            Nombre de la BBDD.
	 */
	public GestorBBDDVehiculos(String usuario, String password, String ip, String nombreBBDD) {
		super(usuario, password, ip, nombreBBDD);
	}

	/**
	 * Constructor de la clase que al heredar de la clase GestorBBDD, recibe los
	 * datos y los asigna a su instanciación. Recibe el puerto de conexión como
	 * parámetro.
	 * 
	 * @param usuario
	 *            Usuario de la BBDD.
	 * @param password
	 *            Contraseña de la BBDD.
	 * @param ip
	 *            IP de la BBDD.
	 * @param nombreBBDD
	 *            Nombre de la BBDD.
	 * @param puerto
	 *            Puerto de la BBDD.
	 */
	public GestorBBDDVehiculos(String usuario, String password, String ip, String nombreBBDD, int puerto) {
		super(usuario, password, ip, nombreBBDD, puerto);
	}

	/**
	 * Método que recibe un objeto de tipo Modelo y agrega sus datos a la Base
	 * de Datos.
	 * 
	 * @param modelo1
	 *            Objeto de tipo Modelo que contiene los datos de un modelo a
	 *            agregar.
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public void insertarModelo(Modelo modelo1) throws SQLException, ClassNotFoundException {
		establecerConexion();
		//Sentencia SQL:
		String sql = "INSERT INTO " + TABLA_MODELOS + " (ID_MARCA,MODELO,CONSUMO,EMISIONES,C_ENERGETICA) VALUES ('"
				+ modelo1.getIdMarca() + "','" + modelo1.getNombre() + "'," + modelo1.getConsumo() + ","
				+ modelo1.getEmisiones() + ",'" + modelo1.getIdEficiencia() + "')";
		Statement st = conexion.createStatement();
		st.executeUpdate(sql);
		st.close();
		cerrarConexion();
	}

	/**
	 * Método que recibe el ID de un modelo ,y elimina ese modelo de la Base de Datos.
	 * @param idModelo ID del modelo a eliminar.
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public void borrarModelo(int idModelo) throws SQLException, ClassNotFoundException {
		establecerConexion();
		//Sentencia SQL:
		String sql = "DELETE FROM " + TABLA_MODELOS + " WHERE id=" + idModelo;
		Statement st = conexion.createStatement();
		st.executeUpdate(sql);
		st.close();
		cerrarConexion();
	}

	/**
	 * Método que recibe el nombre de un modelo y proporciona el ID de ese modelo.
	 * @param modelo Nombre del modelo.
	 * @return ID del modelo recuperado.
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public int getModelo(String modelo) throws SQLException, ClassNotFoundException {
		establecerConexion();		
		int idModelo = 0; // ID del modelo a recuperar, inicializado a 0 ya que no existe ni existirá ningun modelo con ese ID.
		//Sentencia SQL:
		String sql = "Select id FROM " + TABLA_MODELOS + " WHERE MODELO like '%" + modelo + "%'";
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery(sql);
		//Recorremos los resultados obtenidos de la consulta.
		while (rs.next() == true) {
			idModelo = rs.getInt("ID"); //Recogemos el ID en IdModelo
		}
		st.close();
		cerrarConexion();
		return idModelo;
	}

	/**
	 * Método que obtiene todas las marcas existentes de la Base de Datos.
	 * @return ArrayList de tipo Marca que contiene todas las marcas existentes en la Base de Datos.
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public ArrayList<Marca> getMarcas() throws SQLException, ClassNotFoundException {
		establecerConexion();		
		ArrayList<Marca> marcas = new ArrayList();
		//Sentencia SQL:
		String sql = "SELECT MARCA FROM " + TABLA_MARCAS +" order by MARCA";
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados del select anterior
		while (rs.next() == true) {
			Marca m = new Marca(rs.getString("MARCA")); //Recogemos la marca y lo instanciamos
			marcas.add(m);//lo agregamos al arrayList
		}
		rs.close();
		cerrarConexion();
		return marcas;
	}

	/**
	 * Método que Recupera todas las eficiencias existentes de la Base de Datos.
	 * @return 
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public ArrayList<String> getEficiencia() throws SQLException, ClassNotFoundException {
		establecerConexion();
		ArrayList<String> eficiencias = new ArrayList();
		//Sentencia SQL
		String sql = "SELECT DESCRIPCION FROM " + TABLA_EFICIENCIAS;
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados de la consulta anterior.
		while (rs.next() == true) {
			String idEfi = rs.getString("DESCRIPCION"); //Recogemos la eficiencia 
			eficiencias.add(idEfi);// y lo añadimos al ArrayList
		}
		cerrarConexion();
		return eficiencias;
	}

	/**
	 * Método que recibe el nombre de una marca y recupera su ID de la Base de Datos.
	 * @param marca
	 * @return
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public int getIDMarca(String marca) throws SQLException, ClassNotFoundException {
		establecerConexion();
		int idMarca = 0;
		//Sentencia SQL
		String sql = "SELECT ID FROM " + TABLA_MARCAS + " where MARCA like ?";
		PreparedStatement ps = conexion.prepareStatement(sql);
		ps.setString(1, marca);
		ResultSet rs = ps.executeQuery();
		//Recorremos los resultados de la consulta anterior
		while (rs.next() == true) {
			idMarca = rs.getInt("ID"); //Recogemos el ID de la marca
		}
		cerrarConexion();
		return idMarca;
	}

	/**
	 * Método que recibe el nombre de una eficiencia y recupera su ID de la Base de Datos.
	 * @param eficiencia
	 * @return ID de la eficiencia
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public String getIDEficiencia(String eficiencia) throws SQLException, ClassNotFoundException {
		establecerConexion();
		String idEficiencia = null;
		//Sentencia SQL.
		String sql = "SELECT C_ENERGETICA FROM " + TABLA_EFICIENCIAS + " where DESCRIPCION like ?";
		PreparedStatement ps = conexion.prepareStatement(sql);
		ps.setString(1, "%" + eficiencia + "%");
		ResultSet rs = ps.executeQuery();
		//Recorremos los resultados de la consulta anterior.
		while (rs.next() == true) {
			idEficiencia = rs.getString("C_ENERGETICA"); //Recogemos el Id de la eficiencia.
		}
		cerrarConexion();
		return idEficiencia;
	}

	/**
	 * Método que recibe una marca y recupera de la Base de Datos todos aquellos modelos que sean de esa marca.
	 * @param marca Id de la marca
	 * @return ArrayList de tipo Modelo que contiene los modelos de una marca concreta.
	 * @throws SQLException
	 *             Error de tipo SQL que puede producirse al hacer alguna
	 *             operación en la BBDD.
	 * @throws ClassNotFoundException
	 *             Error que puede producirse por intentar extablecer la
	 *             conexión con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(int marca) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> modelos = null;
		establecerConexion();
		modelos = new ArrayList();
		//Sentencia SQL:
		String sql = "select modelo,consumo,emisiones,marca,icono,id_marca from " + TABLA_MARCAS + "," + TABLA_MODELOS
				+ "," + TABLA_EFICIENCIAS
				+ " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  marcas.id =" + marca
				+ " order by consumo limit 100";
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados obtenidos de la consulta anterior.
		while (rs.next() == true) {
			Modelo modelo = new Modelo(rs.getString("marca"), rs.getString("icono"), rs.getString("modelo"),
					Float.parseFloat(rs.getString("consumo")), Float.parseFloat(rs.getString("emisiones")),
					rs.getInt("MODELOS.ID_MARCA"));
			modelos.add(modelo);
		}
		rs.close();
		cerrarConexion();
		return modelos;
	}
	/**
	 * Método que recibe un consumo y recupera de la Base de Datos todos aquellos modelos que tengan ese consumo.
	 * @param consumo Consumo del modelo.
	 * @return ArrayList de tipo Modelo que contiene los modelos de un cosnumo concreto.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(float consumo) throws SQLException, ClassNotFoundException {
		establecerConexion();
		ArrayList<Modelo> modelos = new ArrayList();
		//Sentencia SQL:
		String sql = "select modelo,consumo,emisiones,marca,icono,id_marca from " + TABLA_MARCAS + "," + TABLA_MODELOS
				+ "," + TABLA_EFICIENCIAS
				+ " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  consumo<=" + consumo
				+ " order by consumo limit 100";
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados obtenidos anteriormente
		while (rs.next() == true) {
			Modelo modelo = new Modelo(rs.getString("marca"), rs.getString("icono"), rs.getString("modelo"),
					Float.parseFloat(rs.getString("consumo")), Float.parseFloat(rs.getString("emisiones")),
					rs.getInt("MODELOS.ID_MARCA"));
			modelos.add(modelo);
		}
		rs.close();
		cerrarConexion();
		return modelos;
	}
	/**
	 * Método que recibe una marca y un consumo y recupera de la Base de Datos todos aquellos modelos que sean de esa marca
	 * y tengas ese consumo.
	 * @param marca ID de la marca.
	 * @param consumo Consumo del modelo.
	 * @return ArrayList de tipo modelo que contiene los modelos de un consumo y marca concreta.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(int marca, float consumo) throws SQLException, ClassNotFoundException {
		establecerConexion();
		ArrayList<Modelo> modelos = new ArrayList();
		//Sentencia SQL
		String sql = "select modelo,consumo,emisiones,marca,icono,id_marca from " + TABLA_MARCAS + "," + TABLA_MODELOS
				+ "," + TABLA_EFICIENCIAS
				+ " where eficiencias.C_ENERGETICA=modelos.C_ENERGETICA and ID_MARCA=marcas.ID and  marcas.id =" + marca
				+ " and consumo<=" + consumo + " order by consumo limit 100";
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados obtenidos de la consulta anterior.
		while (rs.next() == true) {
			Modelo modelo = new Modelo(rs.getString("marca"), rs.getString("icono"), rs.getString("modelo"),
					Float.parseFloat(rs.getString("consumo")), Float.parseFloat(rs.getString("emisiones")),
					rs.getInt("MODELOS.ID_MARCA"));
			modelos.add(modelo);
		}
		rs.close();
		cerrarConexion();
		return modelos;
	}
	/**
	 * Método que recupera el consumo máximo de los modelos de la Base de Datos.
	 * @return Consumo máximo
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public String getConsumoMax() throws ClassNotFoundException, SQLException {
		establecerConexion();
		String consumoMax = null;
		//Sentencia SQL:
		String sql = "SELECT max(consumo) FROM " + TABLA_MODELOS;
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados obtenidos de la consulta anterior.
		while (rs.next() == true) {
			consumoMax = rs.getString("max(CONSUMO)");
		}
		rs.close();
		cerrarConexion();
		return consumoMax;

	}
	/**
	 * Método que recupera el nombre de la imagen GIF de una eficiencia.
	 * @param idEficiencia ID de la eficiencia.
	 * @return Nombre de la imagen.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public String getIconoEficiencia(String idEficiencia) throws SQLException, ClassNotFoundException {
		establecerConexion();
		String icono = null;
		//Sentencia SQL:
		String sql = "SELECT ICONO FROM " + TABLA_EFICIENCIAS + " where C_ENERGETICA = '" + idEficiencia + "'";
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados obtenidos de la consulta anterior
		while (rs.next() == true) {
			icono = rs.getString("ICONO");
		}
		cerrarConexion();
		return icono;
	}
	/**
	 * Método que recupera todos los nombres de las imágenes GIF de las eficiencias existentes de la Base de Datos.
	 * @return ArrayList que contiene todas las eficiencias existentes en la Base de Datos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<String> getIconoEficiencias() throws SQLException, ClassNotFoundException {
		establecerConexion();
		ArrayList<String> iconos = new ArrayList();
		//Sentencia SQL:
		String sql = "select icono from " + TABLA_EFICIENCIAS;
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados obtenidos de la consulta anterior.
		while (rs.next() == true) {
			iconos.add(rs.getString("ICONO"));
		}
		cerrarConexion();
		return iconos;
	}
	/**
	 * Método que recupera el número de registros de modelos de una marca y consumo concreto de la Base de Datos.
	 * @param idMarca ID de la marca.
	 * @param consumo Consumo del modelo.
	 * @return Número de registros de los modelos de una marca y consumo concreto.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getNumModelos(int idMarca, float consumo) throws ClassNotFoundException, SQLException {
		establecerConexion();
		int numRegistros = -1; //Inicializamos a -1 el Nº de registros para saber que no existe ninguno.
		//Sentencia SQL.
		String sql = "SELECT count(*) FROM " + TABLA_MODELOS + " where id_marca =" + idMarca + " and consumo<="
				+ consumo;
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados de la consulta anterior.
		while (rs.next() == true) {
			numRegistros = rs.getInt("count(*)");
		}
		cerrarConexion();
		return numRegistros;
	}
	/**
	 * Método que recupera el número de registros de modelos de un consumo concreto de la Base de Datos.
	 * @param consumo Consumo del modelo.
	 * @return Número de registros de los modelos de un consumo concreto.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getNumModelos(float consumo) throws ClassNotFoundException, SQLException {
		establecerConexion();
		int numRegistros = -1; //Inicializamos a -1 el Nº de registros para saber que no existe ninguno.
		//Sentencia SQL.
		String sql = "SELECT count(*) FROM " + TABLA_MODELOS + " where consumo<=" + consumo;
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados de la consulta anterior.
		while (rs.next() == true) {
			numRegistros = rs.getInt("count(*)");
		}
		cerrarConexion();
		return numRegistros;
	}
	/**
	 * Método que recupera el número de registros de modelos de una marca concreta de la Base de Datos.
	 * @param idMarca ID de la marca.
	 * @return Número de registros de los modelos de una marca concreta.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getNumModelos(int idMarca) throws ClassNotFoundException, SQLException {
		establecerConexion();
		int numRegistros = -1; //Inicializamos a -1 el Nº de registros para saber que no existe ninguno.
		//Sentencia SQL.
		String sql = "SELECT count(*) FROM " + TABLA_MODELOS + " where id_marca =" + idMarca;
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery(sql);
		//Recorremos los resultados de la consulta anterior.
		while (rs.next() == true) {
			numRegistros = rs.getInt("count(*)");
		}
		cerrarConexion();
		return numRegistros;
	}
}
