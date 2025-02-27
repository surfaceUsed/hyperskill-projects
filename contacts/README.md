---

# Contacts

---

### Overview

This application is a command-line based program that serves as a **phonebook manager** for organizing contacts efficiently in
a file-based storage system. It allows users to *store*, *retrieve*, *update* and *delete* contact records in a persistent database 
file. 

Each contact-record can be of two types:

1. `Person` - Stores an individual's details; name, surname, birth date, gender and phone number.
2. `Organization` - Stores an organization's details; organization name, address, and phone number.

---

### Features

- **Contact Management** – Add, view, edit, and delete contact records with ease.
- **Persistent Storage** – Contacts are saved in a local database file, ensuring data remains accessible across multiple sessions.
- **Structured & Organized Data** – Contact records are stored in a structured format (`List`) to ensure efficient retrieval, searching, 
and management.
- **Flexible Search Capabilities** – Find contacts instantly by searching for names or phone numbers.
- **Intuitive Record Listing** – Display a structured list of contacts, with options to edit or delete entries.
- **Supports Multiple Contact Types** – Store details for both Persons and Organizations.
- **Input Validation & Error Handling** – RegEx validation ensures correct phone number formats, valid birth dates, and proper gender 
selection, reducing entry errors.

---

### How it works

1. The user provides a path to a file that functions as a storage, when launching the application. If the path is not a valid file or 
it doesn't exist, a default `default_database_storage.db` will be created in the `database/` directory.
2. Once started, the user is presented with a main menu:
   - `"add"`: Create a new contact-record (person or organization).
   - `"list"`: Display all saved records.
   - `"search"`: Search for records that match a specific query (match by name or phone number). 
   - `"count"`: Displays the total number of contact-records in the database.
   - `"exit"`: Closes the application, and saves the contact registry to the save file.
3. **Adding a contact**: User chooses to add a `"contact"` or `"organization"`. 
   - For a `"person"`, the following details are required:
      - First name.
      - Surname.
      - Birth date.
      - Gender.
      - Phone number.
   - For an `"organization"`, the following details are required:
      - Organization name.
      - Address.
      - Phone number. 
4. **Searching for a Contact**: Users can enter a search query (name or phone number). If matches are found, they are displayed in a 
numbered list. Users can select a specific record for more details or perform further actions (edit/delete).
5. **Editing or Deleting a Contact:** After selecting a contact, users can:
   - `"edit"` – Modify specific contact details details.
   - `"delete"` – Remove the contact permanently.
6. **Data Persistence:** All contacts are stored in a local database file. The database ensures that records remain available even 
after restarting the program.
7. **Input Validation:** The application uses regular expressions (RegEx) to validate inputs such as phone numbers, dates, and names.
Invalid inputs prompt an error message and require re-entry.

---

### Project structure

- `database/`: directory for a default database storage if path from command-line is not valid (also contains a already prepared test
file `phonebook.db`). 
- `src/config/`: Handles configuration and serialization/deserialization logic.
- `src/contact/`: Contains contact-related classes, including `person` and `organization`.
- `src/controller/`: Houses the Controller class, which manages user interaction.
- `src/enums/`: Contains enumerations like State.java.
- `src/handler/`: Manages different operations such as searching, updating, and listing contacts.
- `src/manager/`: Handles core logic like current contact- and state management.
- `src/service/`: Provides additional services for managing contact records.
- `src/util/`: Contains helper classes for validation and other utilities.
- `src/view/`: Manages the console-based user interface.
- `src/Main.java`:  The program’s entry point.

```plaintext
contacts/
│── database/phonebook.db                      
│
│── src/
│   ├── config/                     
│   │   ├── ConfigurationManager.java
│   │   ├── Deserialize.java
│   │   ├── PhoneBook.java
│   │   └── Serialize.java
│   │
│   ├── contact/                    
│   │   ├── Contact.java
│   │   ├── ContactCreator.java
│   │   ├── Organization.java
│   │   └── Person.java
│   │
│   ├── controller/  
│   │   ├── ContactManager.java                 
│   │   ├── Controller.java
|   |   └── StateManager.java
│   │
│   ├── enums/                       
│   │   └── State.java
│   │
│   ├── handler/                     
│   │   ├── ListHandler.java
│   │   ├── SearchHandler.java
│   │   └── UpdateHandler.java                    
│   │
│   ├── service/                     
│   │   ├── ContactRegistry.java
│   │   └── Service.java
│   │
│   ├── util/                       
│   │   ├── InputValidator.java
|   |   ├── PatternMatcher.java
│   │   └── ValidationConstants.java
│   │
│   ├── view/                    
│   │   └── ConsoleView.java
│   │
│   └── Main.java                     
│
└── README.md  
```

#### **`config/`**

- `ConfigurationManager.java`: A singleton class responsible for handle the phonebook configurations and storage. It loads the phonebook
from a file and ensures that a valid storage file exists. If an invalid file is provided, it creates a default database file
 (`database/default_database_storage.db`) in the local `database/` directory. 

- `Deserialize.java`: A utility class responsible for reading and reconstructing serialized objects from a file. It restores saved data
 so that objects saved to a file can be retireved for later use.

- `PhoneBook.java`: The class represents a **serializable contact storage** that maintains a list of contacts that provides functionality
 for loading, storing and retrieving contact records. It acts as the primary data structure for managing phonebook entries, ensuring that
 contacts are persistently stored and retrieved from a file.

- `Serialize.java`: A utility class responsible for serializing a java object, and saving it to a file. 

#### **`contact/`**

- `Contact.java`: The class serves as an abstract base class for different types of contact records in the phonebook application. 
It provides common attributes and behaviors that all contacts share such as storing `"names"`, `"phone numbers"`, and timestamps for when 
a contact was created or last updated.

- `ContactCreator.java`: The class is responsible for creating new contacts based on user input. It provides a 
factory-like method to instantiate either a `person` or an `organization`, guiding the user through the required input fields.

- `Organization.java`: The class is a specialized type of contact that represents a business or institution. It extends 
the `Contact` class and includes an additional attribute (`"address"`) and functionality specific to organizations. 

- `Person.java`: The class represents an individual contact and extends the Contact class. It adds personal details such as 
`"surname"`, `"birth date"`, and `"gender"`, along with validation mechanisms for data consistency.

#### **`controller/`**

- `ContactManager.java, StateManager.java`: These two manager classes serve as global controllers for handling the application's 
state and the currently selected contact. They enable different parts of the application to interact with the current state- and 
contact selection:
   - `StateManager`: Manages the application's current state, ensuring smooth navigation between different views (e.g., MENU, LIST,
 UPDATE). It provides methods to set and retrieve the current state.

   - `Contactmanager`: Keeps track of the currently selected contact, allowing different handlers to retrieve and modify the 
contact as needed. It provides methods to set and get the active contact record.

- `Controller.java`: The class acts as the **central coordinator** of the application. It processes user inputs, manages the different 
states of the application, and invokes appropriate handlers for different operations:

1. User input is validated then processed through the `ConsoleWindow` class, and defines the state the application is in. 
2. To manage the flow of the application, the `Controller` relies on a **state machine** that is used to keep track of the 
current state, and determin what action to execute next. 
3. Different operations are delegated to various handler classes (`ListHandler`, `SearchHandler`, `UpdateHandler`) that are 
resposible for processing user actions related to *listing*, *searching* and *modifying* the contact registry. 

#### **`enums/`**

- `State.java`: Enum class that helps manage the user navigation and interaction in a structured way. It defines various states, and
represents different actions in the application:
    - `MENU`: Represents the main menu state where users choose actions.
    - `ADD`: Used when the user wants to add a new contact.
    - `LIST`: Represents the state for listing all contacts.
    - `EDIT`: Indicates that the user is in edit mode for modifying a contact.
    - `SEARCH`: Used when the user is searching for a contact.
    - `COUNT`: Displays the total number of contacts.
    - `EXIT`: Used to exit the application.
    - `BACK`: Represents a state where the user wants to go back to the previous menu.
    - `AGAIN`: Used when repeating an action or retrying an input.
    - `UPDATE`: Indicates that the user wants to update a contact (edit/delete).
    - `DELETE`: Represents the state for deleting a contact.
    - `INVALID_STATE`: A fallback state when an unrecognized input is provided.

#### **`handler/`**

The `ListHandler`, `SearchHandler`, and `UpdateHandler` classes work together to manage contact records in the application, ensuring smooth 
navigation between listing, searching, and modifying contacts. They all interact with the `Service` layer and provide a structured and
efficient way to interact with the contact registry.
`ListHandler` displays stored contacts and allows selection of them for performing updates. `Searchhandler` enables users to find 
contacts using a query, and select one for modification. `UpdateHandler` manages editing and/or deletion of contact records. 

- `ListHandler.java`: Responsible for managing the listing of contacts records. Retrieves and displays stored contacts. It allows 
users to select a contact for viewing and potential modifications while ensuring proper state transitions based on user input.

   - **Functionality:**

       - If the contact list is not empty, the user is presented with a list of contact names and can select one by entering its corresponding 
number. If it is empty, the user is informed and automatically returned to the main menu.
       - Input validation ensures that only existing contact IDs can be selected. If the user enters a valid number, the corresponding contact 
is retrieved and displayed.

   - **State transitions:**

       - `UPDATE`: The user selects a valid contact from the listing, which is set as the current record for modification (edit/delete).
       - `BACK`: The user chooses to return to the main menu without selecting a contact.
       - `LIST`: The user enters an invalid input (e.g., a non-existent contact number or an unrecognized command). The user is taken back to
the listing display of contacts, and prompted to enter a valid selection and try again.

- `SearchHandler.java`: responsible for handling search functionality in the contact registry. Allows users to search for contacts using
a query string (matches names and phone numbers), selecting a matching contact, and transition to an appropriate state.

   - **Functionality:**

      - If there are contacts in the registry, the user is prompted to enter a search query, or else return to the main menu.
      - The query is matched against stored contacts using the `PatternMatcher` class for efficient pattern searching.
      - If matches are found, the user can select a contact by entering a number corresponding to the search results.
      - If no matches are found, the user is prompted to try again, until they either enter a valid input, or choose to exit (`BACK`), and 
return to the main menu.

   - **State transitions:**

      - `UPDATE`: The selected contact is set as the current record for modification (edit/delete).
      - `BACK`: The user returns to the main menu without selecting a contact.
      - `AGAIN`: The search restarts, allowing the user to enter a new query.

- `UpdateHandler.java`: Responsible for managing contact updates. It determins whether to add a new contact, edit an existing one, or 
delete a contact, and updates the application state accordingly.

   - **Functionality:**

      - The handler ensures that invalid input does not disrupt the application flow. If an invalid option is entered, the user is prompted 
to try again. 
      - Once a contact update operation is completed, the currently selected contact is cleared to avoid unintended modifications.

   - **State transitions:**

      - `ADD`: A new contact is added and the state is set to `MENU`, returning the user to the main menu.
      - `UPDATE`: Indicates that the user wants to modify a specific contact record:
         - `EDIT`: Allows the user to modify a specific field in the record. After editing, the user can choose to modify additional fields 
    of the same contact before exiting.
         - `DELETE`: Removes the contact from the registry and updates the state to `List` ensuring that the updated list of contacts is 
    displayes, allowing the user to select another contact.
         - `MENU`: Cancels the update and returns to the main menu.

#### **service/**

- `ContactRegistry.java`: The class serves as the main service layer for managing contacts in the application. It 
implements the `Service` interface and acts as a wrapper around the phonebook, providing methods like adding, removing, retrieving,  
listing contacts, including saving the registry to a file. 

- `Service.java` (interface): The interface defines a contract for managing contacts. It outlines a set of operations:

|Method                                   |Description            |
|-----------------------------------------|-----------------------|
|`void addContact(Contact contact);`      |Adds a new contact.                       |
|                                         |                       |
|`void removeContact(Contact contact);`   |Removes an existing contact.                       |
|                                         |                       |      
|`int countContacts();`                   |Returns the total number of contacts.                       |
|                                         |                       |
|`List<String> listContactNamesAsList();` |Returns a formatted list of contact names.                       |
|                                         |                       |
|`Contact getContactById(int contactId);` |Retrieves a contact by its ID.                       |
|                                         |                       |
|`Contact getContactByName(String name);` |	Finds a contact by its name.                       |
|                                         |                       |
|`List<Contact> getContacts();`           |Returns the full list of contacts.                       |
|                                         |                       |
|`void closeRegistry();`                  |	Saves changes before closing the service.                       |
|                                         |                       |

#### **util/**

- `InputValidator.java`: The class is a utility class designed to validate user input. It provides static methods for phone number,
 gender, birth date, and number validation:

|Method                |Description                   |
|----------------------|------------------------------|
|`isValidPhoneNumber(String phoneNumber)`|Checks if a phone number matches the expected format.                              |
|                      |                              |
|`isValidGender(String gender)`|Ensures gender is either `"Male"` or `"Female"`.                              |
|                      |                              |
|`isValidBirthDate(String birthDay)`|Validates a birth date against a European date format (`dd.MM.yyyy`).                              |
|                      |                              |
|`isNumber(String input)`|Checks if a string can be parsed as an integer.                              |
|                      |                              |

- `PatternMatcher.java`: A utility class designed to efficiently search for matching contacts in the registry, based on a specific
search query. It uses the [Knuth-Morris-Pratt (KMP)](https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm) 
algorithm which improves search performance by avoiding unnecessary character comparisons. Every contact record that matches the 
search query are saved in a `List`, and returned back to the caller. 

- `ValidationConstants.java`: A utility class that stores constant values related to input validation. It defines standarized error 
messages, formats and regex patterns used to validate and format user input like phone numbers, dates and gender identifiers:

|Constant                |Purpose                   |
|----------------------|------------------------------|
|`INVALID_NUMBER`|Placeholder when a contact has no phone number.                              |
|                      |                              |
|`INVALID_INPUT`|Placeholder when a field has no valid input.                              |
|                      |                              |
|`MALE/FEMALE`|Standardized gender identifiers for validation.                              |
|                      |                              |
|`DATE_TIME_FORMAT`|Specifies the date-time format as `"dd.MM.yyyy HH:mm"` (European format).                              |
|                      |                              |
|`DATE_FORMAT`         |Specifies the date format as `"dd.MM.yyyy"`.                              |
|                      |                              |
|`PHONE_NUMBER_VALIDATOR`|Regex pattern used to validate phone numbers, allowing international and alphanumeric formats.                              |
|                      |                              |

The regex for the phone number is quite complex, here are the rules for it:

1. The phone number should be split into groups using a space or dash. One group is also possible.
2. Before the first group, there may or may not be a plus symbol.
3. The first group or the second group can be wrapped in parentheses, but there should be no more than one group that is wrapped in 
parentheses. There may also be no groups wrapped in parentheses.
4. A group can contain numbers, uppercase, and lowercase English letters. A group should be at least 2 symbols in length. But the 
first group may be only one symbol in length.

#### **`view/`**

- `ConsoleView.java`: A utility class responsible for handling user input and output via the console. It provides a set if static 
methods for interacting with the user in a command-line interface, making it easier to display prompts, retrieve input and display
messages.

---

### Running the application

1. Clone the repository.
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.
```bash
cd hyperskill-projects/contacts
```

3. Compile the application.
```bash
javac -d out src/Main.java src/config/*.java src/contact/*.java src/controller/*.java src/enums/State.java src/handler/*.java src/service/*.java src/util/*.java src/view/ConsoleView.java 
```

4. Run the application.
```bash
java -cp out Main
```

**Example run**
```plaintext
open database\phonebook.db

[menu] Enter action (add, list, search, count, exit): count
The Phone Book has 0 records.

[menu] Enter action (add, list, search, count, exit): add
Enter the type (person, organization): person
Enter first name: Tom
Enter surname: Tim
Enter birth date (dd.mm.yyyy): 24.12.2000
Enter gender (M, F): M
Enter number: 123 982 123

[menu] Enter action (add, list, search, count, exit): list
1. Tom Tim
[list] Enter action ([number], back): back

[menu] Enter action (add, list, search, count, exit): search
Enter search query (search for contact name or number): t
1. Tom Tim

[search] Enter action ([number], back, again): again
Enter search query (search for contact name or number): l

The query "l" produced no matching results.

[menu] Enter action (add, list, search, count, exit): list
1. Tom Tim
[list] Enter action ([number], back): 1
Name: Tom
Surname: Tim
Birth date: 24.12.2000
Gender: M
Number: 123 982 123
Time created: 27.02.2025 18:26
Time last edit: 27.02.2025 18:26

[record] Enter action (edit, delete, menu): edit
Select a field (name, surname, birth date, gender, number): surname
Enter surname: newName
saved!
Name: Tom
Surname: newName
Birth date: 24.12.2000
Gender: M
Number: 123 982 123
Time created: 27.02.2025 18:26
Time last edit: 27.02.2025 18:27

[record] Enter action (edit, delete, menu): menu

[menu] Enter action (add, list, search, count, exit): list
1. Tom newName
[list] Enter action ([number], back): back

[menu] Enter action (add, list, search, count, exit): exit
```