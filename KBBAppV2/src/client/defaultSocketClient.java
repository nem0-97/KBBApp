package client;
import java.net.*;
import java.util.*;
import java.io.*;
import model.*;

public class defaultSocketClient extends Thread{//doesn't need to extend thread but why not

	private ObjectInputStream objReader;
	private ObjectOutputStream objWriter;
	private Socket sock;
	private String strHost;
	private int iPort;

	public defaultSocketClient(String strHost, int iPort) {       
		setPort (iPort);
		setHost (strHost);
	}// constructor

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	public boolean openConnection() {
		try {
			sock = new Socket(strHost, iPort);
		} catch (IOException socketError) {
			System.err.println("Unable to connect to " + strHost);
			return false;
		}
		try {
			objWriter = new ObjectOutputStream(sock.getOutputStream());
			objReader = new ObjectInputStream(sock.getInputStream());
		} catch (Exception e) {
				System.err.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	public void handleSession() {
		try {
			Scanner scanner = new Scanner(System.in);
			ArrayList<String> models = (ArrayList<String>) objReader.readObject();
			System.out.println("Would you like to upload a vehicle(U) or coinfigure one(C):");
			String chos = scanner.nextLine();
			objWriter.writeObject(chos);
			objWriter.flush();

			if (chos.equalsIgnoreCase("U")) {
				System.out.println("Where is the car file you want to upload?:");
				String loc = scanner.nextLine();
				Properties prop = new Properties();
				FileInputStream get= new FileInputStream(loc);
				prop.load(get);
				objWriter.writeObject(prop);
				objWriter.flush();
				
				
				System.out.println("Vehicle models stored on the server: ");
				System.out.println((ArrayList<String>) objReader.readObject());

			} 
			else if (chos.equalsIgnoreCase("C")) {
				System.out.println("Vehicle models you can configure: ");
				System.out.println(models);

				System.out.println("Enter the model to configure:");
				String mode = scanner.nextLine();
				objWriter.writeObject(mode);
				objWriter.flush();
				
				Vehicle car = (Vehicle) objReader.readObject();
				if (car != null) {
					System.out.println("Choose your options");
					for(int i =0;i<car.numComponents();i++){
						String comp = car.printComponent(i);
						System.out.println("\n");
						String opt = scanner.nextLine();
						System.out.println("\n");
						car.setCompChose(comp, opt);
					}
					System.out.println("Your car is now this:\n");
					car.printInfo();
				}
				else{
					System.out.println("Server sent no car over, wrong model name?");
				}
			} 
			else {
				System.out.println("not an option");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendOutput(Object output) {
		try {
			objWriter.writeObject(output);
		} catch (IOException e) {
			System.out.println("Error writing to " + strHost);
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
		} catch (IOException e) {
				System.err.println("Error closing socket to " + strHost);
		}
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void setPort(int iPort) {
		this.iPort = iPort;
	}

	public static void main(String arg[]) {
		
		String strLocalHost = "";
		try {
			strLocalHost = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.err.println("Unable to find local host");
		}
		defaultSocketClient d = new defaultSocketClient(strLocalHost, 4444);
		d.start();
	}

}
