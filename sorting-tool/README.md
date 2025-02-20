---

# Sorting tool

---

### Overview

The application is a command-Line tool for data parsing, analyzing and sorting based on user-specific options.

The application allows users to provide input through a file or system input, choose a sorting method and data type,
and then outputs the processed results. The output can either be displayed on the console or written to an output file.

The program is fully customizable, allowing users to define their sorting preferences and input data sources 
through command-line arguments.

---

### Features

- **Data Parsing:** The tool allows you to process data according to your defined parameters.
- **Sorting Options:** You can choose from different sorting methods.
- **Multiple Input Methods:** Data can be inputted either from a file or directly from the system's standard input..
- **Output Flexibility:** You can display the results on the console or save them to an output file.
- **Error Handling:** Includes robust error handling for file reading and writing operations.

---

### How it works

The application starts by taking command-line arguments that dictates the programs behaviour:

1. `-dataType`: Specifies how the data is tokenized (split into units) for processing:
   - `"word"`: Each distinct word (separated by spaces) is treated as a separate unit.
   - `"number"`: Each individual numeric value is treated as a separate entity, even if it is part of a larger string or sentence.
   - `"line"`: Each line of input is treated as a separate unit, regardless of what is in the line.
2. `-sortingType`: Specifies the sorting method to be used:
   - `"byCount"`: Sorts the data based on the frequency or count of occurrences of each word, sentence, or number, 
   depending on the data type chosen. If multiple items have the same count, they will be sorted alphabetically or numerically.
   - `"natural"`: Organizes the data in the natural order; **words** are sorted alphabetically, **numbers** are sorted in numerically
   (ascending order) and **sentences** are sorted lexicographicaly. 
3. `-inputFile`: Specifies the input file path (file to read from).
4. `-outputFile`: Specifies the output file (file to write the result to). If there is no `-outputFile` argument, the result will
be written to the console.

**Example:**

```bash
-sortingType byCount -dataType word -inputFile data/test.txt -outputFile data/outputTest.txt
```

This command analyzes the total occurences of specific words from a file `data/test.txt`, and writes the result to a 
file `data/outputTest.txt`.

Once the data has been processed according to the specified sorting and tokenization criteria, the application will write the 
resulting based on the existence of the `-outputFile` argument. If provided, the output is written to the specified file;
 otherwise, the result will be printed to the console.

---

### Project structure

- `data/`: test files directory:
   - `outputTest.txt` - file to write result to.
   - `test.txt` - a text file to use as input argument for word and/or sentence parsing.
   - `testNumbers.txt` - a text file to use as input argument for number parsing.

```plaintext
sorting-tool/
├── data/
│   ├── outputTest.txt
│   ├── test.txt
│   └── testNumbers.txt
├── src/
│   ├── controller/
│   │   └── CMLController.java
│   ├── error/
│   │   └── InvalidTypeException.java
│   ├── parse/
│   │   ├── CMLParser.java
│   │   ├── DataParser.java
│   │   ├── LineParser.java
│   │   ├── LongParser.java
│   │   ├── Parser.java
│   │   └── WordParser.java
│   ├── util/
│   │   ├── io/
│   │   │   └── IOUtil.java
│   │   ├── sort/
│   │   │   ├── CountSort.java
│   │   │   ├── QuickSort.java
│   │   │   └── SortUtil.java
│   │   └── statistics/
│   │       └── StatisticsUtil.java
│   ├── SortingType.java
│   └── Main.java
└── README.md
```

#### **`controller/`**

`CMLController.java`: The class serves as the main controller of the application, and acts as a bridge between input handling, data
processing and output generation:

- Initializes based on command-line input.
- Fetches data from a file. 
- Calls `Parser.parsedata(...)` to process the data according to the selected sorting method and data type.
- Writes the processed data to a file or the console. 

The class ensures that the command-line handling and core logic are seperated.

#### **`error/`**

`InvalidTypeException.java`: A custom `RuntimeException` used to handle invalid/unsupported data types. 

- Thrown when the user provides incorrect values for `-datatType` or `-sortingType`. 

#### **`parse/`**

`CMLParser.java`: Responsible for parsing and validating command-line arguments provided by the user. It extracts sorting and data 
processing options, as well as input and output file paths, ensuring that the program behaves according to user specifications.

`DataParser.java` (interface): Defines a method `parseData(String sortType)` that parses data based on a specific `"sortType"`. 
Ensues that any class implementing `DataParser` is able to process data according to a given sorting type. 

`LineParser.java, LongParser.java, WordParser.java`: Implements the `DataParser` interface and are responsible for parsing, 
sorting, and analyzing different types of input data. Each class processes a specific type of input:

- `LineParser` handles full lines of text.
- `LongParser` deals with numeric values (`long`).
- `WordParser` processes individual words. 

They all return a string representation if the parsed data, allowing sorting by natural order (ascending) or by count (frequency 
of occurence).

`Parser.java`: A factory class responsible for selecting the appropriate `DataParser` implementation based on the specified data type
(`word`, `line`, `long`). 
It delegates the parsing task to the correct handler, ensuring that the data is processed according to its type and the specified 
sorting method.


#### **`util/`**

`util/io/IOUtil.java`: A utility class that provides methods for handling basic input and output operations, including:

- Reading from the console (system input).
- Reading from a file.
- Writing to a file.

`util/sort/CountSort.java`: Utility class that sorts data types (`word`, `line`, `long`) based on their frequency of occurence. It 

`util/sort/QuickSort.java`: Utility class that Implements th e**Quick sort** algorithm for sorting lists. It requires that the 
elements in the list implement the `Comparable` interface, as comparisons are performed using the `compareTo()` method.

`util/sort/SortUtil.java`: Utility class that serves as a gateway to the **Quicksort** and **CountSort** algorithms providing methods 
for sorting sets and lists.

`util/statistics/StatisticsUtil.java`: Utility class for generating statistical summaries of data. It provides methods for
displaying elements in their natural order and calculating frequency-based statistics, including occurrence counts and percentages.

---

### Run the application

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.
```bash
cd hyperskill-projects/sorting-tool
```

3. Compile the application.
```bash
javac -d out src/Main.java src/controller/CMLController.java src/error/InvalidTypeException.java src/parse/*.java src/util/io/IOUtil.java src/util/sort/*.java src/util/statistics/StatisticsUtil.java
```

4. Run the application.
```bash
java -cp out Main
```

**Example run**

1. Word data type, reads from file, writes to console and sorts by count:

```bash
java -cp out Main "-sortingType" "byCount" "-dataType" "word" "-inputFile" "data/test.txt"
```

```plaintext
Total words: 23.
a: 1 time(s), 5%
application: 1 time(s), 5%
down: 1 time(s), 5%
file: 1 time(s), 5%
for: 1 time(s), 5%
format: 1 time(s), 5%
good: 1 time(s), 5%
java: 1 time(s), 5%
just: 1 time(s), 5%
luck: 1 time(s), 5%
parse: 1 time(s), 5%
sort: 1 time(s), 5%
testing: 1 time(s), 5%
written: 1 time(s), 5%
you: 1 time(s), 5%
and: 2 time(s), 10%
random: 2 time(s), 10%
text: 2 time(s), 10%
to: 2 time(s), 10%
```

2. Word data type, reads from file, writes to console and sorts alphabetically:

```bash
java -cp out Main "-sortingType" "natural" "-dataType" "word" "-inputFile" "data/test.txt"
```

```plaintext
Total words: 23.
Sorted data: a and and application down file for format good java just luck parse random random sort testing text text to to written you
```

3. Long datatype, reads from file, writes to console and sorts in ascending order:

```bash
java -cp out Main "-sortingType" "natural" "-dataType" "long" "-inputFile" "data/testNumbers.txt"
```

```plaintext
"a" is not a long. It will be skipped.
"bv" is not a long. It will be skipped.
Total numbers: 9.
Sorted data: -1 1 2 2 2 12 12 37 8871
```

---