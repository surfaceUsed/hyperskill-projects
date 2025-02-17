
# Phone book

--- 

### Overview

The application allows users to compare two files: 

1. A file containing a set of phone numbers and names (phone book).
2. A file containing a set of names (search names).

The goal is to search for each name in the second file within 
the first file and determine if a match exists.
To achieve this, the application tests multiple search methods, 
measuring and comparing their performance based on execution 
time.

---

### How It Works
The application processes two files:

1. **`phone_book.txt`** – Contains a list of names paired with phone numbers (1000 entries).
2. **`search_names.txt`** – Contains a list of names to be searched in the phonebook (100 entries).

The program attempts to find each name from the search file in 
the phone book file using different search methods. Each method 
is timed to measure its efficiency.

#### **Search Methods Used:**

- **Linear Search** – Scans each entry in the phonebook one by 
one until a match is found.

- **Bubble Sort + Jump Search** – First sorts the phonebook 
using Bubble Sort, then searches with Jump Search.

- **Quick Sort + Binary Search** – First sorts the phonebook 
using Quick Sort, then searches with Binary Search.

- **Hash Table Search** – Creates a hash table from the 
phonebook for near-instant lookups.

After running all search methods, the application reports:

- The number of matches found.
- The total time taken for each search method.
- The breakdown of sorting and searching times for applicable 
methods.

This comparison highlights the strengths and weaknesses of 
different search algorithms in terms of speed and efficiency.

---

### Project structure

- `data/`: Test directory containing the two input files.
- `src/`: Contains the source code.

```plaintext
phone-book/
├── data/
│   ├── search_names.txt
│   └── phone_book.txt
├── src/
│   ├── config/
│   │   └── ConfigurationManager.java
│   ├── error/
│   │   └── ApplicationInitiationException.java
│   ├── handler/
│   │   ├── HandleBinarySearch.java
│   │   ├── HandleHashTableSearch.java
│   │   ├── HandleJumpSearch.java
│   │   ├── HandleLinearSearch.java
│   │   ├── SearchHandler.java
│   │   └── SearchHandlerDispatcher.java
│   ├── util/
│   │   ├── FilesUtil.java
│   │   ├── StringUtil.java
│   │   ├── TimeUtil.java
│   │   ├── search/
│   │   │   ├── BinarySearch.java
│   │   │   ├── HashTableSearch.java
│   │   │   ├── JumpSearch.java
│   │   │   ├── LinearSearch.java
│   │   │   └── SearchUtil.java
│   │   └── sort/
│   │       ├── BubbleSort.java
│   │       ├── QuickSort.java
│   │       └── SortUtil.java
│   └── Main.java
└── README.md
```

##### **`config/`-package:**

`ConfigurationManager.java`: A **Singleton class** responsible for loading 
and managing configuration data:

- Loads the phone book from a file. Saves the content in a `List`.
- Loads the search names from a file. Saves the content in a `List`.
- If one of the `List` objects are called before initialization, a
`ApplicationInitiationException` is thrown.

Ensures that these objects are loaded and initialized before use, and
is responsible for controlling access to them.

##### **`error/`-package:**

`ApplicationInitiationException.java`: A custom runtime exception designed
to handle errors such as when a necessary resource or configuration has 
not been properly loaded or set up.

##### **`handler/`-package:**

This package contains different classes for handling the different 
search processes, and a dispatcher to coordinate them.

`SearchHandler.java` (interface): Serves as a blueprint for the specific 
handler implementations. it ensures that each class implements the method
`printResults()`, which displays search statistics such as:

- Number of matches found.
- Sorting or initiation time.
- Search time.

`SearchHandlerDispatcher.java`: It acts as a controller that manages all 
search handlers. Each handler class are loaded into a `List`, ensuring that
they will be executed sequentially. 

- Loads `phone_book.txt` and `search_names.txt`.
- The two files are passed to each handler class.
- Each class are instantly initiated in a `List`, and can be called
via the `getHandlers()` method.

`HandleBinarySearch.java`:  Manages the sorting and searching of 
the phone book using the Quick sort and Binary search algorithms:

- Sorts the phone book using a **Quick sort** algorithm, which is a 
fast, in-place sorting algorithm with average time complexity 
of *O(n log n)*.
- Searches the sorted phone book for matching names using a 
**Binary search** algorithm, an efficient search method with 
a time complexity of *O(log n)* for sorted data.

Both the sorting and searching processes are individually timed, 
and the total time for the entire operation is also measured 
to evaluate performance.

`HandleHashTableSearch.java`: Handles the creation of a hash 
map from the phone book, and searches for matching names 
using the Hash table method:

- Creates a hash map from the phone book using `HashMap`, 
where each contact's name is mapped to its phone number.
- Searches for matching names in the hash map using the Hash 
table search method, which offers efficient lookups with 
an average time complexity of *O(1)* for each search.

The process includes timing both the map creation and searching
steps separately, and also measures the total time taken for 
the entire process to assess performance.

`HandleJumpSearch.java`: Sorts the phone book and then searches 
for matching names using the Jump search algorithm:

- Sorts the phone book using **Bubble sort** to order the 
contact names in a sequential manner, with an average time complexity
of *O(n^2)*. 
- Searches for matching names in the sorted phone book using 
the **Jump search** algorithm, with an average time complexity of
  *O(\sqrt{n})*. 

The sorting and searching processes are individually timed, 
with the total time for all steps calculated to evaluate 
performance.

`HandleLinearSearch.java`: Searches for matching names in the phone book 
using a **Linear search** algorithm:

- Iterates through the phone book one netry at the time to find matching
names.
- Does not require sorting beforehand.
- Average time complexity: *O(n)*.

Since Linear search scans each element sequentially, its performance 
depends on the size of the phone book. If a match is found early, 
the search is faster; otherwise, it may require checking all entries.

##### **`util/`-package:**

`search/BinarySearch.java`: This class implements the [Binary Search](https://en.wikipedia.org/wiki/Binary_search) 
algorithm 
to efficiently locate target elements within a sorted list. Given a list of 
names to search for, it performs a binary search on each name within the 
sorted phone book. The method returns the total number of matching elements 
found between the two `List` objects.

`search/HashTableSearch.java`: This class implements a **hash table-based 
search** to efficiently find matching elements between a map of contacts and 
a list of names. The method returns the total number of matching
elements found between the `Map` and `List` objects.

`search/JumpSearch.java`: This class implements the [Jump Search](https://en.wikipedia.org/wiki/Jump_search) 
algorithm to efficiently search for a target element within a sorted list. 
Returns the total number of matches between the two `List` objects.

`search/LinearSearch.java`: This class implements the [Linear Search](https://en.wikipedia.org/wiki/Linear_search) 
algorithm to find matching elements between a list of contacts and
a list of names. Returns the total number of matches between the two 
`List` objects.

`search/SearchUtil.java`: Provides a utility interface for performing different 
search algorithms on a contact list. It acts as a wrapper class, 
delegating calls to specific search algorithms without requiring 
direct interaction with their implementations.

`sort/BubbleSort.java`: Implements the [Bubble sort](https://en.wikipedia.org/wiki/Bubble_sort) algorithm to sort
a `List` in alphabetical order. 

`sort/QuickSort.java`: Implements the [Quick sort](https://en.wikipedia.org/wiki/Quicksort) 
algorithm to sort a `List` in alphabetical order.

`sort/SortUtil.java`: Provides an interface for sorting a `List`. It acts
as a wrapper class for different sorting algorithm, allowing other parts
of the application to call sorting methods without direct interaction with
their implementations.

`FilesUtil.java`: Provides a utility method for reading from a text 
file and returning the file content as a `List`.

`StringUtil.java`: Provides helper methods for processing and 
manipulating string:

1. `getContactNameInfo(String contactInfo)`: used to parse data from `phone_book.txt` and 
returning a name from the file.
2. `getContactNumberInfo(String contactInfo)`: used to parse data from `phone_book.txt`
and returning a phone number from the file.

`TimeUtil.java`: Provides helper methods for measuring and handling time-related 
operations. It is designed to assist in tracking execution time for 
various processes within the application:

- Measuring execution time for operations (sorting, searching etc) in the application.
- Time formatting; converts time measurements into readable formats.

---

### Running the application

**Software requirements**

- Java 8 or older.

**Installation**

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate the project directory.
```bash
cd hyperskill-projects/phone-book
```

3. Compile project.
```bash
javac -d out src/Main.java src/config/*.java src/error/*.java src/handler/*.java src/util/search/*.java src/util/sort/*.java src/util/*.java
```

4. Run the application.
```bash
java -cp out Main
```

**Example run**

```plaintext
Start searching (linear search)...
Found 100 / 100 entries. Time taken: 0 min. 0 sec. 11 ms.

Start searching (bubble sort + jump search)...
Found 100 / 100 entries. Time taken: 0 min. 0 sec. 365 ms.
Sorting time: 0 min. 0 sec. 357 ms.
Searching time: 0 min. 0 sec. 8 ms.

Start searching (quick sort + binary search)...
Found 100 / 100 entries. Time taken: 0 min. 0 sec. 117 ms.
Sorting time: 0 min. 0 sec. 112 ms.
Searching time: 0 min. 0 sec. 5 ms.

Start searching (hash table)...
Found 100 / 100 entries. Time taken: 0 min. 0 sec. 6 ms.
Creating time: 0 min. 0 sec. 6 ms.
Searching time: 0 min. 0 sec. 0 ms.
```
---

### Notes

- In the future, make it possible for the user to use their
own phone book and search file.
- On larger datasets linear searching will prove to be more ineffective than
on smaller sets.

---


