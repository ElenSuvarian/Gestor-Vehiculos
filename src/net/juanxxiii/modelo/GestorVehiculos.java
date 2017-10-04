package net.juanxxiii.modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import net.juanxxiii.persistencia.GestorBBDDVehiculos;

/**
 * Clase que contiene los métodos apropiados para la gestión de alta, eliminación y 
 * amplia recuperación de datos sobre Vehículos.
 * @author Elen Suavrian
 */
public class GestorVehiculos {
	private GestorBBDDVehiculos gbdv; //Instanciación de Gestor de Bases de Datos de Vehículos

	/**
	 * Constructor de la clase. Encargado además, de inicializar el fichero de los properties.
	 */
	public GestorVehiculos() {
		try {
			FileInputStream fis = new FileInputStream("Config.xml"); // Creamos el fichero de properties
			Properties p = new Properties(); //Instanciamos un objeto de tipo properties.
			p.loadFromXML(fis); 
			//REcuperamos los datos de las properties
			gbdv = new GestorBBDDVehiculos(p.getProperty("usuario"), p.getProperty("password"), p.getProperty("ruta"),
					p.getProperty("nombreBBDD"));
			fis.close();
		} catch (IOException e) {
			System.out.println("FALLO DE CREACIÓN DE LAS PROPERTIES"); //Fallo identificativo
			e.printStackTrace(); //Traza del fallo
		}
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD,
	 * que permite la inserción de un modelo en la BBDD.
	 * @param modelo Nombre del modelo.
	 * @param marca Nombre de la marca del modelo.
	 * @param consumo Consumo del modelo.
	 * @param emisiones Emisiones del modelo.
	 * @param eficiencia Eficiencia del modelo.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public void addVehiculo(String modelo, String marca, String consumo, String emisiones, String eficiencia)
			throws SQLException, ClassNotFoundException {
		int idMarca = gbdv.getIDMarca(marca); //Recuperamos la Id de la marca a agregar
		//String modeloFinal = marca + " " + modelo; // Añadimos la marca al principio del modelo
		String idEficiencia = gbdv.getIDEficiencia(eficiencia); //Recuperamos el ID de la eficiencia según la eficiencia introducida por el usuario
		float consumoFinal = Float.parseFloat(consumo); //Recuperamos el consumo introdudcido por el usuario parseándolo a float
		float emisionesFinal = Float.parseFloat(emisiones); //Recuperamos las emisiones introdudcidas por el usuario parseándolo a float
		//Instanciamos un objeto de tipo Modelo para poder agreagarlo a la BBDD
		Modelo modelo1 = new Modelo(idMarca, idEficiencia, modelo, consumoFinal, emisionesFinal);
		//Enviamos el modelo al Gestor de la BBDD para finalmente agregarlo.
		gbdv.insertarModelo(modelo1);
	}

	/**
	 *  Método intermediario entre la interfaz gráfica y el Gestor de la BBDD,
	 *  que recupera la lista de marcas existentes en la BBDD.
	 * @return ArrayList de tipo Marca que contiene las marcas existentes en la BBDD.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<Marca> getMarcas() throws SQLException, ClassNotFoundException {
		ArrayList<Marca> marcas = gbdv.getMarcas();//Instanciamos un arrayList para poder almacenar las marcas recibidas.
		return marcas;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD,
	 * que recupera la lista de eficiencias existentes en la BBDD.
	 * @return ArrayList de tipo String que contiene los nombres de las eficiencias existentes en la BBDD.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<String> getEficiencias() throws SQLException, ClassNotFoundException {
		ArrayList<String> eficiencias = gbdv.getEficiencia();//Instanciamos un arrayList para poder almacenar las eficiencias recibidas.
		return eficiencias;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera los modelos
	 * existentes en la BBDD de una marca concreta.
	 * @param marca ID de la marca del modelo a buscar.
	 * @return ArrayList de tipo Modelo que contiene los modelos de una marca concreta.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(int marca) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(marca);//Instanciamos un arrayList para poder almacenar los modelos recibidos.
		return alm;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera los modelos
	 * existentes en la BBDD de un consumo concreto.	
	 * @param consumo Consumo del Modelo a buscar.
	 * @return ArrayList de tipo Modelo que contiene los modelos de un consumo concreto.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(float consumo) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(consumo);
		return alm;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera los modelos
	 * existentes en la BBDD de un consumo concreto y una marca concreta.
	 * @param marca marca ID de la marca del modelo a buscar.
	 * @param consumo Consumo del Modelo a buscar.
	 * @return ArrayList de tipo Modelo que contiene los modelos de un consumo y una marca concretos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(int marca, float consumo) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(marca, consumo);
		return alm;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera el consumo máximo de los modelos
	 * de la BBDD.
	 * @return Consumo máximo existenete en al BBDD.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public String getConsumoMax() throws ClassNotFoundException, SQLException {
		String consumoMax = gbdv.getConsumoMax();//Recogemos el consumo máximo de la BBDD
		return consumoMax;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera el ID de una marca según su nombre.
	 * @param marca Nombre de la marca.
	 * @return ID de la marca.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getIDMarca(String marca) throws SQLException, ClassNotFoundException {
		int idMarca = gbdv.getIDMarca(marca); ////Recogemos el ID de una marca por su nombre.
		return idMarca;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que elimina un modelo de la
	 * BBDD según su ID.
	 * @param modelo Nombre del modelo a eliminar.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public void eliminarModelo(String modelo) throws ClassNotFoundException, SQLException {		
		int idModelo=gbdv.getModelo(modelo); //Recogemos el ID de un modelo según su nombre.
		gbdv.borrarModelo(idModelo);
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera el nombre de la imagen GIF de 
	 * una eficiencia según el ID de la eficiencia.
	 * @param IdEficiencia ID de la eficiencia a recuperar.
	 * @return Nombre de la imagen GIF de la eficiencia.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public String getIconoEficiencia(String IdEficiencia) throws ClassNotFoundException, SQLException {
		String icono = gbdv.getIconoEficiencia(IdEficiencia);//Recogemos el nombre de la imagen de una eficiencia
		return icono;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera la cantidad de registros de modelos
	 * existentes en la BBDD según una marca y un consumo concretos.
	 * @param idMarca ID de la marca.
	 * @param consumo Consumo del modelo.
	 * @return Número de registros de modelos según una marca y consumo concretos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getNumModelos(int idMarca,float consumo) throws ClassNotFoundException, SQLException{
		int numRegistros=gbdv.getNumModelos(idMarca, consumo);//Recogemos el número de registros de modelos en la BBDD según la marca y el consumo.
		return numRegistros;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera la cantidad de registros de modelos
	 * existentes en la BBDD según un consumo concreto.
	 * @param consumo Consumo del modelo.
	 * @return Número de registros de modelos según un consumo concretos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getNumModelos(float consumo) throws ClassNotFoundException, SQLException{
		int numRegistros=gbdv.getNumModelos(consumo);//Recogemos el número de registros de modelos en la BBDD según el consumo.
		return numRegistros;
	}
	/**
	 * Método intermediario entre la interfaz gráfica y el Gestor de la BBDD, que recupera la cantidad de registros de modelos
	 * existentes en la BBDD según una marca concreta.
	 * @param idMarca ID de la marca.
	 * @return Número de registros de modelos según una marca concreta.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operación en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexión con la BBDD.
	 */
	public int getNumModelos(int idMarca) throws ClassNotFoundException, SQLException{
		int numRegistros=gbdv.getNumModelos(idMarca);//Recogemos el número de registros de modelos en la BBDD según la marca.
		return numRegistros;
	}
}

