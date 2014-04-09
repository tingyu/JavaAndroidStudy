package server;

import java.net.Socket;
import java.util.Properties;

import model.Automobile;

/**AutoServer Interface, the functions defined in proxy*/
public interface AutoServer {
	public void parseProperties(Properties pros);
	
	public String GetAllModels();
	
	public void SendAutoObject(String modelName, Socket socket);
}
