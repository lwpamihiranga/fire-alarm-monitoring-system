import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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
    public Boolean addSensor() {
        Boolean isSuccess = false;
        String jsonToken = ""; // TODO: json token should be applied here
        URL url;
        HttpURLConnection conn = null;
        
        try {
            url = new URL("http://localhost:3000/sensor");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Contenct-Type", "application/json");
            conn.setRequestProperty("Authorization", jsonToken);
            conn.setDoInput(true);
            
            String jsonInputString = ""; // TODO: build request json body here
            
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;

                while((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                System.out.println(response.toString());
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
