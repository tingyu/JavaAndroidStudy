package autoutil;

import java.io.*;
import java.util.StringTokenizer;

import model.Automotive;

public class FileIO {
	/*
	 * private int optionsetsize; 
	 * private int colornumber; 
	 * private int transnumber; 
	 * private int brakesnumber; 
	 * private int airbagsnumber; private
	 * int powernumber;
	 */
	// read car data from file and populate the Automotive object
	public Automotive buildAutoObject(String filename) {
		Automotive auto = null;
		try {
			File file = new File(filename);
			// Open the file using FileReader Object. Get data from this file
			// using a file reader.
			FileReader fr = new FileReader(file);
			// To store the contents read via File Reader
			BufferedReader buff = new BufferedReader(fr);

			boolean eof = false;
			int index = 0;// indicate line in the file

			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					// Tokenize each line using StringTokenizer Object
					StringTokenizer st = new StringTokenizer(line, ",");

					while (st.hasMoreTokens()) {
						String firsttoken = st.nextToken();

						// read the first line and findout the model information
						// to populate Automotive
						if (firsttoken.equals("name")) {
							int optionsetsize = Integer
									.parseInt(st.nextToken());
							auto = new Automotive(st.nextToken(),
									Integer.parseInt(st.nextToken()),
									optionsetsize);
						}/*
						 * else { int optnumber =
						 * Integer.parseInt(st.nextToken());
						 * auto.setOptionSet(auto.getOptionSet(index - 1),
						 * firsttoken, optnumber); for (int i = 0; i <
						 * optnumber; i++) {
						 * auto.setOption(auto.getOptionSet(index - 1),
						 * st.nextToken(), Integer.parseInt(st.nextToken()), i);
						 * } }
						 */

						// read follow-up lines and populate OptionSet and
						// Option

						if (firsttoken.equals("Color")) {
							int colornumber = Integer.parseInt(st.nextToken());
							auto.setOptionSet(auto.getOptionSet(index - 1),
									firsttoken, colornumber);
							for (int i = 0; i < colornumber; i++) {
								auto.setOption(auto.getOptionSet(index - 1),
										st.nextToken(),
										Integer.parseInt(st.nextToken()), i);
							}
						}

						if (firsttoken.equals("Transmission")) {
							int transnumber = Integer.parseInt(st.nextToken());
							auto.setOptionSet(auto.getOptionSet(index - 1),
									firsttoken, transnumber);
							for (int i = 0; i < transnumber; i++) {
								auto.setOption(auto.getOptionSet(index - 1),
										st.nextToken(),
										Integer.parseInt(st.nextToken()), i);
							}
						}

						if (firsttoken.equals("Brakes/Traction Control")) {
							int brakesnumber = Integer.parseInt(st.nextToken());
							auto.setOptionSet(auto.getOptionSet(index - 1),
									firsttoken, brakesnumber);
							for (int i = 0; i < brakesnumber; i++) {
								auto.setOption(auto.getOptionSet(index - 1),
										st.nextToken(),
										Integer.parseInt(st.nextToken()), i);
							}
						}

						if (firsttoken.equals("Side Impact Air Bags")) {
							int airbagsnumber = Integer
									.parseInt(st.nextToken());
							auto.setOptionSet(auto.getOptionSet(index - 1),
									firsttoken, airbagsnumber);
							for (int i = 0; i < airbagsnumber; i++) {
								auto.setOption(auto.getOptionSet(index - 1),
										st.nextToken(),
										Integer.parseInt(st.nextToken()), i);
							}
						}

						if (firsttoken.equals("Power Moonroof")) {
							int powernumber = Integer.parseInt(st.nextToken());
							auto.setOptionSet(auto.getOptionSet(index - 1),
									firsttoken, powernumber);
							for (int i = 0; i < powernumber; i++) {
								auto.setOption(auto.getOptionSet(index - 1),
										st.nextToken(),
										Integer.parseInt(st.nextToken()), i);
							}
						}

					}
				}
				index++;
			}
			buff.close();
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Caught IOException:" + e.getMessage());
		}

		return auto;
	}

	// write a serialized file
	public void serializeAuto(Automotive auto) {
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
	public Automotive deserializeAuto() {
		Automotive auto = null;
		try {
			FileInputStream filein = new FileInputStream("serializeout.ser");
			ObjectInputStream in = new ObjectInputStream(filein);
			auto = (Automotive) in.readObject();
			in.close();
			filein.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Automotive class not found");
			e.printStackTrace();
		}

		System.out.println("Deserialize Sucessfully!");
		System.out.println();
		return auto;
	}
}
