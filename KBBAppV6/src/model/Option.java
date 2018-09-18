package model;

import java.io.Serializable;

public class Option implements Serializable{
	private static final long serialVersionUID = 1L;
	private float cost;
	private String name;

	protected Option(){

	}

	protected Option(String call,float pri){
		cost=pri;
		name=call;
	}

	protected float getPrice(){
		return cost;
	}

	protected void setPrice(float c){
		cost=c;
	}

	protected String getName(){
		return name;
	}
	protected void setName(String n){
		name=n;
	}
}
