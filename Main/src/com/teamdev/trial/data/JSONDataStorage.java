package com.teamdev.trial.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;

/**
 * @author Vladimir Ikryanov
 */
public class JSONDataStorage<D> {

    private final File file;
    private final TypeToken<D> type;
    private final Gson gson;

    public JSONDataStorage(File file, TypeToken<D> type) {
        this.file = file;
        this.type = type;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    public D load() {
        try {
            FileReader reader = new FileReader(file);
            D result = gson.fromJson(reader, type.getType());
            reader.close();
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(D data) {
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(data, type.getType(), writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
