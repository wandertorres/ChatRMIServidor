import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
	public void disse(String name, String msg)
		throws RemoteException;
	
	public void evento(String msg)
		throws RemoteException;
}
