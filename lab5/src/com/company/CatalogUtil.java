package com.company;

import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException {
        Catalog c;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            c = (Catalog)ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
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
        //… browse or open, depending of the location type
    }

}