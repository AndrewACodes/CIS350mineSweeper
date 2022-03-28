package CIS350mineSweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenuGUI extends JFrame implements ActionListener{
	
	JButton easyButton = new JButton("Easy");
	JButton mediumButton = new JButton("Medium");
	JButton hardButton = new JButton("Hard");

	JPanel buttonPanel = new JPanel();
	JLabel chooseLabel = new JLabel("Choose your difficulty:");
	JPanel labelPanel = new JPanel();
	JPanel spacingPanel = new JPanel();
	public MainMenuGUI() {
		super("Minesweeper Main Menu");
		setSize(500,300);
		setLayout(new BorderLayout(0, 50));
		chooseLabel.setFont(new Font("Ariel",1,40));
		easyButton.addActionListener(this);
		mediumButton.addActionListener(this);
		hardButton.addActionListener(this);
		labelPanel.add(chooseLabel);
		add(labelPanel, BorderLayout.NORTH);
		add(spacingPanel, BorderLayout.SOUTH);
		buttonPanel.add(easyButton);
		buttonPanel.add(mediumButton);
		buttonPanel.add(hardButton);
		buttonPanel.setLayout(new GridLayout(1,3));
		add(buttonPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == easyButton) {
			new GameGUI(9,10);
			//closes the main menu
			setVisible(false);
		}
		if (e.getSource() == mediumButton) {
			new GameGUI(15,40);
			//closes the main menu
			setVisible(false);
		}
		if (e.getSource() == hardButton) {
			new GameGUI(15,99);
			//closes the main menu
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new MainMenuGUI();
	}
}
