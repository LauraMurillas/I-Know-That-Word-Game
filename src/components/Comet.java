package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * @author Ingrid-E
 * @version 2.0
 * Class that creates a Comet with a word inside that 
 * can be changed and is rotated.
 */
public class Comet extends JPanel{
	//Attributes
	private static final long serialVersionUID = 1L;
	private JLabel comet, word;
	private float fontSize;
	//Methods
	/**
	 * Comet constructor
	 * @param String <- word
	 */
	public Comet(String word) {
		this.setLayout(null);
		this.fontSize = 64;
		this.word = rotateWord(word);
		this.comet = new JLabel();
		this.comet.setIcon(new ImageIcon(Comet.class.getResource("/img/Comet.png")));
		this.comet.setBounds(0, 0, 550, 450);
		this.add(this.word);
		this.add(this.comet);
		this.setSize(550, 450);
		this.setBackground(new Color(0,0,0,0));
		this.setVisible(true);
	}
	/**
	 * Sets the word inside the comet
	 * @param String <- word
	 */
	public void setText(String word) {
		this.fontSize =(float) (66-(word.length()*2.616));
		this.word.setFont(GameText.nasalization.deriveFont(this.fontSize));
		this.word.setText(word);
	}
	/**2,3,4
	 * Returns the JLabel with the word rotated
	 * @param String <- word
	 * @return JLabel
	 */
	public JLabel rotateWord(String word) {
		JLabel rotatedLabel = new JLabel(word) {
			private static final long serialVersionUID = 1L;
			@Override
		     public void paintComponent(Graphics g) {
		        Graphics2D gx = (Graphics2D) g;
		        gx.rotate(0.6, getX() + getWidth()/2, getY() + getHeight()/2);
		        super.paintComponent(g);
		     };
		};
		rotatedLabel.setBounds(10, 0, 500, 450);
		rotatedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		rotatedLabel.setFont(GameText.nasalization.deriveFont(this.fontSize));
		rotatedLabel.setForeground(new Color(221, 58, 19));
		return rotatedLabel;
	}
	

}
