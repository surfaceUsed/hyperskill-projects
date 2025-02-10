package org.example.handler;

import org.example.datasource.Datasource;
import org.example.util.Response;

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
