package com.rajendarreddy.reports.odttopdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import fr.opensagres.odfdom.converter.pdf.PdfConverter;
import fr.opensagres.odfdom.converter.pdf.PdfOptions;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ConvertODTToPDF {
    public static void main(final String[] args) {
        generatePDF("src/main/resources/ODTToPDF.odt", "target/ODTToPDF.pdf");
    }

    /**
     * 
     */
    private static void generatePDF(final String odtFileName, final String targetFilePath) {
        long startTime = System.currentTimeMillis();
        try {
            File inputFile = new File(odtFileName);
            OdfTextDocument document = OdfTextDocument.loadDocument(new FileInputStream(inputFile));
            /*InputStream in = ConvertODTToPDF.class.getResourceAsStream( "ODTToPDF.odt" );*/
            File outFile = new File(targetFilePath);
            outFile.getParentFile().mkdirs();
            OutputStream out = new FileOutputStream(outFile);
            PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
            PdfConverter.getInstance().convert(document, out, options);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Generate " + targetFilePath + " with " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}