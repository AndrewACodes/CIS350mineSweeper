package CIS350mineSweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SettingsMenuGUI extends JFrame implements ActionListener{
	
	
	
	JButton easyButton = new JButton("Easy");
	JButton mediumButton = new JButton("Medium");
	JButton hardButton = new JButton("Hard");
	JButton lightButton = new JButton("Light");
	JButton darkButton = new JButton("Dark");
	JButton creamButton = new JButton("Cream");
	JButton smallButton = new JButton("Small");
	JButton normalButton = new JButton("Normal");
	JButton largeButton = new JButton("Large");
	JButton backButton = new JButton("Back");
	JButton refreshButton = new JButton("Refresh");

	JPanel diffButtonPanel = new JPanel();
	JPanel modeButtonPanel = new JPanel();
	JPanel sizeButtonPanel = new JPanel();
	JPanel miscButtonPanel = new JPanel();
	
	JLabel diffLabel = new JLabel("Choose your difficulty:");
	JLabel modeLabel = new JLabel("Choose your color mode");
	JLabel sizeLabel = new JLabel("Choose the size of the board");
	
	JPanel diffLabelPanel = new JPanel();
	JPanel modeLabelPanel = new JPanel();
	JPanel sizeLabelPanel = new JPanel();
	
	JPanel spacingPanel = new JPanel();
	public SettingsMenuGUI() {
		
		//FRAME SETTINGS
		super("Minesweeper Settings Menu");
		setSize(600,800);
		setLayout(new GridLayout(7,0));
		setBackground(SettingsData.backgroundC);
		
		//ACTION LISTENER
		easyButton.addActionListener(this);
		mediumButton.addActionListener(this);
		hardButton.addActionListener(this);
		lightButton.addActionListener(this);
		darkButton.addActionListener(this);
		creamButton.addActionListener(this);
		smallButton.addActionListener(this);
		normalButton.addActionListener(this);
		largeButton.addActionListener(this);
		backButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		//DIFFICULTY LABEL PANEL SETTINGS
		diffLabelPanel.setLayout(new BorderLayout());
		diffLabelPanel.setBorder(BorderFactory.createEmptyBorder(35, 10, 0, 0));
		diffLabel.setFont(new Font("Ariel",1,40));
		diffLabel.setForeground(SettingsData.textC);
		diffLabelPanel.setBackground(getBackground());
		diffLabelPanel.add(diffLabel);
		add(diffLabelPanel);
		
		//DIFFICULTY BUTTON PANEL SETTINGS
		diffButtonPanel.setLayout(new GridLayout(0,3));
		diffButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		easyButton.setBackground(SettingsData.surfaceC);
		mediumButton.setBackground(SettingsData.surfaceC);
		hardButton.setBackground(SettingsData.surfaceC);
		easyButton.setForeground(SettingsData.textC);
		mediumButton.setForeground(SettingsData.textC);
		hardButton.setForeground(SettingsData.textC);
		diffButtonPanel.setBackground(getBackground());
		diffButtonPanel.add(easyButton);
		diffButtonPanel.add(mediumButton);
		diffButtonPanel.add(hardButton);
		add(diffButtonPanel);
		
		//MODE LABEL PANEL SETTINGS
		modeLabelPanel.setLayout(new BorderLayout());
		modeLabel.setFont(new Font("Ariel",1,40));
		modeLabelPanel.setBorder(BorderFactory.createEmptyBorder(35, 10, 0, 0));
		modeLabel.setForeground(SettingsData.textC);
		modeLabelPanel.setBackground(getBackground());
		modeLabelPanel.add(modeLabel, BorderLayout.WEST);
		add(modeLabelPanel);
		
		//MODE BUTTON PANEL SETTINGS
		modeButtonPanel.setLayout(new GridLayout(0, 3));
		modeButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		lightButton.setBackground(SettingsData.surfaceC);
		darkButton.setBackground(SettingsData.surfaceC);
		creamButton.setBackground(SettingsData.surfaceC);
		lightButton.setForeground(SettingsData.textC);
		darkButton.setForeground(SettingsData.textC);
		creamButton.setForeground(SettingsData.textC);
		modeButtonPanel.setBackground(getBackground());
		modeButtonPanel.add(lightButton);
		modeButtonPanel.add(darkButton);
		modeButtonPanel.add(creamButton);
		add(modeButtonPanel);
		
		//SIZE LABEL PANEL SETTINGS
		sizeLabelPanel.setLayout(new BorderLayout());
		sizeLabel.setFont(new Font("Ariel",1,40));
		sizeLabel.setForeground(SettingsData.textC);
		sizeLabelPanel.setBorder(BorderFactory.createEmptyBorder(35, 10, 0, 0));
		sizeLabel.setForeground(SettingsData.textC);
		sizeLabelPanel.setBackground(getBackground());
		sizeLabelPanel.add(sizeLabel);
		add(sizeLabelPanel);
		
		//SIZE BUTTON PANEL SETTINGS
		sizeButtonPanel.setLayout(new GridLayout(0,3));
		sizeButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		smallButton.setBackground(SettingsData.surfaceC);
		normalButton.setBackground(SettingsData.surfaceC);
		largeButton.setBackground(SettingsData.surfaceC);
		smallButton.setForeground(SettingsData.textC);
		normalButton.setForeground(SettingsData.textC);
		largeButton.setForeground(SettingsData.textC);
		sizeButtonPanel.setBackground(getBackground());
		sizeButtonPanel.add(smallButton);
		sizeButtonPanel.add(normalButton);
		sizeButtonPanel.add(largeButton);
		add(sizeButtonPanel);
		
		//MISC BUTTON PANEL SETTINGS
		miscButtonPanel.setLayout(new BorderLayout());
		miscButtonPanel.setBorder(BorderFactory.createEmptyBorder(35, 10, 35, 10));
		backButton.setBackground(SettingsData.primaryC);
		refreshButton.setBackground(SettingsData.primaryC);
		backButton.setForeground(SettingsData.backgroundC);
		refreshButton.setForeground(SettingsData.backgroundC);
		miscButtonPanel.setBackground(getBackground());
		miscButtonPanel.add(backButton, BorderLayout.WEST);
		miscButtonPanel.add(refreshButton, BorderLayout.EAST);
		add(miscButtonPanel);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == easyButton) {
			SettingsData.changeDifficulty(0);
		}
		if (e.getSource() == mediumButton) {
			SettingsData.changeDifficulty(1);
		}
		if (e.getSource() == hardButton) {
			SettingsData.changeDifficulty(2);
		}
		if (e.getSource() == lightButton) {
			SettingsData.changeMode(0);
		}
		if (e.getSource() == darkButton) {
			SettingsData.changeMode(1);
		}
		if (e.getSource() == creamButton) {
			SettingsData.changeMode(2);
		}
		if (e.getSource() == smallButton) {
			SettingsData.changeSize(0);
		}
		if (e.getSource() == normalButton) {
			SettingsData.changeSize(1);
		}
		if (e.getSource() == largeButton) {
			SettingsData.changeSize(2);
		}
		if (e.getSource() == backButton) {
			new MainMenuGUI();
			//goes back to main menu
			setVisible(false);
		}
		if (e.getSource() == refreshButton) {
			new SettingsMenuGUI();
			setVisible(false);
		}
		
	}
	
	public static void main(String[] args) {
		new SettingsMenuGUI();
	}
}
