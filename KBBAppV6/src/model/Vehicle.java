//Neoman Nouiouat
package model;
import java.io.*;
import java.util.*;

//New Class
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String make;
	private String model;
	private int year;
	private float base;
	private ArrayList<Component> comps;
	private ArrayList<Option> chos;

	public Vehicle(){

	}

	public Vehicle(String name, float cost, int size){
		type=name;
		base=cost;
		comps = new ArrayList<Component>(size);
		chos = new ArrayList<Option>(size);
		int i = type.indexOf(' ');
		int j = type.indexOf(' ', i+1);
		year = Integer.parseInt(type.substring(0,i));
		make = type.substring(i,j);
		model = type.substring(type.indexOf(' '));
	}

	public void setMake(String s){
		make=s;
	}
	
	public void setModel(String s){
		model=s;
	}
	
	public void setYear(int y){
		year = y;
	}
	
	public String getMake(){
		return make;
	}
	
	public String getModel(){
		return model;
	}
	
	public int getYear(){
		return year;
	}
	
	public void changeCompNameInd(int i,String ne){
		comps.get(i).setName(ne);
	}
	
	public void changeCompName(String old,String ne){
		int i = findCompInd(old);
		if(i!=-1){
			comps.get(i).setName(ne);
		}
	}
	public String getCompName(int i){
		return getComp(i).getName();
	}
	
	public int numOptions(int i){
		return getComp(i).getNumOpts();
	}
	public String getOptionName(int i,int j){
		return getComp(i).getOptNameInd(j);
	}
	
	public String getOption(int i,int j){
		return (getComp(i).getOptNameInd(j)+"--$"+getComp(i).getOptCostInd(j));
	}
	
	public void changeOptionPrice(String comp,String opt, float newPrice){
		int i=findCompInd(comp);
		if(i!=-1){
			int j =chos.indexOf(comps.get(i).findOpt(opt));
			if(j!=-1){
				chos.get(j).setPrice(newPrice);
			}
			comps.get(i).setOptPrice(opt,newPrice);
		}
	}

	public void setCost(float c){
		base=c;
	}

	public float getCost(){
		return base;
	}

	public void setType(String s){
		type=s;
	}

	public String getType(){
		return type;
	}

	public Component getComp(int i){
		return comps.get(i);
	}

	public String getCompChose(String comp){
		int i =findCompInd(comp);
		if(i!=-1){
			return chos.get(i).getName();
		}
		return "component doesn't exist"; 
	}
	
	public void setCompChose(String comp,String opt){
		int i = findCompInd(comp);
		if(i!=-1){
			Component use = comps.get(i);
			use.setChosen(opt);
			chos.set(i,use.getChosen());
		}
	}
	
	public float getCompCost(String comp){
		int i =findCompInd(comp);
		if(i!=-1){
			return chos.get(i).getPrice();
		}
		return 666;
	}
	
	public Component findComp(String s){
		int i = findCompInd(s);
		if(i!=-1){
			return comps.get(i);
		}
		return null;
	}

	public int findCompInd(String s){//use iterator or keep my for loop?
		for(int i=0;i<comps.size();i++){
			if(comps.get(i).getName().equalsIgnoreCase(s)){
				return i;
			}
		}
		return -1;
	}

	public void addComponent(String name, String[] choices,float[] prices){
		Component ad = new Component(name,choices,prices);
		comps.add(ad);	
		chos.add(ad.getChosen());
	}

	public void indModComponent(String name, String[] choices,float[] prices,int j){
		Component ad = new Component(name,choices,prices);
		comps.set(j, ad);
		chos.set(j,ad.getChosen());
	}

	public void namModComponent(String oname,String name, String[] choices,float[] prices){
		Component ad = new Component(name,choices,prices);
		int j =findCompInd(oname);
		if(j!=-1){
			comps.set(j,ad);
			chos.set(j, ad.getChosen());
		}
	}

	public float getTotal(){
		float total=base;
		Iterator<Option> costs = chos.iterator();
		while(costs.hasNext()){
			total+=costs.next().getPrice();
		}
		return total;
	}

	public void removeCompName(String s){
		int i =findCompInd(s);
		if(i!=-1){
			removeCompInd(i);
		}
	}

	public void removeCompInd(int i){
		comps.remove(i);
		chos.remove(i);
	}
	
	public int numComponents(){
		return comps.size();
	}
	
	public String printComponent(int i){
		Component comp = comps.get(i);
		System.out.print(comp.getName());
		for(int j=0;j<comps.get(i).getNumOpts();j++){
			System.out.print('\n');
			System.out.print('\t');
			System.out.print(comps.get(i).getOptNameInd(j));
			System.out.print(" $");
			System.out.print(comps.get(i).getOptCostInd(j));
		}
		return comp.getName();
	}
	
	public void printInfo(){
		StringBuilder temp=  new StringBuilder(0);
		temp.append(type);
		temp.append("  base: $");
		temp.append(base);
		temp.append('\n');

		for(int i=0;i<comps.size();i++){
			temp.append('\n');
			temp.append("   ");
			temp.append(comps.get(i).getName());
			temp.append(": ");
			for(int j=0;j<comps.get(i).getNumOpts();j++){
				temp.append('\n');
				if(chos.contains(comps.get(i).getOpt(j))){
					temp.append("   [X]  ");
				}else{
					temp.append('\t');
				}
				temp.append(comps.get(i).getOptNameInd(j));
				temp.append(" $");
				temp.append(comps.get(i).getOptCostInd(j));
			}
			temp.append('\n');
		}
		temp.append('\n');
		temp.append("Total: $");
		temp.append(getTotal());
		temp.append('\n');
		System.out.println(temp);
	}
}
