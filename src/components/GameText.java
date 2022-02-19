package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 *Clase GameText contiene la fuente de la letra y crea JLabels con la fuente en color negro
 */
public class GameText extends JLabel{
	//Atributos
	private static final long serialVersionUID = 1L;
	public static Font fuente = GameText.loadFont();


	//Metodos
	/**
	 * Contructor de la clase GameText
	 */
	public GameText(String text, float size) {
		this.setText(text);
		this.setFont(GameText.fuente.deriveFont(size));
		this.setForeground(Color.BLACK);
	}

	/**
	 * Carga la fuente del archivo en la carpeta fonts
	 */
	private static Font loadFont() {
		try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File(GameText.class.getResource("/fonts/fuente.ttf").getFile()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fuente);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fuente;
	}
}
