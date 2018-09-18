//Neoman Nouiouat
package util;
import java.io.*;
import java.util.*;
import model.*;
import exception.*;

public class FileIO {

	public Vehicle newCarFromFile(String file) throws VehicleException{
		try{
			FileReader get= new FileReader(file);
			BufferedReader use = new BufferedReader(get);
			String model=use.readLine();
			if(model==null||!hasLetters(model)){//should really check if it has no letters then throw it
				use.close();
				throw new VehicleException(1,"text file: Missing model name");
			}
			String fun = use.readLine();
			if(fun==null||hasLetters(fun)){
				use.close();
				throw new VehicleException(2,"text file: Missing base price");
			}
			float base = Float.parseFloat(fun);
			if(base < 100){
				use.close();
				throw new VehicleException(3,"text file: Missing base price/base price too low");//if its less than 100 than its probably number of components read in not price
			}
			String tester = use.readLine();
			if(tester==null||hasLetters(tester)){//if it has letters throw exception
				use.close();
				throw new VehicleException(4,"text file: Missing number of components");
			}
			int x = Integer.parseInt(tester);
			Vehicle car= new Vehicle(model,base,x);
			for(int i=0;i<x;i++){
				String compName=use.readLine();
				if(compName==null||!hasLetters(compName)){
					use.close();
					throw new VehicleException(5,"text file: Missing componenet"+(i+1)+"'s name");
				}
				String numOpt=use.readLine();
				if(numOpt==null||hasLetters(numOpt)){
					use.close();
					throw new VehicleException(13,"text file: Missing number options for "+compName);
				}
				int y = Integer.parseInt(numOpt);
				String[] opts = new String[y];
				float[] prs= new float[y];
				for(int j=0;j<y;j++){
					opts[j]=use.readLine();
					if(opts[j]==null||!hasLetters(opts[j])){
						use.close();
						throw new VehicleException(14,"text file: Missing an option name for "+compName);
					}
					String optPrice = use.readLine();
					if(optPrice==null||hasLetters(optPrice)){
						use.close();
						throw new VehicleException(15,"text file: Missing price for "+compName + "'s "+opts[j]+" option");
					}
					prs[j]=Float.parseFloat(optPrice);
				}
				car.addComponent(compName,opts,prs);
			}
			use.close();
			return car;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public Vehicle newCarFromProps(String file) throws VehicleException{
		Properties prop = new Properties();
		try{
			FileInputStream get= new FileInputStream(file);
			prop.load(get);
			String model=prop.getProperty("Model");
			if(model==null||!hasLetters(model)){//should really check if it has no letters then throw it
				throw new VehicleException(6,"prop file: Missing model name");
			}
			String fun = prop.getProperty("Base");
			if(fun==null||hasLetters(fun)){
				throw new VehicleException(7,"prop file: Missing base price");
			}
			float base = Float.parseFloat(fun);
			if(base < 100){
				throw new VehicleException(8,"prop file: Missing base price/base price too low");//if its less than 100 than its probably number of components read in not price
			}
			String tester = prop.getProperty("NumberComponents");
			if(tester==null||hasLetters(tester)){//if it has letters throw exception
				throw new VehicleException(9,"prop file: Missing number of components");
			}
			int x = Integer.parseInt(tester);
			Vehicle car= new Vehicle(model,base,x);
			for(int i=0;i<x;i++){
				int z = i+1;
				String compNum = "Component"+z;
				String compName=prop.getProperty(compNum);
				if(compName==null||!hasLetters(compName)){
					throw new VehicleException(0,"prop file: Missing componenet"+z+"'s name");
				}
				String numOpt = prop.getProperty(compNum+"Options");
				if(numOpt==null||hasLetters(numOpt)){
					throw new VehicleException(10,"prop file: Missing number options for "+compName);
				}
				int y = Integer.parseInt(numOpt);
				String[] opts = new String[y];
				float[] prs= new float[y];
				for(int j=0;j<y;j++){
					int f = j+1;
					String cO = compNum + "_Option"+f;
					opts[j]= prop.getProperty(cO);
					if(opts[j]==null||!hasLetters(opts[j])){
						throw new VehicleException(11,"prop file: Missing an option name for "+compName);
					}
					String optPrice = prop.getProperty(cO+"_Price");
					if(optPrice==null||hasLetters(optPrice)){
						throw new VehicleException(12,"prop file: Missing price for "+compName + "'s "+opts[j]+" option");
					}
					prs[j]=Float.parseFloat(optPrice);
				}
				car.addComponent(compName,opts,prs);
			}
			return car;
		}
		catch(IOException e){
			System.err.println(e);
			return null;
		}
	}
	
	public String storeProperties(Properties props){
		String name = props.get("Model")+".prop";
		File f = new File(name);
		try{
			OutputStream out = new FileOutputStream(f);
			props.store(out,"");
			out.close();
        }
		catch(IOException e){
			System.err.println(e);
		}
        return name;
	}
	
	private boolean hasLetters(String h){
		char[] f=h.toCharArray();
		for(char c:f){
			if(Character.isLetter(c)){
				return true;
			}
		}
		return false;
	}
	
	public void serializeCar(Vehicle car)
	{
		StringBuilder file = new StringBuilder(car.getType());
		file.append(".ser");
		try{
			ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(file.toString()));
			serial.writeObject(car);
			serial.close();
		}
		catch(Exception e){
			System.out.print("Error: " + e);
			System.exit(1);
		}
	}
	
	
	public Vehicle deserializeCar(String filename)
	{
		Vehicle car = null;
		try{
			ObjectInputStream necr = new ObjectInputStream(new FileInputStream(filename));
			car = (Vehicle) necr.readObject();
			necr.close();
		}
		catch(Exception e){
			System.out.print("Error: " + e);
			System.exit(1);
		}
		return car;
	}
	
	
}
