package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;
/**
 * @author Ingrid
 * @version 2.0
 * Class that contains the game font and makes JLabels with the
 * font and white color.
 */
public class GameText extends JLabel{
	//Attribute
	private static final long serialVersionUID = 1L;
	public static Font nasalization = GameText.loadFont();
	//Methods
	/**
	 * GameText constructor
	 * @param String <- Label text
	 * @param float <- Font Size
	 */
	public GameText(String text, float size) {
		this.setText(text);
		this.setFont(GameText.nasalization.deriveFont(size));
		this.setForeground(Color.WHITE);
	}
	/**
	 * Loads nasalization font from it's file
	 * @return Font <- Nasalization
	 */
	private static Font loadFont() {
		try {
			nasalization = Font.createFont(Font.TRUETYPE_FONT, new File(GameText.class.getResource("/fonts/nasalization-rg.ttf").getFile()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(nasalization);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nasalization;
	}
}
