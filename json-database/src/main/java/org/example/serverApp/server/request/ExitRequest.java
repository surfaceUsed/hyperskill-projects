package org.example.serverApp.server.request;

import org.example.serverApp.server.database.Datasource;
import org.example.serverApp.server.util.Response;

public class ExitRequest implements Command {

    private Response response;
    private final Datasource datasource;

    public ExitRequest(Datasource source) {
        this.datasource = source;
    }

    @Override
    public void execute() {
        this.response = this.datasource.exit();
    }

    @Override
    public Response getResponse() {
        return this.response;
    }
}
