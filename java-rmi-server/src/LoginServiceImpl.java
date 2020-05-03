import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginServiceImpl implements LoginService {
    @Override
    public String loginAsAdmin() {
        URL url;
        HttpURLConnection conn = null;
        String result = "";
        
        try {
            url = new URL("http://localhost:3000/user/login");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Contenct-Type", "application/json");
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
                
                result = response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}