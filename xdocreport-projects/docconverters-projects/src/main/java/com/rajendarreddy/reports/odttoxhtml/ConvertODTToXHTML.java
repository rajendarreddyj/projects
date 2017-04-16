package com.rajendarreddy.reports.odttoxhtml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import fr.opensagres.odfdom.converter.xhtml.XHTMLConverter;
import fr.opensagres.odfdom.converter.xhtml.XHTMLOptions;

/**
 * @author rajendarreddy.jagapathi
 *
 */
public class ConvertODTToXHTML {
    public static void main(final String[] args) {
        generateXHTML("src/main/resources/ODTFile.odt", "target/ODTFile.htm");
    }

    /**
     * 
     */
    private static void generateXHTML(final String odtFileName, final String pdfFilePath) {
        long startTime = System.currentTimeMillis();
        try {
            File inputFile = new File(odtFileName);
            // 1) Load odt with ODFDOM
            OdfTextDocument document = OdfTextDocument.loadDocument(new FileInputStream(inputFile));
            // 2) Convert ODFDOM OdfTextDocument 2 xhtml
            File outFile = new File(pdfFilePath);
            outFile.getParentFile().mkdirs();
            OutputStream out = new FileOutputStream(outFile);
            XHTMLOptions options = XHTMLOptions.create();
            XHTMLConverter.getInstance().convert(document, out, options);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Generate " + pdfFilePath + " with " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
