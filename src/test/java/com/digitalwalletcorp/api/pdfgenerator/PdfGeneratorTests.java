package com.digitalwalletcorp.api.pdfgenerator;

import com.digitalwalletcorp.api.pdfgenerator.domain.Orientation;
import com.digitalwalletcorp.api.pdfgenerator.domain.Size;
import com.digitalwalletcorp.api.pdfgenerator.dto.PdfGeneratorRequest;
import com.digitalwalletcorp.api.pdfgenerator.service.PdfGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PdfGeneratorTests {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Test
    void generateSimplePdf_shouldReturnNonEmptyBytes() {
        PdfGeneratorRequest request = new PdfGeneratorRequest();
        request.setHtml("<p>Hello World!</p>");
        request.setSize(Size.A4);
        request.setOrientation(Orientation.PORTRAIT);

        byte[] pdfBytes = pdfGeneratorService.generate(request);

        // PDF が生成されていることを確認
        assertThat(pdfBytes).isNotNull();
        assertThat(pdfBytes.length).isGreaterThan(0);
    }
}
