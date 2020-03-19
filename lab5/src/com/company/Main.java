package com.company;

import java.io.IOException;
import java.rmi.server.ExportException;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        try {
            app.testCreateSave();
            app.testLoadView();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void testCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("Java Resources", "d:/java/catalog.ser");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws InvalidCatalogException, IOException {
        Catalog catalog = CatalogUtil.load("d:/java/catalog.ser");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}