import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorDataService extends Remote {
    String getSensorData() throws RemoteException;
    
    Boolean addSensor(int roomNo, int floor, String token) throws RemoteException;
}