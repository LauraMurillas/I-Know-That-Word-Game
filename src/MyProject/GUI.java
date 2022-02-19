package MyProject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
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

import components.*;
import components.Palabra;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */




/**
 * Clase GUI
 * contiene todos los elementos y diferentes interfaces
 */
public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	//Atributos
	private static int width = 800;
	private static int height = 600;
	private ImageIcon loadingImg = (new ImageIcon(GUI.class.getResource("/img/loadingGIF.gif")));
	private JButton exit = new JButton("X");
	private JButton start = new JButton("Iniciar");
	private JButton back = new JButton("Atras");
	private JButton newGame = new JButton("Nuevo Juego");
	private JButton loadGame = new JButton("Cargar Juego");
	private JButton rules = new JButton("Reglas");
	private JButton buttonYES = new JButton("SI");
	private JButton buttonNO = new JButton("NO");
	private JLabel user1, user2, user3, loading;
	private Player player;
	private RoundTextField roundTextField;
	private JFrame main;
	protected Game game;
	private Palabra palabra;
	private GameText text, title;
	private Listen listener = new Listen();
	//Informacion del usuario
	private ArrayList<PlayerInfo> playerGames;
	//Array de respuestas de usuario
	private ArrayList<String> respuestas = new ArrayList<>();
	//Array que contiene todas las palabras de la primera parte de cada nivel
	private ArrayList<String> wordsSet1 = new ArrayList<>();
	//Array que contiene todas las palabras de la segunda parte de cada nivel
	private ArrayList<String> wordsSet2 = new ArrayList<>();
	private Boolean loadSave, cancelTimer, writingWords;
	private int aciertos;


	// Metodos


	/**
	 * Constructor de la clase GUI, inicializa los valores de los atributos
	 * y establece las configuraciones de la interfaz
	 */
	public GUI(Game game) {
		this.game = game;

		//Caja de texto
		this.roundTextField = new RoundTextField(25);
		this.exit.setBounds(720, 0, 72, 72);

		this.loading = new JLabel();
		this.loading.setIcon(loadingImg);
		this.loading.setVisible(false);

		this.user1 = new JLabel();
		this.user1.setIcon(new ImageIcon(GUI.class.getResource("/img/User1.jpg")));
		this.user1.addMouseListener(listener);

		this.user2 = new JLabel();
		this.user2.setIcon(new ImageIcon(GUI.class.getResource("/img/User2.jpg")));
		this.user2.addMouseListener(listener);

		this.user3 = new JLabel();
		this.user3.setIcon(new ImageIcon(GUI.class.getResource("/img/User3.jpg")));
		this.user3.addMouseListener(listener);

		this.palabra = new Palabra("");

		//botones
		this.newGame.addMouseListener(listener);
		this.loadGame.addMouseListener(listener);
		this.rules.addMouseListener(listener);
		this.exit.addMouseListener(listener);
		this.start.addMouseListener(listener);
		this.start.setBounds(620, 530, 200, 50);
		this.back.addMouseListener(listener);
		this.back.setBounds(0, 530, 180, 50);
		this.buttonYES.addMouseListener(listener);
		this.buttonYES.setBounds(150, 500, 100, 50);
		this.buttonNO.addMouseListener(listener);
		this.buttonNO.setBounds(500, 500, 100, 50);

		//configuracion de la interfaz
		this.main = this;
		main.addMouseMotionListener(listener);
		main.addMouseListener(listener);
		this.main.setUndecorated(true);
		this.main.setVisible(true);
		this.main.setSize(width, height);
		this.main.setLocationRelativeTo(null);
		this.main.setResizable(false);
		this.main.setLayout(null);
		//inicia en Menu principal
		this.changeGUI("Main Menu");
	}


	/**
	 * Cambia la ventana y la interfaz llamando a las metodos correspondientes
	 * Y añade nuevos componentes dentro de los metodos
	 */
	protected void changeGUI(String change) {
		this.main.getContentPane().removeAll();

		switch (change) {
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
		}
		this.main.add(exit);
		this.main.revalidate();
		this.main.repaint();
	}

	/**
	 * Interfaz del Menu Principal, contiene los botones Juego Nuevo, Cargar Juego y Reglas.
	 */
	private void menu() {
		GameText title = new GameText("I KNOW THAT WORD!!", 32);
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
	 * Crea una nueva interfaz, contiene la caja de texto JTextField para ingresar el nombre de usuario
	 */
	private void newGame() {
		title = new GameText("Nombre del jugador", 20);
		title.setBounds(227, 46, 350, 60);

		text = new GameText("Seleccionar Personaje", 23);
		text.setBounds(170, 243, 460, 60);
		loadSave = false;

		this.roundTextField.setBounds(185, 130, 420, 50);
		this.roundTextField.removeActionListener(listener);
		this.user1.setBounds(75, 340, 160, 160);
		this.user2.setBounds(298, 340, 160, 160);
		this.user3.setBounds(517, 340, 160, 160);
		this.start.setBounds(620, 530, 200, 50);

		this.main.add(loading);
		this.main.add(title);
		this.main.add(text);
		this.main.add(start);
		this.main.add(back);
		this.main.add(user3);
		this.main.add(user2);
		this.main.add(user1);
		this.main.add(roundTextField);
	}

	/**
	 * Interfaz carga juego guardado
	 * Contiene todos los jugadores previos
	 */
	private void loadGame() {
		loadSave = true;
		GameText chooseGame = new GameText("Seleccionar Juego", 20);
		chooseGame.setBounds(230, 35, 300, 50);
		int x = 0;
		this.main.add(chooseGame);
		JPanel savedGames = new JPanel();
		savedGames.setLayout(null);
		savedGames.setOpaque(false);
		this.playerGames = new ArrayList<>();
		for (PlayerInfo games : game.getGames()) {
			games.setBounds(0, 130 * x, 600, 130);
			games.addMouseListener(listener);
			this.playerGames.add(games);
			savedGames.add(games);
			x++;
		}
		savedGames.setPreferredSize(new Dimension(600, 130 * x));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(savedGames);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton invisible = new JButton();
				invisible.setBackground(Color.BLACK);
				invisible.setFocusable(false);
				invisible.setBorder(null);
				return invisible;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton invisible = new JButton();
				invisible.setBackground(Color.BLACK);
				invisible.setFocusable(false);
				invisible.setBorder(null);
				return invisible;
			}

			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(61, 55, 234);
				this.trackColor = Color.BLACK;

			}
		});

		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
		scroll.setBounds(50, 100, 650, 400);
		scroll.setBorder(null);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		this.main.add(scroll);
		this.main.add(start);
		this.main.add(back);
	}

	/**
	 * Interfazde las Reglas
	 * Contiene las reglas basicas del juego
	 */
	private void rules() {
		GameText rulesText = new GameText("<html><div style='text-align: center;'>Tu objetivo es recodar el mayor numero"
				+ "<br>de palabras que puedas."
				+ "<br>"
				+ "<br>tendras que indicar si la palabra estaba o no"
				+ "<br>contenida en la serie de palabras a memorizar."
				+ "<br>Tienes 7 segundos para responder por cada palabra, "
				+ "<br>si se te pasa el tiempo cuenta como un error :c"
				+ "<br>"
				+ "<br> En cada nivel incrementa el porcentaje de aciertos "
				+ "<br> que debes tener para pasar al siguiente nivel."
				+ "<br> Buena suerte! </div></html>", 20);
		rulesText.setHorizontalAlignment(SwingConstants.CENTER);
		rulesText.setBounds(0, 84, 800, 302);

		GameText specialText = new GameText("Prueba tu memoria!!!", 32);
		specialText.setHorizontalAlignment(SwingConstants.CENTER);
		specialText.setForeground(new Color(61, 55, 234));
		specialText.setBounds(0, 441, 800, 52);

		this.main.add(rulesText);
		this.main.add(specialText);
		this.main.add(back);
	}

	/**
	 * Interfaz de Juego
	 * Muestra las palabras y la informacion del jugador
	 */
	private void game() {
		GameText level = new GameText("Nivel: " + player.getLevel(), 32);
		writingWords = false;
		level.setBounds(17, 12, 700, 57);
		GameText set = new GameText("Parte: " + player.getSet(), 32);
		set.setBounds(400, 12, 250, 57);
		game.showPalabras(palabra, player.getName(), this);

		if (player.getSet() == 1) {
			this.main.add(palabra);
			this.main.add(level);
			this.main.add(set);
			this.wordsSet1 = player.levelWords();
		} else {
			this.main.add(palabra);
			this.main.add(level);
			this.main.add(set);
			this.wordsSet2 = player.levelWords();
			this.main.add(buttonNO);
			this.main.add(buttonYES);
		}
	}

	/**
	 * Este metodo muestra las interfaces entre PARTES del juego.
	 */
	protected void writeWords() {
		if (this.player.getSet() == 1) {
			GameText title = new GameText("<html><div style='text-align: center;'>Es tu turno de responder"
					+ "<br> BUENA SUERTE! </div></html>", 50);
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(0, 84, 800, 302);
			cancelTimer = false;
			writingWords = true;
			this.main.add(timer());
			this.main.add(title);
		} else {
			GameText title = new GameText("<html><div style='text-align: center;'>Espera un poco..."
					+ "</div></html>", 50);
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(0, 84, 800, 302);
			cancelTimer = false;
			writingWords = true;
			this.main.add(timer());
			this.main.add(title);
		}
	}

	/**
	 * Metodo que muestra los resultados despues de la segunda parte de cada nivel
	 * Muestra losaciertos necesarios para pasar el nivel y los obtenidos por el usuario
	 */
	protected void levelValidation() {
		GameText passText = new GameText("Aciertos para pasar nivel:", 32);
		passText.setBounds(100, 195, 800, 100);

		GameText wordQuantity = new GameText(String.valueOf(game.quantityToPass(player)), 32);
		wordQuantity.setBounds(100, 250, 400, 100);
		wordQuantity.setHorizontalAlignment(SwingConstants.CENTER);

		GameText correctText = new GameText("Aciertos obtenidos:", 32);
		correctText.setBounds(100, 310, 600, 100);

		text = new GameText("--", 32);
		text.setBounds(100, 370, 400, 100);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		this.exit.removeMouseListener(listener);

		this.compareWordsSets();
		showGuessedWords();

		this.main.add(passText);
		this.main.add(wordQuantity);
		this.main.add(correctText);
		this.main.add(text);
		this.respuestas.clear();

	}

	/**
	 * Interfaz de Fin de juego (Felicitaciones)
	 */
	private void endGame() {
		GameText title = new GameText("Juego Finalizado", 32);
		title.setBounds(130, 46, 581, 76);

		JLabel userIcon = new JLabel();
		userIcon.setIcon(new ImageResize(player.getIcon().getIcon(), 215, 215).resize());
		userIcon.setBounds(99, 192, 215, 215);

		GameText congratulations = new GameText("Felicitaciones", 32);
		congratulations.setBounds(340, 261, 357, 50);

		GameText playerName = new GameText(player.getName() + "!", 32);
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setBounds(340, 311, 357, 50);

		this.main.add(title);
		this.main.add(userIcon);
		this.main.add(congratulations);
		this.main.add(playerName);
		this.main.add(back);
	}

	/**
	 * Metodo que valida los resultados
	 * Se utilizan las variables words (las palabras acertadas) y quantity para validar si paso el nivel
	 */
	private void showGuessedWords() {
		int Aciertos = this.aciertos;
		GameText result = new GameText("", 50);
		result.setBounds(48, 243, 800, 114);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int words = Aciertos;
			int quantity = 0;
			boolean nextLevel = true;

			@Override
			public void run() {
				if (words == -1) {
					main.getContentPane().removeAll();
					//Si la cantidad de palabras adivinadas es mayor o igual a la cantidad
					// de palabras minima para pasar de nivel
					if (quantity >= game.quantityToPass(player)) {
						//Si es el nivel 10 ya gano
						if (player.getLevel() == 10) {
							result.setText("JUEGO TERMINADO!");
						} else { //Si no es el nivel 10, pasa de nivel
							result.setText("SIGUIENTE NIVEL!");
						}
					//Si la cantidad de palabras adivinadas no es mayor a igual a la cantidad
					//minima para pasar de nivel
					} else {
						nextLevel = false;
						result.setText("NO FUE SUFICIENTE");
					}
					exit.addMouseListener(listener);
					main.add(result);
					main.revalidate();
					main.repaint();
				} else if (words < -5) {
					if (nextLevel) {
						if (player.getLevel() == 10) {
							player.finishedGame = true;
							changeGUI("End Game");
						} else {
							player.nextLevel();
							changeGUI("Game");
						}
					} else {
						player.repeatLevel();
						changeGUI("Game");
					}
					timer.cancel();
				} else if (words >= 0) {
					//Si los aciertos son mayores o iguales a 0
					text.setText(String.valueOf(quantity));
					quantity++;
				}
				//disminuye los aciertos en 1
				words--;
			}

		}, 2000, 1000);
	}

	/**
	 * Timer del usuario para que responda (Botones SI y NO)
	 */
	private JLabel timer() {
		GameText timerLabel = new GameText("Tiempo: " + 10, 25);
		timerLabel.setBounds(20, 20, 200, 50);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int time = 10;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (cancelTimer) {
					timer.cancel();
				}
				if (time == 0) {
					if (player.getSet() == 1) {
						player.nextSet();
						changeGUI("Game");
					} else {
						changeGUI("Validate Level");
					}
					timer.cancel();
				}
				time--;
				timerLabel.setText("Tiempo: " + time);
			}

		}, 1500, 1000);
		return timerLabel;
	}

	/**
	 * FeedBack para el usuario cuando selecciona un personaje
	 */
	private void selectedCharacter(JLabel character) {
		loading.setVisible(true);
		loading.setBounds(character.getBounds().x - 5, character.getBounds().y - 5, 176, 176);
	}


	/**
	 * Llena un Array con las respuestas del usuario
	 */
	private void fillRespuestas(String word) {
		this.respuestas.add(word);
	}

	/**
	 * Hace una busqueda en el Array de las palabras de la parte 1 de cada nivel (palabras a recordar)
	 * Si la respuesta del usuario esta en el array (es un acierto) retorna True
	 * de lo contrario (Es un error) retorna False.
	 */
	public boolean compareWord(String word) {
		for (String words : this.wordsSet1) {
			if (words.toLowerCase().equals(word.toLowerCase())) {
				return true;
			}
		}
		return false;
	}



	/**
	 * Este metodo se encarga de almacenar los aciertos comparando los arrays de las palabras y las respuestas
	 */

	private void compareWordsSets() {
		this.aciertos = 0;
		//Si el tamaño del Array de las respuestas es igual o mayor al array de la parte 2
		if (this.respuestas.size() == this.wordsSet2.size() || this.respuestas.size() > this.wordsSet2.size()) {
			//mientras i sea menor que el Array de la parte 2
			for (int i = 0; i < this.wordsSet2.size(); i++) {
				//Si la palabra SI esta en el Array de la parte 1 y 2  Y  el usuario dio click al boton SI
				if (this.compareWord(this.wordsSet2.get(i)) && (this.respuestas.get(i) == "SI")) {
					//Se suma un acierto
					this.aciertos++;
				} else if (this.compareWord(this.wordsSet2.get(i)) == false && (this.respuestas.get(i) == "NO")) {
					//Si la palabra NO esta en el Array de la parte 1 y 2  Y  el usuario dio click al boton NO
					//Se suma un acierto
					this.aciertos++;
				}
			}
		//Si el tamaño del Array de las respuestas es menor al array de la parte 2
		} else if (this.respuestas.size() < this.wordsSet2.size()) {
			for (int i = 0; i < this.respuestas.size(); i++) {
				if (this.compareWord(this.wordsSet2.get(i)) && (this.respuestas.get(i) == "SI")) {
					this.aciertos++;
				} else if (this.compareWord(this.wordsSet2.get(i)) == false && (this.respuestas.get(i) == "NO")) {
					this.aciertos++;
				}
			}
		}
		System.out.println("Aciertos: " + aciertos);
	}


	/**
	 * Clase listener implementa las escuchas del Mouse
	 */
	class Listen implements MouseListener, ActionListener, MouseMotionListener {
		private JLabel icon;
		private PlayerInfo info;
		private int x, y;
		public int wordLvl;
		public int wordQuantity;

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			if (e.getSource() == exit) {
				System.exit(0);

				wordLvl = player.getWordLvl();
				wordQuantity = player.getWordQuantity();
				if (player.getSet() == 2) {
					wordQuantity -= wordLvl;
					player.setSet(1);
					player.setWordQuantity(wordQuantity);
				} else {
					if (player != null && writingWords) {
						for (String word : player.levelWords()) {
							if (player.guessedWords.contains(word)) {
								player.guessedWords.remove(word);
								player.totalGuessed--;
							}
						}
					}
				}
				game.saveGame();
				System.exit(0);
			} else if (e.getSource() == newGame) {
				changeGUI("New Game");
			} else if (e.getSource() == loadGame) {
				changeGUI("Load Game");
			} else if (e.getSource() == user1 || e.getSource() == user2 || e.getSource() == user3) {
				icon = (JLabel) e.getSource();
				selectedCharacter(icon);
			} else if (e.getSource() == back) {
				loading.setVisible(false);
				icon = null;
				changeGUI("Main Menu");
			} else if (e.getSource() == start && !loadSave) {
				boolean emptyText = roundTextField.getText() == "" || roundTextField.getText() == " " || roundTextField.getText().isEmpty();
				if (icon == null || emptyText) {
					Color orange = Color.BLUE;
					if (icon == null && emptyText) {
						text.setForeground(orange);
						title.setForeground(orange);
					} else if (emptyText) {
						title.setForeground(orange);
					} else {
						text.setForeground(orange);
					}
				} else {
					game.createPlayer(roundTextField.getText(), icon);
					player = game.getPlayer(roundTextField.getText());
					changeGUI("Game");
				}
			} else if (e.getSource() instanceof PlayerInfo) {
				info = (PlayerInfo) e.getSource();
				for (PlayerInfo playerGame : playerGames) {
					if (!playerGame.equals(info)) {
						playerGame.setBorder(null);
					}
				}
				info.selected();
				main.revalidate();
				main.repaint();
			} else if (e.getSource() == start && loadSave && info != null) {
				player = info.getPlayer();
				if (player.finishedGame) {
					changeGUI("End Game");
				} else {
					changeGUI("Game");
				}
			} else if (e.getSource() == rules) {
				changeGUI("Rules");
			} else if (e.getSource() == buttonYES) {
				fillRespuestas("NO");
			} else if (e.getSource() == buttonNO) {
				fillRespuestas("SI");
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
			if (e.getSource() instanceof JButton) {
				main.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
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

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			main.setLocation(main.getLocation().x + e.getX() - x, main.getLocation().y + e.getY() - y);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}

