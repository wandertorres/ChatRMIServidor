import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			ChatServer servidor = new ChatServerImpl();
			Naming.rebind("//localhost/chat", servidor);
			System.out.println("Servidor pronto...");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
