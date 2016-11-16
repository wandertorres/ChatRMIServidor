import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
	protected ChatServerImpl() throws RemoteException {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	private static Map<String, ChatClient> clientesRegistrados = new Hashtable<String, ChatClient>();
	
	private void evento(String nickname, String msg) throws RemoteException {
		for (Entry<String, ChatClient> hash : clientesRegistrados.entrySet())
			if(!hash.getKey().equals(nickname))
				hash.getValue().evento(nickname+msg);
	}
	
	@Override
	public void conectar(String nickname, ChatClient cliente) throws RemoteException {
		clientesRegistrados.put(nickname, cliente);
		evento(nickname, " entrou na sala...");
	}

	@Override
	public void desconectar(String nickname, ChatClient cliente) throws RemoteException {
		clientesRegistrados.remove(nickname, cliente);
		evento(nickname, " saiu da sala...");
	}

	@Override
	public void falar(String name, String msg) throws RemoteException {
		for (Entry<String, ChatClient> hash : clientesRegistrados.entrySet())
			if(!hash.getKey().equals(name))
				hash.getValue().disse(name, msg);
	}
	
	@Override
	public void falarPara(String name, String para, String msg) throws RemoteException {
		for (Entry<String, ChatClient> hash : clientesRegistrados.entrySet())
			if(hash.getKey().equals(para))
				hash.getValue().disse(name, msg);
	}

	@Override
	public String[] naSessao() throws RemoteException {
		String usuarios[] = new String[clientesRegistrados.size()];
		int i = 0;
		for(Entry<String, ChatClient> hash : clientesRegistrados.entrySet())
			usuarios[i++] = hash.getKey();
		return usuarios;
	}
}