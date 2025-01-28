package org.example.serverApp.server.request;

import org.example.serverApp.server.util.Response;

public interface Command {

    void execute();

    Response getResponse();
}
