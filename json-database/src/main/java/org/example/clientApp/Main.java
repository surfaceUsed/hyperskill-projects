package org.example.clientApp;

import org.example.clientApp.client.Client;
import org.example.clientApp.client.util.CommandLineParser;

import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5555)) {

            new Client(socket, CommandLineParser.jsonParser(args)).startClient();

        } catch (IOException e) {
            System.out.println("Error communicating with server, main(): " + e.getMessage());
        }
    }
}
