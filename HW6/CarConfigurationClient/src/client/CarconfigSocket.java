package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Properties;

/** CarconfigSocket extends DefaultSocketClient class, and override the handleSession function*/
public class CarconfigSocket extends DefaultSocketClient {

	private static final int WAITING = 0;
	private static final int UPLOADING = 1;
	private static final int SELECTING = 2;
	private static final int CONFIGURING = 3;
	private static final int DONE = 3;
	private int state = WAITING;

	/**Constructor, input host and port*/
	public CarconfigSocket(String string, int i) {
		super(string, i);
	}

	/**run the thread in client*/
	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}

	/**Override the handleSession in DefaultSocketClient to fit our situation*/
	public void handleSession() {
		String strInput = "";
        String fromServer;
        String fromUser;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        //debug 
		if (DEBUG)
			System.out.println("Handling session with " + super.getHost() + ":"
					+ super.getPort());
	
		try {

			while (true) {
				//handle the the transmission of object to avoid print the binary 
				if (state == CONFIGURING) {
					handleInput(null);
				} else {
					//these are the common situation 
					fromServer = in.readLine();
					if (fromServer == null) {
						break;
					}
					System.out.println("Server: " + fromServer);
					if (fromServer.equals("Bye."))
						break;
					
					//User input in the Client side
					fromUser = stdIn.readLine();
					if (fromUser != null) {
						System.out.println("Client: " + fromUser);
						//Process the user input and state in the client
						handleInput(fromUser);
					}
				}
			}
					
		} catch (IOException e) {
			if (DEBUG)
				System.out.println("Handling session with " + super.getHost()
						+ ":" + super.getPort());
		}


	}
	
	/**Key function to acts as protocol for communication between the server and client*/
	public void handleInput(String theInput){
		CarModelOptionsIO carIO = new CarModelOptionsIO();
        SelectCarOption s = new SelectCarOption();

        //waiting for upload a file to the server
        if(state == WAITING){
			carIO.uploadFile(theInput, super.getSocket());
			state = UPLOADING;
        }
        if(state == UPLOADING){
        	//if user input "u" state convert to WAITING and upload another file
        	if(theInput.equalsIgnoreCase("u")){
        		sendOutput(theInput);
        		state = WAITING;
    		}
        	//if user input "s" state begin to select the model
        	if(theInput.equalsIgnoreCase("s")){
    			sendOutput(theInput);
    			state = SELECTING;
    			return;
    		}
        }
        if(state == SELECTING){
			sendOutput(theInput);
			state = CONFIGURING;
			return;
        }
        if(state == CONFIGURING){
        	//All the methods in the SelectCarOption acts as configure the car in the client side, not the server side
        	s.getAutoObject(getSock());
        	s.printModel();
        	System.out.println("Please choose the optionSet and Option to configure the car");
        	s.configureCar();
        	s.printChoice();
        	state = DONE;
        }
        if(state == DONE){
        	System.out.println("Exit the Client");
        	System.exit(0);
        }

	}
	
	public static void main(String arg[]) {
		CarconfigSocket client = new CarconfigSocket("localhost", 4448);
		client.start();
		System.out.println("Client is up...");
	}
	

}
