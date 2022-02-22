package CIS350mineSweeper;

import java.util.Random;

public class Minesweeper {
	public int numMines;
	public int boardSize;
	private int[][] tileValues;
	private static final int MINE_VALUE = -1;
	
	public Minesweeper(int boardSize, int numMines) {
		tileValues = new int[boardSize][boardSize];
		this.boardSize = boardSize; 
		this.numMines = numMines; 
	}
	
	public int getTileValue(int xVal, int yVal) {
		return tileValues[xVal][yVal];
	}
	
	public void generateMines() {
		Random rng = new Random();
		int placedMines = 0;
		while(placedMines < numMines) {
			int mineXVal = rng.nextInt(boardSize);
			int mineYVal = rng.nextInt(boardSize);
			if(tileValues[mineXVal][mineYVal] != MINE_VALUE) {
				tileValues[mineXVal][mineYVal] = MINE_VALUE; 
				placedMines++; 
			}
		}
		System.out.println(placedMines);
	}
	
	public void generateTileValues() {
		
	}
	
	public void restart() {
		tileValues = new int[boardSize][boardSize];
		generateMines();
		generateTileValues();
	}
	
	
}
