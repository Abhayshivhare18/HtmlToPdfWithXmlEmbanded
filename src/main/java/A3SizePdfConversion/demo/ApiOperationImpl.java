package A3SizePdfConversion.demo;

import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import requestBody.InvoiceDetails;
import requestBody.InvoiceItem;
import requestBody.InvoiceSummaryItem;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;



@Service
public class ApiOperationImpl implements ApiOperation {

    @Autowired
    ThymeleafService thymeleafService;
    @Override
    public ResponseEntity<byte[]> createInvoice(HttpServletResponse response) {
        try {
            //Create an instance of InvoiceDetails
            InvoiceDetails invoiceDetails = new InvoiceDetails();

            // Set data into the object
            invoiceDetails.setTemplateName("INSA");
            invoiceDetails.setSummaryTemplateName("sample3");
            invoiceDetails.setCompanyAddress("7335, PRINCE TURKI IBN ABDULAZIZ AL AWWAL ROAD,AL NAKHEEL DISTRICT, PO Box 12385, Riyadh KSA");
            invoiceDetails.setCompanyId(3);
            invoiceDetails.setCompanyName("TruKKer Arabia Limited");
            invoiceDetails.setPhoneNumber("966 13 844 8889");
            invoiceDetails.setEmail("CONTACT@TRUKKER.COM");
            invoiceDetails.setVatNo("310327979400003");
            invoiceDetails.setCustomerName("Saudi Basic Industries Corporation (SABIC)");
            invoiceDetails.setCustomerNameArabic("الشركه السعوديه للصناعات الاساسيه");
            invoiceDetails.setRegion("KSA");
            invoiceDetails.setCustomerId("112258804");
            invoiceDetails.setCustomerContactNo("112258804");
            invoiceDetails.setCrNo("1010455561");
            invoiceDetails.setCustomerAddress("Saudi Basic Industries Corporation (SABIC),PO Box 5101,,Riyadh,11422,United Arab Emirates");
            invoiceDetails.setCustomerAddressArabic("ص.ب 5101 الرياض 11422 المملكة العربية السعودية");
            invoiceDetails.setCustomerVatNo("300000316410003");
            invoiceDetails.setInvoiceDate("09 Feb 2024");
            invoiceDetails.setCreditDays(30);
            invoiceDetails.setInvoiceNo("INSA202402090016782");
            invoiceDetails.setDueDate("10 Mar 2024");
            invoiceDetails.setBankName("Riyad Bank");
            invoiceDetails.setAccountName("TruKKer Arabia LLC");
            invoiceDetails.setAccountNumber("3511237149940");
            invoiceDetails.setBranchName(" ");
            invoiceDetails.setInvoiceList(Arrays.asList(
                    new InvoiceItem("15 Sep 2022", "Domestic", "Al jubail", "Riyadh", "الجبيل", "الرياض",
                            "1", "SAR", "1275.00", "Freight Charges:أجور الشحن", "15%", "191.25",
                            "1466.25", "P2223366788", "805229351", "2411 LDA", "Test", Arrays.asList(""), "7942670 4237920")
            ));
            invoiceDetails.setInvoiceListSummary(Arrays.asList(
                    new InvoiceSummaryItem("Riyadh>Al jubail", "1275.00", "805229351", "1", 1275, "7942670 4237920")
            ));
            invoiceDetails.setFiles(Arrays.asList());
            invoiceDetails.setSummaryTotalAmt("1275.00");
            invoiceDetails.setSummaryTotalVatCh("0.00");
            invoiceDetails.setSummaryTotalTaxableAmt("1275.00");
            invoiceDetails.setPayeeName("TruKKer Arabia LLC");
            invoiceDetails.setSwiftCode("RIBLSARI");
            invoiceDetails.setIBAN("SA7620000003511237149940");
            invoiceDetails.setCurrencyCode("SAR");
            invoiceDetails.setStandardOrdersTotalAmt("1275.00");
            invoiceDetails.setStandardTotalQty("1");
            invoiceDetails.setStandardTotalVatCh("191.25");
            invoiceDetails.setStandardTotalAmt("1466.25");
            invoiceDetails.setStandardTotalAmtInArabicWord("واحد ألف  أربعة مائة ستون ستة بالدينار و ثلاثون  ھللة فق  ");
            invoiceDetails.setStandardTotalAmtInEnglishWord("One Thousand  Four Hundred Sixty Six riyals  and Thirty Halala Only");
            invoiceDetails.setInvoicePdfLink("https://s3-us-west-2-1a-d1-invoice.s3.us-west-2.amazonaws.com/invoiceTemplates/Invoicedetail_INSA202402090016782.pdf?AWSAccessKeyId=AKIAWR4US2KLT7DXHN7F&Expires=1737717582&Signature=L8J1qFuZuXyRkWFMvvz9CiReboY%3D");
            invoiceDetails.setStatus("Invoice Generated");
            invoiceDetails.setExternalShipperId("SH2223034400");
            invoiceDetails.setGeneratedOn(new Date());
            invoiceDetails.setAnnexureRefId("SH222303440004745");
            invoiceDetails.setSummaryVatPercentage(0);
            invoiceDetails.setAnnexureRequiredForInv(true);
            invoiceDetails.setImageNotRequiredForInv(true);
            invoiceDetails.setAnnexureType(3);
            invoiceDetails.setPdfFileName("Invoicedetail_INSA202402090016782");
            invoiceDetails.setInvoiceGenerationType("Single");
            invoiceDetails.setCreateBy("beepan.sahani@trukker.com");
            invoiceDetails.setModifiedBy("");
            invoiceDetails.setCreatedAt(new Date());
            invoiceDetails.setModifiedAt(new Date());
            invoiceDetails.setCompanyLogo("https://trukker.com/assets/img/logo-black.png");
            invoiceDetails.setCompanyLogoArabic("https://trukker.com/assets/img/logo-black-ar.png");
            invoiceDetails.setCurrencyDecimalPlace(2);
            invoiceDetails.setSubmissionType("Hard Copy");
            invoiceDetails.setStatusMessage("Congratulation your invoice Generated with invoice number INSA202402090016782");
            invoiceDetails.setRevisionNo(0);
            invoiceDetails.setShipperCustomerReferenceRequired("Yes");
            invoiceDetails.setBankAddress(" ");
            invoiceDetails.setBankTelNo("966 13 844 8889");
            invoiceDetails.setCompanyUrl("WWW.TRUKKER.COM");
            invoiceDetails.set_class("com.trukker.invoice.v1.model.InvoiceGenerationDetails");
            HashMap<String, Object> map = new HashMap<>();


            map.put("invoiceDetails", invoiceDetails);
            OutputStream outputStream = null;
            String content ="";
            try {
                content = thymeleafService.createContent("invoiceBig.html", map);

//                String filePath = "C:\\Users\\Maharudra Davare\\Desktop\\Learning\\demo1.pdf";
//                outputStream = new FileOutputStream(filePath);
//
//                ITextRenderer renderer = new ITextRenderer();
//                renderer.setDocumentFromString(content);
//                renderer.layout();
//                renderer.createPDF(outputStream);

            } finally {
//                outputStream.close();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "preview_invoice.pdf");
            // Return the PDF as a byte array
//        byte[] pdfBytes = convertToByteStream(content);
             convertToByteStream(content,response,invoiceDetails);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(null);
        } catch (Exception e) {
           // log.error(e.getMessage(), e);
        }
        return null;
    }


    public static void convertToByteStream(String htmlInvoice,HttpServletResponse response,InvoiceDetails invoiceDetails) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlInvoice, buffer);

         // Create XML content dynamically
        // Create XML content dynamically
        String xmlContent = "<invoice>" +
                "<invoiceNumber>" + invoiceDetails.getInvoiceNo() + "</invoiceNumber>" +
                "<customerName>" + invoiceDetails.getCustomerName() + "</customerName>" +
                "</invoice>";

        byte[] xmlBytes = xmlContent.getBytes();

        // Attach XML to PDF using iText
        PdfReader reader = new PdfReader(buffer.toByteArray());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, out);
        String description = "Invoice XML Data"; // Provide a meaningful description
        String fileDisplay = invoiceDetails.getInvoiceNo() + "_invoice.xml";
        stamper.addFileAttachment(description, xmlBytes, null, fileDisplay);
        stamper.close();
        reader.close();

        // Set response content type
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=invoice_with_attachment.pdf");
        response.getOutputStream().write(out.toByteArray());
        response.getOutputStream().flush();


    }
}
