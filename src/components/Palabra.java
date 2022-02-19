package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.lang.String;
/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 * Clase crea el objeto Palabra con una palabra adentro
 * puede cambiar de posicion para hacer la ilusion de moverse en diagonal
 */
public class Palabra extends JPanel{
	//Atributos
	private static final long serialVersionUID = 1L;
	private JLabel word;
	private float fontSize;


	//Metodos
	/**
	 * Palabra constructor
	 */
	public Palabra(String word) {
		this.setLayout(null);
		this.fontSize = 64;
		this.word = rotateWord(word);
		this.add(this.word);
		this.setSize(550, 450);
		this.setBackground(new Color(0,0,0,0));
		this.setVisible(true);
	}

	/**
	 * Establece la palabra
	 */
	public void setText(String word) {
		this.fontSize =(float) (66-(word.length()*2.616));
		this.word.setFont(GameText.fuente.deriveFont(this.fontSize));
		this.word.setText(word);
	}


	/**
	 * Retorna un JLabel con la palabra adentro
	 * aqui se 'rota' o 'mueve' la palabra
	 */


	public JLabel rotateWord(String word) {

		JLabel rotatedLabel = new JLabel(word) {
			private static final long serialVersionUID = 1L;
			@Override
		     public void paintComponent(Graphics g) {
		        Graphics2D gx = (Graphics2D) g;
		        gx.rotate(0.0, getX() + getWidth()/2, getY() + getHeight()/2);
		        super.paintComponent(g);
		     };
		};
		rotatedLabel.setBounds(0, 0, 500, 450);
		rotatedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		rotatedLabel.setFont(GameText.fuente.deriveFont(this.fontSize));
		rotatedLabel.setForeground(new Color(61, 55, 234));
		return rotatedLabel;
	}

}
