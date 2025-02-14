--- 

# Tic-tac-toe application

---

### Overview

This is a simple command-line game. The game allows the user to input a 3x3 grid
state and make a move by selecting valid coordinates. The program then updates
the grid and displays the new state.

--- 

### Features

- Users take turn in making a move (`x` always starts).
- Allows the user to input coordinates for their next move.
- Validates input to ensure correctness (numbers only, withing bounds, and not
  already occupied).
- Updates the grid and displays it after a valid move.

---

### How it works

1. The program displays the current grid.
2. The user inputs the row and column numbers (1-3) for their move.
3. The program validates the move and updates the grid if its valid.
4. The update grid is displayed.
5. If the move is invalid, an error message is shown, and the user is prompted
   to enter new coordinates. If not, then the next player makes a move.

---

### Project structure

```plaintext
simple-tic-tac-toe/
│── src/
│   ├── Main.java 
│── README.md      
```
---

### Running the application

**Software requirements**
- Java 8 or later

**Installation**

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.
```bash
cd hyperskill-projects/simple-tic-tac-toe
```

3. Compile the application.
```bash
javac -d out src/Main.java
```

4. Run the application.
```bash
java -cp out Main
```

##### Example run

```plaintext
---------
|       |
|       |
|       |
---------

Enter row and column (1-3): 1 3
---------
|     X |
|       |
|       |
---------

Enter row and column (1-3): 1 1
---------
| O   X |
|       |
|       |
---------

Enter row and column (1-3): 2 3
---------
| O   X |
|     X |
|       |
---------

Enter row and column (1-3): 2 1
---------
| O   X |
| O   X |
|       |
---------

Enter row and column (1-3): 3 3
---------
| O   X |
| O   X |
|     X |
---------

X wins!
```
---