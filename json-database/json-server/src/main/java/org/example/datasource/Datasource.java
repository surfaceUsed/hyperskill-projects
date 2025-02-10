package org.example.datasource;


import com.google.gson.JsonElement;
import org.example.util.Response;

public interface Datasource {

    Response set(JsonElement key, JsonElement value);

    Response get(JsonElement key);

    Response delete(JsonElement key);

    Response exit();
}
