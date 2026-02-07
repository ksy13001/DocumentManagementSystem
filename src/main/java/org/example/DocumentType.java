package org.example;

public enum DocumentType {
    IMAGE("jpg"),
    LETTER("letter"),
    REPORT("report");

    private final String extension;

    DocumentType(String extension){
        this.extension = extension;
    }

    public String getExtension(){
        return extension;
    }

    public static DocumentType parseExtension(String extension){
        for (DocumentType type : values()) {
            if (type.extension.equals(extension)) return type;
        }
        throw new IllegalArgumentException("Unknown extension: " + extension);
    }
}
