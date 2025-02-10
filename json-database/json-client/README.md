--- 

# json-client

### Overview

This is a simple JSON-based client application that connects to a server via sockets. The client sends requests in 
JSON format and receives responses from the server. The project is designed to interact with a JSON database and 
supports various operations such as retrieving, updating, setting, and deleting values.

---

### Project structure

- `src/`: contains the source code.

   - `main/java/org/example/`: contains the main classes for the application logic.

   - `resources/scripts`: contains `set`, `get`, `delete` and `update` scrips in `JSON` format.

```plaintext
json-client/
├── src/main/java/org/example/
│   ├── ClientApplicationRunner.java
│   ├── client/
│   │   ├── Client.java
│   ├── util/
│   │   ├── CommandLineParser.java
│   ├── resources/
│       ├── scripts/
│           ├── deleteFile.json
│           ├── getFile.json
│           ├── setFile.json
│           ├── updateFile.json
├── pom.xml
├── README.md
```

#### **Classes and Responsibilities**

`ClientApplicationRunner.java`

- Entry point of the application.

- Establishes a socket connection to the server.

- Calls the Client class to handle the request.

- Parses command-line arguments using `CommandLineParser.java`.

- Reads JSON requests from predefined files in resources/scripts/.

- Handles connection errors.

`Client.java`

- Represents the client-side network communication.

- Sends a JSON-encoded request to the server.

- Reads and prints the server's response.

- Uses a Socket with `DataInputStream` and `DataOutputStream` for communication.

- Ensures proper resource management using try-with-resources.

`CommandLineParser.java`

- Parses command-line arguments using the `JCommander` library.

- Converts user input into a structured JSON request.

- Reads JSON files if provided as input.

- Supports the following command-line arguments:

   - `-t` (type): Type of request (get, set, delete, update, exit)

   - `-k` (key): The key to access data

   - `-v` (value): The value to set in the database

   - `-in` (jsonFile): Path to a JSON file containing a request

---