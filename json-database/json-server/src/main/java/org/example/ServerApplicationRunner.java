package org.example;

import org.example.core.Server;
import org.example.util.Directory;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerApplicationRunner {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5555)) {

            Server server = new Server(serverSocket, Directory.loadDirectory(args[0]));

            server.start();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}