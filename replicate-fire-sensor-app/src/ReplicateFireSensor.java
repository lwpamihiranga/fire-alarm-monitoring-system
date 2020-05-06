import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;


public class ReplicateFireSensor {
    public ReplicateFireSensor() {}
    
    private JSONObject getSensorData(String id) {
        JSONObject response = null;
        try {
            URL url = new URL("http://localhost:3000/sensor/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
              
            if(conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }
            
            String result = "";
            try (Scanner myScanner = new Scanner(url.openStream())) {
               while(myScanner.hasNextLine()) {
                   result += myScanner.nextLine();
               }
            }
            
            response = new JSONObject(result);
            response = response.getJSONObject("message");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return response;
    }
    
    private boolean updateSensor(JSONObject sensor) {
        boolean isSuccess = false;
        
        String sensorId = sensor.getString("_id");
        boolean isActive = sensor.getBoolean("isActive");
        JSONObject location = sensor.getJSONObject("location");
        int smokeLevel = generateRandomNumber();
        int co2Level = generateRandomNumber();
        
        System.out.println("SmokeLevel: " + smokeLevel);
        System.out.println("CO2Level: " + co2Level);
        try {
            URL url = new URL("http://localhost:3000/sensor/" + sensorId);
            
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            
            JSONObject reqBody =  new JSONObject();
            
            reqBody.put("co2Level", co2Level);
            reqBody.put("smokeLevel", smokeLevel);
            reqBody.put("location", location);
            reqBody.put("isActive", isActive);
            
            String jsonInputString = reqBody.toString(); 
            
            try(OutputStream os = con.getOutputStream()) {
                byte[]   input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            
            if(con.getResponseCode() == 200) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isSuccess;
    }
    
    private int generateRandomNumber() {
        Random random = new Random();
        int rand = 0;
        while (true){
            rand = random.nextInt(11);
            if(rand !=0) break;
        }
        
        return rand;
    }
    
    public static void main(String args[]) {
        ReplicateFireSensor app = new ReplicateFireSensor();
        Scanner myScanner = new Scanner(System.in);
        
        System.out.print("Input sensor id to replicate: ");
        String sensorID = myScanner.nextLine();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Updating sensor data every 10 seconds...");
                JSONObject obj = app.getSensorData(sensorID);
                boolean success = app.updateSensor(obj);
        
                if(success) {
                    System.out.println("Successfully updated the sensor\n");
                } else {
                    System.out.println("Failed to update the sensor\n");
                }
            }
        }, 10 * 1000, 10 * 1000);

    }
}
