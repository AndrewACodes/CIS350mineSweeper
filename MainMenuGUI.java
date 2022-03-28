package CIS350mineSweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenuGUI extends JFrame implements ActionListener{
	
	JButton startButton = new JButton("Start");
	JButton settingsButton = new JButton("Settings");
	JButton exitButton = new JButton("Exit");

	JPanel buttonPanel = new JPanel();
	JLabel chooseLabel = new JLabel("MINESWEEPER! java edition");
	JPanel labelPanel = new JPanel();
	public MainMenuGUI() {
		super("Minesweeper Main Menu");
		setSize(500,300);
		
		setLayout(new BorderLayout(0, 50));
		
		startButton.addActionListener(this);
		settingsButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		buttonPanel.add(startButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(exitButton);
		buttonPanel.setLayout(new GridLayout(3,1));
		
		add(buttonPanel, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			new GameGUI(15, 40);
			//closes the main menu
			setVisible(false);
		}
		if (e.getSource() == settingsButton) {
			new SettingsMenuGUI();
			//closes the main menu
			setVisible(false);
		}
		if (e.getSource() == exitButton) {
			//closes the main menu
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new MainMenuGUI();
	}
}
