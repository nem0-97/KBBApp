package server;
import java.net.*;
import java.io.*;

public class Server {
	private ServerSocket serve;
	
	public Server(){
		try{
			serve = new ServerSocket(4444);
		} 
		catch (IOException e){
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}
	}
	
	public Server(int port){
		try{
			serve = new ServerSocket(port);
		} 
		catch (IOException e){
			System.err.println("Could not listen on port:" + port);
			System.exit(1);
		}
	}
	
	public void run(){
		defaultClientSocket client = null;
		while(true){
			try{
				client = new defaultClientSocket(serve.accept());
				client.start();
			}
			catch(IOException e){
				System.err.println(e);
			}	
		}
		
	}

	public static void main(String[] args) {
		Server s = new Server();
		s.run();
	}
}
