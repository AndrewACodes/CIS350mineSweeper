package CIS350mineSweeper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MinesweeperUnitTests {
	private Minesweeper minesweeper;
	private int[][] valueArray;
	
	@BeforeEach
	void createNewMinesweeper() {
		minesweeper = new Minesweeper(15,15);
		minesweeper.generateMines();
		minesweeper.generateTileValues();
		valueArray = minesweeper.getValueArray();
	}
	
	@Test
	void minesweeperConstructorTest() {
		assertEquals(15, minesweeper.boardSize);
		assertEquals(15, minesweeper.numMines);
		assertEquals(15, valueArray.length);
		assertEquals(15, valueArray[0].length);
	}
	
	@Test
	void getTileValueTest() {
		for(int i = 0; i < minesweeper.boardSize; i++) {
			for(int j = 0; j < minesweeper.boardSize; j++) {
				assertEquals(valueArray[i][j], minesweeper.getTileValue(i, j));
			}
		}
	}

	@Test
	void getTileValueInBoundsTest() {
		for(int k = 0; k < 1000; k++) {
			for(int i = 0; i < minesweeper.boardSize; i++) {
				for(int j = 0; j < minesweeper.boardSize; j++) {
					//tiles cannot have 9 or more surrounding bombs, or negative surrounding bombs; -1 is a bomb, however
					assertTrue(minesweeper.getTileValue(i,j) < 9 && minesweeper.getTileValue(i,j) > -2);				
				}
			}
		minesweeper = new Minesweeper(15,15);
		minesweeper.generateMines();
		minesweeper.generateTileValues();
		}
	}
	
	@Test
	void generateMinesCorrectNumMines() {
		int numMinesOnBoard = 0;
		for(int i = 0; i < minesweeper.boardSize; i++) {
			for(int j = 0; j < minesweeper.boardSize; j++) {
				if(minesweeper.getTileValue(i,j) == Minesweeper.MINE_VALUE) numMinesOnBoard++;
			}
		}
		assertEquals(minesweeper.numMines, numMinesOnBoard);
	}
	
	@Test
	void restartGivesDifferentBoard() {
		minesweeper.restart();
		boolean isDifferent = false;
		for(int i = 0; i < minesweeper.boardSize; i++) {
			for(int j = 0; j < minesweeper.boardSize; j++) {
				if(minesweeper.getTileValue(i, j) != valueArray[i][j]) isDifferent = true;
			}
		}
		assertTrue(isDifferent);
	}
}
