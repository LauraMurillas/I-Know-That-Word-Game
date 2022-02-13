package starWords;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JLabel;
/**
 * @author Ingrid Echeverri
 * @version 2.0
 * Contains basic info of the game player and
 * the words assigned to it.
 */
public class Player implements Serializable{
	private static final long serialVersionUID = 1L;
	//Atributes
	private String name;
	private int level, set, wordPos, wordQuantity, wordLvl;
	protected int totalGuessed;
	private JLabel icon;
	private ArrayList<String> words;
	protected ArrayList<String> guessedWords;
	public boolean finishedGame;
	//Methods
	/**
	 * Player class constructor to create player profile
	 * @param name: String 

	 * @param icon: JLabel
	 * @param words: ArrayList<String>
	 */
	public Player(String name, JLabel icon, ArrayList<String> words) {
		this.setName(name);
		this.level = 1;
		this.setIcon(icon);
		this.words = words;
		this.setSet(1);
		this.wordPos = 0;
		this.wordQuantity = 4;
		this.finishedGame = false;
		this.wordLvl = 4;
		this.totalGuessed = 0;
		this.guessedWords = new ArrayList<>();
	}
	/**
	 * Returns player guessed words for that specific level
	 * @return ArrayList String
	 */
	public ArrayList<String> getGuessedWords(){
		return guessedWords;
	}
	/**
	 * Check if the word guessed already exists
	 * in the ArrayList wordGuessed
	 * @param word
	 * @return boolean
	 */
	public boolean sameWord(String word) {
		for(String words:this.guessedWords) {
			if(words.equalsIgnoreCase(word)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds a word to the wordGuessed
	 * @param word
	 */
	public void addGuessedWord(String word) {
		if(!guessedWords.contains(word)) {
			this.totalGuessed += 1;
			guessedWords.add(word);
		}
	}
	/**
	 * Returns the player words for that specific level and set
	 * @return ArrayList:String
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
	 * Adds a +1 level to the player and resets the set of
	 * words and the new word quantity
	 */
	protected void nextLevel() {
		this.level++;
		this.setSet(1);
		this.wordPos += this.wordLvl;
		this.wordLvl += 2;
		this.guessedWords.clear();
		this.wordQuantity += this.wordLvl;
	}
	/**
	 * Adds a +1 to the set of words shown and changes
	 * the word position to start in the required position.
	 */
	protected void nextSet() {
		this.setSet(this.getSet() + 1);
		this.wordPos += this.wordLvl;
		this.wordQuantity += this.wordLvl;
	}
	/**
	 * Check if the word that the user inputted exists
	 * in the words from that set and level to be added
	 * in the list
	 * @param word <- Word inputted by user
	 * @return boolean 
	 */
	public boolean isValid(String word) {
		for(String words: this.levelWords()) {
			if(words.toLowerCase().equals(word.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Resets player values to last values because the 
	 * player didn't pass the level and will start again
	 */
	protected void repeatLevel() {
		this.setSet(1);
		this.wordPos -= this.wordLvl;
		this.wordQuantity -= this.wordLvl;
		this.guessedWords = new ArrayList<>();
	}
	/**
	 * @return JLabel <- current player icon
	 */
	public JLabel getIcon() {
		return icon;
	}


	public void setIcon(JLabel icon) {
		this.icon = icon;
	}
	/**
	 * @return String <- current player name
	 */
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return int <- current player set
	 */
	public int getSet() {
		return set;
	}


	public void setSet(int set) {
		this.set = set;
	}
	/**
	 * @return int <- current player level
	 */
	public int getLevel() {
		return this.level;
	}
	
	
}
