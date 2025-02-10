package org.example;

import org.example.core.Server;
import org.example.util.Directory;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerApplicationRunner {

   // private static final String DATABASE_URL = "src/main/resources/database/db.json";

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5555)) {

            Server server = new Server(serverSocket, Directory.loadDirectory(args[0]));
            //Server server = new Server(serverSocket, Directory.loadDirectory(DATABASE_URL));

            server.start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}