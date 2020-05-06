import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class SensorDataServiceImpl implements SensorDataService {
    @Override
    public String getSensorData() {
       URL url;
       HttpURLConnection conn = null;
       String response = "";
       
       try {
            url = new URL("http://localhost:3000/sensor");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
           
            if(conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }
           
           try (Scanner myScanner = new Scanner(url.openStream())) {
               while(myScanner.hasNextLine()) {
                   response += myScanner.nextLine();
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return response;
    }

    @Override
    public Boolean addSensor(int roomNo, int floor, String token) {
        Boolean isSuccess = false;
        String jsonToken = token; // TODO: json token should be applied here
        URL url;
        HttpURLConnection conn = null;
        
        try {
            url = new URL("http://localhost:3000/sensor");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", jsonToken);
            conn.setDoOutput(true);
            
            JSONObject locationObj = new JSONObject();
            locationObj.put("roomNo", roomNo);
            locationObj.put("floor", floor);
            
            JSONObject reqBody =  new JSONObject();
            
            reqBody.put("co2Level", 0);
            reqBody.put("smokeLevel", 0);
            reqBody.put("location", locationObj);
            reqBody.put("isActive", true);
            
            String jsonInputString = reqBody.toString();
                
            try(OutputStream os = conn.getOutputStream()) {
                byte[]   input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            if(conn.getResponseCode() == 201) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isSuccess;
    }
}
