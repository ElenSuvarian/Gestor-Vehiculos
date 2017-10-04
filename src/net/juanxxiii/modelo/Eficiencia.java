package net.juanxxiii.modelo;

/**
 * Clase que contiene los atributos y m�todos de la eficiencia de un veh�culo.
 * @author Elen Suvarian
 */
public class Eficiencia {
	private int id; //id que identifica con un n�mero �nico un tipo de eficiencia
	private String nombre; // el nombre que tiene asignada la eficiencia
	private String descripcion; //el significado de la eficiencia
	private String nombre_fichero; //nombre de la imagen gif
	
	/**
	 * Constructor de la clase Eficiencia. 
	 * @param nombre Nombre identificativo de la eficiencia
	 * @param descripcion Significado de la eficiencia
	 * @param nombre_fichero //Nombre de la imagen GIF
	 */
	public Eficiencia(String nombre, String descripcion, String nombre_fichero) {
		this.nombre = nombre;
		this.nombre_fichero = nombre_fichero;
		this.descripcion = descripcion;
	}
	/**
	 * M�todo que devuelve el valor del ID de la eficiencia.
	 * @return Id de la eficiencia.
	 */
	public int getId() {
		return id;
	}
	/**
	 * M�todo que devuelve el nombre de la eficiencia
	 * @return Nombre de la eficiencia.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * M�todo que devuelve la descripci�n de la eficiencia
	 * @return Descripci�n de la eficiencia
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * M�todo que devuelve el nombre de la imagen correspondiente al GIF de la eficiencia.
	 * @return Nombre de la imagen GIF de la eficiencia.
	 */
	public String getNombre_fichero() {
		return nombre_fichero;
	}
}