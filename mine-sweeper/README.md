---

# Mine sweeper

---

### Overview

This is a simple console-based minesweeper game. The player is promted to input the number of mines they want in the game, and the board is randomly generated with mines. The object is to reveal all non-mine cells without triggering a mine. 

--- 

### Features

- Customizable number of mines

- 9x9 grid gameplay

- Automatic mine placement ensuring the first move is always safe

- Recursive clearing of empty cells

- Simple marker system for suspected mines

- Console-based interface for a classic minesweeper experience

---

### How to play

1. Run the game and input the number of mines.

2. The game will display the board, where:

   - `.` represents an unrevealed cell
   - `*` marks a suspected mine.
   - `/` represents a cleared empty cell.
   - Numbers indicate how many mines are adjacent to that cell
3. Enter the move by specifying a row and a column, followed by an action:

   - `"free"` to reveal.
   - `"mine"` to mark/unmark a cell.

4. The game ends when all non-mine cells are revealed (win) or a mine is triggered (lose). 

--- 

### Project structure

```plaintext
mine-sweeper/
│── src/
│   ├── Main.java                
│   ├── game/                   
│       ├── MineController.java   
│       ├── MineModel.java        
│       ├── MineView.java                                
│── README.md                                    
```

**`Main.java`**: Entry point of the application, responsible for initializing the game, and starting the game loop. 

##### **game-package**

**`MineController.java`**: Acts as the central game controller, managing user input, game logic, and interactions between the model (`MineModel`) and the view (`MineView`).

- Reads and processes user input.
- Controls the game loop, ensuring the game progresses until completion.
- Calls the model to update the game state and the view to display the board.
- Handles mine placement, marking, and checking for victory or defeat.

**`MineModel.java`**: Represents the game’s internal data and logic, managing the board state, mine placement, and calculations.

- Stores the game board, mine locations, and threat levels.
- Generates the minefield while ensuring the first move is safe.
- Calculates and stores mine threat levels for each cell.
- Processes user moves, revealing cells and checking for game-ending conditions.

**`MineView`**: Handles the display of the game board and user messages.

- Prints the current game board state.
- Displays instructions and feedback messages for the player.
- Shows the game-over screen when the player wins or loses.

### Installation and setup

**Software requirements**

- Java 8 or later.

1. Clone the repository.

```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.

```bash
cd hyperskill-projects/mine-sweeper
```

3. Compile the code.

```bash
javac -d out src/Main.java src/game/*.java
```

4. Run the application.

```bash
java -cp out Main
```

#### **Example run:**

```plaintext
Input number of mines in game: 50

To make a move, enter the coordinates followed by the type of action.
First, enter the row number (y-coordinate), then the column number (x-coordinate).
After the coordinates, specify the action:

  - Type 'mine' to mark a potential mine.
  - Type 'free' to uncover the cell.

Example: '3 5 free' will uncover the cell at row 3, column 5.
Set/unset mine marks or claim a cell as free (y-coordinate x-coordinate mine/free): 1 1 free
 | 1 2 3 4 5 6 7 8 9 |
-| - - - - - - - - - |
1| 2 . . . . . . . . |
2| . . . . . . . . . |
3| . . . . . . . . . |
4| . . . . . . . . . |
5| . . . . . . . . . |
6| . . . . . . . . . |
7| . . . . . . . . . |
8| . . . . . . . . . |
9| . . . . . . . . . |
-| - - - - - - - - - |
Set/unset mine marks, or claim a cell as free: 9 7 free
 | 1 2 3 4 5 6 7 8 9 |
-| - - - - - - - - - |
1| 2 X . X X . X X X |
2| X . X X . X X X X |
3| X . . X X . . X . |
4| . X X X X X X X X |
5| . . X X X X . X X |
6| . X X . . . . X X |
7| . X X . X X X . X |
8| . . X . . X X . . |
9| . . X X . X X X X |
-| - - - - - - - - - |
You stepped on a mine and failed!
```

---
