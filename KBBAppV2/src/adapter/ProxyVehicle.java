//Neoman Nouiouat
package adapter;

import java.util.*;

import editoptions.EOThread;
import exception.*;
import model.*;
import util.*;

//just call functions in vehicle

public abstract class ProxyVehicle {
	private static LinkedHashMap<String,Vehicle> garage = new LinkedHashMap<String,Vehicle>();
	
	public void BuildVehicle(String file,int opt){
		FileIO use = new FileIO();//could also make a FileIO property that is initialized in a constructor just in case want to use it in any other methods
		boolean fixed=false;
		while(!fixed){	
			try{
				Vehicle add = null;
				if(opt==0){
					add = use.newCarFromFile(file);
				}
				else if(opt==1){
					add = use.newCarFromProps(file);
				}
				fixed=true;
				garage.put(add.getType().toLowerCase(),add);   //either get type as key or use filename
			}
			catch(VehicleException e){
				e.fix(file);
			}
		}
	}
	
	public void buildFromProp(Properties prop){
		FileIO use = new FileIO(); //could just use props as linked hash map and copy code from newCarFromProps without the loading file first
		BuildVehicle(use.storeProperties(prop),1);
	}
	
	public Vehicle getModel(String mode){
		return garage.get(mode.toLowerCase());
	}
	
	public ArrayList<String> getModels(){
		return new ArrayList<String>(garage.keySet());
	}
	
	public void printAuto(String car){
		Vehicle use = garage.get(car.toLowerCase());
		if(use!=null){
			use.printInfo();
		}
	}
	
	public void updateComponentName(String car,String old,String ne){
		Vehicle use = garage.get(car.toLowerCase());
		if(use!=null){
			use.changeCompName(old, ne);
		}
	}
	
	public void updateOptionPrice(String car,String comp,String opt,float newPrice){
		Vehicle use = garage.get(car.toLowerCase());
		if(use!=null){
			use.changeOptionPrice(comp, opt, newPrice);
		}
	}
	
	public void chooseOption(String car,String comp,String opt){
		Vehicle use = garage.get(car.toLowerCase());
		if(use!=null){
			use.setCompChose(comp, opt);
		}
	}
	
	public void update(String car,int tD,String[] paras){
		EOThread use = new EOThread(car,tD,paras);
		use.start();
	}
	
}
