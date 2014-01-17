package sorcer.extremum.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import sorcer.core.SorcerConstants;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.util.Log;

import com.sun.jini.start.LifeCycle;
/**
 * @author m.weiss s8035
 *
 */
@SuppressWarnings("rawtypes")
public class ExtremumProvider extends ServiceTasker implements Extremum,
		ServiceExtremum, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	Double result[] = new Double[2];

	/**
	 * Constructs an instance of the SORCER extremum provider implementing
	 * SorcerExtremum and Extremum. This constructor is required by Jini 2 life
	 * cycle management.
	 * 
	 * @param args
	 * @param lifeCycle
	 * @throws Exception
	 */
	public ExtremumProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		//result = stringToarr(getProperty("provider.result"));
	}

	public Context extremum(Context context) throws RemoteException,ExtremumException{
		logger.info("--------extremum() extremumProvider------------");
		try {
			Double result[] = null;
			Double a = null, b = null;
			a = (Double) context.getValue("extremum/a");
			b = (Double)context.getValue("extremum/b");
			
			result = extremum(a,b);				
			
		
		context.putValue("result", result);
		}catch (Exception ex) {
			throw new ExtremumException(ex);
		}
		return context;
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
	/**
	 * Returns name of the local host.
	 * 
	 * @return local host name
	 * @throws UnknownHostException
	 */
	private String getHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}
}
