package customer.leave_management.leaveRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class leaveReqService {

    public String sessionId;

    public JsonNode postB1Login(loginReq reqEntity) {
        try {
            URL url = new URL("https://htpc19835d03.cloudiax.com:50000/b1s/v2/Login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Serialize POJO to JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(reqEntity);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("utf-8"));
            }

            // Read response
            int statusCode = conn.getResponseCode();
            InputStream responseStream = (statusCode >= 200 && statusCode < 300) ? conn.getInputStream()
                    : conn.getErrorStream();

            JsonNode jsonResponse = mapper.readTree(responseStream);

            // Extract session ID
            String sessionId = jsonResponse.get("SessionId").asText();
            this.sessionId = sessionId; // Save for future calls
            return jsonResponse;

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
            URL url = new URL("https://htpc19835d03.cloudiax.com:50000/b1s/v2/ITFZILMF");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cookie", "B1SESSION=" + sessionId);
            conn.setDoOutput(true);

            // Convert object to JSON and send
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(lEntity);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("utf-8"));
            }

            // Read response
            InputStream inputStream = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();
            return mapper.readTree(inputStream);

            // InputStream inputStream = (conn.getResponseCode() < 400) ?
            // conn.getInputStream() : conn.getErrorStream();
            // leaveReqentity responseEntity = mapper.readValue(inputStream,
            // leaveReqentity.class);
            // return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   public JsonNode getB1UDO() {
    if (sessionId == null) {
        throw new IllegalStateException("Session not initialized. Call postB1Login first.");
    }

    ObjectMapper mapper = new ObjectMapper();

    try {
        String urlString = "https://htpc19835d03.cloudiax.com:50000/b1s/v2/ITFZILMF";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Cookie", "B1SESSION=" + sessionId);

        InputStream inputStream = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();

        // Return the full JSON response
        return mapper.readTree(inputStream);

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

public JsonNode getB1UDOById(String DocEntry) {
    if (sessionId == null) {
        throw new IllegalStateException("Session not initialized. Call postB1Login first.");
    }

    ObjectMapper mapper = new ObjectMapper();

    try {
       String urlString = "https://htpc19835d03.cloudiax.com:50000/b1s/v2/ITFZILMF('"+DocEntry+"')";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Cookie", "B1SESSION=" + sessionId);

        InputStream inputStream = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();

        // Return the full JSON response
        return mapper.readTree(inputStream);

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


}
