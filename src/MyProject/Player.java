package MyProject;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */



/**
 * Clase Player contiene la informacion basica del juego del jugador
 * y las palabras que se le asignan a cada nivel
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 1L;
	//Atributos
	private String name;
	private int level, set, wordPos, wordQuantity, wordLvl;
	protected int totalGuessed;
	private JLabel icon;
	private ArrayList<String> words;
	protected ArrayList<String> guessedWords;
	public boolean finishedGame;

	//Metodos


	/**
	 * Constructor de la clase Player
	 * crea el perfil del jugador
	 */
	public Player(String name, JLabel icon, ArrayList<String> words) {
		this.setName(name);
		this.level = 1;
		this.setIcon(icon);
		this.words = words;
		this.setSet(1);
		this.wordPos = 0;
		this.wordQuantity = 10;
		this.finishedGame = false;
		this.wordLvl = 10;
		this.totalGuessed = 0;
		this.guessedWords = new ArrayList<>();
	}



	/**
	 * Retorna las palabras del jugador para un especifico nivel y parte de nivel
	 */
	public ArrayList<String> levelWords(){
		ArrayList<String> playerLevel = new ArrayList<>();
		//Starts from the latest word position to not repeat words
		for(int i = this.wordPos; i < wordQuantity; i++) {
			playerLevel.add(words.get(i));
		}
		return playerLevel;
	}


	/**
	 * Metodo para pasar al siguiente nivel en la parte 1
	 * Limpia las variables words Y crea la variable WordQuantity
	 */
	protected void nextLevel() {
		this.wordQuantity -= this.wordLvl;
		this.level++;
		this.setSet(1);
		this.wordPos += this.wordLvl;
		if (this.level == 3 || this.level == 4 || this.level == 5 || this.level == 6){
			this.wordLvl +=5;
		}else if (this.level == 10){
			this.wordLvl += 30;
		}else{
			this.wordLvl += 10;
		}

		this.guessedWords.clear();
		this.wordQuantity += this.wordLvl;
	}

	/**
	 * Metodo para pasar de la parte 1 (palabras a recordar) a la parte 2 (Recuerda la palabra) del nivel
	 */
	protected void nextSet() {
		this.setSet(this.getSet() + 1);
		this.wordQuantity += this.wordLvl;
	}


	/**
	 * Reinicia los valores de Player a los anteriores
	 * para que el jugador repita el nivel
	 */
	protected void repeatLevel() {
		this.setSet(1);
		this.wordQuantity -= this.wordLvl;
		this.guessedWords = new ArrayList<>();
	}

	/**
	 * Metodo para acceder a la variable icon
	 */
	public JLabel getIcon() {
		return icon;
	}



	public void setIcon(JLabel icon) {
		this.icon = icon;
	}
	/**
	 * Metodo para acceder a la variable name
	 */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Metodo para acceder a la variable set
	 */
	public int getSet() {
		return set;
	}


	public void setSet(int set) {
		this.set = set;
	}
	/**
	 * Metodo para acceder a la variable level
	 */
	public int getLevel() {
		return this.level;
	}


	public int getWordLvl(){
		return this.wordLvl;
	}

	public int getWordQuantity(){
		return this.wordQuantity;
	}

	public void setWordQuantity(int wordquantity){
		this.wordQuantity = wordquantity;
	}
}
