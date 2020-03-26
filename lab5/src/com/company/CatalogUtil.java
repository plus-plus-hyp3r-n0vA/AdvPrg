package com.company;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CatalogUtil {
    public static void saveBinary(Catalog catalog) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog loadBinary(String path) throws InvalidCatalogException {
        Catalog c;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            c = (Catalog)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new InvalidCatalogException(e);
        }
        return c;
    }

    public static void saveText(Catalog catalog) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(catalog.getPath()))) {
            Gson gson = new Gson();
            Type catalogType = new TypeToken<Catalog>() {}.getType();
            fos.write(gson.toJson(catalog, catalogType).getBytes());
        }
    }

    public static Catalog loadText(String path) throws InvalidCatalogException {
        Catalog c;
        try (JsonReader reader = new JsonReader(new FileReader(path))) {
            Gson gson = new Gson();
            c = gson.fromJson(reader, Catalog.class);
        } catch (IOException e) {
            throw new InvalidCatalogException(e);
        }
        return c;
    }

    public static void view (Document doc) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(URI.create(doc.getLocation()));
        } catch (UnsupportedOperationException e)
        {
            desktop.open(new File(doc.getLocation()));
        }
    }

}