# CIS350mineSweeper

This MineSweeper project is created for GVSU CIS350 semester project. The developers for this project consist of Andrew Arent, Ryley Rawlings, and Kaden Wende. This is a remake of the popular game MineSweeper. 

The game works by uncovering squares that have a chance to contain a bomb. If it doesn't contain a bomb, it will reveal the number of bombs adjacent to that uncovered square. The goal of the game is to uncover all the the squares leaving only bomb squares left. 

This game runs on java which means it will be able to run on any operating system! Windows, Mac, any Linux distribution, doesn't matter it will be able to run. (The game may have different style based on operating system, but overall gameplay will be accurate and the same.)

<img src="https://github.com/AndrewACodes/CIS350mineSweeper/blob/main/READMEpics/minesweeper_pic2.jpg" alt="---" style="max-width: 100%;" />

## Menu

- [Upcoming Features](#upcoming-features)
- [New Features](#new-features)
- [How Does It Work?](#how-does-it-work)
- [About](#about)

## Upcoming Features
- **Flagging Squares.** Being able to mark squares to flag them. This would allow the user a piece of mind and make the game not so tedious when remembering where strategically deduced bombs have been located.


- **Timer.** A timer would allow for some type of urgency and thrill when playing. We'd also allow a customizable timer for the laid back players who may be busy while playing and will take longer to complete each puzzle.

## New Features
- **Difficulties.** Having multiple difficulties (Easy, Medium, Hard, Impossible) would allow the game to be customizable to different skill level players. The same difficulty may get repetitive. Multiple difficulties would give the user something to progress towards and a skill they can learn and improve on. We've implemented a easy, medium, and hard difficulties that places the bombs as a percentage of the total number of tiles. This is great because it allows the difficulties to scale with the size of the board. Default values could be changed but for now it is 12%, 16%, 20% respectively.


- **Customizable environments.** The user will be able to change what the board looks like. This would allow the user to feel more comfortable depending on the environment they are in. It would also allow the user to feel more connected to the game when able to change the colors to exactly what they want. So far we've implemented light mode, dark mode, and cream mode. We've also implemented an adaptive system to allow quick additions of new environments in the SettingsData.java file.<div style="page-break-after: always"></div>
  <img src="https://github.com/AndrewACodes/CIS350mineSweeper/blob/main/READMEpics/settingsMenuLight.png" width="230" height="300" alt="---" style="max-width: 10%;" />
  <img src="https://github.com/AndrewACodes/CIS350mineSweeper/blob/main/READMEpics/settingsMenuDark.png" width="230" height="300" alt="---" style="max-width: 10%;" />
  <img src="https://github.com/AndrewACodes/CIS350mineSweeper/blob/main/READMEpics/settingsMenuCream.png" width="230" height="300" alt="---" style="max-width: 10%;" />


- **Customizable grid size.** Allowing the user to change the grid size of the MineSweeper game will allow the user to roughly control how long it may take them to finish a game. A larger grid size will take longer to complete giving the player an added sense of accomplishment. We've implemented 3 sizes; small, normal, and large. These sizes are based on the length of one side of the board, so small is 10x10, normal is 16x16, and large is 20x20.

## How Does It Work?

The program first initializes a window the main menu which consists of a start, a settings, and a exit button. Clicking exit will exit the program. Clicking Settings will open the settings menu and clicking start will start the game. 

The settings menu consists of 3 labels each with 3 options; a difficulty, a environment, and a grid size. Then at the bottom there is a back and a refresh button. When an option is selected, it will update a group of static variables that will then be accessed by the program and when the game is starting.
- The difficulty option will assign a default percentage of bombs to size of grid to a static variable. When the game is started it will take the length of the board and square it. This gives us the area. It then multiplies that by the difficulty percentage to give us the number of bombs needed to be in the game.
- The mode option will change the color environment of the program. These color presets are coded into SettingsData and when the button is selected the main static variable will be changed to the new color preset. This allows the program to easily add more color presets without combing through files and files of code. The refresh button serves to show the user the color preset before starting the game, the user can stay in the settings menu and try out all the presets.
- The size option will change the size of the grid. This will determine the length of the board which, when clicked, will change the static variable and allow the game to adjust its board size depending on the option the user wanted. 

The back button goes back to the main menu. The main menu will access the mode static variable to change its color preset.

When the start button is clicked, the program accesses all static variables that were set from the settings menu. This window contains a Back, a Give-up, and a Reset button. The back button will allow the user to go back to the main menu to adjust settings or exit. The Give-up button lets the user give-up and see the location of all bombs. And the Reset button will let the user Reset all tiles to play again. 

When the window is initialized, all the bombs are then placed on the grid and the corresponding grids determine their number based on how many bombs are adjacent to them. When a grid is clicked, it checks to see if it was a bomb or if it was a 0. 

When a grid of number 0 is clicked within a large pool of 0 grids, a recursive formula removes all grids from the 0 grids plus 1 adjacent square next to the 0 grid. We can do this because if 0 adjacent squares contain bombs, then we can remove those squares for the user. This just makes the game quicker and easier for the user without messing with game mechanics. 

When a number grid 1-8 is clicked, just that grid is shown. 

When a bomb is clicked, a Gameover pop-up window is displayed alerting the user that they have clicked a mine. After which the entire board is cleared off and the user can click the reset button to start a new game. 

<img src="https://github.com/AndrewACodes/CIS350mineSweeper/blob/main/READMEpics/youlostminesweeper2.0.png" alt="---" style="max-width: 100%;" />

If the user manages to remove all non-bomb grids, leaving only bomb grids left, a pop-up window alerting the user that they've won is displayed. After clicking ok, the user can then click the reset button to start a new game.

<img src="https://github.com/AndrewACodes/CIS350mineSweeper/blob/main/READMEpics/youwonminesweeper2.0.png" alt="---" style="max-width: 100%;" />

## About

Project owner is Andrew Arent. Scrum Master is Ryley Rawlings. We state that we are by no means the owner of the game "Minesweeper", we have just made a copy of a popular game for this project.
