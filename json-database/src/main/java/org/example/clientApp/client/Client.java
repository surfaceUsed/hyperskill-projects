package org.example.clientApp.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private final Socket socket;
    private final String clientRequest;

    public Client(Socket socket, String clientRequest) {
        this.socket = socket;
        this.clientRequest = clientRequest;
    }

    public void startClient() {

        try (DataInputStream input = new DataInputStream(this.socket.getInputStream());
             DataOutputStream output = new DataOutputStream(this.socket.getOutputStream())) {

            output.writeUTF(this.clientRequest);
            String serverResponse = input.readUTF();

            System.out.println("Sent: " + this.clientRequest);
            System.out.println("Received: " + serverResponse);

        } catch (IOException e) {
            System.out.println("Error running client: " + e.getMessage());
        }
    }
}
