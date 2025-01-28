package org.example.serverApp.server.util;

import org.example.serverApp.server.database.Datasource;
import org.example.serverApp.server.request.*;

public abstract class ClientRequestHandler {

    public static Response handleRequest(Request request, Datasource datasource) {

        RequestType type = RequestType.getRequest(request.getType());

        return switch (type) {

            case SET -> performRequest(new SetRequest(request.getKey(), request.getValue(), datasource));

            case GET -> performRequest(new GetRequest(request.getKey(), datasource));

            case DELETE -> performRequest(new DeleteRequest(request.getKey(), datasource));

            case EXIT -> performRequest(new ExitRequest(datasource));

            case INVALID_REQUEST -> Response.newBuilder().response("ERROR").reason(RequestType.getRequest(type)).build();
        };
    }

    private static Response performRequest(Command command) {
        ExecuteRequest executor = new ExecuteRequest(command);
        executor.execute();
        return executor.getResponse();
    }
}
