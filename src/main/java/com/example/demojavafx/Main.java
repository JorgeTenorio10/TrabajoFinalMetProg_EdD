package com.example.demojavafx;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String SAVE_FILE = "game_data.json";

    public static void main(String[] args) {
        // Crear el archivo JSON si no existe
        createSaveFileIfNotExists();

        // Resto del código de inicio de tu aplicación
        // launch(args); si estás usando JavaFX
    }

    private static void createSaveFileIfNotExists() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");  // Escribe un array JSON vacío
                System.out.println("Archivo game_data.json creado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Archivo game_data.json ya existe.");
        }
    }
}
