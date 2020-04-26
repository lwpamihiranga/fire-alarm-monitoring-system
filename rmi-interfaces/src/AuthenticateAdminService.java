import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author amith
 */
public interface AuthenticateAdminService extends Remote {
    void authenticateUser() throws RemoteException;
}
