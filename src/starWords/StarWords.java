package starWords;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * @author Ingrid Echeverri
 * @version 2.0
 * Main class that reads game data and runs the interface
 */ 
public class StarWords {
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
				WGUI game = new WGUI(data);
			}
			
		});
	}

}
