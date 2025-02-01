---

# Encryption decryption application

This is a simple text encryption and decryption application based on the Caesar cipher. Users can choose to encrypt or decrypt text using one of two algorithm types: **Shift** or **Unicode**. The application takes instructions via command-line arguments to define how the encryption/decryption process should be handled.


### Features
- **Shift cipher**: shifts each letter by a specified number according to its position in the alphabet.
- **Unicode cipher**: shifts each letter by a specified number according to its position in the Unicode table. 
- **Flexible input**: choose between inputting text directly from the command-line, or reading it from a file. 
- **Customizable output**: output the manipulated text to the console or a specified file. 

### Command-line arguments

To run the application you need to provide command-line arguments specifying how the encryption/decryption process should be handled:

|*Argument*      | *Description*    |
|--------------|---------------|
|`-alg`        |Specifies the algorithm to use; `'shift'` or `'unicode'.`                        |
| `-mode`      |Defines the operation; `enc` for encryption and `dec` for decryption.    |               
|`-key`        |(Integer) Sets the number of positions to shift for the cipher.|
|`-data`       |The `text` to manipulate.             |               |
|`-in`         |Path to a file containing the text to manipulate.
|`-out`        |Path to an output file where the result from the cipher will be stored.

**Default values:**
- If `-alg` is missing, the default algorithm is "shift".
- If `-key` is missing, the default value is 0.
- If `-mode` is missing, the default value is "enc".
- If `-data` is missing, the application will check the `-in` argument. If both are missing, the default value is an empty string.
- If both `-in` and `-data` are present, the `-data` argument takes priority.
- If `-out` is missing, the result will be printed to the console.

**example commands:**:
1. **Encrypting a text from a file and saving the result to another file:**
```plaintext
-mode enc -in file_to_read_path.txt -out file_to_write_path.txt -key 5
```
A text from the file `file_to_read_path.txt` was encrypted using the `shift` algorithm (by default), and saved to the file `file_to_write_path.txt`. 

2. Encrypting a text input using the `unicode` cipher:
```plaintext
-mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode
```
The input *Welcome to hyperskill!* was encrypted using the `unicode` algorithm and printed to the console: `\jqhtrj%yt%m~ujwxpnqq&`. 


### Project structure

- `src/`: contains the source code.
   - `main/java/org/example/`: contains the main classes for the application logic.
   - `data/`: A test directory used for intput and output files.

```plaintext
encryption-decryption/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               ├── Main.java
│   │               │
│   │               ├── cipher/
│   │               │   ├── Cipher.java
│   │               │   ├── CipherCreator.java
│   │               │   ├── Shift.java
│   │               │   ├── TextManipulator.java
│   │               │   └── Unicode.java
│   │               │
│   │               └── controller/
│   │                   └── Controller.java
│
├── data/
│   ├── readFromFile.txt
│   ├── writeToFile.txt
│
├── README.md
└── pom.xml
```
**Main.java**
- The driver class for running the application. 

**Controller.java** (Controller) 

- Manages input parameters and parses command-line arguments into key-value pairs.

- Stores the parsed arguments in a `Map` and uses them to initialize class attributes.

- When the `runCipher()` method is called, it validates the arguments and applies the appropriate cipher process based on user input.

**Cipher.java** (Interface)

- Defines the core logic for manipulating a text, handling encryption and decryption based on the selected algorithm and key-value.

**CipherCreator.java** (Factory)

- Flexible object creation.
- Creates the appropriate cipher algorithm instance based on user input.

**Shift.java, Unicode.java** (Cipher.java implementations)

- Implements the logic for a specific algorithm type (`shift` or `unicode`). 

- Performs text encryption and decryption by shifting each letter in the input text by a specified number of positions in the alphabet (`shift`), or according to its position in the Unicode table (`unicode`).
- Takes into account wrapping around the alphabet and the range of the Unicode characters, when reaching the last character. 
- Handles both *encryption* (shifting to the right) and *decryption* (shifting to the left) based on the provided key value.

**TextManipulator.java**

- Acts as a bridge between the input text and the cipher algorithm, ensuring the correct transformation of the text.

- Responsible for initiating the appropriate cipher algorithm based on the input parameters.

- Returns the manipulated (encrypted or decrypted) text after applying the chosen cipher (Shift or Unicode).

### Running the application

**Software requirements**

- Java 8 or later.
- (Optional) Maven. 

**Initial steps**

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```
2. Navigate to the project directory.
```bash
cd hyperskill-projects/encryption-decryption
```

**Compile with Maven**
1. Make sure maven is installed. 
2. Follow these steps to clean, compile and package the project.
```bash
mvn clean
mvn compile
mvn package
```
This will create an executable `.jar` file in the `target/` directory.

**Standard Java**
1. Compile the code. 
```bash
javac -d out src/main/java/org/example/Main.java src/main/java/org/example/cipher/*.java src/main/java/org/example/controller/Controller.java
```
**Run the application**

After the run script, remember to include the command-line arguments:

- **Maven**:
```bash
java -jar target/encryption-decryption-1.0.jar -mode enc -key 5 -data "Hello" -alg unicode
```

- **Standard Java application**:
```bash
java -cp out org/example/Main -mode enc -key 5 -data "Hello" -alg unicode
```