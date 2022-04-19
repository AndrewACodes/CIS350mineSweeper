package CIS350mineSweeper;

import javax.swing.JButton;

public class MinesweeperButton extends JButton {
	
	private boolean flagged;
	
	public MinesweeperButton() {
		super();
		flagged = false;
	}
	
	public void setFlagged(boolean isFlagged) {
		flagged = isFlagged;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
}
