package CIS350mineSweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The GameGUi.java class is a fully functioning GUI used to play the game MineSweeper.
 * This includes the creation of the GUI itself, as well as methods for winning, losing, 
 * and revealing tiles.
 * @author Ryley Rawlings, Kaden Wende, and Andrew Arent
 * @version 1.0
 */

public class GameGUI extends JFrame implements ActionListener{

	JButton restartButton = new JButton("Reset");
	JButton giveUpButton = new JButton("Give Up");
	JPanel bottomPanel = new JPanel();
	JPanel tilePanel = new JPanel(); 
	JButton[][] buttonGrid;
	Minesweeper game;

	/**
	 * Constructs a new GameGUI with a specified board size and number of mines.
	 * Creates all GUI components and places them on  a JFrame
	 * @param boardSize the number of tiles each side of the game board should have.
	 * @param numMines the number of mines that should be placed on the game board
	 */
	
	public GameGUI(int boardSize, int numMines) {
		super("MinesweeperGameGUI");

		this.game = new Minesweeper(boardSize, numMines);
		game.generateMines();
		game.generateTileValues();

		setSize(800, 800);
		setLayout(new BorderLayout()); 
		restartButton.addActionListener(this);
		giveUpButton.addActionListener(this);
		bottomPanel.add(giveUpButton);
		bottomPanel.add(restartButton);
		add(bottomPanel, BorderLayout.NORTH);

		buttonGrid = new JButton[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				buttonGrid[i][j] = new JButton();
				buttonGrid[i][j].addActionListener(this);
				tilePanel.add(buttonGrid[i][j]);
			}
		}
		tilePanel.setLayout(new GridLayout(boardSize, boardSize));
		add(tilePanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void checkIfWin() {
		int remainingClearTiles = 0;
		for (int i = 0; i < game.boardSize; i++) {
			for (int j = 0; j < game.boardSize; j++) {
				if (buttonGrid[i][j].isEnabled() == true 
						&& game.getTileValue(i, j) != Minesweeper.MINE_VALUE) remainingClearTiles++;
			}
		}
		if (remainingClearTiles == 0) {
			JOptionPane.showMessageDialog(null, "All Safe tiles revealed! You win!");
		}
	}

	private void restartBoard() {
		for (int i = 0; i < game.boardSize; i++) {
			for (int j = 0; j < game.boardSize; j++) {
				buttonGrid[i][j].setText("");
				buttonGrid[i][j].setEnabled(true); 
			}
		}
	}

	private void reveal(int x, int y) {
		buttonGrid[x][y].setText(""+game.getTileValue(x, y));
		buttonGrid[x][y].setEnabled(false);
		if (game.getTileValue(x, y) == 0) {
			//if tile is not a leftmost tile in a row
			if (x > 0) { 
				if (game.getTileValue(x-1, y) != Minesweeper.MINE_VALUE) {
					//tile to the left of [x][y]
					buttonGrid[x-1][y].setText(""+game.getTileValue(x-1, y)); 
					if (game.getTileValue(x-1, y) == 0 
							&& buttonGrid[x-1][y].isEnabled() != false){
						reveal(x-1, y);
					}
					buttonGrid[x-1][y].setEnabled(false);
				}
				//if tile is not an uppermost tile in a column
				if (y > 0){ 
					if (game.getTileValue(x-1, y-1) != Minesweeper.MINE_VALUE) {
						//tile to the upper left of [x][y]
						buttonGrid[x-1][y-1].setText("" 
						+ game.getTileValue(x-1, y-1)); 
						if (game.getTileValue(x-1, y-1) == 0 
								&& buttonGrid[x-1][y-1].isEnabled() != false){
							reveal(x-1, y-1);
						}
						buttonGrid[x-1][y-1].setEnabled(false);
					}
				} 
				//if tile is not the bottommost tile in a column
				if (y < game.boardSize -1) { 
					if (game.getTileValue(x-1, y) 
							!= Minesweeper.MINE_VALUE) {
						//tile to the lower left of [x][y]
						buttonGrid[x-1][y+1].setText("" 
						+ game.getTileValue(x-1, y+1)); 
						if (game.getTileValue(x-1, y+1) == 0 
								&& buttonGrid[x-1][y+1].isEnabled() != false){
							reveal(x-1, y+1);
						}
						buttonGrid[x-1][y+1].setEnabled(false);
					}
				}
			}
			//if tile is not rightmost tile in a row
			if (x < game.boardSize -1) { 
				if (game.getTileValue(x+1, y) != Minesweeper.MINE_VALUE) {
					//tile to the right of [x][y]
					buttonGrid[x+1][y].setText(""+game.getTileValue(x+1, y)); 
					if (game.getTileValue(x+1, y) == 0 
							&& buttonGrid[x+1][y].isEnabled() != false){
						reveal(x+1, y);
					}
					buttonGrid[x+1][y].setEnabled(false);
				}
				//if tile is not uppermost tile in a column
				if (y > 0) {
					if (game.getTileValue(x+1, y-1) != Minesweeper.MINE_VALUE) {
						//tile to the upper right of [x][y]
						buttonGrid[x+1][y-1].setText("" 
						+ game.getTileValue(x+1, y-1)); 
						if (game.getTileValue(x+1, y-1) == 0 
								&& buttonGrid[x+1][y-1].isEnabled() != false){
							reveal(x+1, y-1);
						}
						buttonGrid[x+1][y-1].setEnabled(false);
					}
				}
				//if tile is not bottommost tile in a column
				if (y < game.boardSize -1) { 
					if (game.getTileValue(x+1, y+1) != Minesweeper.MINE_VALUE) {
						//tile to the lower right of [x][y]
						buttonGrid[x+1][y+1].setText("" 
						+ game.getTileValue(x+1, y+1)); 
						if (game.getTileValue(x+1, y+1) == 0 
								&& buttonGrid[x+1][y+1].isEnabled() != false){
							reveal(x+1, y+1);
						}
						buttonGrid[x+1][y+1].setEnabled(false);
					}
				}
			}
			//if tile is not uppermost tile in a column
			if (y > 0) { 
				if (game.getTileValue(x, y-1) != Minesweeper.MINE_VALUE) {
					//tile above [x][y]
					buttonGrid[x][y-1].setText(""+game.getTileValue(x, y-1)); 
					if (game.getTileValue(x, y-1) == 0 && buttonGrid[x][y-1].isEnabled() != false){
						reveal(x, y-1);
					}
					buttonGrid[x][y-1].setEnabled(false);
				}
			}
			//if tile is not bottommost tile in a column
			if (y < game.boardSize -1) {
				if (game.getTileValue(x, y+1) != Minesweeper.MINE_VALUE) {
					buttonGrid[x][y+1].setText(""+game.getTileValue(x, y+1));
					if (game.getTileValue(x, y+1) == 0 && buttonGrid[x][y+1].isEnabled() != false){
						reveal(x, y+1);
					}
					buttonGrid[x][y+1].setEnabled(false);
				}
			}
		}
	}

	private void gameOver() {
		for (int i = 0; i < game.boardSize; i++) {
			for (int j = 0; j < game.boardSize; j++) {
				buttonGrid[i][j].setText("" + game.getTileValue(i, j));
				buttonGrid[i][j].setEnabled(false); 
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restartButton) {
			restartBoard();
			game.restart();
		} else if (e.getSource() == giveUpButton) {
			gameOver();
		} else {
			for (int i = 0; i < game.boardSize; i++) {
				for (int j = 0; j < game.boardSize; j++) {
					if (e.getSource() == buttonGrid[i][j]) {
						if (game.getTileValue(i, j) 
								!= Minesweeper.MINE_VALUE) {
							reveal(i, j);
							checkIfWin();
						} else {
							gameOver();
							JOptionPane.showMessageDialog
							(null, "You clicked on a mine. Game over.");
						}
					}
				}
			}
		}


	}

	public static void main(String[] args) {
		new GameGUI(15, 25);
	}

}
