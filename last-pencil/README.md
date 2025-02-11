# last-pencil

---

### Overview

This application is a simple turn-based game where two 
players (one human and one AI bot) take turns removing 1-3
pencils from a pile. The player who removes the last pencil loses the 
game. The AI bot plays optimally to maximize its chances 
of winning.

---

### Game rules

The user starts the game by defining the total number of pencils. The next step is to choose which player goes first: **Jack** or **John**.

- **John** is always the user.
- **Jack** is always the AI bot.

Players then take turns removing 1-3 pencils from the pile. The last player forced to take the final pencil **loses** the game.

##### **AI Logic** 

The AI bot follows a strategy based on the mathematical principle of winning positions in the game of [NIM](https://en.wikipedia.org/wiki/Nim):

- If the number of pencils remaining is a multiple of **4**, the AI is in a losing position and makes a random move. 
- Otherwise, the AI ensures that after its move, the remaining pencils are a multiple of **4**, forcing the human player into a losing position. 
- If only one pencil remains, the AI is forced to remove it and **loses** the game.

---

### Project structure

```plaintext
last-pencil/
│── README.md
└── src/
    ├── Main.java
    ├── Game.java
    └── Player.java
```

`Main.java`: Entry point of the game, manages user input and game initialization.

`Game.java`: Handles game logic, including turns an AI moves.

`Player.java`: Represents a player, either human or AI.

---

### Running the application

**Software requirements**

- Java 8 or later
- A terminal or command prompt

**Installation**

1. Clone the repository.

```bash 
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.

```bash
cd hyperskill-projects/last-pencil
```

3. Compile the code.

```bash
javac -d out src/*.java
```

4. Run the application.

```bash 
java -cp out Main
```

#### **Run example**

```plaintext
How many pencils would you like to use:
10
Who will be the first (John, Jack):
John
||||||||||
John's turn!
3
|||||||
Jack's turn!
||||
John won!
```
---