package test;

import autoutil.FileIO;
import model.Automotive;

public class Driver {

	public static void main(String[] args) {
		//Build Automotive object from a file
		FileIO fileio = new FileIO();
		Automotive FordZTW = fileio.buildAutoObject("FordZTW.txt");
		
		//Print attributes before serialization
		System.out.println("Print attributes before serialization");
		FordZTW.print();
		fileio.serializeAuto(FordZTW);
		
		//Deserialize the object and read it into memory
		Automotive newFordZTW = fileio.deserializeAuto();
		
		//Print new attributes
		System.out.println("Print attributes after serialization");
		newFordZTW.print();
		
		//Test find function
		System.out.println("Test case for find function");
		FordZTW.findOptionSet("run");
		FordZTW.findOptionSet("Transmission");
		FordZTW.findOption("Color","Infra-Red Clearcoat");
		System.out.println();
		
		//Test delete function
		System.out.println("Test case for deleting OptionSet function");
		FordZTW.deleteOptionSet(1);
		FordZTW.print();
		
		System.out.println("Test case for deleting Option function");
		FordZTW.deleteOption(FordZTW.findOptionSet("Color"), 1);
		FordZTW.print();
	}

}
