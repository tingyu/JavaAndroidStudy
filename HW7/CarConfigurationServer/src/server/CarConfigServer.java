package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

/**Server Class*/
public class CarConfigServer{
	private ServerSocket serverSocket;
	private static Socket clientSocket;
	private boolean listening = true;
	//private static DefaultSocketClient clientSocket;
	
	/**constructor, new a ServerSocket object for listenning*/
	public CarConfigServer(){
    	serverSocket = null;
    	try{
    		serverSocket = new ServerSocket(4448);
    	}catch(IOException e){
    		System.err.println("Could not listen on port: 4448.");
    		System.exit(1);
    	}
	}
	
	/**ProcessRequest and listening for the connection from the client, this function allows multi-threading*/
	public void processRequest(){
		clientSocket = null;
		while(listening){
			try{
				clientSocket = serverSocket.accept();
				CarconfigSocket carSocket = new  CarconfigSocket(clientSocket);
			//	DefaultSocketClient carSocket = new DefaultSocketClient(clientSocket);
				Thread t1 = new Thread(carSocket);
				t1.start();
				System.out.println("Server is up...");
				//clientSocket = (DefaultSocketClient) serverSocket.accept();
			}catch(IOException e){
				System.err.println("Accept failed");
				System.exit(1);
			}
		}
		
	}
	
    public static void main(String[] args) throws IOException {
    	
    	CarConfigServer server = new CarConfigServer();
    	server.processRequest();
    	
    }
}
