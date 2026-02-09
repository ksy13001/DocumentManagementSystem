package org.example;

import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;

import static org.example.Attributes.*;
import static org.junit.jupiter.api.Assertions.*;

class DocumentManagementSystemTest {

    private static final String RESOURCE= "src/test/java/resources/";
    private static final String IMAGE_PATH = RESOURCE + "xray.jpg";
    private static final String INVOICE_PATH = RESOURCE + "patient.invoice";
    private static final String LETTER_PATH = RESOURCE + "patient.letter";
    private static final String REPORT_PATH = RESOURCE + "patient.report";
    private static final String UNKNOWN_PATH = RESOURCE + "patient.unknown";

    private static final String JOE_BLOGGS = "Joe Bloggs";

    private DocumentManagementSystem dms = new DocumentManagementSystem();

    @Test
    void shouldImportDocument() throws IOException {
        // given
        dms.importFile(INVOICE_PATH);
        // when && then
        Document document = onlyOneDocument();
        assertEquals(JOE_BLOGGS, document.getAttribute(PATIENT));
    }


    @Test
    void shouldImportLetterAttributes() throws IOException {
        // given
        dms.importFile(LETTER_PATH);
        // when && then
        Document document = onlyOneDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");
        assertAttributeEquals(document, BODY,
                "We are writing to you to confirm the re-scheduling of your appointment\n" +
                        "with Dr. Avaj from 29th December 2016 to 5th January 2017.");
        assertTypeIs("LETTER", document);
    }

    @Test
    void shouldImportReportAttributes() throws IOException {
        // given
        dms.importFile(REPORT_PATH);
        // when && then
        Document document = onlyOneDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, BODY,
                "On 5th January 2017 I examined Joe's teeth.\n" +
                        "We discussed his switch from drinking Coke to Diet Coke.\n" +
                        "No new problems were noted with his teeth.");
        assertTypeIs("REPORT", document);
    }

    @Test
    void shouldImportInvoiceAttributes() throws IOException {
        // given
        dms.importFile(INVOICE_PATH);
        // when && then
        Document document = onlyOneDocument();
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, AMOUNT, "$100");
        assertTypeIs("INVOICE", document);
    }

    @Test
    void shouldImportImageAttributes() throws IOException {
        // given
        dms.importFile(IMAGE_PATH);
        // when && then
        Document document = onlyOneDocument();
        assertAttributeEquals(document, WIDTH, "193");
        assertAttributeEquals(document, HEIGHT, "183");
        assertTypeIs("IMAGE", document);
    }

    @Test
    public void shouldBeTotalFiles() throws IOException {
        dms.importFile(INVOICE_PATH);
        dms.importFile(LETTER_PATH);
        dms.importFile(REPORT_PATH);
        dms.importFile(IMAGE_PATH);

        List<Document> documents = dms.content();
        assertEquals(4, documents.size());
    }

    @Test()
    public void  shouldUnknownFileTypeException() throws Exception
    {
        assertThrows(UnknownFileTypeException.class, ()->dms.importFile(UNKNOWN_PATH));
    }




    private void assertAttributeEquals(
            final Document document,
            final String attributeName,
            final String expectedValue)
    {
        assertEquals(
                expectedValue,
                document.getAttribute(attributeName));
    }
    // end::assertAttributeEquals[]

    private void assertTypeIs(final String type, final Document document)
    {
        assertAttributeEquals(document, TYPE, type);
    }


    private Document onlyOneDocument(){
        final List<Document> documents = dms.content();
        assertEquals(1, dms.content().size());
        return documents.getFirst();
    }


}