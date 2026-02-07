package org.example;

import javax.print.Doc;
import java.util.List;

public class Document {
    private String name;
    private DocumentType type;
    private String path;
    private List<String> content;

    public Document(String name, DocumentType type, String path, List<String> content){
        this.name = name;
        this.type = type;
        this.path = path;
        this.content = content;
    }

    public String getName(){
        return name;
    }

    public DocumentType getType(){
        return type;
    }

    public String getPath(){
        return path;
    }

    public List<String> getContent(){
        return content;
    }
}
