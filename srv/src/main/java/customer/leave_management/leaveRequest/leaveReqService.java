package customer.leave_management.leaveRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class leaveReqService {

    private String instanceUrl;
    public String sessionId;

    public String postB1Login(loginReq reqentity) {
        try {

            instanceUrl = "https://htpc19835d03.cloudiax.com:50000/b1s/v2/Login";

            URL url = new URL(instanceUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 3. Serialize Object to JSON
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(reqentity);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 4. Read Response
            int statusCode = conn.getResponseCode();
            InputStream responseStream = (statusCode >= 200 && statusCode < 300) ? conn.getInputStream()
                    : conn.getErrorStream();

            StringBuilder responseContent = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    responseContent.append(line.trim());
                }
            }

            String responseString = responseContent.toString();
           // System.out.println("Response: " + responseString);

            JsonNode jsonResponse = mapper.readTree(responseString);
            sessionId = jsonResponse.get("SessionId").asText();
            return sessionId;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JsonNode postB1UDO(leaveReqentity lEntity) {
        if (sessionId == null) {
            throw new IllegalStateException("Session not initialized. Call postB1Login first.");
        }

        try {
            String instanceUrl = "https://htpc19835d03.cloudiax.com:50000/b1s/v2/ITFZILMF";

            // Open connection
            URL url = new URL(instanceUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");

            // Add session cookie
            conn.setRequestProperty("Cookie", "B1SESSION=" + sessionId);
            conn.setDoOutput(true);

            // Convert object to JSON
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(lEntity);
            System.out.println(requestBody);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestBody.getBytes("utf-8"));
            }

            // Read response
            InputStream responseStream = (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            StringBuilder responseContent = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, "utf-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    responseContent.append(line.trim());
                }
            }

            String responseString = responseContent.toString();
           // System.out.println("UDO Response: " + responseString);

            // Convert response string to JSON and return
            return mapper.readTree(responseString);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
