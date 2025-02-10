---
# json-database application

### Project overview

The project is a simple remote json-based storage system accessible over a network. It stores data as key-value pairs with persistant storage. The project demonstartes network communication, json processing, argument parsing, and structured request handling.

### Fetures 

- *Command-line interface*: The application allows users to interact with the server through a command-line interface. By using different flags, users can specify the type of operation, keys, values, and JSON file input.

- *Support for multiple request types:* 
   - **Get:** Retrieve data from a database based on the provided keys.
   - **Set:** Add data to a database by providing a key-value pair.
   - **Update:** Modify existing data in the database using a given key and value.
   - **Delete:** Remove a specified entrie from a database by their key.
   - **Exit:** Shuts down the server.


- *JSON-based input:* Users can choose to provide inputs as plaintext or in JSON-format, either from a file or directly in the command-line.

- *Socket communication:* The client communicates with the server over a TCP socket. Both the client and server applications support a
basic request-response cycle with each other. Every request and response is processed in real-time.

- *Flexible key-value handling:* The application allows for flexible key-value handling, including complex nested values (e.g., JSON objects or arrays).

- *Dependencies:*

   - `JCommander` (com.beust:jcommander:1.82)
Used for parsing command-line arguments.

   - `Gson` (com.google.code.gson:gson:2.10.1)
Used for handling JSON serialization and deserialization.

- Build-tool:

   - Apache Maven.

### Project structure

The `json-database` project consists of two main modules:

- `json-client` - A client application that interacts with the server by sending requests and processing responses.

- `json-server` - A server that handles client requests, processes database operations, and manages file storage.

#### **json-client**

This module is responsible for sending requests to the server, and handling responses. The client application takes input from the command line and parses the input into a json string in the form of a request-object. Communication with the server ends after the response from the server is received. 

- `resources/scripts/` - contains JSON scripts for various client requests.

- `org.example.ClientApplicationRunner.java` - The entry point for the client application.

- `org.example.util.CommandLineParser.java` - Parses command-line arguments.

- `org.example.client.Client.java` - Manages communication with the server. Sends requests and parses the response.

#### **json-server**

This module processes client requests, manages the JSON database, and handles file operations. The server application serves 
as a remote datbase storage, and listens to incoming client requests, processes them, and sends back an appropriate respons. 
The server provides basic CRUD-operations.

- `resources/database/db.json` - The main JSON database file.

- `org.example.ServerApplicationRunner.java` - The entry point for the server application.

- `org.example.util/` - Utility classes for handling directories, requests and responses. 

- `org.example.handler/` - Processes various request types.

- `org.example.enums.RequestType.java` - Defines request types.

- `org.example.datasource/` - Manages data storage and file system operations.

- `org.example.core/` - Core server logic. 

```plaintext
json-database/
│
├── json-client/
│   ├── pom.xml
│   ├── README.md
│   ├── src/main/java/
│       ├── resources/scripts/
│       │   ├── deleteFile.json
│       │   ├── getFile.json
│       │   ├── setFile.json
│       │   ├── updateFile.json
│       ├── org/example/
│           ├── ClientApplicationRunner.java
│           ├── util/
│           │   ├── CommandLineParser.java
│           ├── client/
│               ├── Client.java
│
├── json-server/
│   ├── pom.xml
│   ├── README.md
│   ├── src/main/java/
│       ├── resources/database/
│       │   ├── db.json
│       ├── org/example/
│           ├── ServerApplicationRunner.java
│           ├── util/
│           │   ├── Directory.java
│           │   ├── DirectoryLoader.java
│           │   ├── Request.java
│           │   ├── Response.java
│           ├── handler/
│           │   ├── RequestHandler.java
│           │   ├── Command.java
│           │   ├── DeleteRequest.java
│           │   ├── ExecuteRequest.java
│           │   ├── ExitRequest.java
│           │   ├── GetRequest.java
│           │   ├── SetRequest.java
│           ├── enums/
│           │   ├── RequestType.java
│           ├── datasource/
│           │   ├── Datasource.java
│           │   ├── FileSystem.java
│           ├── core/
│               ├── Server.java
│               ├── Session.java
```

### Command-line usage

The json-client application allows users to interact with the server using various commands. These commands follow a structured format using command-line arguments.

**General command format:**

```plaintext
-t <type> -k <key> [-v <value>] [-in <json-file>]
```

**Command arguments:**
- `-t` <type> → Specifies the type of request (`get`, `set`, `delete`, `update`).

- `-k` <key> → The key or path in the database to operate on.

- `-v` <value> → (Optional) The value to set/update in the database.

- `-in` <json-file> → (Optional) A JSON file containing the request.

#### **Setting a Value in the Database**

To store a value under a key:

```plaintext
-t set -k person -v "Bill Gates"
```
**How It Works:**

- `-t` set → This is a set request.

- `-k` person → The key is "person".

- `-v` "Bill Gates" → The value is "Bill Gates".

**JSON sent to server:**

```json
{
  "type": "set",
  "key": "person",
  "value": "Bill Gates"
}
```

**Server response:**

```json
{"response": "OK"}
```

#### **Retrieving a Value from the Database**

```plaintext
-t get -k person
```

**How It Works:**

- `-t` get → This is a get request.

- `-k` person → Retrieves the value stored under "person".

**JSON sent to server:**

```json
{
  "type": "get",
  "key": "person"
}
```

**Server response:**

```json
{"response": "OK", "value": "Bill Gates"}
```

#### **Retrieving a Nested Value**

To get a nested value (e.g., the name inside person):

```plaintext
-t get -k person,name
```

**How It Works:**

- `-k person,name` → The key path `"person,name"` targets `person → name`.

- If the database contains:

```json
{
  "person": {
    "name": "Elon Musk",
    "car": {
      "model": "Tesla Roadster",
      "year": "2018"
    }
  }
}
```

- The server returns:

```json
{"response": "OK", "value": "Elon Musk"}
```

#### **Deleting a value**

```plaintext
-t delete -k person,name
```

**How it works:**

- Deletes the `"name"` field under `"person"`.

- If successful, the server responds:

```json
{"response": "OK"}
```

#### **Updating an existing value**

```plaintext
-t update -k person,name -v "Steve Jobs"
```

**How It Works:**

- Updates `"person → name"` from `"Elon Musk"` to `"Steve Jobs"`.

**JSON Sent to Server:**

```json
{
  "type": "set",
  "key": ["person","name"],
  "value": "Steve Jobs"
}
````

**Server response:**

```json
{"response": "OK"}
```

#### **Using a JSON File for Complex Requests**

Instead of passing arguments manually, you can use a predefined JSON file (using `setFile.json` from `resources/scripts/` in `json-client`:

```plaintext
-in src/main/resources/scripts/setFile.json
```

**Json sent to server:**

```json
{
  "type": "set",
  "key": "person",
  "value": {
    "name": "Bill Gates",
    "car": {
      "model": "Porsche 911",
      "year": "2019"
    },
    "rocket": {
      "name": "Falcon 9",
      "launches": "87"
    }
  }
}
```

**Server response:**

```json
{"response": "OK"}
```

#### **Shutting down the server**

The server can only be closed manually when it receives an `exit` command from a `client`.

```plaintext
-t exit
```
**Server response:**

```json
{"response": "OK"}
```

This command shuts down the server properly.

---

### Running the application

For the `json-database` application to function, it's important to run the `json-server` before starting the `json-client`.

**Software requirements**

- Java 20 or later
- Apache Maven. If Maven is not installed, download it [here](https://maven.apache.org/install.html).

**Initial steps**

1. Clone the repository.

```bash
git clone https://github.com/surfaceUsed/hyperskill-projects.git
```

2. Navigate to the project repository.

`json-server`:
```bash
cd hyperskill-projects/json-database/json-server
```

`json-client`:
```bash
cd hyperskill-projects/json-database/json-client
```

3. Follow these steps to create an executable `.jar` (do the same thing for both modules).

```bash
mvn clean
mvn compile
mvn package
```
This will create a `.jar` file in the `target/` directory.

4. Run the application (remember command-line arguments when running the `.jar` for the `json-client`).

When running the `json-server` it's necessary to give a path to the storage file. The application already has a built-in file for this, so you can use the default path `src/main/java/resources/database/db.json`, or create your own. 

```bash
java -jar target/json-server-1.0.jar src/main/java/resources/database/db.json
```

```bash
java -jar target/json-client-1.0.jar -t <type> -k <key> [-v <value>] [-in <json-file>]
```
---

### Notes

- The application uses comma-separated keys for nested structures.

- You can use either command-line arguments or JSON files.

- Always wrap values containing spaces in quotes.

---