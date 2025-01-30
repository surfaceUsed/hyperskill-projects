# Cinema Room Manager

### Description
The Cinema Room Manager is a console-based application that allows users to manage a cinema room, including booking tickets, 
viewing seat availability, and checking statistics. It simulates a cinema environment where users can interact with a menu 
to perform different actions.

### Features

- **Show the seats**: Display the current seating arrangement of the cinema.
- **Buy a ticket**: Allows users to select a seat and purchase a ticket.
- **Statistics**: View statistics like the number of purchased tickets, percentage of seats taken, current income, and total 
income (the maximum income the cinama can have based on the number of seats).

### Pricing requierments
If the cinema has 60 or fewer seats, the price for all tickets is $10.
If the cinema has more than 60 seats:
Seats in the first half of the cinema (front rows) cost $10.
Seats in the second half (back rows) cost $8.

### How to run

**Software requierments**

- Java 8 or later.

**Installation**


1. Clone the repository or download the source code
```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project directory
```bash
cd hyperskill-projects/cinema-room-manager
```

3. Compile the code
```bash
javac -d out src/Main.java
```

4. Run the application
```bash
java -cp out Main
```

### Project structure
```plaintext
cinema-room-manager/
├── src/
│   |── Main.java 
|── README.md
```

### Run example
```plaintext
Enter the number of rows:
10
Enter the number of seats in each row:
10
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
1
Cinema:
  1 2 3 4 5 6 7 8 9 10
1 S S S S S S S S S S
2 S S S S S S S S S S
3 S S S S S S S S S S
4 S S S S S S S S S S
5 S S S S S S S S S S
6 S S S S S S S S S S
7 S S S S S S S S S S
8 S S S S S S S S S S
9 S S S S S S S S S S
10 S S S S S S S S S S

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
2
Enter a row number:
1
Enter a seat number in that row:
1

Ticket price: $10

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
1
Cinema:
  1 2 3 4 5 6 7 8 9 10
1 B S S S S S S S S S
2 S S S S S S S S S S
3 S S S S S S S S S S
4 S S S S S S S S S S
5 S S S S S S S S S S
6 S S S S S S S S S S
7 S S S S S S S S S S
8 S S S S S S S S S S
9 S S S S S S S S S S
10 S S S S S S S S S S

1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
3
Number of purchased tickets: 1
Percentage: 1,00%
Current income: $10
Total income: $900
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
0
```