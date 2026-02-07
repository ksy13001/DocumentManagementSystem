package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DocumentManagementSystem {

    private final List<Document> documents = new ArrayList<>();

    void importFile(String path) throws IOException {
        Path p = Paths.get(path);
        List<String> lines = Files.readAllLines(p);

        int index = path.lastIndexOf('.');
        String extension = path.substring(index + 1);

        Document document = new Document(
                p.getFileName().toString(),
                DocumentType.parseExtension(extension),
                path,
                lines);
        documents.add(document);
    }

    private List<String> readLines(String path) throws IOException {
        Path p = Paths.get(path);
        return Files.readAllLines(p);
    }

    List<Document> content(){
        return documents;
    }

    List<Document> searchDocumentsByName(String query){
        return documents.stream()
                .filter(doc -> doc.getName().contains(query))
                .toList();
    }
}
