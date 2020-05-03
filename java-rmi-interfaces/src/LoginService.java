import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote{
    String loginAsAdmin(String username, String password) throws RemoteException;
}
