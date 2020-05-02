import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginService extends Remote{
    void loginAsAdmin() throws RemoteException;
}
