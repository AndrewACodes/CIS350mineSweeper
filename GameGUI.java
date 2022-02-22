package CIS350mineSweeper;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class GameGUI extends JFrame implements ActionListener, MouseListener{
	
	JButton restartButton = new JButton("Reset");
	JButton giveUpButton = new JButton("Give Up");
	JPanel bottomPanel = new JPanel();
	JPanel tilePanel = new JPanel(); 
	JButton[][] buttonGrid;
	Minesweeper game;
	
	public GameGUI(int boardSize, int numMines) {
		super("MinesweeperGameGUI");
		
		this.game = new Minesweeper(boardSize, numMines);
		game.generateMines();
		game.generateTileValues();
		
		setSize(1000,1000);
		setLayout(new BorderLayout()); 
		restartButton.addActionListener(this);
		giveUpButton.addActionListener(this);
		bottomPanel.add(giveUpButton);
		bottomPanel.add(restartButton);
		add(bottomPanel, BorderLayout.NORTH);
		
		buttonGrid = new JButton[boardSize][boardSize];
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
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
	
	private void restartBoard() {
		for(int i = 0; i < game.boardSize; i++) {
			for(int j = 0; j < game.boardSize; j++) {
				buttonGrid[i][j].setText("");
				buttonGrid[i][j].setEnabled(true); 
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restartButton) {
			restartBoard();
			game.restart();
		}
		else if(e.getSource() == giveUpButton) {
			for(int i = 0; i < game.boardSize; i++) {
				for(int j = 0; j < game.boardSize; j++) {
					buttonGrid[i][j].setText("" + game.getTileValue(i, j));
					buttonGrid[i][j].setEnabled(false); 
				}
			}
		}
//		else if (e.getsource())
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		GameGUI GUI = new GameGUI(15, 15);
	}
	
}
