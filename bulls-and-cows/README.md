---

# Bulls and Cows

## Overview

**Bulls and Cows** is a console-based game written in Java where the player tries to guess a secret code consisting of unique digits and/or letters. The game provides feedback in terms of "bulls" and "cows" to guide the player towards the correct answer.

- **Bulls**: Correct symbols in the correct position.
- **Cows**: Correct symbols in the wrong position.

## Features

- Allows the user to specify the length of the secret code.
- The code consists of numbers '0-9' and letters 'a-z', with no duplicates.
- Provides detailed feedback on each guess.
- Uses a fair randomization process to generate secret codes.

## How to Play

1. Run the program.
2. Enter the desired length of the secret code.
3. Enter the number of possible unique symbols.
4. Try to guess the secret code based on the feedback provided.
5. The game continues until the correct code is guessed.

## Project Structure

```plaintext
bulls-and-cows/
├── src/
│   ├── Main.java
│   ├── game/
│   │   ├── Game.java
│   │   └── util/
│   │       └── RandomStringGenerator.java
├── README.md
```

**Main.java**
The entry point of the game. It handles user input for code length and possible symbols and then starts the game.

**Game.java**
Contains the logic of the game. It generates a secret code and compares it against the user's guesses. The game continues until the player correctly guesses the code.

**RandomStringGenerator.java**
A utility class used to generate random strings for the secret code using the specified number of symbols ignoring duplicates.

## Running the Application

**Software requirements**

- Java 8 or later.

**Installation**

1. Clone the repository or download the source code:

    ```bash
    git clone https://github.com/surfaceUsed/hyperskill-projects.git
    ```

2. Navigate to the project directory:

    ```bash
    cd hyperskill-projects/bulls-and-cows
    ```

3. Compile the code:

    ```bash
    javac -d out src/Main.java src/game/Game.java src/game/util/RandomStringGenerator.java
    ```

4. Run the application:

    ```bash
    java -cp out Main
    ```

### Example run
```plaintext
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