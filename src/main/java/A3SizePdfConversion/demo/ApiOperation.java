package A3SizePdfConversion.demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface ApiOperation {

    ResponseEntity<byte[]> createInvoice(HttpServletResponse response);
}
