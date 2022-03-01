package CIS350mineSweeper;

import java.util.Random;

/**
 * A class that provides the back-end game logic for GAMEGUI.java.
 * @author Ryley Rawlings, Kaden Wende, and Andrew Arent
 * @version 1.0
 */
public class Minesweeper {
	public int numMines;
	public int boardSize;
	private int[][] tileValues;
	public static final int MINE_VALUE = -1;
/**
 * A constructor responsible for creating an int array to store the tile values.
 * 
 * @param boardSize 
 * @param numMines
 * 
 */
	public Minesweeper(int boardSize, int numMines) {
		tileValues = new int[boardSize][boardSize];
		this.boardSize = boardSize; 
		this.numMines = numMines; 
	}

	/**
	 * A method for getting the array of tile values.
	 * @return a 2d array on integers representing the values on the game board
	 */
	public int[][] getValueArray(){
		return tileValues;
	}

	/**
	 * A method for getting a specific tile's value.
	 * @param xVal the x value of the tile whose you want returned
	 * @param yVal the y value of the tile whose value you want returned
	 * @return returns an int, the value of the tile [xVal][yVal]
	 */
	public int getTileValue(int xVal, int yVal) {
		return tileValues[xVal][yVal];
	}

	/**
	 * Generates mines on random tiles in the game board, a mine is signified by the value -1.
	 */
	public void generateMines() {
		Random rng = new Random();
		int placedMines = 0;
		while (placedMines < numMines) {
			int mineXVal = rng.nextInt(boardSize);
			int mineYVal = rng.nextInt(boardSize);
			if (tileValues[mineXVal][mineYVal] != MINE_VALUE) {
				tileValues[mineXVal][mineYVal] = MINE_VALUE; 
				placedMines++; 
			}
		}
	}

	/**
	 * Generates the tile values for the game board.
	 * Uses the number of surrounding mines to determine a tile's value.
	 */
	public void generateTileValues() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				int neighboringMines = 0;
				if (tileValues[i][j] != MINE_VALUE) {
					if (i != 0) { //all except leftmost tiles
						if (tileValues[i-1][j] == MINE_VALUE) {
							neighboringMines++;
						} //tile to the left of [i][j]
						if (j != 0) {
							if (tileValues[i-1][j-1] == MINE_VALUE) {
								neighboringMines++; 
							} //tile to the upper left of [i][j]
						}
						if (j != boardSize-1) {
							if (tileValues[i-1][j+1] == MINE_VALUE) {
								neighboringMines++; 
							} //tile to the lower left of [i][j]
						}
					}
					if (i != boardSize-1) { // all except rightmost tiles
						if (tileValues[i+1][j] == MINE_VALUE) {
							neighboringMines++; 
						} //tile to right of [i][j]
						if (j != 0) {
							if (tileValues[i+1][j-1] == MINE_VALUE) {
								neighboringMines++; 
							} //tile to upper right of [i][j]
						}
						if (j != boardSize -1) {
							if (tileValues[i+1][j+1] == MINE_VALUE) {
								neighboringMines++; 
							} //tile to lower right of [i][j]
						}
					}
					if (j != 0) { //all except top tiles
						if (tileValues[i][j-1] == MINE_VALUE) {
							neighboringMines++; 
						} //tile above [i][j]
					}
					if (j != boardSize -1) { //all except bottom tiles
						if (tileValues[i][j+1] == MINE_VALUE) {
							neighboringMines++; 
						} //tile below [i][j]
					}
					tileValues[i][j] = neighboringMines;
				}
			}
		}
	}

	/**
	 * restarts the game board.
	 * creates a new int[][] and regenerates mines and tile values.
	 */
	public void restart() {
		tileValues = new int[boardSize][boardSize];
		generateMines();
		generateTileValues();
	} 


}
