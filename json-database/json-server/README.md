--- 
# json-server 

### Overview 

The `json-server` is a simple server application designed to handle JSON-based requests over a network. The server is designed to handle multiple clients simultaneously. Each client request is processed in a separate thread, ensuring that multiple users can interact with the server without blocking each other.
It listens for incoming client requests, processes them and returns appropriate JSON responses. The server supports basic CRUD operations. 

--- 
### Project structure 

```plaintext
json-server/
├── pom.xml
├── README.md
├── src/main/java/
│   ├── resources/database/
│   │   ├── db.json
│   ├── org/example/
│       ├── ServerApplicationRunner.java
│       ├── util/
│       │   ├── Directory.java
│       │   ├── DirectoryLoader.java
│       │   ├── Request.java
│       │   ├── Response.java
│       ├── handler/
|       │   ├── RequestHandler.java
│       │   ├── Command.java
│       │   ├── DeleteRequest.java
|       │   ├── ExecuteRequest.java
|       │   ├── ExitRequest.java
│       │   ├── GetRequest.java
│       │   ├── SetRequest.java
│       ├── enums/
│       │   ├── RequestType.java
│       ├── datasource/
│       │   ├── Datasource.java
│       │   ├── FileSystem.java
│       ├── core/
│           ├── Server.java
│           ├── Session.java
```

#### **Classes and responsebilities**

**core-package:**

- `ServerApplicationRunner.java`

   - Entry point of the application.

   - Initializes the server socket on a default port `5555`, parses the command-line input and starts the server. 

   - The command-line input loads the directory where the JSON data is stored.

- `Server.java`:
The server is responsible for accepting new client connections, and creates a new thread for each client connection to handle requests concurrently. 

   - Uses the loaded `Directory` to initialize the `Datasource` the program will be working with.

   - It delegates each new connection to their own handler-object (`Session.java`). 

   - Manages the server’s lifecycle, ensuring it can be started and stopped gracefully.

- `Session.java`: Each Session instance is run in a separate thread, allowing the server to manage multiple client connections at the same time without blocking other requests. This makes the server capable of handling concurrent client interactions efficiently.

   - Acts as a handler for individual client connections.

   - Manages communication between the server and a singel client. 

   - Reads a request from the client, processes it, and sends back a response.

   - Uses the `Datasource` object to interact with the underlying data storage.

   - Invokes the RequestHandler to process client requests.

   - Closes the client socket after handling the request.

**datasource-package:**

- `Datasource.java`: The Datasource interface defines a contract for data storage operations. It provides methods to set, retrieve, delete, and persist data. Implementations of this interface handle actual data storage and retrieval.

- `FileSystem.java`: The `FileSystem` class is a concrete implementation of the `Datasource` interface that manages data persistence using a JSON-based file storage system. It ensures efficient and concurrent handling of key-value storage while persisting data to a file.

   - Loads and maintains a JSON database from a file.
   - Provides thread-safe access to data using read-write locks:

        - **read-locks:** for retrieval operations to allow concurrent reads.

        - **write-locks:** for modifying operations to prevent data corruption.


   - Implements key-value storage where keys can be single strings or neste JSON structures.
   - Saves changes to the file on exit.

**util-package:**

- `Directory.java`: The class provides an abstract base for managing file-related operations. It defines abstract methods for creating, reading, writing to, and retrieving file information such as file name and full path. It also includes a static `loadDirectory` method that returns a valid `Directory` implementation, specifically `DirectoryLoader`, if the provided directory path is valid.

- `DirectoryLoader.java`: `DirectoryLoader` is a concrete subclass of `Directory`, responsible for interacting with a specific directory and its files. It handles the verification of directory validity, file creation, reading- and writing to a file.

- `Request.java`: The class represents an incoming request with a type, a key, and a value, typically used in scenarios where JSON data is exchanged. It uses the Gson library to deserialize the JSON string into an instance of `Request`. The class provides getter methods for the type, key, and value fields, which can be accessed after deserialization.

   - `type`: A string representation of the request type.
   - `key`: A `JsonElement` that represents the key in the request data (data location).
   - `value`: A `JsonElement` that represents the value associated with the key in the request.

- `Response.java`: The class represents an HTTP-like response with a status, reason, and optional value. It includes an inner `Builder` class for constructing a `Response` object in a fluent manner. The class uses Gson to convert the response object into a JSON string. The `Response` class is used to send responses with a status and additional information back to the client.

   - `response`: A string representation of the response status. Either *"OK"* or *"ERROR"*. 
   - `reason`: A string representation that provides a reason for the response. In this application the `reason` attribute is only set if an invalid key value is entered by the client: `"no such key"`. 
   - `value`: A `JsonElement` that can hold aditional data in the response. Used in `get` requests. 

**enums-package:**

- `RequestType.java`: An `enum` class that represents different types of requests. 

   - `SET`: A `set` request for adding and updating data in the database. 
   - `GET`: A `get` request for retrieving data from the database. 
   - `DELETE`: A `delete` request for removing data from the database.
   - `EXIT`: Represents an `exit` request for ending the client session, and shutting down the server. 
   - `INVALID_REQUEST`: Represents an invalid or unrecognized request. 

**handler-package:**

Each of these classes plays a role in the command pattern architecture, allowing actions (such as `set`, `get`, `update`, `delete` and exit) to be encapsulated as individual commands that can be executed, with responses returned after the actions are completed. The RequestHandler class ties everything together by dispatching the correct command based on the incoming request type.

- `Command.java`: An interface that defines the contract for all command classes that handles specific requests (`set`, `get`, `update`, `delete` or `exit`). It declares two methods:
   
   - `execute()`: performs the request.
   - `getResponse()`: returns the response after the `execute()` method is done processing. 

- `SetRequest.java, GetRequest.java, DeleteRequest.java, ExitRequest.java`: Responsible for handling specific client requests. Each class has access to the `Datasource` and performs operations on it, and returns a `Response` after the operation. 

   - The `SetRequest.java` is responsible for both `set` and `update` requests.
   - The `ExitRequest.java` makes sure all modifications are saved to the database.

- `RequestHandler.java`: Responsible for handling incoming requests based on the request type. It acts as a central dispatcher that interprets the request type and routs it to the appropriate handler class. The class simplifies request processing and keeps the logic of different request types separate, following the command pattern.

   - `handleRequest()`: takes a `Request` and `Datasource` object as parameters, and determins the type of request it has to deal with via the method `RequestType.getRequest()`. 

   - The matching request type is executed in the `switch` statement, and an appropriate `Response` is returned. 

---