package starWords;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import components.Comet;
import components.GameTable;
import components.GameText;
import components.ImageResize;
import components.PlayerInfo;
import components.RoundTextField;
/**
 * @author Ingrid Echeverri
 * @version 2.0
 * Class that is the game interface
 * contains all the elements, and diferent scenes.
 */

public class WGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	//Atributes
	private static int width = 800;
	private static int height = 600;
	//private static ImageIcon backgroundImg = (new ImageResize(new ImageIcon(GUI.class.getResource("/img/StarBackground.gif")), WIDTH, HEIGHT)).resize();
	//private static ImageIcon planetBackgroundImg = (new ImageResize(new ImageIcon(GUI.class.getResource("/img/planetBackground.gif")), WIDTH, HEIGHT)).resize();
	//private ImageIcon circleImg = (new ImageIcon(GUI.class.getResource("/img/circle.gif")));
	private JLabel background = new JLabel();
	private JButton exit = new JButton("X");
	private JButton start = new JButton("Start");
	private JButton back =new JButton("Back");
	private JButton newGame = new JButton("New Game");
	private JButton loadGame = new JButton("Load Game");
	private JButton rules = new JButton("Rules");
	private JButton topPlayers;
	private JLabel male, female, cat, circle;
	private Player player;
	private RoundTextField roundTextField;
	private JFrame main;
	protected Game game;
	private Comet comet;
	private GameTable table;
	private GameText text, title;
	private Listen listener = new Listen();
	private ArrayList<PlayerInfo> playerGames;
	private Boolean loadSave, cancelTimer, writingWords;
	
	//Methods
	/**
	 * GUI Constructor, inicialces the attributes values and sets interface size.
     * @param game <- Contians game data
     */
	public WGUI(Game game) {
		this.game = game;
		this.roundTextField = new RoundTextField(25);
		this.add(background);
		this.background.setBounds(0, 0, WIDTH, HEIGHT);
		this.exit.setBounds(720,0,72,72);
		//this.circle = new JLabel();
		//this.circle.setIcon(circleImg);
		//this.circle.setVisible(false);
		//this.male = new JLabel();
		//this.male.setIcon(new ImageIcon(GUI.class.getResource("/img/Men.png")));
		//this.male.addMouseListener(listener);
		//this.female = new JLabel();
		//this.female.setIcon(new ImageIcon(GUI.class.getResource("/img/Woman.png")));
		//this.female.addMouseListener(listener);
		//this.cat = new JLabel();
		//this.cat.setIcon(new ImageIcon(GUI.class.getResource("/img/Cat.png")));
		//this.cat.addMouseListener(listener);
		this.comet = new Comet("");
		this.newGame.addMouseListener(listener);
		this.loadGame.addMouseListener(listener);
		this.rules.addMouseListener(listener);
		this.exit.addMouseListener(listener);
		this.start.addMouseListener(listener);
		this.start.setBounds(620,530, 200,50);
		this.back.addMouseListener(listener);
		this.back.setBounds(0,530, 180,50);
		this.main = this;
		main.addMouseMotionListener(listener);
		main.addMouseListener(listener);
		this.main.setUndecorated(true);
		this.main.setVisible(true);
		this.main.setSize(width, height);
		this.main.setLocationRelativeTo(null);
		this.main.setResizable(false);
		this.main.setLayout(null);
		this.changeGUI("Main Menu");

	}
	/**
	 * Changes the Window that is shown by deleting everything
	 * and adding new components inside the methods.
	 * @param change
	 */
	protected void changeGUI(String change) {
		this.main.getContentPane().removeAll();
		
		//if(background.getIcon() == planetBackgroundImg) {
		//	background.setIcon(backgroundImg);
		//}
		
		switch(change) {
			case "Main Menu":
				menu();
				break;
			case "New Game":
				newGame();
				break;
			case "Load Game":
				loadGame();
				break;
			case "Game":
				game();
				break;
			case "Write Words":
				writeWords();
				break;
			case "Validate Level":
				levelValidation();
				break;
			case "End Game":
				endGame();
				break;
			case "Rules":
				rules();
				break;
			case "Top Players":
				topPlayers();
				break;
			
		}
		this.main.add(exit);
		this.main.add(background);
		this.main.revalidate();
		this.main.repaint();
	}
	/**
	 * Main Menu GUI, contains the buttons to start, load games and rules.
	 */
	private void menu() {
		GameText title = new GameText("STAR WORDS", 72);
		title.setBounds(147, 56, 510, 86);
		
		this.newGame.setBounds(24, 180, 243, 243);
		this.loadGame.setBounds(279, 250, 243, 243);
		this.rules.setBounds(522, 310, 243, 243);
		
		this.newGame.addMouseListener(listener);
		this.loadGame.addMouseListener(listener);
		
		this.main.add(newGame);
		this.main.add(loadGame);
		this.main.add(rules);
		this.main.add(title);
	}
	/**
	 * Create New Game GUI, contains a JTextField so the user can input the name
	 */
	private void newGame() {
		title = new GameText("Player Name", 48);
		title.setBounds(227, 46, 350, 60);
		
		text = new GameText("Choose Character", 48);
		text.setBounds(170, 243, 460, 60);
		loadSave = false;
		
		this.roundTextField.setBounds(185, 130, 420, 50);
		this.roundTextField.removeActionListener(listener);
		//this.male.setBounds(75, 340, 160, 160);
		//this.female.setBounds(298, 340, 160, 160);
		//this.cat.setBounds(517, 340, 160, 160);
		this.start.setBounds(620,530, 200,50);
		
		this.main.add(circle);
		this.main.add(title);
		this.main.add(text);
		this.main.add(start);
		this.main.add(back);
		this.main.add(cat);
		this.main.add(female);
		this.main.add(male);
		this.main.add(roundTextField);
	}
	/**
	 * Load a Saved Game GUI
	 * Contains all the players game
	 */
	private void loadGame() {
		loadSave = true;
		GameText chooseGame = new GameText("Choose Game", 48);
		chooseGame.setBounds(230, 35, 360, 60);
		int x = 0;
		this.main.add(chooseGame);
		JPanel savedGames = new JPanel();
		savedGames.setLayout(null);
		savedGames.setOpaque(false);
		this.playerGames = new ArrayList<>();
		for(PlayerInfo games: game.getGames()) {
			games.setBounds(0, 130*x, 600, 130);
			games.addMouseListener(listener);
			this.playerGames.add(games);
			savedGames.add(games);
			x++;
		}
		savedGames.setPreferredSize(new Dimension(600, 130*x));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(savedGames);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected JButton createIncreaseButton(int orientation){
		    	JButton invisible = new JButton();
		    	invisible.setBackground(Color.BLACK);
		    	invisible.setFocusable(false);
		    	invisible.setBorder(null);
		    	return invisible;
		    }
		    
		    @Override
		    protected JButton createDecreaseButton(int orientation){
		    	JButton invisible = new JButton();
		    	invisible.setBackground(Color.BLACK);
		    	invisible.setFocusable(false);
		    	invisible.setBorder(null);
		    	return invisible;
		    }
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(234,55,234);
				this.trackColor = Color.BLACK;
				
			}
		});

		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
		scroll.setBounds(50, 100, 700, 400);
		scroll.setBorder(null);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		this.main.add(scroll);
		this.main.add(start);
		this.main.add(back);
	}
	/**
	 * Rules GUI
	 * contains the basic rules and a button to 
	 * see top players
	 */
	private void rules() {
		
		GameText rulesText = new GameText("<html><div style='text-align: center;'>Look and remember the most words"
				+ "<br>shown in the comets."
				+ "<br>"
				+ "<br>You must remember a quantity of"
				+ "<br>words to pass the level, if not it will"
				+ "<br>repeat."
				+ "<br>"
				+ "<br>Can you remember them all?</div></html>",30);
		rulesText.setHorizontalAlignment(SwingConstants.CENTER);
		rulesText.setBounds(0, 84, 800, 302);
		
		GameText specialText = new GameText("May the Force Be With You",36);
		specialText.setHorizontalAlignment(SwingConstants.CENTER);
		specialText.setForeground(new Color(228,255,61));
		specialText.setBounds(0, 441, 800, 52);
		
		topPlayers = new JButton("Top Players");
		topPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		topPlayers.setBounds(450,530, 350,50);
		topPlayers.addMouseListener(listener);
		
		this.main.add(rulesText);
		this.main.add(specialText);
		this.main.add(topPlayers);
		this.main.add(back);
	}
	/**
	 * Shows top 5 players in a list order
	 */
	private void topPlayers() {
		GameText title = new GameText("Guessed Words", 48);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, 44, 800, 40);
		
		ArrayList<Player> playersOrganized = game.getPlayers();
		for(int i=0; i < playersOrganized.size(); i++) {
			for(int j=0; j < playersOrganized.size(); j++) {
				if(playersOrganized.get(i) != playersOrganized.get(j)) {
					if(playersOrganized.get(i).totalGuessed > playersOrganized.get(j).totalGuessed) {
						Collections.swap(playersOrganized,i,j);
					}
				}
			}
		}
		
		
		this.main.add(title);
		int size = (playersOrganized.size() > 5) ? 5 : playersOrganized.size();
		for(int i=0; i < size; i++) {
			GameText info = new GameText(i+1 + ". " + playersOrganized.get(i).getName() + " -> " + playersOrganized.get(i).totalGuessed, 36);
			info.setBounds(130, 160+(i*60), 620, 45);
			this.main.add(info);
		}
		
		this.main.add(back);
		
	}
	/**
	 * Game GUI
	 * Shows the comets and level info
	 */
	private void game() {
		GameText level = new GameText("Level: " + player.getLevel(), 36);
		writingWords = false;
		level.setBounds(17, 12, 300, 57);
		GameText set = new GameText("Set: " + player.getSet(), 36);
		set.setBounds(600, 12, 150, 57);
		game.showComets(comet, player.getName(), this);
		this.main.add(comet);
		this.main.add(level);
		this.main.add(set);
	}
	/**
	 * GUI that allows player to write words
	 * and if they are correct it adds them to
	 * a JTable
	 */
	protected void writeWords() {
		
		GameText title = new GameText("Words",48);
		title.setBounds(300, 50, 170, 55);
		cancelTimer = false;
		writingWords = true;
		
		roundTextField = new RoundTextField(25);
		roundTextField.setBounds(115, 140, 550, 50);
		roundTextField.addActionListener(listener);
		
		table = new GameTable(player.levelWords().size()/2,2);
		table.setTableSize(550, 300);
		table.setLocation(115, 210);
		
		//this.background.setIcon(planetBackgroundImg);
		this.main.add(timer());
		this.main.add(title);
		this.main.add(roundTextField);
		this.main.add(table);
	}
	/**
	 * Shows player inputted words and checks
	 * if the amount of words are valid to pass level
	 */
	protected void levelValidation() {
		GameText passText = new GameText("Words to pass level:" , 36);
		passText.setBounds(400, 195, 400, 65);
		
		GameText wordQuantity = new GameText(String.valueOf(game.quantityToPass(player)), 36);
		wordQuantity.setBounds(400, 250, 400, 50);
		wordQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		
		GameText correctText = new GameText("Correct Words", 36);
		correctText.setBounds(400, 310, 400, 50);
		
		text = new GameText("--", 36);
		text.setBounds(400, 370, 400, 50);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		
		if(player.getGuessedWords().size() != 0) {
			double rows =Math.ceil((float)player.getGuessedWords().size()/2);
			table = new GameTable((int)rows, 2);
			table.setTableSize(380, 420);
			table.setLocation(15, 90);
		}
		this.exit.removeMouseListener(listener);
		
		showGuessedWords();
		
		this.main.add(passText);
		this.main.add(wordQuantity);
		this.main.add(correctText);
		this.main.add(text);
		this.main.add(table);
		
	}
	/**
	 * Show end Game congratulation GUI
	 */
	private void endGame() {
		//ImageIcon confenttiImg = (new ImageIcon(GUI.class.getResource("/img/Confetti.gif")));
		//JLabel confetti = new JLabel();
		//confetti.setIcon(confenttiImg);
		//confetti.setBounds(50, -90, 800, 800);
		
		GameText title = new GameText("Game Finished", 64);
		title.setBounds(130, 46, 581, 76);
		
		JLabel userIcon = new JLabel();
		userIcon.setIcon(new ImageResize(player.getIcon().getIcon(),215,215).resize());
		userIcon.setBounds(99, 192, 215, 215);
		
		GameText congratulations = new GameText("Congratulations", 42);
		congratulations.setBounds(340, 261, 357, 50);
		
		GameText playerName = new GameText(player.getName() + "!", 42);
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setBounds(340, 311, 357, 50);
		
		//this.main.add(confetti);
		this.main.add(title);
		this.main.add(userIcon);
		this.main.add(congratulations);
		this.main.add(playerName);
		this.main.add(back);
	}
	/**
	 * Timer that shows the guessed words every second
	 */
	private void showGuessedWords() {
		ArrayList<String> guessedWords = player.getGuessedWords();
		GameText result = new GameText("", 96);
		result.setBounds(58, 243, 800, 114);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int words = guessedWords.size()-1;
			int quantity = 1;
			boolean nextLevel = true;
			@Override
			public void run() {
				if(words == -1) {
					main.getContentPane().removeAll();
					if(quantity >= game.quantityToPass(player)) {
						if(player.getLevel() == 5) {
							result.setText("GAME DONE!");
						}else {
							result.setText("NEXT LEVEL!");
						}
						
					}else {
						nextLevel = false;
						result.setText("NOT ENOUGH");
					}
					exit.addMouseListener(listener);
					main.add(result);
					main.add(background);
					main.revalidate();
					main.repaint();
				}else if(words < -5){
					if(nextLevel) {
						if(player.getLevel() == 5) {
							player.finishedGame = true;
							changeGUI("End Game");
						}else {
							player.nextLevel();
							changeGUI("Game");
						}
					}else {
						player.repeatLevel();
						changeGUI("Game");
					}
					timer.cancel();
				}
				else if(words >= 0){
					table.addWord(guessedWords.get(words));
					text.setText(String.valueOf(quantity));
					quantity++;
				}
				words--;
			}
			
		}, 2000, 1000);
	}
	/**
	 * Timer that counts the time the player has
	 * to inputt the words
	 * @return
	 */
	private JLabel timer() {
		GameText timerLabel = new GameText("Timer: " + 60, 36);
		timerLabel.setBounds(20, 20, 200, 50);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int time = 60;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(cancelTimer) {
					timer.cancel();
				}
				if(time == 0) {
					if(player.getSet() == 1) {
						player.nextSet();
						changeGUI("Game");
					}else {
						changeGUI("Validate Level");
					}
					timer.cancel();
				}
				time--;
				timerLabel.setText("Timer: " + time);
			}
			
		}, 1500, 1000);
		return timerLabel;
	}
	/**
	 * Makes the JButtons with desired font
	 * @param text
	 * @param size
	 * @return JButton
	 */
	private static JButton gameButton(String text, float size) {
		JButton button = new JButton();
		button.setText(text);
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setFont(GameText.nasalization.deriveFont(size));
		button.setContentAreaFilled(false);
		return button;
	}
	/**
	 * Shows a circle in the selected character
	 * @param character
	 */
	private void selectedCharacter(JLabel character) {
		circle.setVisible(true);
		circle.setBounds(character.getBounds().x-5, character.getBounds().y-5, 176, 176);
	}
	/**
	 * Creates the planet button
	 * @param planet
	 * @return JButton
	 */
	private static JButton planetButton(ImageIcon planet) {
		JButton button = new JButton();
		button.setIcon(planet);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
		return button;
	}
	/**
	 * Class Listener that does all the clicking and mouse methods.
	 * @author Ingrid E
	 *
	 */
	class Listen implements MouseListener, ActionListener, MouseMotionListener{
		private JLabel icon;
		private PlayerInfo info;
		private int x,y;
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == exit) {
				if(player != null && writingWords) {
					for(String word: player.levelWords()) {
						if(player.guessedWords.contains(word)) {
							player.guessedWords.remove(word);
							player.totalGuessed--;
						}
					}
				}
				game.saveGame();
				System.exit(0);
			}else if(e.getSource() == newGame) {
				changeGUI("New Game");
			}else if(e.getSource() == loadGame) {
				changeGUI("Load Game");
			}else if(e.getSource() == male || e.getSource() == female || e.getSource() == cat) {
				icon = (JLabel)e.getSource();
				selectedCharacter(icon);
			}else if(e.getSource() == back) {
				circle.setVisible(false);
				icon = null;
				changeGUI("Main Menu");
			}else if(e.getSource() == start && !loadSave) {
				boolean emptyText = roundTextField.getText() == "" || roundTextField.getText() == " " || roundTextField.getText().isEmpty();
				if(icon == null || emptyText) {
					Color orange = Color.ORANGE;
					if(icon == null && emptyText) {
						text.setForeground(orange);
						title.setForeground(orange);
					}else if(emptyText) {
						title.setForeground(orange);
					}else {
						text.setForeground(orange);
					}
				}
				else {
					game.createPlayer(roundTextField.getText(), icon);
					player = game.getPlayer(roundTextField.getText());
					changeGUI("Game");
				}
			}else if(e.getSource() instanceof PlayerInfo) {
				info = (PlayerInfo)e.getSource();
				for(PlayerInfo playerGame: playerGames) {
					if(!playerGame.equals(info)) {
						playerGame.setBorder(null);
					}
				}
				info.selected();
				main.revalidate();
				main.repaint();
			}else if(e.getSource() == start && loadSave && info != null) {
				player = info.getPlayer();
				if(player.finishedGame) {
					changeGUI("End Game");
				}else {
					changeGUI("Game");
				}
			}else if(e.getSource() == rules) {
				changeGUI("Rules");
			}else if(e.getSource() == topPlayers) {
				changeGUI("Top Players");
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() instanceof JButton) {
				main.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else {
				main.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String word = roundTextField.getText();
			if(player.isValid(word) && !player.sameWord(word)) {
				table.addWord(word);
				player.addGuessedWord(word);
				roundTextField.setText("");
				
				if(table.isFull()) {
					table.setForeground(Color.GREEN);
					cancelTimer = true;
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						int counter = 0;
						@Override
						public void run() {

							if(counter >=3) {
								if(player.getSet() == 1) {
									player.nextSet();
									changeGUI("Game");
								}else {
									changeGUI("Validate Level");
								}
								table.setForeground(Color.WHITE);
								timer.cancel();
							}
							counter++;
							
						}
						
					}, 1000, 1000);
					
				}
				
			}else{
				roundTextField.wrongAnimation();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			main.setLocation(main.getLocation().x+e.getX()-x, main.getLocation().y +e.getY()-y);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
