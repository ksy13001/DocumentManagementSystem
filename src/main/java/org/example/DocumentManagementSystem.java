package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {

    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem(){
        extensionToImporter.put("jpg", new ImageImporter());
        extensionToImporter.put("report", new ImageImporter());
        extensionToImporter.put("letter", new ImageImporter());
    }

    void importFile(String path){

    }

    List<Document> content(){
        return null;
    }
}
