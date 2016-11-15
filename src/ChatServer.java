import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
	public void conectar(String nickname, ChatClient cliente)
		throws RemoteException;
	
	public void desconectar(String name, ChatClient cliente)
		throws RemoteException;
	
	public void falar(String name, String msg)
		throws RemoteException;
	
	public void falarPara(String name, String para, String msg)
			throws RemoteException;
	
	public String[] naSessao()
		throws RemoteException;
}
