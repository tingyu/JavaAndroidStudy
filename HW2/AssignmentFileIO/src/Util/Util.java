package Util;

import java.io.*;
import java.util.StringTokenizer;

import Model.Student;
import Exception.MoreStudentsException;

public class Util {
	public static int actualLength;

	public static Student[] readFile(String filename, Student[] stu)
			throws MoreStudentsException {
		// Reads the file and builds student array.
		try {
			File f = new File(filename);
			// Open the file using FileReader Object.
			// Get data from this file using a file reader.
			FileReader fr = new FileReader(f);
			// To store the contents read via File Reader
			BufferedReader br = new BufferedReader(fr);

			boolean eof = false;
			int i = 0;
			int temp[] = new int[5];
			int j = 0;

			// skip the header of the file
			br.readLine();

			//
			while (!eof) {
				String line = br.readLine();
				if (line == null)
					eof = true;
				else {
					// Tokenize each line using StringTokenizer Object
					StringTokenizer st = new StringTokenizer(line);
					while (st.hasMoreTokens()) {
						if (i > 39) {
							// if input file has more than 40 records throw an
							// exception
							throw new MoreStudentsException("more than 40");
						}
						stu[i].setSID(Integer.parseInt(st.nextToken()));
						for (j = 0; j < 5; j++) {
							temp[j] = Integer.parseInt(st.nextToken());
						}

					}

					// set the score data for a student
					stu[i].setScores(temp);
					++i;
				}
			}
			br.close();// close buffer
			actualLength = i; // get actualLength if less than 40 records
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Caught IOException:" + e.getMessage());
		} 

		return stu;
	}
}
