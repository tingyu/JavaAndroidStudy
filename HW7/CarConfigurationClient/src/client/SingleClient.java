package client;

public class SingleClient extends CarconfigSocket {
	
	private static SingleClient client;
	
	private SingleClient(String strHost, int iPort){
		super(strHost, iPort);
		
		client = null;
	}
	
	public static synchronized SingleClient getInstance(String strHost, int iPort){
		if(client ==null)
			client = new SingleClient(strHost, iPort);
		return client;
	}
}
