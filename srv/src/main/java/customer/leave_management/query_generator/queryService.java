package customer.leave_management.query_generator;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import customer.leave_management.leaveRequest.leaveReqService;
import customer.leave_management.leaveRequest.loginReq;
@Service
public class queryService {
    
 @Autowired 
    // public queryEntity gentator;
    public leaveReqService reqService;
    public loginReq logreqService ;

    // public void queryloginservice()
    // {
    //  reqService.postB1Login(logreqService);
    // }

    public JsonNode PostQueryService(queryEntity gentator)
    {
        try {
            //queryloginservice();
            URL url = new URL("https://htpc19835d03.cloudiax.com:50000/b1s/v2/SQLQueries");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cookie", "B1SESSION=" + reqService.sessionId);
            conn.setDoOutput(true);

            // Convert object to JSON and send
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(gentator);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("utf-8"));
            }

            // Read response
            InputStream inputStream = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();
            JsonNode responseJson = mapper.readTree(inputStream);
            return responseJson;

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
  public JsonNode getqueryService(String id)
  {
    try {
            //queryloginservice();
            URL url = new URL("https://htpc19835d03.cloudiax.com:50000/b1s/v2/SQLQueries('"+id+"')/List");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Cookie", "B1SESSION=" + reqService.sessionId);
            conn.setDoOutput(true);

            // Convert object to JSON and send
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload ="{}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.getBytes("utf-8"));
            }

            // Read response
            InputStream inputStream = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();
            JsonNode responseJson = mapper.readTree(inputStream);
            return responseJson;

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


}
