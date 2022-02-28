package CIS350mineSweeper;

import java.util.Random;

public class Minesweeper {
	public int numMines;
	public int boardSize;
	private int[][] tileValues;
	public static final int MINE_VALUE = -1;
	
	public Minesweeper(int boardSize, int numMines) {
		tileValues = new int[boardSize][boardSize];
		this.boardSize = boardSize; 
		this.numMines = numMines; 
	}
	
	public int[][] getValueArray(){
		return tileValues;
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
	}
	
	public void generateTileValues() {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				int neighboringMines = 0;
				if(tileValues[i][j] != MINE_VALUE) {
					if(i != 0) { //all except leftmost tiles
						if(tileValues[i-1][j] == MINE_VALUE) neighboringMines++; //tile to the left of [i][j]
						if(j != 0) {
							if(tileValues[i-1][j-1] == MINE_VALUE) neighboringMines++;//tile to the upper left of [i][j]
						}
						if(j != boardSize-1) {
							if(tileValues[i-1][j+1] == MINE_VALUE) neighboringMines++; //tile to the lower left of [i][j]
						}
					}
					if(i != boardSize-1) { // all except rightmost tiles
						if(tileValues[i+1][j] == MINE_VALUE) neighboringMines++; //tile to right of [i][j]
						if(j != 0) {
							if(tileValues[i+1][j-1] == MINE_VALUE) neighboringMines++; //tile to upper right of [i][j]
						}
						if(j != boardSize -1) {
							if(tileValues[i+1][j+1] == MINE_VALUE) neighboringMines++; //tile to lower right of [i][j]
						}
					}
					if(j != 0) { //all except top tiles
						if(tileValues[i][j-1] == MINE_VALUE) neighboringMines++; //tile above [i][j]
					}
					if(j != boardSize -1) { //all except bottom tiles
						if(tileValues[i][j+1] == MINE_VALUE) neighboringMines++; //tile below [i][j]
					}
					tileValues[i][j] = neighboringMines;
				}
			}
		}
	}
	
	public void restart() {
		tileValues = new int[boardSize][boardSize];
		generateMines();
		generateTileValues();
	}
	
	
}
