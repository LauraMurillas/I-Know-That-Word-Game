package components;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import starWords.Player;
/**
 * 
 * @author Ingrid Echeverri
 * @version 2.0
 * Class that creates a JPanel that shows saved
 * games and basic player info.
 *
 */
public class PlayerInfo extends JPanel{
	//Atributes
	private static final long serialVersionUID = 1L;
	private JLabel icon;
	private GameText name,level,set;
	private Player player;
	//Methods
	/**
	 * Player Info constructor
	 * @param String <- Player name
	 * @param int <- Level
	 */
	public PlayerInfo(Player player) {
		this.player = player;
		this.name = new GameText("Name: " + this.player.getName(), 40);
		this.name.setBounds(130, 15, 700, 50);
		
		if(player.finishedGame) {
			GameText finished = new GameText("Completed", 40);
			finished.setBounds(130, 60, 350,48);
			this.add(finished);
		}else {
			this.level = new GameText("Level: " + this.player.getLevel(), 40);
			this.level.setBounds(130,60,350,48);
			
			this.set = new GameText("Set: " + this.player.getSet(), 40);
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
		this.setSize(600, 120);
		
		
		this.add(this.icon);
		this.add(this.name);
		
		
		this.setVisible(true);
	}
	/**
	 * Sets a border on the JPanel
	 */
	public void selected() {
		Border border = new LineBorder(new Color(234,55,234), 4, true);
		this.setBorder(border);
	}
	/**
	 * Returns the player inside the class
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
}
