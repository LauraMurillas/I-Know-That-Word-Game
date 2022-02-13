package components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author Ingrid Echeverri
 * Class that creates a JTable, but expands the cells 
 * to the table size and fills it. Adds words.
 *
 */
public class GameTable extends JTable{
	//Atributes
	private static final long serialVersionUID = 1L;
	private int rows, columns;
	private static Font font = GameText.nasalization;
	private float fontSize;
	//Methods
	/**
	 * GameTable constructor
	 * @param rows 
	 * @param columns
	 */
	public GameTable(int rows, int columns) {
		super(new DefaultTableModel(rows, columns));
		this.rows = rows;
		this.columns = columns;
		this.fontSize = 64;
		this.setForeground(Color.WHITE);
		this.setFont(font.deriveFont(64));
		this.setRowSelectionAllowed(false);
		this.setColumnSelectionAllowed(false);
		this.setCellSelectionEnabled(false);
		this.setFocusable(false);
		this.setOpaque(false);
		this.setShowGrid(false);
	}
	/**
	 * Sets the table size and makes the cell width and height
	 * relative to the container to fill it evenly.
	 * @param width
	 * @param height
	 */
	public void setTableSize(int width, int height) {
		this.setSize(width, height);
		this.setRowHeight(height/this.rows);
		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
		cellRender.setHorizontalAlignment(JLabel.CENTER);
		cellRender.setOpaque(false);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		for(int i=0; i < this.columns; i++) {
			this.getColumnModel().getColumn(i).setMinWidth(width/this.columns);
			this.getColumnModel().getColumn(i).setCellRenderer(cellRender);
		}
		this.getColumnModel().getColumn(this.columns-1).setMaxWidth(Integer.MAX_VALUE);
	}
	/**
	 * Adds a word in the table in the next null position it finds
	 * @param word
	 * @return boolean
	 */
	public void addWord(String word) {
		for(int i= 0; i < this.rows; i++) {
			for(int j=0; j <this.columns; j++) {
				if(this.getModel().getValueAt(i, j) == null) {
					this.getModel().setValueAt(word, i, j);
					float wordFont = (float)(60-(word.length()*2.616));
					if(wordFont < fontSize) {
						fontSize = wordFont;
					}
					this.setFont(font.deriveFont(fontSize));
					return;
				}
			}
		}
	}
	
	public boolean isFull() {
		for(int i = 0; i < this.rows; i++) {
			for(int j=0; j < this.columns; j++) {
				if(this.getModel().getValueAt(i, j) == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	

}
