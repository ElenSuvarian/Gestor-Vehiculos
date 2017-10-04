package net.juanxxiii.modelo;
/**
 * Clase que contiene los atributos y m�todos del modelo de un veh�culo.
 * @author Elen Suvarian
 */
public class Modelo {
	private int idMarca; //N�mero identificativo de la marca.
	private String idEficiencia; //N�mero identificativo de la eficiencia.
	private String nombre; //Nombre del modelo.
	private float consumo; //Consumo del modelo.
	private float emisiones; // Emisiones del consumo.
	private String marca; //Nombre de la marca del modelo.
	private String logoEficiencia; //nombre de la imagen GIF de la eficiencia el modelo.

	/**
	 * Constructor de la clase que recibe los par�metros necesarios para agregar un modelo nuevo a la BBDD.
	 * @param idMarca N�mero identificativo de la marca.
	 * @param idEficiencia N�mero identificativo de la eficiencia.
	 * @param nombre Nombre del modelo.
	 * @param consumo Consumo del modelo.
	 * @param emisiones Emisiones del consumo.
	 */
	public Modelo(int idMarca, String idEficiencia, String nombre, float consumo, float emisiones) {
		this.idMarca = idMarca;
		this.idEficiencia = idEficiencia;
		this.nombre = nombre;
		this.consumo = consumo;
		this.emisiones = emisiones;
	}
	/**
	 * Constructor de la clase que recibe los par�metros necesarios para recuperar de la BBDD un modelo.
	 * @param marca Nombre de la marca del modelo.
	 * @param logoEficiencia Nombre de la imagen GIF de la eficiencia el modelo.
	 * @param nombre Nombre del modelo.
	 * @param consumo Consumo del modelo.
	 * @param emisiones Emisiones del consumo.
	 * @param idMarca N�mero identificativo de la marca.
	 */
	public Modelo(String marca, String logoEficiencia, String nombre, float consumo, float emisiones, int idMarca) {
		this.marca = marca;
		this.logoEficiencia = logoEficiencia;
		this.nombre = nombre;
		this.consumo = consumo;
		this.emisiones = emisiones;
		this.idMarca = idMarca;
	}
	/**
	 * M�todo que devuelve el nombre de la imagen GIF de la eficiencia del modelo.
	 * @return nombre de la imagen GIF de la eficiencia del modelo.
	 */
	public String getIcono() {
		return logoEficiencia;
	}
	/**
	 * M�todo que devuelve el nombre del modelo.
	 * @return Nombre del modelo.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * M�todo que devuelve el consumo del modelo.
	 * @return Consumo del modelo.
	 */
	public float getConsumo() {
		return consumo;
	}
	/**
	 * M�todo que devuelve las emisiones del modelo.
	 * @return Emisiones del modelo.
	 */
	public float getEmisiones() {
		return emisiones;
	}
	/**
	 * M�todo que devuelve el n�mero identificativo de la marca del modelo.
	 * @return ID de la marca del modelo
	 */
	public int getIdMarca() {
		return idMarca;
	}
	/**
	 * M�todo que devuelve el n�mero identificativo de la eficiencia del modelo.
	 * @return ID de la eficiencia del modelo.
	 */
	public String getIdEficiencia() {
		return idEficiencia;
	}
	/**
	 * M�todo que devuelve el nombre de la marca del modelo.
	 * @return Nombre de la marca del modelo.
	 */
	public String getMarca() {
		return marca;
	}
}
