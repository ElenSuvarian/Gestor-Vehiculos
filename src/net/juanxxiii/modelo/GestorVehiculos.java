package net.juanxxiii.modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import net.juanxxiii.persistencia.GestorBBDDVehiculos;

/**
 * Clase que contiene los m�todos apropiados para la gesti�n de alta, eliminaci�n y 
 * amplia recuperaci�n de datos sobre Veh�culos.
 * @author Elen Suavrian
 */
public class GestorVehiculos {
	private GestorBBDDVehiculos gbdv; //Instanciaci�n de Gestor de Bases de Datos de Veh�culos

	/**
	 * Constructor de la clase. Encargado adem�s, de inicializar el fichero de los properties.
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
			System.out.println("FALLO DE CREACI�N DE LAS PROPERTIES"); //Fallo identificativo
			e.printStackTrace(); //Traza del fallo
		}
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD,
	 * que permite la inserci�n de un modelo en la BBDD.
	 * @param modelo Nombre del modelo.
	 * @param marca Nombre de la marca del modelo.
	 * @param consumo Consumo del modelo.
	 * @param emisiones Emisiones del modelo.
	 * @param eficiencia Eficiencia del modelo.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public void addVehiculo(String modelo, String marca, String consumo, String emisiones, String eficiencia)
			throws SQLException, ClassNotFoundException {
		int idMarca = gbdv.getIDMarca(marca); //Recuperamos la Id de la marca a agregar
		//String modeloFinal = marca + " " + modelo; // A�adimos la marca al principio del modelo
		String idEficiencia = gbdv.getIDEficiencia(eficiencia); //Recuperamos el ID de la eficiencia seg�n la eficiencia introducida por el usuario
		float consumoFinal = Float.parseFloat(consumo); //Recuperamos el consumo introdudcido por el usuario parse�ndolo a float
		float emisionesFinal = Float.parseFloat(emisiones); //Recuperamos las emisiones introdudcidas por el usuario parse�ndolo a float
		//Instanciamos un objeto de tipo Modelo para poder agreagarlo a la BBDD
		Modelo modelo1 = new Modelo(idMarca, idEficiencia, modelo, consumoFinal, emisionesFinal);
		//Enviamos el modelo al Gestor de la BBDD para finalmente agregarlo.
		gbdv.insertarModelo(modelo1);
	}

	/**
	 *  M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD,
	 *  que recupera la lista de marcas existentes en la BBDD.
	 * @return ArrayList de tipo Marca que contiene las marcas existentes en la BBDD.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public ArrayList<Marca> getMarcas() throws SQLException, ClassNotFoundException {
		ArrayList<Marca> marcas = gbdv.getMarcas();//Instanciamos un arrayList para poder almacenar las marcas recibidas.
		return marcas;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD,
	 * que recupera la lista de eficiencias existentes en la BBDD.
	 * @return ArrayList de tipo String que contiene los nombres de las eficiencias existentes en la BBDD.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public ArrayList<String> getEficiencias() throws SQLException, ClassNotFoundException {
		ArrayList<String> eficiencias = gbdv.getEficiencia();//Instanciamos un arrayList para poder almacenar las eficiencias recibidas.
		return eficiencias;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera los modelos
	 * existentes en la BBDD de una marca concreta.
	 * @param marca ID de la marca del modelo a buscar.
	 * @return ArrayList de tipo Modelo que contiene los modelos de una marca concreta.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(int marca) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(marca);//Instanciamos un arrayList para poder almacenar los modelos recibidos.
		return alm;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera los modelos
	 * existentes en la BBDD de un consumo concreto.	
	 * @param consumo Consumo del Modelo a buscar.
	 * @return ArrayList de tipo Modelo que contiene los modelos de un consumo concreto.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(float consumo) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(consumo);
		return alm;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera los modelos
	 * existentes en la BBDD de un consumo concreto y una marca concreta.
	 * @param marca marca ID de la marca del modelo a buscar.
	 * @param consumo Consumo del Modelo a buscar.
	 * @return ArrayList de tipo Modelo que contiene los modelos de un consumo y una marca concretos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public ArrayList<Modelo> getModelos(int marca, float consumo) throws ClassNotFoundException, SQLException {
		ArrayList<Modelo> alm = gbdv.getModelos(marca, consumo);
		return alm;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera el consumo m�ximo de los modelos
	 * de la BBDD.
	 * @return Consumo m�ximo existenete en al BBDD.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public String getConsumoMax() throws ClassNotFoundException, SQLException {
		String consumoMax = gbdv.getConsumoMax();//Recogemos el consumo m�ximo de la BBDD
		return consumoMax;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera el ID de una marca seg�n su nombre.
	 * @param marca Nombre de la marca.
	 * @return ID de la marca.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public int getIDMarca(String marca) throws SQLException, ClassNotFoundException {
		int idMarca = gbdv.getIDMarca(marca); ////Recogemos el ID de una marca por su nombre.
		return idMarca;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que elimina un modelo de la
	 * BBDD seg�n su ID.
	 * @param modelo Nombre del modelo a eliminar.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public void eliminarModelo(String modelo) throws ClassNotFoundException, SQLException {		
		int idModelo=gbdv.getModelo(modelo); //Recogemos el ID de un modelo seg�n su nombre.
		gbdv.borrarModelo(idModelo);
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera el nombre de la imagen GIF de 
	 * una eficiencia seg�n el ID de la eficiencia.
	 * @param IdEficiencia ID de la eficiencia a recuperar.
	 * @return Nombre de la imagen GIF de la eficiencia.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public String getIconoEficiencia(String IdEficiencia) throws ClassNotFoundException, SQLException {
		String icono = gbdv.getIconoEficiencia(IdEficiencia);//Recogemos el nombre de la imagen de una eficiencia
		return icono;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera la cantidad de registros de modelos
	 * existentes en la BBDD seg�n una marca y un consumo concretos.
	 * @param idMarca ID de la marca.
	 * @param consumo Consumo del modelo.
	 * @return N�mero de registros de modelos seg�n una marca y consumo concretos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public int getNumModelos(int idMarca,float consumo) throws ClassNotFoundException, SQLException{
		int numRegistros=gbdv.getNumModelos(idMarca, consumo);//Recogemos el n�mero de registros de modelos en la BBDD seg�n la marca y el consumo.
		return numRegistros;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera la cantidad de registros de modelos
	 * existentes en la BBDD seg�n un consumo concreto.
	 * @param consumo Consumo del modelo.
	 * @return N�mero de registros de modelos seg�n un consumo concretos.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public int getNumModelos(float consumo) throws ClassNotFoundException, SQLException{
		int numRegistros=gbdv.getNumModelos(consumo);//Recogemos el n�mero de registros de modelos en la BBDD seg�n el consumo.
		return numRegistros;
	}
	/**
	 * M�todo intermediario entre la interfaz gr�fica y el Gestor de la BBDD, que recupera la cantidad de registros de modelos
	 * existentes en la BBDD seg�n una marca concreta.
	 * @param idMarca ID de la marca.
	 * @return N�mero de registros de modelos seg�n una marca concreta.
	 * @throws SQLException Error de tipo SQL que puede producirse al hacer alguna operaci�n en la BBDD.
	 * @throws ClassNotFoundException Error que puede producirse por intentar extablecer la conexi�n con la BBDD.
	 */
	public int getNumModelos(int idMarca) throws ClassNotFoundException, SQLException{
		int numRegistros=gbdv.getNumModelos(idMarca);//Recogemos el n�mero de registros de modelos en la BBDD seg�n la marca.
		return numRegistros;
	}
}

