package starWords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import components.Comet;
import components.PlayerInfo;
/**
 * 
 * @author Ingrid Echeverri
 * @version 2.0
 * Class that contains all the game data and basic comands.
 */
public class Game implements Serializable{
	private static final long serialVersionUID = 1L;
	//Atributes
	private ArrayList<Player> players;
	private ArrayList<String> words;
	private static int levels = 5;
	private static int[] quantityToPass = {7,9,12,15,18};
	//Methods
	/**
	 * Public constructor of Game, initialices atributes
	 */
	public Game() {
		this.players = new ArrayList<>();
		this.words = new ArrayList<>();
		readFile();
	}
	/**
	 * Word Quantity needed to pass level
	 * @param player
	 * @return int
	 */
	public int quantityToPass(Player player) {
		return quantityToPass[player.getLevel()-1];
	}
	/**
	 * Returns the saved Players
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	};
	/**
	 * Returns PlayerInfo component that contains all the player info
	 * @return
	 */
	public ArrayList<PlayerInfo> getGames(){
		ArrayList<PlayerInfo> savedGames = new ArrayList<>();
		for(Player player: this.getPlayers()) {
			PlayerInfo playerInfo = new PlayerInfo(player);
			savedGames.add(playerInfo);
		}
		return savedGames;
	}
	
	/**
	 * Creates a new Player object and adds it to the players list
	 * @param name <- String
	 * @param icon <- JLabel
	 */
	protected void createPlayer(String name, JLabel icon) {
		ArrayList<String> playerWords = new ArrayList<>();
		playerWords.addAll(words);
		Collections.shuffle(playerWords);
		Player player = new Player(name, icon, playerWords);
		players.add(player);
	}
	/**
	 * Returns player object saved in list
	 * @param name <- String
	 * @return Player object
	 */
	public Player getPlayer(String name) {
		for(Player player: players) {
			if(player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
	/**
	 * Writes the game data in a specific file
	 */
	protected void saveGame() {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("src/data/game-data"));
			file.writeObject(this);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Reads the file containing the words for the game and
	 * saves them in a list.
	 */
	private void readFile() {
		String line;
		try {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/data/words.txt"), "UTF-8"));
			while((line = reader.readLine())!=null) {
				words.add(line);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return int <- level
	 */
	public int getLevels() {
		return levels;
	}
	
	protected void showComets(Comet comet, String name, WGUI gui) {
		Player player = this.getPlayer(name);
		ArrayList<String> playerWords = player.levelWords();
		System.out.println(player.levelWords().toString());
		int wordQuantity = playerWords.size()-1;
		comet.setText(playerWords.get(wordQuantity));
		comet.setBounds(-528, -428, 528, 428);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int x = -524;
			int y = -425;
			int word = wordQuantity;
			@Override
			public void run() {
				if(y > 600) {
					word--;
					if(word < 0) {
						timer.cancel();
						gui.changeGUI("Write Words");
					}else {
						comet.setText(playerWords.get(word));
						x = -528;
						y = - 428;
					}
				}else {
					x +=5;
					y +=4;
				}
				comet.setLocation(x, y);
				gui.revalidate();
				gui.repaint();
			}
			
		};
		timer.scheduleAtFixedRate(task, 1000, 30);
	}

}
