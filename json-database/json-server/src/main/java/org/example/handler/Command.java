package org.example.handler;


import org.example.util.Response;

public interface Command {

    void execute();

    Response getResponse();
}
