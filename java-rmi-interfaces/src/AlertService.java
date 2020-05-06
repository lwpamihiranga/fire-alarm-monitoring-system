import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AlertService extends Remote {
    void sendEmailAlert(String sensorId) throws RemoteException;
    
    void sendSMSAlert(String sensorId) throws RemoteException;
}
