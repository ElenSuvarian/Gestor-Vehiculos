package net.juanxxiii.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite la conexión y desconexión con una Base de Datos, variando
 * los datos de conexión con los necesarios.
 * 
 * @author Elen Suvarian
 */
public class GestorBBDD {
	private static final int DEFAULT_PORT = 3306; // Generalmente puerto MYSQL
													// que más se utiliza
	private String usuario; // Usuario de la BBDD
	private String password;// Contraseña de la BBDD
	private String ip; // IP de la BBDD
	private String nombreBBDD; // Nombre de la BBDD
	private int puerto; // Puerto de conexión de la BBDD
	// Instanciación de un objeto de tipo Connection que nos permitirá crear una
	// conexión con la BBDD
	protected Connection conexion;

	/**
	 * Constructor de la clase, que asigna los valores de la conexión de la
	 * BBDD. No incluye la recepción del puerto ya que utiliza el de por defecto (3306).
	 * @param usuario Usuario de la BBDD.
	 * @param password Contraseña de la BBDD.
	 * @param ip IP de la BBDD.
	 * @param nombreBBDD Nombre de la BBDD.
	 */
	public GestorBBDD(String usuario, String password, String ip, String nombreBBDD) {
		this.usuario = usuario;
		this.password = password;
		this.ip = ip;
		this.nombreBBDD = nombreBBDD;
		this.puerto = DEFAULT_PORT;
	}
	/**
	 * Constructor de la clase, que asigna los valores de la conexión de la
	 * BBDD. Incluye la recepción del puerto.
	 * @param usuario Usuario de la BBDD.
	 * @param password Contraseña de la BBDD.
	 * @param ip IP de la BBDD.
	 * @param nombreBBDD Nombre de la BBDD.
	 * @param puerto Puerto de la BBDD.
	 */
	public GestorBBDD(String usuario, String password, String ip, String nombreBBDD, int puerto) {
		this.usuario = usuario;
		this.password = password;
		this.ip = ip;
		this.nombreBBDD = nombreBBDD;
		this.puerto = puerto;
	}
	/**
	 * Método que utilza el Driver MYSQL.JDBC para conectarse con la Base de Datos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public void establecerConexion() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		String servidor_bbdd = "jdbc:mysql://" + ip + "/" + nombreBBDD;
		conexion = DriverManager.getConnection(servidor_bbdd, usuario, password);
	}
	/**
	 * Método que finaliza la conexión con la Base de Datos.
	 * @throws SQLException
	 */
	public void cerrarConexion() throws SQLException {
		conexion.close();
	}
}