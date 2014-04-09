package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

public class CarModelOptionsIO {
	private Socket socket = null;

	public void createSocket() {
		try {
			// socket = new Socket("153.18.26.137", 4448);
			socket = new Socket("localhost", 4448);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: taranis.");
			System.exit(1);
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to: taranis.");
			System.exit(1);
		}

	}

	public void uploadFile(String filename, Socket socket) {
		Properties props = new Properties();
		FileInputStream in = null;

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
		} // This loads the entire file in memory

		try {
			ObjectOutputStream outObj = new ObjectOutputStream(
					socket.getOutputStream());
			outObj.writeObject(props);
			outObj.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// out.close();
	}

	public boolean receiveResponse(String s) {
		if(s.equals("Receive the file and Create Automobile object Successfully."))
			return true;
		return false;
	}
}
