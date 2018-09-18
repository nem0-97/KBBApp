//Neoman Nouiouat
package model;

import java.io.Serializable;
import java.util.*;

public class Component implements Serializable	{
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Option> opts;
	private Option chosen;

	protected Component(){
	}

	protected Component(String nam,String[] opt,float[] costs){
		name=nam;
		opts = new ArrayList<Option>(opt.length);
		for(int i=0;i<opt.length;i++){
			opts.add(new Option(opt[i],costs[i]));
		}
		chosen=opts.get(1);
	}
	
	protected int getNumOpts(){
		return opts.size();
	}
	
	protected String getOptNameInd(int i){
		return opts.get(i).getName();
	}
	
	protected float getOptCostInd(int i){
		return opts.get(i).getPrice();
	}

	protected void removeOptionName(String s){
		int i=findOptInd(s);
		if(i!=-1){
			opts.remove(i);
		}
	}

	protected void removeOptionInd(int i){
		opts.remove(i);
	}

	protected void setChosen(String s){
		int i = findOptInd(s);
		if(i!=-1){
			chosen=opts.get(i);
		}
	}

	protected void indModOpt(String s,float f,int i){
		opts.set(i,new Option(s,f));
	}

	protected void namModOpt(String os,String s,float f){
		int i=findOptInd(os);
		if(i!=-1){
			opts.set(i,new Option(s,f));
		}
	}

	protected void addOpt(String s,float f){
		opts.add(new Option(s,f));
		
	}

	protected Option findOpt(String s){
		int i = findOptInd(s);
		if(i!=-1){
			return opts.get(i);
		}
		return null;
	}

	protected int findOptInd(String s){
		for(int i=0;i<opts.size();i++){
			if(opts.get(i).getName().equalsIgnoreCase(s)){
				return i;
			}
		}
		return -1;
	}
	
	protected void setName(String n) {
		name=n;
	}
	
	protected String getName() {
		return name;
	}
	
	protected void setOptPrice(String opt, float newPrice) {
		int i=findOptInd(opt);
		if(i!=-1){
			opts.get(i).setPrice(newPrice);
		}
	}
	
	protected Option getChosen() {
		return chosen;
	}
	
	protected boolean isChosenInd(int i) {
		return (chosen==opts.get(i));
	}

	protected Option getOpt(int i) {
		return opts.get(i);
	}
}