package client;

import java.net.*;
import java.io.*;

/**DefaultSocketClient extends the Thread to providing multi-threading functions for sockets communication*/
public class DefaultSocketClient extends Thread implements
		SocketClientInterface, SocketClientConstants {
	protected static final boolean DEBUG = true;
	protected BufferedReader reader;
	private BufferedWriter writer;
	private Socket sock;
	private String strHost;
	private int iPort;
	protected PrintWriter out;
	protected BufferedReader in;

	/**Default constructor*/
	public DefaultSocketClient(){
	}
	
	/**constructor*/
	public DefaultSocketClient(String strHost, int iPort) {
		setPort(iPort);
		setHost(strHost);
	}// constructor

	/**run method for multi-threading*/
	public void run() {
		if (openConnection()) {
			sendOutput("hahha");
			handleSession();
			closeSession();
		}
	}

	/**new a socket and open Connection*/
	public boolean openConnection() {

		try {
			setSock(new Socket(strHost, iPort));
		} catch (IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect to " + strHost);
			return false;
		}
		try {
			out = new PrintWriter(getSock().getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(getSock().getInputStream()));
		} catch (Exception e) {
			if (DEBUG)
				System.err
						.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	/**handle session, which acts as protocol between the server and client*/
	public void handleSession() {
		String strInput = "";
		if (DEBUG)
			System.out
					.println("Handling session with " + strHost + ":" + iPort);
		try {
			while ((strInput = reader.readLine()) != null)
				handleInput(strInput);
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + strHost + ":"
						+ iPort);
		}
	}

	/**send the String to the other side of the socket, client or server*/
	public void sendOutput(String strOutput) {
		//	writer.write(strOutput, 0, strOutput.length());
		out.println(strOutput);
	}

	public void handleInput(String strInput) {
		System.out.println(strInput);
	}

	/**close session*/
	public void closeSession() {
		try {
			reader = null;
			writer = null;
			in.close();
			out.close();
			getSock().close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
	}

	/**Setter and Getters*/
	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}
	
	public String getHost() {
		return this.strHost;
	}

	public int getPort() {
		return this.iPort;
	}

	public Socket getSocket() {
		return getSock();
	}

	public void setSocket(Socket sock) {
		this.setSock(sock);
	}

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

}// class DefaultSocketClient
