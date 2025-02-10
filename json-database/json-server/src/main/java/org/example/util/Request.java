package org.example.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Request {

    private String type;
    private JsonElement key;
    private JsonElement value;

    private Request() {}

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    public static Request fromJson(String json) {
        return new Gson().fromJson(json, Request.class);
    }
}

