package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.Automobile;
import adapter.BuildAuto;

/** SelectCarOption class contain functions that receive the Serialized Automobile object and    */
 public class SelectCarOption {
	public Automobile auto = new Automobile();
//	public ArrayList<ArrayList> opset = new ArrayList<ArrayList>();

	
	/** receive the Serialized Automobile object and populate a Automobile object*/
	public Automobile getAutoObject(Socket socket){
		auto = null;
		try {
			if (socket.isClosed()){
				System.out.println("Socket is closed");
				return null;
			}
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			System.out.println("getAutoObject: in" + in);
			auto = (Automobile) in.readObject();
			System.out.println("getAutoObject: auto" + auto);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Automobile class not found");
			e.printStackTrace();
		}
		return auto;
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
        		//opset.add(i, auto.getOptionSet(i));
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
