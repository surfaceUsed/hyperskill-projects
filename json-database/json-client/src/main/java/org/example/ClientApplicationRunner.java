package org.example;

import org.example.client.Client;
import org.example.util.CommandLineParser;
import java.io.IOException;
import java.net.Socket;

public class ClientApplicationRunner {

    // input delete: src/main/resources/scripts/deleteFile.json
    // input get: src/main/resources/scripts/getFile.json
    // input get: src/main/resources/scripts/secondGetFile.json
    // input set: src/main/resources/scripts/setFile.json
    // input update: src/main/resources/scripts/updateFile.json

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5555)) {

            new Client(socket, CommandLineParser.jsonParser(args)).startClient();

        } catch (IOException e) {
            System.out.println("Error communicating with server, main(): " + e.getMessage());
        }
    }
}