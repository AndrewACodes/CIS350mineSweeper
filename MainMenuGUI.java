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
		setBackground(SettingsData.backgroundC);
		
		startButton.addActionListener(this);
		settingsButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		
		//TODO ADD A PICTURE ABOVE THE MENU BUTTONS.... actually i dont think its worth the effort
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 35, 10));
		startButton.setBackground(SettingsData.primaryC);
		settingsButton.setBackground(SettingsData.primaryC);
		exitButton.setBackground(SettingsData.primaryC);
		startButton.setForeground(SettingsData.backgroundC);
		settingsButton.setForeground(SettingsData.backgroundC);
		exitButton.setForeground(SettingsData.backgroundC);
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setMargin(new Insets(15, 60, 15, 60));
		settingsButton.setMargin(new Insets(10, 40, 10, 40));
		exitButton.setMargin(new Insets(10, 53, 10, 53));
		buttonPanel.setBackground(getBackground());
		buttonPanel.add(startButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(exitButton);
		add(buttonPanel, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			new GameGUI(SettingsData.size, calculateNumBombs());
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
	
	public int calculateNumBombs() {
		double diff = SettingsData.difficulty;
		double size = SettingsData.size;
		
		//Explanation: Find area of board, then multiply by diff. Then round this, but it returns long so we cast it to an int
		int numBombs = (int) Math.round((size * size) * diff);
		
		return numBombs;
	}
	
	public static void main(String[] args) {
		new MainMenuGUI();
	}
}
