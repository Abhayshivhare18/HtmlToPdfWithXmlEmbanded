package requestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItem {

    private String moveDate;
    private String moveType;
    private String originCity;
    private String destinationCity;
    private String originCityArb;
    private String destinationCityArb;
    private String qty;
    private String currencyCode;
    private String price;
    private String description;
    private String vatPercentage;
    private String vatCh;
    private String standardOrderTotalAmt;
    private String orderNumber;
    private String custRefNo;
    private String truckRegNo;
    private String truckType;
    private List<String> bayanDocs;
    private String projectId;

}
