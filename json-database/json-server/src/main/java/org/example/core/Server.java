package org.example.core;

import org.example.datasource.Datasource;
import org.example.datasource.FileSystem;
import org.example.util.Directory;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public final static String EXIT_COMMAND = "exit";

    private final ServerSocket serverSocket;
    private final Datasource datasource;

    public Server(ServerSocket serverSocket, Directory directory) {
        this.serverSocket = serverSocket;
        this.datasource = new FileSystem(directory);
    }

    public void start() {

        try {

            System.out.println("Server is running.");

            while (!this.serverSocket.isClosed()) {

                System.out.println("Waiting for client connection..");
                Thread thread = new Thread(new Session(this, this.serverSocket.accept(), this.datasource));
                thread.start();
                System.out.println("Client connection established!");
            }

        } catch (IOException e) {
            System.out.println("Error connecting to client: " + e.getMessage());
        }

        System.out.println("Server is closed.");
    }

    synchronized void closeServer() {

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error shutting down server: " + e.getMessage());
        }
    }
}
