import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public Server () {}
    
    public static void main(String args[]) {
        try {
            LoginServiceImpl objOne = new LoginServiceImpl();
            SensorDataServiceImpl objTwo = new SensorDataServiceImpl();
            AlertServiceImpl objThree = new AlertServiceImpl();
            
            LoginService stubOne = (LoginService) UnicastRemoteObject.exportObject(objOne, 0);
            SensorDataService stubTwo = (SensorDataService) UnicastRemoteObject.exportObject(objTwo, 0);
            AlertService stubThree = (AlertService) UnicastRemoteObject.exportObject(objThree, 0);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            
            registry.bind("LoginService", stubOne);
            registry.bind("SensorDataService", stubTwo);
            registry.bind("AlertService", stubThree);
            
            System.err.println("Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}