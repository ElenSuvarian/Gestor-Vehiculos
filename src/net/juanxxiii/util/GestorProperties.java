package net.juanxxiii.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Clase encargada de la creación del fichero, en el cual se almacena la información necesaria para la conexión con la Base de DAtos.
 * @author Elen Suvarian
 */
public class GestorProperties {
	/**
	 * Método encargado de crear un fichero de Properties, el cual almacena la información necesaria para la conexión con la Base de Datos.
	 * El fichero creado es de tipo XML.
	 */
	public static void crearPropertiesXML() {
		try {
			Properties p = new Properties();
			FileOutputStream fos = new FileOutputStream("Config.xml");
			p.setProperty("usuario", "dam2017");
			p.setProperty("password", "dam2017");
			p.setProperty("ruta", "127.0.0.1");
			p.setProperty("nombreBBDD", "bbdd_gestmotor");
			p.storeToXML(fos, "Fichero de configuración");
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("HA OCURRIDO UN PROBLEMA AL CREAR EL FICHERO Config.xml");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("HA OCURRIDO UN PROBLEMA AL INTENTAR CERRAR EL FileOutputStream");
			e.printStackTrace();
		}
	}	
}
