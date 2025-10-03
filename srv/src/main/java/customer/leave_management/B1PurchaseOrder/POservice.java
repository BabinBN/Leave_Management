package customer.leave_management.B1PurchaseOrder;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import customer.leave_management.leaveRequest.leaveReqService;

@Service
public class POservice {

    @Autowired
    public leaveReqService reqService;
    public JsonNode AddPO(POentity eOentity) {
        try {
            URL url = new URL("https://htpc19835d03.cloudiax.com:50000/b1s/v2/PurchaseOrders");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cookie", "B1SESSION=" + reqService.sessionId);
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(eOentity);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("utf-8"));
            }

        
            InputStream inputStream = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();
            JsonNode responseJson = mapper.readTree(inputStream);
            return responseJson;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
