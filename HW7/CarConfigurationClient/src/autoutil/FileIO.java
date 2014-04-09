package autoutil;

import java.io.*;
import java.util.StringTokenizer;

import model.Automobile;

import java.util.*;
import java.util.logging.Logger;

import exception.FixProblems;
import exception.MyException;
import exception.MyException.MissFileName;
import exception.MyException.MissMake;
import exception.MyException.MissModel;
import exception.MyException.MissOptionData;
import exception.MyException.MissOptionSetData;
//import java.util.LinkedHashMap;
import exception.MyException.MissPrice;

public class FileIO {

	//Log the exception
	private final static Logger LOGGER = Logger.getLogger(FileIO.class
			.getName());

	// read car data from file and populate the Automobile object
	public Automobile buildAutoObject(String filename) // throws MyException,
														// MissPrice
	{
		Automobile auto = null;
		BufferedReader buff = null;
		// LinkedHashMap<String, Automobile> autoHashMap = new
		// LinkedHashMap<String, Automobile>();
		try {
			try {
				File file = new File(filename);
				// Open the file using FileReader Object. Get data from this file using a file reader.
				FileReader fr = new FileReader(file);
				// To store the contents read via File Reader
				buff = new BufferedReader(fr);
			} catch (FileNotFoundException e) {
				try {
					throw new MyException.MissFileName("Miss FileName");
				} catch (MissFileName e1) {
					LOGGER.info("Missing Filename or wrong filename");
					FixProblems fx = new FixProblems(4, "Miss FileName");
					fx.selHealingMechanism();
					filename = fx.getInputData().get(0);

					File file = new File(filename);
					FileReader fr = new FileReader(file);
					buff = new BufferedReader(fr);
				}
			}
			boolean eof = false;
			int index = 0;// indicate line in the file
			int price = 0;

			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					// Tokenize each line using StringTokenizer Object
					StringTokenizer st = new StringTokenizer(line, ",");

					while (st.hasMoreTokens()) {
						String firsttoken = st.nextToken();

						// read the first line and findout the model information  to populate Automobile
						if (firsttoken.equals("Name")) {
							String key = st.nextToken();
							try {
								price = Integer.parseInt(st.nextToken());
							} catch (NoSuchElementException e) {
								// throws new FixProblem(e.getClass());
								try {
									throw new MyException.MissPrice(
											"Miss Price");
								} catch (MissPrice e1) {
									LOGGER.info("Missing price for Automobile in Text file");
									FixProblems fx = new FixProblems(1,
											"Miss Price");
									fx.selHealingMechanism();
									price = Integer.parseInt(fx.getInputData()
											.get(0));
								}
							}
							auto = new Automobile(key, price);
							// autoHashMap.put(key, auto);
						}

						if (firsttoken.equals("Model")) {
							try {
								auto.setModel(st.nextToken());
							} catch (NoSuchElementException e) {
								throw new MyException.MissModel("Miss Model");
							}
						}

						if (firsttoken.equals("Make")) {
							try {
									auto.setMake(st.nextToken());
							} catch (NoSuchElementException e) {
								throw new MyException.MissMake("Miss Make");
							}
						}

						if (firsttoken.equals("Color")
								| firsttoken.equals("Transmission")
								| firsttoken.equals("Brakes/Traction Control")
								| firsttoken.equals("Side Impact Air Bags")
								| firsttoken.equals("Power Moonroof")) {
							auto.addOptionSet(firsttoken);

							try {
								if (st.countTokens() <= 0) {
									throw new MyException.MissOptionData(
											"Miss Option Data");
								}
							} catch (MissOptionData e) {
								LOGGER.info("Miss Option data");
								FixProblems fx = new FixProblems(3,
										"Miss OptionSet");
								fx.selHealingMechanism();
								auto.addOption(auto.getOptionSet(index), fx
										.getInputData().get(0), Integer
										.parseInt(fx.getInputData().get(1)));
							}

							while (st.hasMoreTokens()) {
								auto.addOption(auto.getOptionSet(index),
										st.nextToken(),
										Integer.parseInt(st.nextToken()));
							}
							
							index++;
						}
					}
				}
			}
			if (index < 5) {
				throw new MyException.MissOptionSetData("Miss OptionSet Data");
			}
			buff.close();
		} catch (IOException e) {
			System.err.println("Caught IOException:" + e.getMessage());
		} catch (MissOptionSetData e) {
			LOGGER.info("Miss OptionSet data");
			FixProblems fx = new FixProblems(2, "Miss OptionSet");
			fx.selHealingMechanism();
			auto.addOptionSet(fx.getInputData().get(0));
		} catch (NumberFormatException e) {
			LOGGER.info("You input and empty string!");
		}catch (MissModel e1) {
			LOGGER.info("Missing Model for Automobile in Text file");
			FixProblems fx = new FixProblems(5,
					"Miss Model");
			fx.selHealingMechanism();
			auto.setModel(fx.getInputData().get(0));
		}catch (MissMake e1) {
			LOGGER.info("Missing Make for Automobile in Text file");
			FixProblems fx = new FixProblems(6,
					"Miss Make");
			fx.selHealingMechanism();
			auto.setModel(fx.getInputData().get(0));
		}

		return auto;
	}

	
	public Automobile parsePropertyFile(String filename){
		Properties props = new Properties();
		FileInputStream in = null;
		Automobile auto = new Automobile();
		
		try {
			in = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//This loads the entire file in memory
		
		
		String carModel = props.getProperty("CarMake");//This is how you read a property. It is like getting a value from HashTable
		if(!carModel.equals(null))
		{
			auto.setModel(carModel);
			auto.setModel(props.getProperty("CarModel"));
			auto.addOptionSet(props.getProperty("Option1"));
			auto.addOption(auto.getOptionSet(0), props.getProperty("OptionValue1a"), 0);
			//auto.setOptionSet(0, props.getProperty("Option1"));
			//auto.setOption(0, 0, props.getProperty("OptionValue1a"), 0);
			auto.addOptionSet(props.getProperty("Option2"));
			auto.addOption(auto.getOptionSet(0), props.getProperty("OptionValue2a"), 0);
		//	auto.setOptionSet(1, props.getProperty("Option2"));
		//	auto.setOption(1, 0, props.getProperty("OptionValue2a"), 0);
			//String optionValue1a = props.getProperty("OptionValue1a");
		}
		return auto;
	}

	// write a serialized file
	public void serializeAuto(Automobile auto) {
		try {
			FileOutputStream fileout = new FileOutputStream("serializeout.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(auto);
			out.close();
			fileout.close();
			System.out.println("Serialized data is saved in serializeout.ser");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// deserialize a serialized file
	public Automobile deserializeAuto() {
		Automobile auto = null;
		try {
			FileInputStream filein = new FileInputStream("serializeout.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			auto = (Automobile) in.readObject();
			in.close();
			filein.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Automobile class not found");
			e.printStackTrace();
		}

		System.out.println("Deserialize Sucessfully!");
		System.out.println();
		return auto;
	}
}
