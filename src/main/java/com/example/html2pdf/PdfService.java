package com.example.html2pdf;

import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] convertHtmlToPdf(String html) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create an instance of ITextRenderer
        ITextRenderer renderer = new ITextRenderer();

        // Set the HTML content
        renderer.setDocumentFromString(html);

        // Render the PDF and output to the ByteArrayOutputStream
        renderer.layout();
        renderer.createPDF(outputStream);

        // Return the PDF as a byte array
        return outputStream.toByteArray();
    }
}
