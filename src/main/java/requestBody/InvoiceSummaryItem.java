package requestBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceSummaryItem {
    private String destination;
    private String summaryOrderTotalAmt;
    private String custRefNo;
    private String noOfTruck;
    private int ratePerTruck;
    private String shipmentNo;
}
