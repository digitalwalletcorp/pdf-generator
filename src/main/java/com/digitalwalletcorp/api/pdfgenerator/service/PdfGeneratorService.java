package com.digitalwalletcorp.api.pdfgenerator.service;

import com.digitalwalletcorp.api.pdfgenerator.domain.Orientation;
import com.digitalwalletcorp.api.pdfgenerator.dto.PdfGeneratorRequest;
import com.digitalwalletcorp.api.pdfgenerator.infrastructure.pdf.FontProviderFactory;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.font.FontProvider;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfGeneratorService {

    private final FontProvider fontProvider;

    public PdfGeneratorService() {
        this.fontProvider = FontProviderFactory.create();
    }

    public byte[] generate(PdfGeneratorRequest request) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDocument = new PdfDocument(writer);
            pdfDocument.setTagged();

            PageSize pageSize = null;
            if (request.getSize() != null) {
                pageSize = request.getSize().toITextPageSize();
                if (Orientation.LANDSCAPE.equals((request.getOrientation()))) {
                    pageSize = pageSize.rotate();
                }
                pdfDocument.setDefaultPageSize(pageSize);
            }

            ConverterProperties props = new ConverterProperties();
            props.setFontProvider(this.fontProvider);
            props.setCharset("UTF-8");
            Document document = HtmlConverter.convertToDocument(
                request.getHtml(),
                pdfDocument,
                props
            );
            document.setMargins(0, 0, 0, 0);
            document.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to generate PDF", e);
        }
    }
}
