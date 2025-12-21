package com.digitalwalletcorp.api.pdfgenerator.controller;

import com.digitalwalletcorp.api.pdfgenerator.dto.PdfGeneratorRequest;
import com.digitalwalletcorp.api.pdfgenerator.service.PdfGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pdf-generator")
public class PdfGeneratorController {

    private final PdfGeneratorService service;

    public PdfGeneratorController(PdfGeneratorService service) {
        this.service = service;
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> generate(@RequestBody PdfGeneratorRequest request) {

        byte[] pdf = service.generate(request);

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename=\"generated.pdf\""
            )
            .body(pdf);
    }
}
