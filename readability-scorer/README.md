---

# Readability scorer

---

### Overview

The application evaluates the readability a given text file using various readability tests. It implements multiple algorithms to
estimate the required education level to udnerstand the input text.

---

### Features

Analyzes a text based on four different readability tests:

- [Automated Readability Index (ARI)](https://en.wikipedia.org/wiki/Automated_readability_index).
- [Flesch–Kincaid Readability Tests (FK)](https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests).
- [Simple Measure of Gobbledygook (SMOG)](https://en.wikipedia.org/wiki/SMOG).
- [Coleman–Liau Index (CL)](https://en.wikipedia.org/wiki/Coleman%E2%80%93Liau_index).

Each test generates a readability score and estimates the minimum age or education level required for a person to comprehend
the text.

---

### How it works

The application analyzes a text file based on the following criteria:

1. **Total amount of words:**
   - Words are split by spaces, punctuation (`.`, `!`, `?`), and other whitespace.
   - Hyphenated words (e.g., `"self-esteem"`) are not split and are treated as a single word.

2. **Total amount of sentences:**
   - Sentences are split based on punctuation marks: `.`, `!`, and `?`.
   - Consecutive punctuation marks (`"Hello!! How are you?"`) are treated as a single delimiter.
   - Spaces following punctuations are ignored.

3. **Total amount of characters:**
   - Every non-whitespace character is counted (spaces, tabs and newlines are excluded).

4. **Total amount of syllables:**
   - Syllables are counted using vowel groupings (`a, e, i, o, u, y`). 
   - The letter `y` is treated as a "special vowel" becouse it can function as both a **vowel** and a 
   **consonant**.
   - Consecutive vowels are not counted (`"rain"`, 2 vowels but only one syllable).
   - If the last letetr is an `e` it wont be counted as a vowel (`"side"`, has one syllable).
   - If the word contains zero vowels, it will be counted as a one-syllable word.

5. **Toal amounts of Polysyllables:**
   - A polysyllabic word is any word that has three or more syllables.

All these factors contribute to various readability tests, providing an estimate of the text's comprehension level.

---

### Project structure

- `data/`: A test directory containint a text file `text.txt` for testing the program.

```plaintext
readability-scorer/
│
├── data/
│   └── text.txt   
│
├── src/
│   ├── controller/
│   │   └── ReadabilityController.java  
│   │
│   ├── enums/
│   │   ├── GradeLevel.java      
│   │   └── TestType.java      
│   │
│   ├── logic/
│   │   └── Calculations.java   
│   │
│   ├── tests/
│   │   ├── AutomatedReadabilityIndex.java   
│   │   ├── ColemanLiauIndex.java            
│   │   ├── FleschKincaldReadabilityTest.java
│   │   ├── ReadabilityTest.java   
│   │   ├── SMOGIndex.java                
│   │   └── TestFactory.java               
│   │
│   └── Main.java                    
│
└── README.md       
```

`Main.java`: Reads a text file from the command-line, line-by-line and passes it to the `ReadabilityController` class for analysis
when it's done reading the file.

#### **`controller/`**

`ReadabilityController.java`: Manages the overall workflow of the application. It takes a given text as input, processes it by 
invoking the appropriate handler classes for text analysis and readability tests, and then displays the results to the user.

#### **`enums/`**

`GradeLevel.java`: `enum` class that consists of constans representing different education levels. Each constant is
assosiated with two values:

   1. A score - representing a grade level/reading level.
   2. An age - the average age of a person at that grade level.

`TestType.java`: `enum` class that consits of constants representing different readability tests. Each constant is assosiated with 
two values:

   1. A name - the name of the test.
   2. A serch key - an identifier for each test. 

#### **`logic/`**

`Calculations.java`: Provides a collection of utility methods for analyzing various aspects of a text:

   - Amount of sentences.
   - Amount of words.
   - Amount of characters.
   - Amount of syllables and polysyllables.
   - Calculating average letter- and sentence count per 100 words (needed for the **Coleman Liau Index test**). 
   - Calculating the total average reading level for a given text based on all the test results. 

#### **`tests/`**

`ReadabilityTest.java` (interface): Defines the structure for all readability tests:

   - `getGradeLevel(String text)` - Returns the grade level based on the readability test.
   - `getResult(String text)` - Returns a string containing the test result (score, estimated age group etc).

`AutomatedReadabilityIndex.java, ColemanLiauIndex.java, FleschKincaldReadabilityTest.java, SMOGIndex.java`: These classes 
implement various readability tests that assess the complexity of a given text.  Each test provides a grade level based on 
the number of words, sentences, syllables, or other linguistic features, which corresponds to an estimated age group for 
the text's readability.
   
   1. AutomatedReadabilityIndex (ARI): Calculates readability using the average number of characters per word and the average 
   number of words per sentence.
   2. ColemanLiauIndex (CLI): Focuses on the average number of letters per 100 words and the average number of sentences per 
   100 words to calculate readability.
   3. Flesch-Kincaid Readability Test (FK): Uses the average number of syllables per word and the average number of words per 
   sentence to determine readability.
   4. SMOG Index: Assesses the number of polysyllabic words (three or more syllables) in the text to determine readability.

Each test returns an estimated grade level based on the text's structure and linguistic complexity, providing an objective 
measure of readability for different types of content.

---

### Running the application

**Installation**

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.
```bash
cd hyperskill-projects/readability-scorer
```

3. Compile the project.
```
javac -d out src/Main.java src/controller/ReadabilityController.java src/enums/*.java src/logic/Calculations.java src/tests/*.java 
```

4. Run the application.
```bash
java -cp out Main
```

##### **Example run** (using the text from `data/text.txt`)


```plaintext
The text is:
This is the front page of the Simple English Wikipedia. Wikipedias are places where people work together to writeencyclopedias in different languages. We use Simple English words and grammar here. The Simple English Wikipedia isfor everyone! That includes children and adults who are learning English. There are 142,262 articles on the SimpleEnglish Wikipedia. All of the pages are free to use. They have all been published under both the Creative CommonsLicense and the GNU Free Documentation License. You can help here! You may change these pages and make new pages.Read the help pages and other good pages to learn how to write pages here. If you need help, you may ask questions atSimple talk. Use Basic English vocabulary and shorter sentences. This allows people to understand normally complexterms or phrases.

Words: 131
Sentences: 14
Characters: 687
Syllables: 210
Polysyllables: 20

Enter the score you want to calculate (ARI, FK, SMOG, CL, all):all

Automated Readability Index: 7,95 (about 13-year-olds)
Flesch–Kincaid readability tests: 6,98 (about 12-year-olds)
Simple Measure of Gobbledygook: 9,96 (about 15-year-olds)
Coleman–Liau index: 11,87 (about 17-year-olds)

This text should be understood on average by 14,25-year-olds.
```

---