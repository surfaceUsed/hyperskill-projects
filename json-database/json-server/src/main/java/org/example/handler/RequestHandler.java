package org.example.handler;

import org.example.datasource.Datasource;
import org.example.enums.RequestType;
import org.example.util.Request;
import org.example.util.Response;

public abstract class RequestHandler {

    public static Response handleRequest(Request request, Datasource datasource) {

        RequestType type = RequestType.getRequest(request.getType());


        return switch (type) {

            case SET -> new SetRequest(request.getKey(), request.getValue(), datasource).getResponse();

            case GET -> new GetRequest(request.getKey(), datasource).getResponse();

            case DELETE -> new DeleteRequest(request.getKey(), datasource).getResponse();

            case EXIT -> new ExitRequest(datasource).getResponse();

            case INVALID_REQUEST -> Response.newBuilder().response("ERROR").reason(RequestType.getRequest(type)).build();
        };
    }
}
