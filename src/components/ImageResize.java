package components;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 * Clase ImageResize sirve para redimensionar imagenes como gifs, jpgs
 * encontradas en internet para usarlas en el juego
 */
public class ImageResize {
	//Atributos
	private int width, height;
	private String imageType;
	private ImageIcon image;

	//Metodos


	/**
	 * Constructor de la clase
	 * @paramIcon <- image
	 * @paramint <- width to change the image
	 * @paramint <- height to change the image
	 */
	public ImageResize(Icon image, int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new ImageIcon(iconToImage(image));
		this.imageType = image.toString();
		this.imageType = this.imageType.substring(this.imageType.indexOf(".")+1, this.imageType.length());
	}

	/**
	 * Cambia el objeto icon a un objeto image para que se pueda manipular y redimensionar
	 */
	private Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
		      return ((ImageIcon)icon).getImage();
		   } 
		   else {
		      int w = icon.getIconWidth();
		      int h = icon.getIconHeight();
		      GraphicsEnvironment ge = 
		      GraphicsEnvironment.getLocalGraphicsEnvironment();
		      GraphicsDevice gd = ge.getDefaultScreenDevice();
		      GraphicsConfiguration gc = gd.getDefaultConfiguration();
		      BufferedImage image = gc.createCompatibleImage(w, h);
		      Graphics2D g = image.createGraphics();
		      icon.paintIcon(null, g, 0, 0);
		      g.dispose();
		      return image;
		   }
	}
	/**
	 * Redimensiona la imagen y la retorna con un nuevo tamaño
	 */
	public ImageIcon resize() {
		Image original = this.image.getImage();
		Image originalResized;
		if(this.imageType.equals("gif")) {
			originalResized = original.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}else {
			originalResized = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}
		ImageIcon resized = new ImageIcon(originalResized);
		return resized;
	}
	
	
}
