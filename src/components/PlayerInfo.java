package components;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import MyProject.Player;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 * Clase que crea un JPanel que muestra los juegos guardados y la informacion basica de usuario.
 */
public class PlayerInfo extends JPanel{
	//Atributos
	private static final long serialVersionUID = 1L;
	private JLabel icon;
	private GameText name,level,set;
	private Player player;


	//Metodos
	/**
	 * Constructor del Player Info
	 */
	public PlayerInfo(Player player) {
		this.player = player;
		this.name = new GameText("Nombre: " + this.player.getName(), 32);
		this.name.setBounds(130, 15, 700, 50);
		
		if(player.finishedGame) {
			GameText finished = new GameText("Terminado", 32);
			finished.setBounds(130, 60, 350,48);
			this.add(finished);
		}else {
			this.level = new GameText("Nivel: " + this.player.getLevel(), 32);
			this.level.setBounds(130,60,350,48);
			
			this.set = new GameText("Parte: " + this.player.getSet(), 32);
			this.set.setBounds(350,60,350,48);
			
			this.add(this.set);
			this.add(this.level);
		}
		
		
		this.icon = new JLabel();
		this.icon.setIcon(new ImageResize(player.getIcon().getIcon(), 116,116).resize());
		this.icon.setBounds(2, 2, 116, 116);
		
		this.setLayout(null);
		this.setOpaque(true);
		this.setBackground(new Color(0,0,0,0));
		this.setSize(500, 120);
		
		
		this.add(this.icon);
		this.add(this.name);
		
		
		this.setVisible(true);
	}

	/**
	 * Establece el borde del JPanel
	 */
	public void selected() {
		Border border = new LineBorder(new Color(61,55,234), 4, true);
		this.setBorder(border);
	}

	/**
	 * Retorna el jugador dentro de la clase
	 */
	public Player getPlayer() {
		return player;
	}
}
