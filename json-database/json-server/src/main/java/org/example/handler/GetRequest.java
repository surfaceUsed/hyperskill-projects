package org.example.handler;

import com.google.gson.JsonElement;
import org.example.datasource.Datasource;
import org.example.util.Response;

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
