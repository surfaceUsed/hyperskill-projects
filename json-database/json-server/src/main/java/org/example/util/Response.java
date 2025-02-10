package org.example.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Response {

    private String response;
    private String reason;
    private JsonElement value;

    private Response() {}

    private void setResponse(String response) {
        this.response = response;
    }

    private void setReason(String reason) {
        this.reason = reason;
    }

    private void setValue(JsonElement value) {
        this.value = value;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    // Inner builder class.
    public static class Builder {

        private final Response response;

        private Builder() {
            this.response = new Response();
        }

        public Builder response(String response) {
            this.response.setResponse(response);
            return this;
        }

        public Builder reason(String reason) {
            this.response.setReason(reason);
            return this;
        }

        public Builder value(JsonElement value) {
            this.response.setValue(value);
            return this;
        }

        public Response build() {
            return this.response;
        }
    }
}
