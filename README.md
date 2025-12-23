# PDF Generator

A Java REST API for generating PDF documents using **iText 7**.
Supports Japanese text, HTML content, and Unicode emojis (monochrome).

---

### 📦 Features

- Generate PDF from HTML content
- Supports Japanese text using **Noto Sans JP**
- Supports monochrome Unicode emojis using **NotoSansSymbols2**
- Configurable page size and orientation (Portrait / Landscape)
- Spring Boot service layer for easy REST integration

---

### 🖋 Technology Stack

- Java 21
- Spring Boot 3
- iText 7
- Noto Sans JP, NotoSansSymbols2 (fonts)
- Maven build support
- Visual Studio Code (VS Code) for development

---

### 🛠 Development Environment

This project is developed and verified using **Visual Studio Code** with the following setup:

- VS Code
- Extension: *Language Support for Java™ by Red Hat*
- Maven-based build and dependency management
- Java 21 (OpenJDK)

---

### ⚙️ Font Usage & License

This project bundles fonts downloaded from Google:

| Font | License | Notes |
|------|--------|------|
| Noto Sans JP | SIL Open Font License 1.1 | Japanese text |
| NotoSansSymbols2 | SIL Open Font License 1.1 | Monochrome emojis |

**Font usage note:**
- English or common Latin text can use standard PDF fonts (Helvetica, Times-Roman, etc.) automatically.
- Japanese text or Unicode emojis require the bundled NotoSans fonts.

---

### 🔌 API Specification

#### EndPoint

`POST /api/v1/pdf-generator`

This endpoint generates a PDF document from the provided HTML content and returns the PDF binary.

#### Request Body (JSON)

| Field          | Type   | Required | Description |
|----------------|--------|----------|-------------|
| `html`         | string | Yes      | Fully rendered HTML content. Dynamic processing such as data binding, loops, or templating is **not** supported. |
| `page`         | string | No       | Page size override. Supported values: `A3`, `A4`, `A5`, `LETTER`. Normally controlled via CSS `@page`, but provided as a fallback when the HTML cannot be modified. |
| `orientation`  | string | No       | Page orientation. Supported values: `PORTRAIT`, `LANDSCAPE`. Normally controlled via CSS `@page`, but provided as a fallback option. This parameter is applied **only when `page` is explicitly specified**; if `page` is omitted, the orientation value is ignored. |

#### Response

* Content-Type: application/pdf
* Body: PDF binary stream
* Headers:
    * Content-Disposition: inline; filename="generated.pdf"
    * X-License: AGPL (iText7)

---

### 🚀 Quick Start

#### Build

```bash
# Using Maven
mvn clean package
```
#### Run
```bash
# Run Spring Boot application
mvn spring-boot:run
```

#### Example Request
```bash
curl -X POST http://localhost:8080/api/v1/pdf-generator \
     -H "Content-Type: application/json" \
     -d '{
           "html": "<p>Hello World!</p>",
           "size": "A4",
           "orientation": "PORTRAIT"
         }' --output output.pdf
```

---

### 📝 Notes
* Only monochrome emojis are supported. Color emojis (Noto Color Emoji) are not supported by iText7.
* FontProvider is configured in FontProviderFactory.java. All fonts must be registered with IDENTITY_H encoding for Unicode support.
* Ensure your HTML specifies UTF-8:
    ```html
    <meta charset="UTF-8">
    ```
* CSS font-family example:
    ```css
    body {
    font-family:
        'Noto Sans JP',
        'NotoSansSymbols2',
        sans-serif;
    }
    ```

---

#### 📜 License

**GNU Affero General Public License v3.0 (AGPL-3.0)**

> This project is licensed under the <strong>GNU Affero General Public License v3.0 (AGPL-3.0)</strong>.
> This licensing choice is due to the use of <strong>iText 7</strong>, which is distributed under the AGPL license.
> See the [LICENSE](https://www.gnu.org/licenses/agpl-3.0.html) file for details.

**Bundled Fonts (SIL Open Font License 1.1)**

> This project bundles the following fonts downloaded from Google:
>
> - **Noto Sans JP**
> - **NotSansSymbols2**
>
> Copyright © Google.
> Licensed under SIL Open Font License 1.1.
> See the [LICENSE](https://openfontlicense.org/) file for details.
</small>
