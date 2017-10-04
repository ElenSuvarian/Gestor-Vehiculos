package net.juanxxiii.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite la conexi�n y desconexi�n con una Base de Datos, variando
 * los datos de conexi�n con los necesarios.
 * 
 * @author Elen Suvarian
 */
public class GestorBBDD {
	private static final int DEFAULT_PORT = 3306; // Generalmente puerto MYSQL
													// que m�s se utiliza
	private String usuario; // Usuario de la BBDD
	private String password;// Contrase�a de la BBDD
	private String ip; // IP de la BBDD
	private String nombreBBDD; // Nombre de la BBDD
	private int puerto; // Puerto de conexi�n de la BBDD
	// Instanciaci�n de un objeto de tipo Connection que nos permitir� crear una
	// conexi�n con la BBDD
	protected Connection conexion;

	/**
	 * Constructor de la clase, que asigna los valores de la conexi�n de la
	 * BBDD. No incluye la recepci�n del puerto ya que utiliza el de por defecto (3306).
	 * @param usuario Usuario de la BBDD.
	 * @param password Contrase�a de la BBDD.
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
	 * Constructor de la clase, que asigna los valores de la conexi�n de la
	 * BBDD. Incluye la recepci�n del puerto.
	 * @param usuario Usuario de la BBDD.
	 * @param password Contrase�a de la BBDD.
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
	 * M�todo que utilza el Driver MYSQL.JDBC para conectarse con la Base de Datos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public void establecerConexion() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		String servidor_bbdd = "jdbc:mysql://" + ip + "/" + nombreBBDD;
		conexion = DriverManager.getConnection(servidor_bbdd, usuario, password);
	}
	/**
	 * M�todo que finaliza la conexi�n con la Base de Datos.
	 * @throws SQLException
	 */
	public void cerrarConexion() throws SQLException {
		conexion.close();
	}
}