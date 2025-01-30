--- 

# Bulls and cows


### Overview

Bulls and Cows is a console-based game written in Java where the player tries to guess a secret code consisting 
of unique digits and/or letters. The game provides feedback in terms of "bulls" and "cows" to guide the player 
towards the correct answer.

Bulls: Correct symbols in the correct position.

Cows: Correct symbols in the wrong position.

### Features

- Allows the user to specify the length of the secret code.

- The code consists of numbers '0-9' and letters 'a-z', no duplicates.

- Provides detailed feedback on each guess.

- Uses a fair randomization process to generate secret codes.

### How to play

- Run the program.

- Enter the desired length of the secret code.

- Enter the number of possible unique symbols.

- Try to guess the secret code based on the feedback provided.

- The game continues until the correct code is guessed.

### Running the application

1. Clone the repository or download the source code
```sh
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory
```sh
cd hyperskill-projects/bulls-and-cows
```

3. Compile the code
```sh
javac -d out src/Main.java src/game/Game.java src/game/util/RandomStringGenerator.java
```

4. Run the application
```sh
java -cp out Main
```

### Project structure
```sh
bulls-and-cows/
└── src/
    ├── Main.java
    └── game/
        ├── Game.java
        └── util/
            └── RandomStringGenerator.java
```

### Code breakdown

#### Main.java
The entry point of the game. It handles user input for code length and possible symbols and then starts the game.

#### Game.java
Contains the logic of the game. It generates a secret code and compares it against the user's guesses. The game continues until the player correctly guesses the code.

#### RandomStringGenerator.java
A utility class used to generate random strings for the secret code using the specified number of symbols ignoring duplicates.

```sh
 **Example run**
Input the length of the secret code:
4
Input the number of possible symbols in the code:
6
The secret is prepared: **** (0-5)
Okay, let's start the game!
Turn 1:
1
The input is not the same length as the secret code.
Grade: 0 bulls and 0 cows
Turn 2:
1234
Grade: 2 bulls and 1 cow
Turn 3:
1245
Grade: 4 bulls
Congratulations! You guessed the secret code.
```
