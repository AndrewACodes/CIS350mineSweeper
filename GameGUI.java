package CIS350mineSweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

/**
 * The GameGUi.java class is a fully functioning GUI used to play the game MineSweeper.
 * This includes the creation of the GUI itself, as well as methods for winning, losing, 
 * and revealing tiles.
 * @author Ryley Rawlings and Andrew Arent
 * @version 2.0
 */

public class GameGUI extends JFrame implements ActionListener{

	JLabel bombLabel = new JLabel("You clicked on a mine. Game over.");
	JLabel winLabel = new JLabel("All Safe tiles revealed! You win!");
	JLabel timerLabel = new JLabel("");
	JButton backButton = new JButton("Back");
	JButton restartButton = new JButton("Reset");
	JButton giveUpButton = new JButton("Give Up");
	JButton flagButton = new JButton("Flagging Mode");
	JButton revealButton = new JButton("Revealing Mode");
	JPanel topPanel = new JPanel();
	JPanel timerPanel = new JPanel();
	JPanel tilePanel = new JPanel(); 
	MinesweeperButton[][] buttonGrid;
	Minesweeper game;
	GameTimer gameTimer = new GameTimer();
	Timer timer = new Timer(0, new TimerListener());
	public final boolean ACTIVE_MODE = false;
	public final boolean INACTIVE_MODE = true;

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


		setSize(952, 1100);
		setBackground(SettingsData.backgroundC);

		UIManager.put("OptionPane.background", SettingsData.backgroundC);
		UIManager.put("OptionPane.messageForeground", SettingsData.textC);
		UIManager.put("Panel.background", SettingsData.backgroundC);
		UIManager.put("Button.background", SettingsData.primaryC);
		UIManager.put("Button.foreground", SettingsData.backgroundC);
		
		setLayout(new BorderLayout()); 
		backButton.addActionListener(this);
		restartButton.addActionListener(this);
		giveUpButton.addActionListener(this);
		flagButton.addActionListener(this);
		revealButton.addActionListener(this);
		backButton.setBackground(SettingsData.primaryC);
		restartButton.setBackground(SettingsData.primaryC);
		giveUpButton.setBackground(SettingsData.primaryC);
		flagButton.setBackground(SettingsData.primaryC);
		revealButton.setBackground(SettingsData.primaryC);
		backButton.setForeground(getBackground());
		restartButton.setForeground(getBackground());
		giveUpButton.setForeground(getBackground());
		flagButton.setForeground(getBackground());
		revealButton.setForeground(getBackground());
		
		topPanel.setBackground(getBackground());
		topPanel.add(backButton);
		topPanel.add(giveUpButton);
		topPanel.add(restartButton);
		topPanel.add(revealButton);
		add(topPanel, BorderLayout.NORTH);
		revealButton.setEnabled(ACTIVE_MODE);
		topPanel.add(flagButton);
		
		timerLabel.setFont(new Font(timerLabel.getFont().getName(), Font.PLAIN, 72));
		timerLabel.setText(gameTimer.toString());
		timerPanel.add(timerLabel);
		add(timerPanel, BorderLayout.SOUTH);
		
		buttonGrid = new MinesweeperButton[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				buttonGrid[i][j] = new MinesweeperButton();
				buttonGrid[i][j].addActionListener(this);
				buttonGrid[i][j].setBackground(SettingsData.surfaceC);
				tilePanel.add(buttonGrid[i][j]);
			}
		}
		tilePanel.setBackground(getBackground());
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
			timer.stop();
			JOptionPane.showMessageDialog(null, "All Safe tiles revealed! You win!");
		}
	}

	private void restartBoard() {
		timer.stop();
		gameTimer = new GameTimer();
		timerLabel.setText(gameTimer.toString());
		for (int i = 0; i < game.boardSize; i++) {
			for (int j = 0; j < game.boardSize; j++) {
				buttonGrid[i][j].setIcon(null);
				buttonGrid[i][j].setFlagged(false);
				buttonGrid[i][j].setText("");
				buttonGrid[i][j].setBackground(SettingsData.surfaceC);
				buttonGrid[i][j].setForeground(SettingsData.textC);
				buttonGrid[i][j].setEnabled(true); 
			}
		}
	}

	private void reveal(int x, int y) {
		buttonGrid[x][y].setIcon(null);
		buttonGrid[x][y].setFlagged(false);
		buttonGrid[x][y].setText(""+game.getTileValue(x, y));
		buttonGrid[x][y].setEnabled(false);
		buttonGrid[x][y].setBackground(getBackground());
		buttonGrid[x][y].setForeground(SettingsData.textC);
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
					buttonGrid[x-1][y].setIcon(null);
					buttonGrid[x-1][y].setFlagged(false);
					buttonGrid[x-1][y].setBackground(getBackground());
					buttonGrid[x-1][y].setForeground(SettingsData.textC);
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
						buttonGrid[x-1][y-1].setIcon(null);
						buttonGrid[x-1][y-1].setFlagged(false);
						buttonGrid[x-1][y-1].setBackground(getBackground());
						buttonGrid[x-1][y-1].setForeground(SettingsData.textC);
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
						buttonGrid[x-1][y+1].setIcon(null);
						buttonGrid[x-1][y+1].setFlagged(false);
						buttonGrid[x-1][y+1].setBackground(getBackground());
						buttonGrid[x-1][y+1].setForeground(SettingsData.textC);
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
					buttonGrid[x+1][y].setIcon(null);
					buttonGrid[x+1][y].setFlagged(false);
					buttonGrid[x+1][y].setBackground(getBackground());
					buttonGrid[x+1][y].setForeground(SettingsData.textC);
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
						buttonGrid[x+1][y-1].setIcon(null);
						buttonGrid[x+1][y-1].setFlagged(false);
						buttonGrid[x+1][y-1].setBackground(getBackground());
						buttonGrid[x+1][y-1].setForeground(SettingsData.textC);
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
						buttonGrid[x+1][y+1].setIcon(null);
						buttonGrid[x+1][y+1].setFlagged(false);
						buttonGrid[x+1][y+1].setBackground(getBackground());
						buttonGrid[x+1][y+1].setForeground(SettingsData.textC);
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
					buttonGrid[x][y-1].setIcon(null);
					buttonGrid[x][y-1].setFlagged(false);
					buttonGrid[x][y-1].setBackground(getBackground());
					buttonGrid[x][y-1].setForeground(SettingsData.textC);
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
					buttonGrid[x][y+1].setIcon(null);
					buttonGrid[x][y+1].setFlagged(false);
					buttonGrid[x][y+1].setBackground(getBackground());
					buttonGrid[x][y+1].setForeground(SettingsData.textC);
					buttonGrid[x][y+1].setEnabled(false);
				}
			}
		}
	}

	private void gameOver() {
		timer.stop();
		gameTimer = new GameTimer();
		for (int i = 0; i < game.boardSize; i++) {
			for (int j = 0; j < game.boardSize; j++) {
				buttonGrid[i][j].setIcon(null);
				buttonGrid[i][j].setFlagged(false);
				buttonGrid[i][j].setText("" + game.getTileValue(i, j));
				buttonGrid[i][j].setBackground(getBackground());
				buttonGrid[i][j].setForeground(SettingsData.textC);
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
		} else if(e.getSource() == backButton){
			new MainMenuGUI();
			setVisible(false);
		} else if(e.getSource() == revealButton) {
			revealButton.setEnabled(ACTIVE_MODE);
			flagButton.setEnabled(INACTIVE_MODE);
		} else if(e.getSource() == flagButton) {
			flagButton.setEnabled(ACTIVE_MODE);
			revealButton.setEnabled(INACTIVE_MODE);
		} else {
			
			if(!timer.isRunning()) {
				timer.start();
			}
			
			for (int i = 0; i < game.boardSize; i++) {
				for (int j = 0; j < game.boardSize; j++) {
					if (e.getSource() == buttonGrid[i][j]) {
						if(revealButton.isEnabled() == ACTIVE_MODE && !buttonGrid[i][j].isFlagged()) {

							if (game.getTileValue(i, j) 
									!= Minesweeper.MINE_VALUE) {
								reveal(i, j);
								checkIfWin();
							} else {
								gameOver();

								JOptionPane.showMessageDialog(null, "You clicked a mine. Game Over!");
							}
						} else if (flagButton.isEnabled() == ACTIVE_MODE) {
							if (buttonGrid[i][j].isEnabled() == true) {
								buttonGrid[i][j].setIcon(new ImageIcon("src/CIS350mineSweeper/Assets/FlagSprite.png"));
								buttonGrid[i][j].setFlagged(true);
							}
						}
					}
				}
			}
		}


	}
	
	private class TimerListener implements ActionListener {
		long lastTime = System.nanoTime();
		public void actionPerformed(ActionEvent e) {
			gameTimer.add((int)(System.nanoTime() - lastTime) / 1000000);
			lastTime = System.nanoTime();
			timerLabel.setText(gameTimer.toString());
		}
	}

	public static void main(String[] args) {
		new GameGUI(15, 25);
	}

}
