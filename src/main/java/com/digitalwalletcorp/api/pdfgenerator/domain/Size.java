package com.digitalwalletcorp.api.pdfgenerator.domain;

import com.itextpdf.kernel.geom.PageSize;

public enum Size {

    A3(PageSize.A3),
    A4(PageSize.A4),
    A5(PageSize.A5),
    LETTER(PageSize.LETTER);

    private final PageSize pageSize;

    Size(PageSize pageSize) {
        this.pageSize = pageSize;
    }

    public PageSize toITextPageSize() {
        return pageSize;
    }
}
