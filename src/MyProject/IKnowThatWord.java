package MyProject;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * I Know That Word Game
 * Laura Murillas 1944153  -  laura.murillas@correounivalle.edu.co
 * Manuel Cuellar 2041041  -  manuel.cuellar@correounivalle.edu.co
 * Version 1.0  / Date: 18/02/22
 */


/**
 * Clase principal que lee los datos del juego e inicia la interfaz
 * Si el archivo game-data no esta creado, lo crea en la carpeta data
 */
public class IKnowThatWord {
	public static Game data;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					ObjectInputStream readData = new ObjectInputStream(new FileInputStream("src/data/game-data"));
					Object gameData = readData.readObject();
					data = (Game) gameData;
					readData.close();
				} catch (IOException | ClassNotFoundException e) {
					System.out.println("No existe el archivo game-data, inicio desde 0");
					data = new Game();
				}
				
				@SuppressWarnings("unused")
				GUI game = new GUI(data);
			}
			
		});
	}

}
