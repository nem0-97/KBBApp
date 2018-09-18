//Neoman Nouiouat
package exception;

import java.io.*;
import java.util.*;

public class VehicleException extends Exception {
	private int errorno;
	private String message; // either this or have text file or static array of Strings and errorno corresponds to message index/line in file
	
	public VehicleException(int e,String mess){
		errorno=e;
		message = mess;
		try{
			File errLog = new File("errorLog");
			errLog.createNewFile();
			FileWriter get= new FileWriter("errorLog",true);
			BufferedWriter use = new BufferedWriter(get);
			PrintWriter fin = new PrintWriter(use);
			Calendar time = Calendar.getInstance();
			fin.println("Erro No."+errorno+" "+message+" at "+time.getTime()+'\n');
			fin.close();
		}
		catch(IOException er){
			System.err.println(er);
		}
		
	}
	
	public void fix(String file){
		Fix1to15 ter = new Fix1to15();
		switch(errorno){
		case 1: ter.fix1(file);break;
		case 2: ter.fix2(file);break;
		case 3: ter.fix3(file);break;
		case 4: ter.fix4(file);break;
		case 5: ter.fix5(file);break;
		case 6: ter.fix6(file);break;
		case 7: ter.fix7(file);break;
		case 8: ter.fix8(file);break;
		case 9: ter.fix9(file);break;
		case 0: ter.fix0(file);break;
		case 10:ter.fix10(file);break;
		case 11:ter.fix11(file);break;
		case 12:ter.fix12(file);break;
		case 13:ter.fix13(file);break;
		case 14:ter.fix14(file);break;
		case 15:ter.fix15(file);break;
		}
	}
	
	public int getErrorNo(){
		return errorno;
	}
	
	public String getErrorMessage(){
		return message;
	}
}
