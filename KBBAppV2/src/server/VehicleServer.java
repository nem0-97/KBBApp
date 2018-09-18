//Neoman Nouiouat
package server;

import java.util.*;
import model.*;

public interface VehicleServer {
	
	void buildFromProp(Properties prop);
	
	Vehicle getModel(String mode);

}
