package com.example.html2pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PdfConverterController {

    private final PdfService pdfService;

    @Autowired
    public PdfConverterController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/convert")
    public ResponseEntity<byte[]> convertHtmlToPdf(@RequestParam("file") MultipartFile file) {
        try {
            // Read the HTML file as a string
            String html = new String(file.getBytes());

            byte[] pdfBytes = pdfService.convertHtmlToPdf(html);

            // Set the response headers to indicate a PDF file
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=converted.pdf");

            // Return the PDF in the response body
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error converting HTML to PDF".getBytes());
        }
    }
}