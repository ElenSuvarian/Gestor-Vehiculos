package net.juanxxiii.modelo;

/**
 * Clase que contiene los atributos y m�todos de la marca de un veh�culo.
 * 
 * @author Elen Suvarian
 */
public class Marca {
	private int id;//N�umero �nico que identifica a una marca
	private String nombre; //Nombre de la marca
	/**
	 * Constructor de la clase Marca.
	 * @param nombre Nombre corresponidente a la marca
	 */
	public Marca(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * M�todo que devuelve la ID de una marca.
	 * @return N�mero identificativo de la Marca
	 */
	public int getId() {
		return id;
	}
	/**
	 * M�todo que devuelve el nombre de la marca.
	 * @return Nombre de la marca.
	 */
	public String getNombre() {
		return nombre;
	}
}
