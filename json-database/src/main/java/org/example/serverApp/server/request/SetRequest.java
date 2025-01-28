package org.example.serverApp.server.request;

import com.google.gson.JsonElement;
import org.example.serverApp.server.database.Datasource;
import org.example.serverApp.server.util.Response;

public class SetRequest implements Command {

    private final JsonElement key;
    private final JsonElement value;
    private final Datasource datasource;
    private Response response;

    public SetRequest(JsonElement key, JsonElement value, Datasource source) {
        this.key = key;
        this.value = value;
        this.datasource = source;
    }

    @Override
    public void execute() {
        this.response = this.datasource.set(this.key, this.value);
    }

    @Override
    public Response getResponse() {
        return this.response;
    }
}
