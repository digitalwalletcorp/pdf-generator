package com.digitalwalletcorp.api.pdfgenerator.infrastructure.pdf;

import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.PdfEncodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public final class FontProviderFactory {

    private FontProviderFactory() {
    }

    public static DefaultFontProvider create() {
        // ・標準PDFフォント:使う
        // ・システムフォント:使わない
        // ・フォールバックフォント:使わない
        DefaultFontProvider fontProvider =
                new DefaultFontProvider(true, false, false);

        addFont(fontProvider, "/fonts/NotoSansJP-Regular.ttf");
        addFont(fontProvider, "/fonts/NotoSansJP-Medium.ttf");
        addFont(fontProvider, "/fonts/NotoSansJP-Bold.ttf");
        addFont(fontProvider, "/fonts/NotoSansSymbols-Regular.ttf");
        return fontProvider;
    }

    private static void addFont(
        DefaultFontProvider provider,
        String resourcePath
    ) {
        try (InputStream is = FontProviderFactory.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Font not found: " + resourcePath);
            }
            byte[] fontBytes = is.readAllBytes();
            provider.addFont(fontBytes, PdfEncodings.IDENTITY_H);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}