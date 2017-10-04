package net.juanxxiii.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Clase que nos permite establecer una portada a la ventana inicial del programa.
 * @author Elen Suvarian
 */
public class JPPortada extends JPanel {

	/**
	 * Panel al cual se le está asignado una imagen, ocupando todo el panel.
	 */
	public JPPortada() {		
		JLabel JLFondo = new JLabel(""); //Creación de un JLabel al que se le asigna un icono que ocupa la totalidad del Panel:
		JLFondo.setIcon(new ImageIcon(JPPortada.class.getResource("/net/juanxxiii/imagenes/fondos/fondoPortada.jpg")));
		add(JLFondo);
	}

}
