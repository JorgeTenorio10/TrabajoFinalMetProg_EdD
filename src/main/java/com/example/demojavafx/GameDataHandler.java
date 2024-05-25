package com.example.demojavafx;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class GameDataHandler {

    private static final String SAVE_FILE = "game_data.json";
    private Gson gson;

    public GameDataHandler() {
        // Crear un Gson con un adaptador personalizado para ListaEnlazada
        this.gson = new GsonBuilder()
                .registerTypeAdapter(ListaEnlazada.class, new ListaEnlazadaTypeAdapter())
                .create();
    }

    public void saveGame(ListaEnlazada<ParameterDataModel> modelList) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            System.out.println("Saving game data: " + gson.toJson(modelList));
            gson.toJson(modelList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ListaEnlazada<ParameterDataModel> loadGame() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.out.println("Save file does not exist, creating a new empty list.");
            return new ListaEnlazada<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ListaEnlazada<ParameterDataModel>>() {}.getType();
            ListaEnlazada<ParameterDataModel> data = gson.fromJson(reader, listType);
            System.out.println("Loaded game data: " + gson.toJson(data));
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class ListaEnlazadaTypeAdapter implements JsonSerializer<ListaEnlazada>, JsonDeserializer<ListaEnlazada> {

    @Override
    public JsonElement serialize(ListaEnlazada src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        ElementoLE current = src.getPrimero();
        while (current != null) {
            jsonArray.add(context.serialize(current.getData()));
            current = current.getSiguiente();
        }
        return jsonArray;
    }

    @Override
    public ListaEnlazada deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ListaEnlazada lista = new ListaEnlazada();
        JsonArray jsonArray = json.getAsJsonArray();
        for (JsonElement element : jsonArray) {
            ParameterDataModel data = context.deserialize(element, ParameterDataModel.class);
            lista.add(data);
        }
        return lista;
    }
}

