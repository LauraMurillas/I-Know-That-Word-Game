package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
/**
 * @author Ingrid-E & Internet
 * @version 1.0
 * Class that created a TextField with rounded corners,
 * founded on the internet on modifed to make it better
 * for the game.
 */
public class RoundTextField extends JTextField{
	//Attributes
	private static final long serialVersionUID = 1L;
	private Shape shape;
	private static int radius = 30;
	private static Color HotPink = new Color(234,55,234);
	public Color color = HotPink;
	//Methods
	/**
	 * RoundTextField constructor
	 * @param int <- size
	 */
	public RoundTextField(int size) {
		super(size);
		setOpaque(false);
		this.setFont(GameText.nasalization.deriveFont(36f));
		this.setForeground(Color.WHITE);
	}
	/**
	 * Paints the component transparent
	 */
	 protected void paintComponent(Graphics g) {
         g.setColor(new Color(255,255,255,0));
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         super.paintComponent(g);
    }
	/**
	 * Paints the component border with desiered color
	 */
    protected void paintBorder(Graphics g) {
         g.setColor(this.color);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
    }
    /**
     * Creates the shape
     */
    public boolean contains(int x, int y) {
         if (this.shape == null || !this.shape.getBounds().equals(getBounds())) {
             this.shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius);
         }
         return this.shape.contains(x, y);
    }
    
	public void wrongAnimation() {
		RoundTextField main = this;
		main.color = Color.RED;
		int initialPos = this.getX();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int position = initialPos;
			int moveX = 1;
			boolean finish = false;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(position == initialPos && finish) {
					main.color = RoundTextField.HotPink;
					timer.cancel();
				}
				
				if(position == initialPos+3) {
					moveX = -1;
				}else if(position == initialPos-3) {
					moveX = 1;
					finish = true;
				}
				position += moveX;
				main.setLocation(position, main.getY());
			}
			
		}, 0, 30);
	}
	

}
