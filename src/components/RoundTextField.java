package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 * Clase que crea la caja de texto para que el usuario introduzca el nombre.
 */
public class RoundTextField extends JTextField{
	//Atributos
	private static final long serialVersionUID = 1L;
	private Shape shape;
	private static int radius = 30;
	private static Color HotPink = new Color(61,55,234);
	public Color color = HotPink;


	//Metodos

	/**
	 * Constructor de la clase
	 */
	public RoundTextField(int size) {
		super(size);
		setOpaque(false);
		this.setFont(GameText.fuente.deriveFont(36f));
		this.setForeground(Color.BLACK);
	}

	/**
	 * Pinta los componentes transparente
	 */
	 protected void paintComponent(Graphics g) {
         g.setColor(new Color(61,55,234,0));
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         super.paintComponent(g);
    }

	/**
	 * Pinta los borden del componente con el color deseado
	 */
    protected void paintBorder(Graphics g) {
         g.setColor(this.color);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }

    /**
	 * Crea la forma
     */
    public boolean contains(int x, int y) {
         if (this.shape == null || !this.shape.getBounds().equals(getBounds())) {
             this.shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         }
         return this.shape.contains(x, y);
    }

}
