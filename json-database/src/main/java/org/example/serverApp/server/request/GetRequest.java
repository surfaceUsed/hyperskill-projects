package org.example.serverApp.server.request;

import com.google.gson.JsonElement;
import org.example.serverApp.server.database.Datasource;
import org.example.serverApp.server.util.Response;

public class GetRequest implements Command {

    private final JsonElement key;
    private Response response;
    private final Datasource datasource;

    public GetRequest(JsonElement key, Datasource source) {
        this.key = key;
        this.datasource = source;
    }

    @Override
    public void execute() {
        this.response = this.datasource.get(this.key);
    }

    @Override
    public Response getResponse() {
        return this.response;
    }
}
