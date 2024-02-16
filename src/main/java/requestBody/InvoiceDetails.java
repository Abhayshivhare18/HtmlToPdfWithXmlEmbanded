package requestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetails {

    private String templateName;
    private String summaryTemplateName;
    private String companyAddress;
    private int companyId;
    private String companyName;
    private String phoneNumber;
    private String email;
    private String vatNo;
    private String customerName;
    private String customerNameArabic;
    private String region;
    private String customerId;
    private String customerContactNo;
    private String crNo;
    private String customerAddress;
    private String customerAddressArabic;
    private String customerVatNo;
    private String invoiceDate;
    private int creditDays;
    private String invoiceNo;
    private String dueDate;
    private String bankName;
    private String accountName;
    private String accountNumber;
    private String branchName;
    private List<InvoiceItem> invoiceList;
    private List<InvoiceSummaryItem> invoiceListSummary;
    private List<String> files;
    private String summaryTotalAmt;
    private String summaryTotalVatCh;
    private String summaryTotalTaxableAmt;
    private String payeeName;
    private String swiftCode;
    private String iBAN;
    private String currencyCode;
    private String standardOrdersTotalAmt;
    private String standardTotalQty;
    private String standardTotalVatCh;
    private String standardTotalAmt;
    private String standardTotalAmtInArabicWord;
    private String standardTotalAmtInEnglishWord;
    private String invoicePdfLink;
    private String status;
    private String externalShipperId;
    private Date generatedOn;
    private String annexureRefId;
    private int summaryVatPercentage;
    private boolean annexureRequiredForInv;
    private boolean imageNotRequiredForInv;
    private int annexureType;
    private String pdfFileName;
    private String invoiceGenerationType;
    private String createBy;
    private String modifiedBy;
    private Date createdAt;
    private Date modifiedAt;
    private String companyLogo;
    private String companyLogoArabic;
    private int currencyDecimalPlace;
    private String submissionType;
    private String statusMessage;
    private int revisionNo;
    private String shipperCustomerReferenceRequired;
    private String bankAddress;
    private String bankTelNo;
    private String companyUrl;
    private String _class;
}
