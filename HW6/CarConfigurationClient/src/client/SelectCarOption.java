package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.Automobile;
import adapter.BuildAuto;

/** SelectCarOption class contain functions that receive the Serialized Automobile object and    */
 class SelectCarOption {
	Automobile auto = new Automobile();
	
	/** receive the Serialized Automobile object and populate a Automobile object*/
	public void getAutoObject(Socket socket){
		auto = null;
		try {
			if (socket.isClosed()) return;
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			auto = (Automobile) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Automobile class not found");
			e.printStackTrace();
		}
	}
	
	/**print model*/
	public void printModel(){
		if (auto != null)
			auto.print();
	}
	
	/**interactively configure the car on the client side*/
	public void configureCar(){
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        String opsetName = null;
        
        for(int i =0; i < 5; i++){
        	if (auto != null) {
        		opsetName = auto.getOptionSetName(i);
        		System.out.println("Please choose the " + opsetName + ":");
        	}
    		try {
    			input = stdIn.readLine();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		auto.setOptionChoice(opsetName, input);
        }
	}
	
	/**print the choice after configure*/
	public void printChoice(){
        String opsetName = null;
		System.out.println("Choices are:");
	      for(int i =0; i < 5; i++){
	        	opsetName = auto.getOptionSetName(i);
	        	System.out.println(opsetName +": " + auto.getOptionChoice(opsetName));
	      }
	      
		System.out.println("Prices are:");
		System.out.println(auto.getTotalPrice());
	}
	
}
