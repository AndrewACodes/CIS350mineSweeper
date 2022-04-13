package CIS350mineSweeper;

import java.awt.Color;

public class SettingsData {
	//difficulty is the percentage of bombs compared to the size of the board. expert = 20%, medium = 16%, easy = 12%
	static double difficulty = .16;
	static double easy = .12;
	static double medium = .16;
	static double hard = .20;
	
	//mode is the color mode for the board, 0 = light, 1 = dark, 2 = cream.
	static int mode = 0;
	//dark mode
	static	Color primaryDM = new Color(187, 134, 252);
	static	Color backgroundDM = new Color(30, 30, 30);
	static	Color surfaceDM = new Color(70, 70, 70);
	static	Color textDM = new Color(255, 255, 255);
	// light mode
	static Color primaryLM = new Color(61, 172, 255);
	static Color backgroundLM = new Color(240, 240, 240);
	static Color surfaceLM = new Color(210, 210, 210);
	static Color textLM = new Color(0, 0, 0);
	// cream
	static Color primaryCM = new Color(192, 167, 142);
	static Color backgroundCM = new Color(253, 249, 223);
	static Color surfaceCM = new Color(241, 225, 191);
	static Color textCM = new Color(0, 0, 0);
	
	static Color primaryC = primaryLM;
	static Color backgroundC = backgroundLM;
	static Color surfaceC = surfaceLM;
	static Color textC = textLM;
	
	//size is the length of one of the sizes of the board. small = 10, medium = 16, large is 20
	static int size = 16; 
	static int small = 10;
	static int normal = 16;
	static int large = 20;
	
	
	
	static void changeDifficulty(int newDiff) {
		if (newDiff == 0) {
			difficulty = easy;
		} else if(newDiff == 1) {
			difficulty = medium;
		} else if(newDiff == 2) {
			difficulty = hard;
		}
	}
	
	static void changeMode(int newMode) {
		if(newMode == 0) {
			primaryC = primaryLM;
			backgroundC = backgroundLM;
			surfaceC = surfaceLM;
			textC = textLM;
		} else if (newMode == 1) {
			primaryC = primaryDM;
			backgroundC = backgroundDM;
			surfaceC = surfaceDM;
			textC = textDM;
		} else if (newMode == 2) {
			primaryC = primaryCM;
			backgroundC = backgroundCM;
			surfaceC = surfaceCM;
			textC = textCM;
		}
	}
	
	static void changeSize(int newSize) {
		if (newSize == 0) {
			size = small;
		} else if(newSize == 1) {
			size = normal;
		} else if(newSize == 2) {
			size = large;
		}
	}
}
