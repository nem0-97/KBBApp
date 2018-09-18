//Neoman Nouiouat
package adapter;

public interface UpdateVehicle {
	void update(String car,int tD,String[] paras);
	void updateComponentName(String car,String old,String ne);
	void updateOptionPrice(String car,String comp,String opt,float newPrice);
	void chooseOption(String car,String comp,String opt);
}
