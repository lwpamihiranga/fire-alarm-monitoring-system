import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorDataService extends Remote {
    String getSensorData() throws RemoteException;
    
    Boolean addSensor(String roomNo, String floor, String token) throws RemoteException;
}