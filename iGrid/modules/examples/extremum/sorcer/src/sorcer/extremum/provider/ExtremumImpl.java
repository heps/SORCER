package sorcer.extremum.provider;

import java.rmi.RemoteException;
import java.lang.Math;
import java.lang.Double;
/**
 * @author m.weiss s8035
 *
 */
public class ExtremumImpl implements Extremum {
	Double result[] = new Double[2];
	
	public ExtremumImpl(){
	}
	public Double[] extremum(double a, double b  ) throws RemoteException{
		result[0] = -1*(b/a);
		
		if(a> Double.parseDouble("0")){
			result[1] = Double.parseDouble("-1");
		}else if(a< Double.parseDouble("0")){
			result[1] = Double.parseDouble("1");
		}else{
			result[0] = Double.parseDouble("0");
			result[1] = Double.parseDouble("0");
		}
		return result;
	}
}
