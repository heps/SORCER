package sorcer.solver.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import sorcer.core.SorcerConstants;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.util.Log;

import com.sun.jini.start.LifeCycle;

@SuppressWarnings("rawtypes")
public class SolverProvider extends ServiceTasker implements Solver,
		ServiceSolver, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	Double result[] = new Double[2];

	/**
	 * Constructs an instance of the SORCER solver provider implementing
	 * SorcerSolver and Solver. This constructor is required by Jini 2 life
	 * cycle management.
	 * 
	 * @param args
	 * @param lifeCycle
	 * @throws Exception
	 */
	public SolverProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		//result = stringToarr(getProperty("provider.result"));
	}

	public Context solve(Context context) throws RemoteException,
			SolverException {
		logger.info("--------solve() solverProvider------------");
		return process(context, ServiceSolver.SOLVE);
	}

	private Context process(Context context, String selector)
			throws RemoteException, SolverException {
		logger.info("--------process() solverProvider------------");
		try {
			Double result[] = null;
			Double delta = null, a = null, b = null;
			if (selector.equals(ServiceSolver.SOLVE)) {
				delta = Double.parseDouble((String) context.getValue(ServiceSolver.SOLVE + CPS
						+ ServiceSolver.DELTA));
				a = Double.parseDouble((String)context.getValue(ServiceSolver.SOLVE + CPS
						+ ServiceSolver.A));
				b = Double.parseDouble((String) context.getValue(ServiceSolver.SOLVE + CPS
						+ ServiceSolver.B));
				
				result = solve(delta,a,b);				
			}

		} catch (Exception ex) {
			throw new SolverException(ex);
		}
		return context;
	}

	public Double[] solve(double delta,double a, double b  ) throws RemoteException{
		Double result[] = new Double[2];
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
