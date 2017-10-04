package net.juanxxiii.modelo;

/**
 * Clase que contiene los atributos y métodos de la marca de un vehículo.
 * 
 * @author Elen Suvarian
 */
public class Marca {
	private int id;//Núumero único que identifica a una marca
	private String nombre; //Nombre de la marca
	/**
	 * Constructor de la clase Marca.
	 * @param nombre Nombre corresponidente a la marca
	 */
	public Marca(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método que devuelve la ID de una marca.
	 * @return Número identificativo de la Marca
	 */
	public int getId() {
		return id;
	}
	/**
	 * Método que devuelve el nombre de la marca.
	 * @return Nombre de la marca.
	 */
	public String getNombre() {
		return nombre;
	}
}
