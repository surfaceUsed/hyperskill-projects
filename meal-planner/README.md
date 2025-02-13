# Meal planner application

---

### Overview

The meal planner application is a command-line application that allows users to store, manage, plan and display meals. It integrates with a PostgreSQL database to persist data, making it possible for users to retrieve data later. 

---

### Features

- **Meal Input and Storage:** Users can add meals categorized as breakfast, lunch, or dinner, including meal name and ingredients.
- **Persistent Storage with PostgreSQL:** Meals and ingredients are stored in a PostgreSQL database, ensuring data persistence across sessions.
- **Command-Based Interaction:** Users can interact with the application via simple commands like `add`, `show`, `plan`, `save`, and `exit`.
- **Meal Planning:** Allows users to plan their meals by selecting meals from the stored list and adding them to a meal plan.
- **Export Meal Plan:** Users can save their meal plan and generate a shopping list with all the ingredients for the planned meals.

---

### Database schema

The application uses a PostgreSQL database that consists of three tables (the user cooses the name of the database):

#### `meals` 
- `category` (VARCHAR): Category of the meal (breakfast, lunch, dinner).
- `meal` (VARCHAR): Name of the meal. 
- `meal_id` (INTEGER): Primary key for the meal.

#### `ingredients`
- `ingredient` (VARCHAR): The name of the ingredient.
- `ingrdient_id` (INTEGER): Unique ID for each ingredient.
- `meal_id` (INTEGER): Foreign key referencing the `meal_id` from the `meals` table.

#### `plan`
- `day` (VARCHAR): The day of the week.
- `meal_name` (VARCHAR):  The name of the planned meal.
- `meal_category` (VARCHAR): Category of the meal (breakfast, lunch, dinner).
- `meal_id` (INTEGER): Foreign key referencing the meal_id from the meals table.

---

### How it works

##### **1. Initializing the application**

To start the application, the user needs to provide three inputs through the command-line:

1. Database url.
2. User name.
3. Password.

```bash
"jdbc:postgresql://<host>:<port>/<database_name>" "user_name" "password"
```
These parameters connect to the PostgreSQL database specified by the url, using the provided username and password for authentication.

##### **2. Adding a meal**
Users can add a meal by:

1. Selecting a meal category (breakfast, lunch, dinner).
2. Providing a meal's name.
3. Listing its ingredients.

The meal is then validated and stored in the database.

##### **3. Displaying meals**
Users can view all stored meals in the system. The application displays meals with their category, name, and ingredients.

##### **4. Meal planning**
Users can plan their meals by selecting meals from the stored list and adding them to a meal plan for the day. The meal plan is saved in the database for future reference.

##### **5. Saving meal plan ingredients**
Users can save the ingredients for theiw meal plan to a file. The application generates a list of ingredients from the planned meals and allows the user to save it to a file on the local system.

##### **6. Exiting the application**
To exit the program, users can type `exit`, which will close the database connection and output a farewell message.

##### **Command options**
The following commands can be used during the execution of the application:

- `add`: Adds a new meal to the database.
- `show`: Displays all stored meals categorized by breakfast, lunch, or dinner.
- `plan`: Allows the user to select and plan meals for the week.
- `save`: Saves the ingredient list for the planned meals to a specified file.
- `exit`: Exits the program and closes the database connection.

---

#### Project structure

- `shoppingList/list.txt`: A test directory to show what it looks like when writing a shopping list to a file.

- `src/main/java/org/example/`: contains the main classes for the application logic.

```plaintext
meal-planner/
├── pom.xml
├── README.md
├── shoppingList/
│   └── list.txt
└── src/main/java/org/example/
    ├── controller/
    │   └── MealController.java
    ├── dao/
    │   ├── MealsDatabaseDAO.java
    │   └── MealsDatabaseDAOImpl.java
    ├── datasource/
    │   ├── Datasource.java
    │   └── MealsDatabase.java
    ├── entity/
    │   ├── Meal.java
    │   └── MealDayPlan.java
    ├── enums/
    │   ├── ApplicationState.java
    │   ├── DaysOfTheWeek.java
    │   └── MealCategory.java
    ├── logic/
    │   ├── DatabaseProperties.java
    │   └── MealHandler.java
    ├── util/
    │   ├── ConnectionManager.java
    │   ├── IngredientIDGenerator.java
    │   ├── IOUtil.java
    │   └── MealIDGenerator.java
    └── Main.java
```

**`Main.java`**: The entry point of the application, responsible for initializing database connections and starting the application logic.

- It uses the command-line input (`args`) to initialize a `Connection` object, and starts the application by calling `new MealController().run()`. 

**controller-package**

**`MealsController.java`**: Acts as the central controller that manages user interactions and directs the flow of the application based on the selected actions.

- Initiates a `Datasource` object to communicate with a database.
- Maintains the state of the application using `ApplicationState` enum.
- Handles all user commands, and updates the state of the application accordingly. 
- Calls `MealHandler` methods for processing of user inputs.

**dao-package**

**`MealsDatabaseDAO.java`** (interface): Defines a contract for a data access object (dao) for performing specific database operations.

**`MealsDatabaseDAOImpl.java`**: Implements the `MealsDatabaseDAO` interface, and performs SQL operations for storing and retrieving data.

- Designed as a singleton to ensure that there is only one instance of the class throughout the application. Database operations occur in a consistent manner. There is no risk of having multiple versions of this DAO working on different data sets.


**datasource-package**

**`Datasource.java`** (interface): Defines the contract for operations related to the database.

**`MealsDatabase.java`**: Implements the `Datasource` interface. When the class is initialized, it checks if all the tables (`meals`, `ingredients`, `plan`) already exists. If not they will be created imediately. 

- Delegates actual database operations to the data access object `MealsDatabaseDAOImpl`: 
   
   - `insertMeal(Meal meal)`: Inserts a new `Meal` in the database.
   - `createPlan(List<MealDayPlan> mealPlan)`: Clears the previous `plan` table and inserts data from a `List` into the table. 
   - `listMealPlan()`: Returns all the data from the table `plan` as a `List`.
   - `listAll()`: Returns all the data from the table `meals` as a `List`. 
   - `listByCategory(String category)`: Returns all the data from the table `meals` that matches the category input, as a `List`.

**entity-package**

**`Meal.java`**: Represents a single meal to store in the database. Each instance created are used to insert into the `meals` table of the database.

- Attributes:

   - `category`: Type of meal (`breakfast`, `lunch`, `dinner`).
   - `mealName`: Name of meal.
   - `ingredients`: A list of ingredients used to make the meal.

**`MealDayPlan.java`**: Represents a meal planned for a specific day (monday-sunday). When all instances for each day are created, they are saved in a `List` and inserted into the `plan` table of the database.

- Attributes: 

   - `day`: The day of the week for the meal.
   - `mealCategory`: Type of meal (`breakfast`, `lunch`, `dinner`). 
   - `mealName`: Name of the meal.

**enums-package**

**`ApplicationState.java`**: Enum class that defines different states to control the flow of the program.

- `STATE_START`: Defines a state where the application goes back to awaiting user input. 
- `STATE_ADD`: Adds a new meal.
- `STATE_SHOW`: Displaying meals based on a certain meal category. 
- `STATE_PLAN`: Creates a meal plan for a week (monday-sunday).
- `STATE_SAVE`: Saves all the ingredients needed for the week, to a file.
- `STATE_EXIT`: Exits the application.
- `STATE_INVALID`: Handles invalid inputs and resets to `STATE_START`.

**`DaysOfTheWeek.java`**: Enum class that defines defines a set of `enum` constants, one for each day of the week.

**`MealCategory.java`**: Enum class that represents the different meal categories (`breakfast`, `lunch`, `dinner`). 

**logic-package**

**`DatabaseProperties.java`**: Encapsulates the properties requiered to connect to a database (database url, username and password). It serves as a container for the database connection credentials.

- Gets initialized via input from the command-line.
- Used by `ConnectionManager` to initialize a `Connection` object.

**`Mealhandler.java`**: Responsible for managing meal-related functionalities, including validating input, sorting meals, adding meals to a plan and printing meal details. 

**1. Meal input validation:**
- Validates and collects user input for meal categories, types and ingredients.

**2. Meal planning:**
- Assists in planning meals for each day of the week, allowing users to select meals for different categories. 
- Sorts meals by category and prompts the user to choose a meal for each category on each day.
- Creates and returns a list of `MealDayPlan` objects. 

**3. Meal printing and listing:**
- Prints meals from each category.
- Displays a meal plan by day and category.

**4. Ingredient management:**
- Maps and lists ingredients accross all meals, counting how many times each ingredient appears. 
- generates and formats a list of ingredients for the user.

**5. File operations:**
- Provides a method for writing meal-related data to a file.

**util-package**

**`ConnectionManager.java`**: Manages the lifecycle and access to the database connection to ensure efficient and consistent resource usage across the application. It follows the Singleton design pattern, ensuring that only one instance of the `Connection` object is used throughout the application.

- Initializes the database connection by fetching configuration values from `DatabaseProperties` and establishing a connection with the database.
- Provides a single Connection instance to be used by classes like `MealsDatabase` and `MealsDatabaseDAOImpl` for database interactions, ensuring centralized connection management.
- Ensures that the database connection is closed properly when the application exits, to release resources and maintain application performance.

**`IngredientIDGenerator.java`**: A utility class that generates a unique ID for each ingredient in 
the database. It ensures that if an ingredient already exists in the database, it retrieves the existing ID, preventing 
duplicates. If the ingredient is new, it assigns a new ID by checking the highest existing ID in the database and incrementing 
it accordingly.

**`IOUtil.java`**: Provides utility methods for handling input and file I/O operations. 

- Creates a static `Scanner` object which is used to read input from the console. This makes sure that only one input stream is used for writing to the console, and that the same stream is closed when `closeInput()` is called.

- Enables the user to write data to a local file of their choosing.

**`MealIDGenerator.java`**: A utility class that generates a unique ID for each new meal entry in the database. 
It works by retrieving the maximum meal ID currently in the database and increments it for new meal entries. 
This class ensures that each meal has a unique identifier while preventing conflicts with existing IDs in the database.

---

### Running the application

**Software requirements**
- Java 20 or later.
- Apache [Maven](https://maven.apache.org/).
- [PostgreSQL](https://www.postgresql.org/download/) (Local or remote server) - Required for storing data.

**Installation steps**

1. Clone the repository.

```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory.

```bash
cd hyperskill/meal-planner
```

3. Compile the application.

```bash
mvn clean compile
```

4. Package the application.

```bash
mvn package
```

5. Run the application.

```bash
java -jar target/meal-planner-1.0.jar "database_url" "username" "password"
```
---