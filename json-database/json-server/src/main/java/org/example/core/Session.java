package org.example.core;

import org.example.datasource.Datasource;
import org.example.handler.ClientRequestHandler;
import org.example.util.Request;
import org.example.util.Response;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Session implements Runnable {

    private final Server server;
    private final Socket socket;
    private final Datasource datasource;

    Session(Server server, Socket socket, Datasource datasource) {
        this.server = server;
        this.socket = socket;
        this.datasource = datasource;
    }

    private void startSession(DataInputStream input, DataOutputStream output) throws IOException {

        Request fromClient = Request.fromJson(input.readUTF());

        Response response = ClientRequestHandler.handleRequest(fromClient, this.datasource);

        output.writeUTF(response.toJson());

        if (fromClient.getType().equals(Server.EXIT_COMMAND)) {
            this.server.closeServer();
        }

        this.socket.close();
    }

    @Override
    public void run() {

        try (DataInputStream input = new DataInputStream(this.socket.getInputStream());
             DataOutputStream output = new DataOutputStream(this.socket.getOutputStream())) {

            startSession(input, output);

        } catch (IOException e ) {
            System.out.println(e.getMessage());
        }
    }
}
