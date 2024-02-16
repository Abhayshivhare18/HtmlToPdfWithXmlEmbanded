package A3SizePdfConversion.demo;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import requestBody.InvoiceData;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


@RestController
public class MainController {

    private final TemplateEngine templateEngine;

    @Autowired
    ApiOperation apiOperation;


    public MainController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    @GetMapping("/PDFA3Formate")
    public void addDataWithTemplateAA(InvoiceData invoiceData, HttpServletResponse response) throws Exception {
        try {
            invoiceData.setInvoiceNumber("123455");
            invoiceData.setCustomerName("dhushudus");
            Context context = new Context();
            context.setVariable("invoiceData", invoiceData);
            String htmlContent = templateEngine.process("invoice.html", context);

            // Convert HTML to PDF using Flying Saucer
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(pdfOutputStream);

            // Get the XML file content
            ClassPathResource xmlResource = new ClassPathResource("note.xml");
            InputStream xmlInputStream = xmlResource.getInputStream();
            byte[] xmlBytes = xmlInputStream.readAllBytes();

            // Set the response content type to PDF
            // Attach XML to PDF using iText
            PdfReader reader = new PdfReader(pdfOutputStream.toByteArray());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, out);
            String description = "Invoice XML Data"; // Provide a meaningful description
            String fileDisplay = "note.xml";
            stamper.addFileAttachment(description, xmlBytes, fileDisplay, fileDisplay);
            stamper.close();
            reader.close();


             // Send the combined PDF as a single attachment
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice_with_attachment.pdf");
            response.getOutputStream().write(out.toByteArray());
            response.getOutputStream().flush();
        }catch (Exception e){
            throw new RuntimeException();
        }
          }

    @GetMapping("/create")
    public ResponseEntity<byte[]> createPdf(HttpServletResponse response){
        return apiOperation.createInvoice(response);

    }


    @GetMapping("/pdfA3")
    public void addDataWithTemplate(InvoiceData invoiceData, HttpServletResponse response) throws Exception {
        try {
            invoiceData.setInvoiceNumber("123455");
            invoiceData.setCustomerName("dhushudus");
            Context context = new Context();
            context.setVariable("invoiceData", invoiceData);
            String htmlContent = templateEngine.process("invoice.html", context);

            // Convert HTML to PDF using Flying Saucer
            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
          //  renderer.setDocumentTitle("Invoice");
            renderer.setPDFVersion(PdfWriter.VERSION_1_7);
            ITextFontResolver fontResolver = renderer.getFontResolver();
            renderer.getFontResolver().addFont("fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(pdfOutputStream);

            // Get the XML file content
            ClassPathResource xmlResource = new ClassPathResource("note.xml");
            InputStream xmlInputStream = xmlResource.getInputStream();
            byte[] xmlBytes = xmlInputStream.readAllBytes();

            // Set the response content type to PDF
            // Attach XML to PDF using iText
            PdfReader reader = new PdfReader(pdfOutputStream.toByteArray());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, out);
            String description = "Invoice XML Data"; // Provide a meaningful description
            String fileDisplay = "note.xml";
            stamper.addFileAttachment(description, xmlBytes, fileDisplay, fileDisplay);
            stamper.close();
            reader.close();


            // Send the combined PDF as a single attachment
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice_with_attachment.pdf");
            response.getOutputStream().write(out.toByteArray());
            response.getOutputStream().flush();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }




























//    @GetMapping("/generate")
//    public void generatePdf(HttpServletResponse response) throws IOException, DocumentException {
//        // Prepare data for the Thymeleaf template
//        Map<String, Object> data = new HashMap<>();
//        data.put("invoiceNumber", "123456");
//        data.put("customerName", "John Doe");
//
//        // Create Thymeleaf context and set variables
//        Context context = new Context();
//        context.setVariables(data);
//
//        // Process the Thymeleaf template to get HTML content
//        String htmlContent = templateEngine.process("invoice.html", context);
//
//        // Generate XML content
//        String xmlContent = "<invoice><number>123456</number><customer>John Doe</customer></invoice>";
//
//        String htmlWithXml = htmlContent + "<!--XML_START-->" + xmlContent + "<!--XML_END-->";
//
//        // Convert HTML to PDF using Flying Saucer and embed XML
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(htmlWithXml);
//        renderer.layout();
//        renderer.createPDF(out);
//
//        // Set the content type and headers for the response
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "inline; filename=invoice.pdf");
//
//        // Write the PDF content to the response output stream
//        response.getOutputStream().write(out.toByteArray());
//        response.getOutputStream().flush();
//    }

//    @GetMapping("/generatePdf")
//    public void generatePdf(HttpServletResponse response) throws IOException, TransformerException, DocumentException {
//        // Prepare data for the Thymeleaf template
//        Map<String, Object> data = new HashMap<>();
//        data.put("invoiceNumber", "123456");
//        data.put("customerName", "John Doe");
//
//        // Create Thymeleaf context and set variables
//        Context context = new Context();
//        context.setVariables(data);
//
//        // Process the Thymeleaf template to get HTML content
//        String htmlContent = templateEngine.process("invoice.html", context);
//
//        // Generate XML content
//        String xmlContent = "<invoice><number>123456</number><customer>John Doe</customer></invoice>";
//
//        // Transform XML content into HTML
//        String transformedHtml = transformXmlToHtml(xmlContent);
//
//        // Combine the transformed HTML with the existing HTML content
//        String htmlWithXml = htmlContent + "<!--XML_START-->" + transformedHtml + "<!--XML_END-->";
//
//        // Convert HTML to PDF using Flying Saucer and embed XML
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(htmlWithXml);
//        renderer.layout();
//        renderer.createPDF(out);
//
//        // Set the content type and headers for the response
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "inline; filename=invoice.pdf");
//
//        // Write the PDF content to the response output stream
//        response.getOutputStream().write(out.toByteArray());
//        response.getOutputStream().flush();
//    }
//
//    private String transformXmlToHtml(String xmlContent) throws TransformerException {
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//
//        StringWriter writer = new StringWriter();
//        transformer.transform(new StreamSource(new StringReader(xmlContent)), new StreamResult(writer));
//
//        return writer.toString();
//    }
//
























    //
//    public void generatePdf(String templateName, InvoiceData invoiceData, String pdfFilePath) throws Exception {
//            Context context = new Context();
//            context.setVariable("invoiceData", invoiceData);
//            String htmlContent = templateEngine.process(templateName, context);
//              String xmlContent = new String(Files.readAllBytes(Paths.get("C:\\Users\\Gopal Contractor\\Downloads\\note.xml")));
//            try (OutputStream os = new FileOutputStream(pdfFilePath)) {
//                ITextRenderer renderer = new ITextRenderer();
//                renderer.setDocumentFromString(htmlContent);
//                 // Ensure the document layout is prepared
//                ByteArrayOutputStream xmlData = new ByteArrayOutputStream();
//                xmlData.write(xmlContent.getBytes());
//                renderer.layout();
//                renderer.createPDF(os, true);
//                os.close();
//
//
//            } catch (DocumentException e) {
//                throw new Exception("Error generating PDF: " + e.getMessage(), e);
//            }
//
//    }


























    //    @GetMapping("/formateA3")
//    public void addDataWithTemplate(InvoiceData invoiceData){
//        String htmlFilePath = "path/to/your.html";
//        String pdfFilePath = "path/to/your.pdf";
//        String xmlData = "<your_xml_data_here></your_xml_data_here>";
//
//        try {
//            convertHtmlToPdfWithXmlEmbedding(htmlFilePath, pdfFilePath, xmlData);
//            System.out.println("PDF created successfully.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//
//        public static void convertHtmlToPdfWithXmlEmbedding(String htmlFilePath, String pdfFilePath, String xmlData) throws Exception {
//           try{
//            OutputStream outputStream = new FileOutputStream(pdfFilePath);
//            PdfWriter writer = new PdfWriter(outputStream);
//            PdfADocument pdf = new PdfADocument(writer, PdfAConformanceLevel.PDF_A_3U, new PdfOutputIntent("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", new FileInputStream("path/to/sRGB_CS_profile.icm")));
//
//            // Convert HTML to PDF
//            ConverterProperties converterProperties = new ConverterProperties();
//            converterProperties.setBaseUri(htmlFilePath);
//            HtmlConverter.convertToPdf(new FileInputStream(htmlFilePath), pdf, converterProperties);
//
//            // Embed XML
//            Document xmlDocument = XmlHelper.parseToDom(xmlData);
//            PdfDictionary embeddedFileParams = new PdfDictionary();
//            PdfFileSpec fileSpec = PdfFileSpec.createEmbeddedFileSpec(pdf, xmlDocument, "data.xml", "XML data", null, null, false);
//            pdf.addFileAttachment("data.xml", fileSpec);
//
//            pdf.close();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    //    @GetMapping("/convertA3Formate")
//    public String addDataWithTemplate(InvoiceData invoiceData){
//
//        invoiceData.setInvoiceNumber("123455");
//        invoiceData.setCustomerName("dhushudus");
//        // Create Thymeleaf context and populate it with invoice data
//        Context context = new Context();
//        context.setVariable("invoiceData", invoiceData);
//
//        // Process the Thymeleaf template with the populated context
//        return  templateEngine.process("invoice.html", context);
//
//        // Convert HTML to PDF/A-3 format
//
//    }
//
//
}







