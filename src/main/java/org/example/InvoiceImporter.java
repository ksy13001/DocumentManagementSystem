package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.jar.Attributes;

import static org.example.Attributes.*;

public class InvoiceImporter implements Importer{

    private static final String NAME_SUFFIX = "Dear ";
    private static final String AMOUNT_SUFFIX = "Amount: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_SUFFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_SUFFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");

        return new Document(attributes);
    }
}
