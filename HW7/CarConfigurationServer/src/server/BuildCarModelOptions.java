package server;

import java.net.Socket;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

/**BuildCarModelOptions class implements AutoServer, and encapsulate the functions in AutoServer*/
public class BuildCarModelOptions implements AutoServer{
	private AutoServer as;
	
	BuildCarModelOptions(){
		 as = new BuildAuto();
	}

	//@Override
	public void parseProperties(Properties pros) {
		// TODO Auto-generated method stub
		as.parseProperties(pros);
	}

	@Override
	public String GetAllModels() {
		return as.GetAllModels();
	}

	@Override
	public void SendAutoObject(String modelName, Socket socket) {
		// TODO Auto-generated method stub
		as.SendAutoObject(modelName, socket);
	}

}
