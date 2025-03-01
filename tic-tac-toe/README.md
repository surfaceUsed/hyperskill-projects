---

# Tic tac toe application (with AI)

---

### Overview

This is a command-line Tic-tac-toe application that supports different difficulty levels for AI oponents (*easy*, *medium* and *hard*). 
Users can choose to play agains an AI or another player, or have two AI's battle it out!

---

### Features

1. Choose which player goes first.
2. Three AI difficulty levels:
   - **Easy:** Moves are chosen randomly.
   - **Medium:** Plays randomly unless it can win in one move or needs to block the opponent from winning.
   - **Hard:** Uses the [minimax algorithm](https://en.wikipedia.org/wiki/Minimax) to play optimally, never making mistakes.
3. The game board updates in real time after each move.
4. Exit the game at any time by typing `exit`.

### How it work

1. **Start the game by specifying the players:**

    - Type `user` for a human player.
    - Type the difficulty level (`easy`, `medium`, or `hard`) for an AI opponent.
    - The first player is always `X`, and the second is `O`.
    
    **Examples:**
    - A game between a human and a medium AI:
    ```plaintext
    Input command: start user medium
    ```
    - A game between two human players:
    ```plaintext
    Input command: start user user
    ```
    - A game between two AI players of different difficulties:
    ```plaintext
    Input command: start easy hard
    ```

2. **Players take turns making moves:**

    - A human player enters the coordinates of their move.
    - If an invalid move is entered, the player must try again.
    - AI players make moves automatically based on their difficulty level.

3. **Game results & replay option:**

    - The game ends when a player wins or the board is full.
    - The result is displayed: either **X wins**, **O wins**, or **Draw**.
    - To play again, enter a new start command.
    - To exit the game, type:
    ```plaintext
    Input command: exit
    ```

---

### project structure 

```plaintext
tic-tac-toe/
│── src/
│   ├── game/
│   │   ├── algorithm/           
│   │   │   ├── EasyAlgorithm.java
│   │   │   ├── GameAlgorithm.java
│   │   │   ├── HardAlgorithm.java
│   │   │   ├── MediumAlgorithm.java
│   │   │   └── UserAlgorithm.java
│   │   │
│   │   ├── player/               
│   │   │   ├── AI.java
│   │   │   ├── Player.java
│   │   │   ├── PlayerCreator.java
│   │   │   └── User.java
│   │   │
│   │   ├── GameController.java    
│   │   ├── GameModel.java        
│   │   ├── GameState.java 
│   │   └── GameView.java      
|   |
│   └── Main.java 
|
└── README.md
```
#### **`game/algorithm/`**

- `GameAlgorithm.java` (interface): Defines a shared functionality between all the player components:
   - `getGamePiece()`: returns the players game piece (`X` or `O`).
   - `gameLogic()`: Executes the player move.

- `EasyAlgorithm.java, HardAlgorithm.java, MediumAlgorithm.java, UserAlgorithm.java`: Implements the `GameAlgorithm` interface and 
define different strategies for making moves in the game:

   **EasyAlgorithm:** 
   - Implements a simple AI that selects a random empty spot on the game board.
   - Uses `Random` to generate coordinates until an empty space is found.

   **MediumAlgorithm:**
   - First, checks if it can win in one move (`tacticalPlay` method).
   - If not, attempts to block the opponent from winning.
   - If neither is possible, makes a random move.
   - Uses `checkTacticalPlay` to evaluate rows, columns, and diagonals.

   **HardAlgorithm:**
   - Implements the **Minimax algorithm** to determine the best possible move.
   - Evaluates all possible game states recursively to maximize the AI’s chances of winning.
   - Uses `miniMax` to evaluate the board and `isMaximizing` to determine move priority.
   - Selects the move that leads to the best outcome.

#### **`game/player/`**

- `Player.java` (interface): Defines a structure for how a player functions.

- `AI.java, User.java`: Implements the `Player` interface represents different types of players:

   **AI:**
   - The class represents a computer-controller player that makes moves based on a specific `GameAlgorithm` (easy, medium, hard).

   **User:**
   - The class represents a human player that makes moves based on the user `GameAlgorithm`.

- `PlayerCreator.java`: A factory class responsible for instantiating the correct player type based on sertain game settings:

   - Determins the game state (`STATE_EASY`, `STATE_MEDIUM`, `STATE_HARD`, `STATE_USER`).
   - Creates an appropriate `GameAlgorithm` based on the state.
   - Returns the correct player type. 

#### **`game/`**

- `GameController.java`: The class manages the flow of the game, handling user input, game state transitions, and interactions between the 
model (`GameModel`) and view (`GameView`). It also processes game commands and updates the game board accordingly.

- `GameModel.java`: The class is responsible for managing the game state, including the game board, players, and game logic. It handles player 
turns, checking for a winner, and resetting the game.

- `GameState.java`: The `GameState` enum represents different states of the game. Each enum constant has an associated string message 
that describes the state.

- `GameView.java`: The class is responsible for rendering the game board as a formatted grid.

---

### Running the application

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.
```bash
cd hyperskill-projects/tic-tac-toe
```

3. Compile the application.
```bash
javac -d out src/Main.java src/game/*.java src/game/algorithm/*.java src/game/player/*.java
```

4. Run the application.
```bash
java -cp out Main
```

**Example**
```plaintext
Input command: start user easy
---------
|       |
|       |
|       |
---------

Enter the coordinates: 
1 1
---------
| X     |
|       |
|       |
---------

Making move level "easy"
---------
| X O   |
|       |
|       |
---------

Enter the coordinates: 
2 1
---------
| X O   |
| X     |
|       |
---------

Making move level "easy"
---------
| X O O |
| X     |
|       |
---------

Enter the coordinates: 
3 1
---------
| X O O |
| X     |
| X     |
---------

X wins
Input command: start easy hard
---------
|       |
|       |
|       |
---------

Making move level "easy"
---------
|   X   |
|       |
|       |
---------

Making move level "hard"
---------
| O X   |
|       |
|       |
---------

Making move level "easy"
---------
| O X   |
|       |
|   X   |
---------

Making move level "hard"
---------
| O X   |
|   O   |
|   X   |
---------

Making move level "easy"
---------
| O X   |
| X O   |
|   X   |
---------

Making move level "hard"
---------
| O X O |
| X O   |
|   X   |
---------

Making move level "easy"
---------
| O X O |
| X O   |
| X X   |
---------

Making move level "hard"
---------
| O X O |
| X O   |
| X X O |
---------

O wins
Input command: exit
```