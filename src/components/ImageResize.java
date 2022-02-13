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
 * @author Ingrid-E & Internet
 * @version 1.0
 * Class created to resize images such as gifs,png,jpg etc..
 * Found on the internet and modified to meet game requirements.
 */
public class ImageResize {
	//Atributes
	private int width, height;
	private String imageType;
	private ImageIcon image;
	//Methods
	/**
	 * Image Resize constructor
	 * @param ImageIcon <- image
	 * @param int <- width to change the image
	 * @param int <- height to change the image
	 */
	public ImageResize(ImageIcon image, int width, int height) {
		this.width = width;
		this.height = height;
		this.image = image;
		this.imageType = image.getDescription();
		this.imageType = this.imageType.substring(this.imageType.indexOf(".")+1, this.imageType.length());
	}
	/**
	 * Image Resize constructor
	 * @param Icon <- image
	 * @param int <- width to change the image
	 * @param int <- height to change the image
	 */
	public ImageResize(Icon image, int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new ImageIcon(iconToImage(image));
		this.imageType = image.toString();
		this.imageType = this.imageType.substring(this.imageType.indexOf(".")+1, this.imageType.length());
	}
	/**
	 * Changed the icon object to an Image object so it 
	 * can be manipulated and resized.
	 * @param Icon <- icon
	 * @return Image
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
	 * Resizes the image and returns it with the new size
	 * @return ImageIcon
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
