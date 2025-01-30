---

# Coffee Machine Simulator

A simple command-line application simulating a coffee machine. The simulator allows users to interact with a virtual coffee machine to buy drinks, add ingredients, check remaining resources and empty the cash register.

### Features

- **Buy coffee**: The user selects between *Espresso*, *Latte* or *Cappuccino*. 

- **Fill ingredients**: Add water, mlik, coffee beans and disposable cups to the machine. 

- **View remaining resources**: List the amount of resources and cash in the machine.

- **Empty cash register**: Retrieve the cash from the register.

### Coffee machine conditions

The machine starts with the following resources:

- **Water** - 400 ml.
- **Milk** - 540 ml.
- **Coffee beans** - 120 g.
- **Cups** - 9.
- **Cash** - $550.

Ingredients and cost per coffee type:

- **Cappuccino**: 200 ml water, 100 ml milk, 12 g. coffee beans, $6.
- **Espresso**: 250 ml water, 16 g. coffee beans, $4.
- **Latte**: 350 ml water, 75 ml milk, 20 g. coffee beans, $7.


### Project structure
```plaintext
coffee-machine-simulator/
│
├── src/
│   ├── Main.java
│   │
│   ├── machine/
│   │   ├── controller/
│   │   │   └── CoffeeMachine.java
│   │   │
│   │   ├── drinks/
│   │   │   ├── Cappuccino.java
│   │   │   ├── Coffee.java
│   │   │   ├── CoffeeFactory.java
│   │   │   ├── Espresso.java
│   │   │   └── Latte.java
│   │   │
│   │   └── view/
│   │       └── MachineView.java
│
└── README.md
```

**Main.java**

- Starts the application by calling the *startMachine*-method of the *CoffeeMachine*-class. 

**CoffeeMachine.java** (Controller)

- Manages the state of the coffee machine.
- Controlls actions:

   - Handles main menu inputs (buy, fill etc).
   - Updates the machine resources. 
   - Checks if there a re enough resources to make a coffee.  

**Coffee.java** (Interface)

- Defines a basic structure for each coffee type.

**CoffeeFactory.java** (Factory)

- Creates an appropriate coffee-instance based on user input. 

**Cappucion.java, Espresso.java, Latte.java** (Coffee impmentations)

- Concrete classes representing different types of coffee.
- Every class implements the *Coffee.java* interface. 
- Each class defines theire own requierments for ingredients, and price. 

**MachineView.java** (View)

- Displays the machines status and menu options. 

### Running the application

**Software requirements**
- Java 8 or later

**Installation**

1. Clone the repository
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
````

2. Navigate to the project directory
```bash
cd hyperskill-projects/coffee-machine-simulator
```

3. Compile the code
```bash
javac -d out sr
c/Main.java src/machine/controller/CoffeeMachine.java src/machine/drinks/*.java src/machine/view/MachineView.java
```

4. Run the application
```bash
java -cp out Main
```

### Run example
```plaintext
Write action (buy, fill, take, remaining, exit):
remaining
The coffe machine has:
400 ml of water
540 ml of milk
120 g of coffee beans
9 disposable cups
$550 of money
Write action (buy, fill, take, remaining, exit):
buy
What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:
2
I have enough resources, making you a coffee!
Write action (buy, fill, take, remaining, exit):
fill
Write how many ml of water you want to add:
10
Write how many ml of milk you want to add:
10
Write how many grams of coffee beans you want to add:
10
Write how many disposable cups you want to add:
2
Write action (buy, fill, take, remaining, exit):
remaining
The coffe machine has:
60 ml of water
475 ml of milk
110 g of coffee beans
10 disposable cups
$557 of money
Write action (buy, fill, take, remaining, exit):
exit
```