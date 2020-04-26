import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author amith
 */
public interface SensorDataService extends Remote {
    void getSensorDetails() throws RemoteException;
    
    void addNewSensor() throws RemoteException;
    
    void updateSensorDetails() throws RemoteException;
}
