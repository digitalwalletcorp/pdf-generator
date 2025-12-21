package com.digitalwalletcorp.api.pdfgenerator.dto;

import com.digitalwalletcorp.api.pdfgenerator.domain.Orientation;
import com.digitalwalletcorp.api.pdfgenerator.domain.Size;

public class PdfGeneratorRequest {

    private String html;
    private Size size;
    private Orientation orientation;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
