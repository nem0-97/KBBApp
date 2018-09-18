//Neoman Nouiouat
package exception;
import java.io.*;
public class Fix1to15 {
	public void fix1(String file){
		try{
			File mFile  = new File(file);
			FileReader get= new FileReader(file);
			BufferedReader use = new BufferedReader(get);
			String result = "";
			String line = "";
			while( (line = use.readLine()) != null){
			 result = result + line+'\n'; 
			}
			use.close();

			int stop = file.indexOf('.');
			if(stop!=-1){
				String nFileN = file.substring(0, stop);
				result = "Model="+nFileN+'\n' + result;
			}
			else{
				result = "Model="+file+'\n' + result;
			}

			mFile.delete();
			FileOutputStream fos = new FileOutputStream(mFile);
			fos.write(result.getBytes());	//This part is from stack overflow i changed the reading in but left writing the file again alone
			fos.flush();
			fos.close();
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
	}
	
	public void fix2(String file){
		
	}
	
	public void fix3(String file){
		
	}
	
	public void fix4(String file){
		
	}
	
	public void fix5(String file){
		
	}
	public void fix6(String file){
		try{
			File mFile  = new File(file);
			FileReader get= new FileReader(file);
			BufferedReader use = new BufferedReader(get);
			String result = "";
			String line = "";
			while( (line = use.readLine()) != null){
			 result = result + line+'\n'; 
			}
			use.close();
			
			int stop = file.indexOf('.');
			if(stop!=-1){
				String nFileN = file.substring(0, stop);
				result = "Model="+nFileN+'\n' + result;
			}
			else{
				result = "Model="+file+'\n' + result;
			}

			mFile.delete();
			FileOutputStream fos = new FileOutputStream(mFile);
			fos.write(result.getBytes());	//This part is from stack overflow i changed the reading in but left writing the file again alone
			fos.flush();
			fos.close();
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
	}
	
	public void fix7(String file){
		
	}
	
	public void fix8(String file){
		
	}
	
	public void fix9(String file){
		
	}
	
	public void fix0(String file){
		
	}
	public void fix10(String file){
		
	}
	
	public void fix11(String file){
		
	}
	
	public void fix12(String file){
		
	}
	
	public void fix13(String file){
		
	}
	
	public void fix14(String file){
		
	}
	
	public void fix15(String file){
		
	}
}
