package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class defaultClientSocket extends Thread{
	private ObjectInputStream objReader;
	private ObjectOutputStream objWriter;
	private Socket sock;
	private BuildVehicleModel use;
	
	public defaultClientSocket(Socket socket) {       
		this.sock = socket;
		use = new BuildVehicleModel();
	}// constructor

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	public boolean openConnection() {
//		try {
//			sock = new Socket(strHost, iPort);
//		} catch (IOException socketError) {
//				System.err.println("Unable to connect to " + strHost);
//			return false;
//		}
		try {
			objWriter = new ObjectOutputStream(sock.getOutputStream());
			objReader = new ObjectInputStream(sock.getInputStream());
		} catch (Exception e) {
				System.err.println("Unable to obtain stream to/from " + sock.getInetAddress().getHostName());
			return false;
		}
		return true;
	}

	public void handleSession() {
		try {
			objWriter.writeObject(use.getModels());
			objWriter.flush();
			
			String chos = (String) objReader.readObject();
			objWriter.flush();

			if (chos.equalsIgnoreCase("U")) {
				Properties prop  = (Properties) objReader.readObject();
				use.buildFromProp(prop);
				objWriter.writeObject(use.getModels());
				objWriter.flush();
			} 
			else if (chos.equalsIgnoreCase("C")) {
				objWriter.writeObject(use.getModel(((String) objReader.readObject())));
				objWriter.flush();

			} 
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendOutput(Object output) {
		try {
			objWriter.writeObject(output);
		} 
		catch (IOException e) {
			System.out.println("Error writing to " + sock.getInetAddress().getHostName());
		}
	}

	public void handleInput(Object input) {
		System.out.println(input);
	}

	public void closeSession() {
		try {
			objWriter = null;
			objReader = null;
			sock.close();
		} 
		catch (IOException e) {
				System.err.println("Error closing socket to " + sock.getInetAddress().getHostName());
		}
	}

}
