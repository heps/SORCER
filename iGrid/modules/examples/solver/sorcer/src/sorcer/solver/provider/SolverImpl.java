package sorcer.solver.provider;

import java.rmi.RemoteException;
import java.lang.Math;
/**
 * @author m.weiss s8035
 *
 */
public class SolverImpl implements Solver {
	Double result[] = new Double[2];
	
	public SolverImpl(){
	}
	public Double[] solve(double delta,double a, double b  ) throws RemoteException{
		//delta = 0; a*(delta-b/2a)
		if(delta == 0.0){
			result[0]=result[1]=a*((delta-b)/(2*a));
		}else if(delta<0){
			result[0]=result[1]=null;
		}else{
			result[0]=(-b-Math.sqrt(delta))/(2*a);
			result[1]=(-b+Math.sqrt(delta))/(2*a);
		}
		
		return result;
	}

	public String arrToString(Double[] arr){
		String str = "";
		for (Double d : arr) {
			str+=d.toString()+"|";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	public Double[] stringToarr(String str){
		Double result[] = new Double[2];
		String doubles[] = str.split("|");
		result[0]=Double.parseDouble(doubles[0]);
		result[1]=Double.parseDouble(doubles[1]);
		return result;
	}
}
