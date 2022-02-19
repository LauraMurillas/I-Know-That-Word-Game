package MyProject;

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

import components.Palabra;
import components.PlayerInfo;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 * Esta clase contiene los datos del juego y acciones basicas.
 */
public class Game implements Serializable{
	private static final long serialVersionUID = 1L;
	//Atributos
	private ArrayList<Player> players;
	private ArrayList<String> words;
	private static int levels = 10;
	//Los porcentajes de aciertos
	private static int[] quantityToPass = {14,28,38,48,56,68,90,108,133,200};


	//Metodos

	/**
	 * Contructor de la clase Game
	 * inicializa los atributos
	 */
	public Game() {
		this.players = new ArrayList<>();
		this.words = new ArrayList<>();
		readFile();
	}


	/**
	 * Cantidad de palabras necesarias para pasar de nivel
	 */
	public int quantityToPass(Player player) {
		return quantityToPass[player.getLevel()-1];
	}


	/**
	 * Retorna los jugadores previos
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	};

	/**
	 * Retorna la informacion de los jugadores previos
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
	 * Crea el objeto Nuevo Jugador y lo añade a la lista de los jugadores
	 */
	protected void createPlayer(String name, JLabel icon) {
		ArrayList<String> playerWords = new ArrayList<>();
		playerWords.addAll(words);
		Collections.shuffle(playerWords);
		Player player = new Player(name, icon, playerWords);
		players.add(player);
	}


	/**
	 * Retorna el objeto Jugador, de los jugadores guardados
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
	 * Escribe los datos del juego en el archivo game-data
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
	 * Lee el archivo donde estan almacenadas las palabras del juego y los guarda en una lista
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
	 * Metodo para acceder a la variable levels
	 */
	public int getLevels() {
		return levels;
	}

	/**
	 * Metodo para mostrar las palabras con Timer de 5 segundos
	 * Hace que se 'mueva' en diagonal
	 */
	protected void showPalabras(Palabra palabra, String name, GUI gui) {
		Player player = this.getPlayer(name);
		ArrayList<String> playerWords = player.levelWords();
		System.out.println(player.levelWords().toString());
		int wordQuantity = playerWords.size()-1;
		palabra.setText(playerWords.get(wordQuantity));
		palabra.setBounds(-528, -428, 528, 428);
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
						palabra.setText(playerWords.get(word));
						x = -528;
						y = - 428;
					}
				}else {
					x +=5;
					y +=4;
				}
				palabra.setLocation(x, y);
				gui.revalidate();
				gui.repaint();
			}
			
		};
		timer.scheduleAtFixedRate(task, 1000, 50);
	}

}
