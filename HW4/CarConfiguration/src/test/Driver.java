package test;

//import exception.MyException;
//import exception.MyException.MissPrice;
import java.util.LinkedHashMap;

import model.Automobile;
import scale.EditOptions;
import adapter.BuildAuto;
import adapter.EditThread;


public class Driver {

	public static void main(String[] args) {
		
		//Create a BuildAuto Object and read txt file to populate the object and initialize static LinkedHashMap of Automobile
		BuildAuto auto = new BuildAuto();
		auto.buildAuto("FordZTW.txt");
		auto.buildAuto("jetta.txt");
		
		//Get the Interface instance of the static LinkedHashMap of Automobile
		EditThread ethread = new BuildAuto(); 
		
		//Create two threads by pass EditThread Interface into EditOptions
		//Set the two threads' option is to modify the Option Price
		EditOptions edit1 = new EditOptions("Thread 1", 0, ethread);
		EditOptions edit2 = new EditOptions("Thread 2", 0, ethread);
		
		// Pass some Parameters to make two threads modify the same data
		edit1.EditOptionPrice("Focus Wagon ZTW", "Brakes/Traction Control", "ABS", 800);
		edit2.EditOptionPrice("Focus Wagon ZTW", "Brakes/Traction Control", "ABS", 600);
		
		// Start to run the two threads, within witch runs a loop and has counter to verify the correctness of synchronized block 
		edit1.start();
		edit2.start();
		
		
		//Create Another two threads by pass EditThread Interface into EditOptions
		//Set the two threads' option is to modify the OptionSet Name
		EditOptions edit3 = new EditOptions("Thread 3", 1, ethread);
		EditOptions edit4 = new EditOptions("Thread 4", 1, ethread);
		
		// Pass some Parameters to make two threads modify the same data
		edit3.EditOptionSetName("Focus Wagon ZTW", "Color", "Car Color");
		edit4.EditOptionSetName("Focus Wagon ZTW", "Color", "Focus Car Color");
		
		// Start to run the two threads, within witch runs a loop and has counter to verify the correctness of synchronized block 
		edit3.start();
		edit4.start();
		
	}

}
