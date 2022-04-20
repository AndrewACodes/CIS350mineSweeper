package CIS350mineSweeper;

import java.text.DecimalFormat;

/******************************************************************
 * A class used to keep time for a minesweeper game using GAMEGUI.java
 */

public class GameTimer {

	private int minutes;
	private int seconds;
	private int milliseconds;

	private boolean suspended = false;

	/******************************************************************
	 * A constructor that sets "this" StopWatch to 0.
	 */
	
	public GameTimer() {
		this.minutes      = 0;
		this.seconds      = 0;
		this.milliseconds = 0;
	}
	
	/******************************************************************
	 * A method for incrementing the milliseconds by 1 millisecond.
	 */
	public void inc() {
		if (!suspended) {
			milliseconds++;
		
			if (milliseconds == 1000) {
				milliseconds = 0;
				seconds++;
			}
		
			if (seconds == 60) {
				seconds = 0;
				minutes++; 
			}
		}
	}
	
	/******************************************************************
	 * A method for adding a specific number of milliseconds
	 * @param milliseconds: number of milliseconds to be added
	 */
	
	public void add(int milliseconds) {		
		if (!suspended) {
			for(int i = 0; i < milliseconds; i++) {
				inc(); 
			}
		}
	}
	
	/******************************************************************
	 * A method for taking the GameTimer and making it into a string that can be used as a clock.
	 * @return formated string to look like a stopwatch.
	 */
	
	public String toString() {
		DecimalFormat minuteFormat = new DecimalFormat("0");
		DecimalFormat secondFormat = new DecimalFormat("00");
		DecimalFormat millisecondFormat = new DecimalFormat("000");
		String resultString = "";
		
		resultString = minuteFormat.format(minutes) + ":" + secondFormat.format(seconds);
		resultString += ":" + millisecondFormat.format(milliseconds);
		return resultString;
	}
	
}
