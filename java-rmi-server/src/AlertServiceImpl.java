
public class AlertServiceImpl implements AlertService {
    @Override
    public void sendEmailAlert(String sensorId) {
        System.out.println("Sending email alert on sensor " + sensorId);
    }
    
    @Override
    public void sendSMSAlert(String sensorId) {
        System.out.println("Sending SMS alert on sensor " + sensorId + "\n");
    }
}
