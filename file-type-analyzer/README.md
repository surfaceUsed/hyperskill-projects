---
# File type analyzer

The application is is used for determening the type of file based on their byte content. It scans files in a given directory and matches their byte 
signatures against a predefined pattern database (`patterns.db`). The program uses the [Knuth-Morris-Pratt (KMP) 
algorithm](https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm) to efficiently search for patterns in a file content, and identifies the most relevant file type based on a priority ranking. 

### Features

- Reads multiple files from the a directory and analyzes their type.
- Uses a pattern database to recognize different file formats.
- Efficiently searches for file signatures usin the KMP algorithm.
- Supports parallel execution using a thread pool to speed up file processing.
- Selects the highest-priority match when multiple patterns are found.

### How it works

The user gives two command-line arguments (`args[0]` and `args[1]`):
   
   - `args[0]`: The directory of the files to analyze (test files are located in the `data/` directory).

   - `args[1]`: The file containing specific patterns and their priority. In this case that would be the file `patterns.db` (located in the `resources/` folder).


##### **1. Analyzing files in directory:** 

The directory is first validated, then each file is analyzed in separate threads. A **thread pool** (`ExecutorService`) is used to process multiple files concurrently.

##### **2. Reading the Pattern Database (`patterns.db`):**

The pattern database contains lines with file sigantures, each associated with file type and a priority value. The program reads the file, extracts the signature pattern (as bytes), and sorts them by priority (higher number = higher priority).

**`patterns.db` file content as plaintext:**

```plaintext
1;"%PDF-";"PDF document"
2;"pmview";"PCP pmview config"
4;"PK";"Zip archive"
5;"vnd.oasis.opendocument.presentation";"OpenDocument presentation"
6;"W.o.r.d";"MS Office Word 2003"
6;"P.o.w.e.r.P.o.i";"MS Office PowerPoint 2003"
7;"word/_rels";"MS Office Word 2007+"
7;"ppt/_rels";"MS Office PowerPoint 2007+"
7;"xl/_rels";"MS Office Excel 2007+"
8;"-----BEGIN\ CERTIFICATE-----";"PEM certificate"
9;"ftypjp2";"ISO Media JPEG 2000"
9;"ftypiso2";"ISO Media MP4 Base Media v2"
```
Example: `"%PDF-"` - PDF document, with low priority (1).

### Project structure

- `src/`: contains the source code.
- `data/`: a test directory containing a set of test files to analyze.

```plaintext
file-type-analyzer/
│── data/
│   ├── test_filesdoc_0.doc
│   ├── word-doc.docx
│
│── src/
│   ├── main/
│       ├── java/
│       |   ├── org/example/
│       |       ├── Main.java
│       |       │
│       |       ├── system/
│       |       │   ├── FileAnalyzer.java
│       |       │   ├── FileSession.java
│       |       │   ├── FileTypeInformation.java
│       |       │
│       |       ├── util/
│       |           ├── KMP.java
│       |           ├── PatternFileMapper.java
│       |
│       ├── resources/
│           ├── patterns.db
│
│── pom.xml
```
**Main.java**

Entry point of the application. Calls and manages the execution of the file-type analysis process. 

**System package** (Core logic)

- `FileAnalyzer.java`:

   - Reads the `patterns.db` file and returns its content as a `String[]` using `patternList()`.
    
   - Iterates through all files in the target directory and creates a `FileSession` for each file.
Stores all analysis processes in a `List<FileSession>`.

   - Executes the `FileSession` tasks concurrently and retrieves the results as a `Future`.
Displays the results on the console in the `init()` method.

- `FileSession.java`:

   - Implements `Callable<String>`. 
   
   - Handler class for analyzing each file in the directory in its own separate thread. 

   - Takes the content of the `patterns.db` file and uses it as a templet to analyze the content of the directory. 

   - Returns a `String`  containing the results of the analysis. 

- `FileTypeInformation`:

   - Parses the content of the `patterns.db` file.

   - Extracts the file signature pattern, their associated file types and priority values.

   - The data is then used to determine the most accurate file type when analyzing a file in the specific directory. 

**util package** (Utility classes)

- `KMP.java`:

   - Implements the **Knuth-Morris-Pratt algorithm**. 
   
   - Analyzes a `text` and a `pattern`, to determin if the pattern exists within the text.

   - Returns the starting index of the first occurrence of the pattern in the text. If no match is found, it returns `-1`.

   - Utilizes the **longest prefix-sufix (LPS)** array to optimize the search and avoid redundant comparisons, acheiving efficient matching. 

- `PatternFileMapper.java`:

   - Parses the content of the patterns.db file into `FileTypeInformation` objects, which are then added to a `List`.

   - The objects in the `List` are sorted based on their priorities, and the sorted `List` is returned.

   - The `sortPriority` method is useful for organizing file type information based on predefined criteria.

### Running the application

**Software requirements**

- Java 8 or higher.

- (Optional) Maven.

**Inital steps**

1. Clone the repository.

```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.

```bash
cd hyperskill-projects/file-type-analyzer
```

**Compile with Maven**

Clean, compile and package application.

```bash
mvn clean
mvn compile
mvn package
```
This will create an executable .jar file in the `target/` directory.

**Compile as standard Java application**

```bash
javac -d out src/main/java/org/example/Main.java src/main/java/org/example/system/*.java src/main/java/org/example/util/*.java
```
**Run the application**

After the run script, remember to include the path to a pre define pattern file, and a path to a directory you want to analyze:

- **Maven**:

```plaintext
java -jar target/file-type-analyzer-1.0.jar "path_to_pattern_file" "path_to_directory"
```
- **Java**

```plaintext
java -cp out org/example/Main "path_to_pattern_file" "path_to_directory"
```

**Example run** (using paths from content root to test directory `data/` and `patterns.db` as command-line inputs): 

```plaintext
"data" "src/main/resources/patterns.db"
```

```plaintext
test_filesdoc_0.doc: Zip archive
word-doc.docx: MS Office Word 2007+
```