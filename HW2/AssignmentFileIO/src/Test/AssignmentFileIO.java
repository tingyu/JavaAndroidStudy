package Test;

import java.util.Arrays;
import java.io.*;

import Model.Student;
import Util.Util;
import Analysis.Statistics;
import Exception.MoreStudentsException;

public class AssignmentFileIO {

	public static void main(String[] args) {

		Student lab2[] = new Student[40];

		// Populate the student array
		for (int i = 0; i < lab2.length; i++) {
			lab2[i] = new Student();
		}

		String filename = null;
		System.out
				.println("Please choose a testcase. Testcase1 contain less than 40 lines in a file, testcase2 contian 40 lines, testcase3 contain more than 40 lines. Please input 1 or 2 or 3:");
		String s = null;

		// Open up standard input to choose test case
		try {
			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			s = bufferRead.readLine();
			System.out.print("You chose test case" + s + ": ");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Use of wrapper class
		Integer testcase = Integer.valueOf(s);
		switch (testcase) {
		// Test Case1: There's less than 40 Students' data in the file
		case 1:
			filename = "testcase1.txt";
			System.out.println("Less than 40 lines");
			break;
		// Test Case2: There's 40 Students' data in the file
		case 2:
			filename = "testcase2.txt";
			System.out.println("40 lines");
			break;
		// Test case3: There's more than 40 students' data in a file
		case 3:
			filename = "testcase3.txt";
			System.out.println("More than 40 lines");
			break;
		}

		//read the chosen txt file, and add a custom exception handler to handle more than 40 records
		try {
			lab2 = Util.readFile(filename, lab2);
		} catch (MoreStudentsException e) {
			System.out
					.println("The txt file has more than 40 line data, we read the first 40 records for calculating");
			Util.actualLength = 40;
		}

		System.out.println("The number of output lines: " + Util.actualLength);

		Student[] lab2_actual = Arrays.copyOf(lab2, Util.actualLength);

		//new a object instance of Statistics class
		Statistics statlab2 = new Statistics();
		statlab2.findlow(lab2_actual);

		// add calls to findhigh and find average
		statlab2.findhigh(lab2_actual);
		statlab2.findavg(lab2_actual);

		// print the data and statistics
		System.out.println("Stud" + "\t" + "Q1" + "\t" + "Q2" + "\t" + "Q3"
				+ "\t" + "Q4" + "\t" + "Q5");
		for (int i = 0; i < lab2_actual.length; i++) {
			lab2_actual[i].printInstanceVariables();
		}

		// print the analyzed data
		statlab2.printInstanceVariables();
	}

}
