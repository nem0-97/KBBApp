//Neoman Nouiouat
package editoptions;

import adapter.*;

public class EOThread extends ProxyVehicle implements Runnable{
	private Thread EO;
	private String model;
	private String[] params;
	private int toDo;
	//private int no;
	//private boolean running = true;
	//private boolean alive = true;
	//commented out sections can be used to make the thread reusable and checkup on it and have it do other things later
	
	public EOThread(String mode,int tD,String[] paras){
		EO = new Thread(this);
		this.model = mode;
		this.toDo = tD;
		this.params = paras;
	}
	
	public void run() {
		//while(alive){
			//if(running){
				//System.out.println("Before EO"+no);
				//printAuto(model);
				if(toDo==0){
					updateComponentName(params[0],params[1]);
				}
				else if(toDo==1){
					updateOptionPrice(params[0],params[1],Float.parseFloat(params[2]));
				}
				else if(toDo==2){
					chooseOption(params[0],params[1]);
				}
				//running=false;
				//System.out.println("After EO"+no);
				printAuto(model);
				//System.out.println("EO"+no+"done");
			//}
		//}
	}
	
//	public void stop(){
//		alive=false;
//	}
	
	public void start(){
		//alive =true;
		//running = true;
		EO.start();
	}
	
//	public void start(String mode,int tD,String[] paras){
//		model = mode;
//		toDo = tD;
//		params = paras;
//		running = true;
//	}
	
	
	private synchronized void updateComponentName(String old,String ne){
		updateComponentName(model,old,ne);
	}
	
	private synchronized void updateOptionPrice(String comp,String opt,float newPrice){
		updateOptionPrice(model,comp,opt,newPrice);
	}
	
	private synchronized void chooseOption(String comp,String opt){
		chooseOption(model,comp,opt);
	}
	
//	public void setModel(String mode){
//		model=mode;
//	}
//	
//	public String getModel(){
//		return model;
//	}
//	
//	public void setToDo(int tD){
//		toDo=tD;
//	}
//	
//	public int getToDo(){
//		return toDo;
//	}
//	
//	public void setParams(String[] paras){
//		params=paras;
//	}
//	
//	public String[] getParams(){
//		return params;
//	}
}
