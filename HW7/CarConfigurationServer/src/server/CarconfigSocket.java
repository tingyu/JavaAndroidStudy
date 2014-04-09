package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/** CarconfigSocket class extends the DefaultSocketClient, and override the handleSession function*/
public class CarconfigSocket extends DefaultSocketClient {

	private static final int WAITING = 0;
	private static final int RECEIVINGFILE = 1;
	private static final int GETTINGALL = 2;
	private static final int SELECTING = 3;
	private static final int DONE = 4;

	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;

	//initialize the state 
	private int state = WAITING;

	/**Constructor*/
	public CarconfigSocket(Socket socket) {

		super(socket);
	}

	/**run the server thread*/
	public void run() {

		if (openConnection()) {
			System.out.println("openConnection() done!");
			handleSession();
			closeSession();
		}
	}

	/**the override handleSession function, which fits for our situation*/
	public void handleSession() {

		String fromUser = null;
		
		//print debug info
		if (DEBUG)
			System.out
					.println("Handling session with " + ":" + super.getPort());
		handleInput(null);

		try {
			while (true) {
				//handle the the transmission of object to avoid print the binary 
				if (state == WAITING) {
					handleInput(null);
				} else {
					//these are the common situation 
					fromUser = in.readLine();
					if (fromUser == null) {
						break;
					}
					System.out.println("User: " + fromUser);
					//the key method acts as protocol between server and client
					handleInput(fromUser);
				}
				
				if (fromUser.equals("q")) {
					sendOutput("Server is down!");
					System.out.println("quit");
					break;
				}
			}
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + ":"
						+ super.getPort());
		}

	}

	/**the key method acts as protocol between server and client*/
	public void handleInput(String theInput) {

		BuildCarModelOptions car = new BuildCarModelOptions();

		//ask for client to upload a txt file
		if (state == WAITING) {
			sendOutput("Please upload a txt file or Properties file");
			readProperties(sock);
			sendOutput("Receive the file and Create Automobile object Successfully. Please choose upload another file(u) or select model(s)");

			state = RECEIVINGFILE;
		} else if (state == RECEIVINGFILE) {
			//process the input from user about "u" and "s"
			if (theInput.equalsIgnoreCase("u")) {
				System.out.println("RECEIVINGFILE:U");
				state = WAITING;
			}
			if (theInput.equalsIgnoreCase("s")) {
				sendOutput("Models are:" + car.GetAllModels() + " Please input one model name");
				//System.out.println(car.GetAllModels());
				System.out.println("RECEIVINGFILE:S");
				//System.out.println("SELECTING");
				//car.SendAutoObject(theInput, sock);
				state = SELECTING;
			} 
		} else if(state == SELECTING){
			car.SendAutoObject(theInput, sock);
			System.out.println("SELECTING");
		}
		else if (state == DONE) {
			// theOutput = "Bye";
			System.out.println("DONE");

			sendOutput("Bye");
		}

	}

	/**Read Serialized Properties object from the socket transmisitting from client*/
	public void readProperties(Socket clientSocket) {

		//read object from socket
		ObjectInputStream inObj = null;
		try {
			inObj = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		//populate the object to property object
		Properties pros = null;
		try {
			pros = (Properties) inObj.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//populate BuildAuto LHM from the property file
		BuildCarModelOptions car = new BuildCarModelOptions();
		car.parseProperties(pros);

		//print the current model in LHM for debugging
		System.out.println(car.GetAllModels());

	}

}
