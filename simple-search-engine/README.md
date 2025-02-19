---

# Simple search engine

---

### Overview

This application is a simple search engine that reads text data (such as names and emails) from a file, builds an [inverted 
index](https://www.geeksforgeeks.org/inverted-index/) for fast lookups, and allows users to search using flexible matching strategies 
through an interactive menu-driven interface.

---

### Features

- **Efficient searching:** uses an **inverted index** for quick look-ups.
- **Multiple search strategies:**
   1. **ALL:** matches results containing all search keys.
   2. **ANY:** matches results containing at least one of the search keys.
   3. **NONE:** excludes results that contain the search keys.
- **Menu-driven interface:** Simple command-line interface to serach, view results and exit the application.
- **case-insensitive search:** finds matches regardless of letetr casings.

---

### How it works

1. **Load the Data:** The application reads the provided text file and treats each line as an individual record.

2. **Build an Inverted Index:** The contents are indexed, mapping each word to the lines where it appears.

3. **Search Using the Index:** Users enter search queries, and the program retrieves matching records using the selected search 
strategy.

4. **Display Results:** The application prints relevant records or notifies users if no matches are found.

---

### Project structure

- `data/data.txt`: test file containing data to index.

```plaintext
simple-search-engine/
│── data/                      
│   └── data.txt               
│── src/                      
│   │── config/                
│   │   ├── ConfigurationManager.java  
│   │   └── FileParserException.java  
│   │── controller/            
│   │   └── Controller.java    
│   │── enums/           
│   │   ├── Inputs.java     
│   │   └── SearchStrategies.java  
│   │── logic/             
│   │   └── TextFileSearcher.java  
│   └── Main.java              
└── README.md 
```

`Main.java`: The Main class serves as the entry point for your simple search engine application. It is responsible for 
initializing the required configurations and starting the search process. 

#### **`config/`**

`ConfigurationManager.java`: Responsible for **loading**, **storing** and **processing** a text file for the search engine. It manages:

   1. Reading the file into memory. The file is read line-by-line and saved in a `List`.
   2. Building a inverted index.
   3. Providing access to the loaded data via getter methods.

It follows the **Singleton pattern**, ensuring that only one instance of the class exists. Additionally, it enforces that a valid file 
must be loaded into memory before the `getInstance()` method can be called.

`FileParserException.java`: Custom `RuntimeException` that is used to signal errors that occurs while loading or reading the input file 
in the application.

#### **`controller/`**

`Controller.java`: Handles user input and overall control flow, and delegates the work to the correct handler classes.

#### **`enums/`**

`Inputs.java`: The `Inputs` enum defines a fixed set of constants that represent the valid commands a user can enter in the 
application:

- **FIND_PERSON:** Represents the command to search for a person.
- **LIST_ALL:** Represents the command to list all people.
- **EXIT:** Represents the command to exit the application.
- **INVALID_VALUE:** Acts as a default value (error) indicating an unrecognized input by the user.

`SearchStrategies.java`: The `SearchStrategies` enum defines the various strategies available for searching text:

- **ALL:** Indicates a strategy where a line is considered a match only if it contains *all* of the search keys.
- **ANY:** Indicates that a line is a match if it contains at *least* one of the search keys.
- **NONE:** Specifies that a line is a match if it contains *none* of the search keys.
- **INVALID:** Acts as a default value (error) indicating an unrecognized input by the user.

#### **`logic/`**

`TextFileSearcher.java`: The class is responsible for processing search queries on a text file that has been preloaded and indexed by 
the `ConfigurationManager`. It uses the inverted index `Map` to efficiently locate and print lines based on a specific search
strategy. 

---

### Running the application

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.
```bash
cd hyperskill-project/simple-search-engine
```

3. Compile the application.
```bash
javac -d out src/Main.java src/config/*.java src/controller/Controller.java src/enums/*.java src/logic/textFileSearcher.java
```

4. Run the application.
```bash
java -cp out Main
```

#### **Example run** (data from `data/data.txt`)

```plaintext
=== Menu ===
1. Find a person
2. Print all people
0. Exit

> 2
=== List of people ===
Kristofer Galley
Fernando Marbury fernando_marbury@gmail.com
Kristyn Nix nix-kris@gmail.com
Regenia Enderle
Malena Gray
Colette Mattei
Wendolyn Mcphillips
Jim Gray
Coreen Beckham
Bob Yeh bobyeah@gmail.com
Shannan Bob stropeshah@gmail.com
Yer Fillion
Margene Resendez marres@gmail.com
Blossom Ambler
Teri Ledet teri_ledet@gmail.com
Dana Baron baron@gmail.com
Abram Goldsberry
Yer Leopold
Stefania Trunzo
Alexis Leopold
Carlene Bob
Oliver Dacruz
Jonie Richter
Pasquale Gallien gallien@evilcorp.com
Verdie Gentle
Gerardo Strouth gallien@evilcorp.com
Agripina Bob
Latricia Niebuhr
Malena Schommer
Drema Leopold
Heide Payeur
Ranae Digiovanni
Simona Pereira
Nick Digiovanni
Angelita Wigington gallien@evilcorp.com
Elin Gray
Dwain Trunzo
Boris Beiler
Remi Malek fsociefy@gmail.com
Demetria Hostetler gallien@evilcorp.com
Nydia Mcduffie
Florencio Defibaugh
Warner Giblin
Bob Mans
Shu Gray
Kaycee Gray
Victorina Froehlich victory@gmail.com
Roseanne Gray
Erica Radford hisam@gmail.com
Elyse Pauling

=== Menu ===
1. Find a person
2. Print all people
0. Exit

> 1
Select a matching strategy: ALL, ANY, NONE
> all
Enter a name or email to search all suitable people.
> Elin Gray

Elin Gray

=== Menu ===
1. Find a person
2. Print all people
0. Exit

> 1
Select a matching strategy: ALL, ANY, NONE
> any
Enter a name or email to search all suitable people.
> Elin Gray

Elin Gray
Malena Gray
Jim Gray
Shu Gray
Kaycee Gray
Roseanne Gray

=== Menu ===
1. Find a person
2. Print all people
0. Exit

> 1
Select a matching strategy: ALL, ANY, NONE
> none
Enter a name or email to search all suitable people.
> gray

Kristofer Galley
Fernando Marbury fernando_marbury@gmail.com
Kristyn Nix nix-kris@gmail.com
Regenia Enderle
Colette Mattei
Wendolyn Mcphillips
Coreen Beckham
Bob Yeh bobyeah@gmail.com
Shannan Bob stropeshah@gmail.com
Yer Fillion
Margene Resendez marres@gmail.com
Blossom Ambler
Teri Ledet teri_ledet@gmail.com
Dana Baron baron@gmail.com
Abram Goldsberry
Yer Leopold
Stefania Trunzo
Alexis Leopold
Carlene Bob
Oliver Dacruz
Jonie Richter
Pasquale Gallien gallien@evilcorp.com
Verdie Gentle
Gerardo Strouth gallien@evilcorp.com
Agripina Bob
Latricia Niebuhr
Malena Schommer
Drema Leopold
Heide Payeur
Ranae Digiovanni
Simona Pereira
Nick Digiovanni
Angelita Wigington gallien@evilcorp.com
Dwain Trunzo
Boris Beiler
Remi Malek fsociefy@gmail.com
Demetria Hostetler gallien@evilcorp.com
Nydia Mcduffie
Florencio Defibaugh
Warner Giblin
Bob Mans
Victorina Froehlich victory@gmail.com
Erica Radford hisam@gmail.com
Elyse Pauling

=== Menu ===
1. Find a person
2. Print all people
0. Exit

> 0

Bye!
```


