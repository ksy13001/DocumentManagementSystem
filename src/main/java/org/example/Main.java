package org.example;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        DocumentManagementSystem dms = new DocumentManagementSystem();
        dms.importFile(("src/main/resources/image.jpg"));
        dms.importFile(("src/main/resources/letter.letter"));
        dms.importFile(("src/main/resources/report.report"));
        System.out.println(dms.content());
        System.out.println(dms.searchDocumentsByName("image"));
    }
}