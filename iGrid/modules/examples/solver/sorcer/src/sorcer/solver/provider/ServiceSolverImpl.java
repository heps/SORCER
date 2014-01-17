package sorcer.solver.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import net.jini.admin.Administrable;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import sorcer.core.SorcerConstants;
import sorcer.core.provider.Provider;
import sorcer.core.proxy.Partnership;
import sorcer.core.proxy.RemotePartner;
import sorcer.service.Context;
import sorcer.service.Exertion;
import sorcer.service.ExertionException;
import sorcer.util.Log;
/**
 * @author m.weiss s8035
 *
 */
public class ServiceSolverImpl implements Solver, ServiceSolver, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	Double result[] = new Double[2];

	public Context solve(Context context) throws RemoteException, SolverException {
		logger.info("--------process() serviceSolver------------");
		return process(context, ServiceSolver.SOLVE);
	}
	public ServiceSolverImpl(){
	}
	private Context process(Context context, String selector)
			throws RemoteException, SolverException {

		logger.info("--------process() serviceSolver------------");
		try {
			Double result[] = null;
			Double delta = null, a = null, b = null;
			if (selector.equals(ServiceSolver.SOLVE)) {
				delta = Double.parseDouble((String)context.getValue(ServiceSolver.SOLVE + CPS
						+ ServiceSolver.DELTA));
				a = Double.parseDouble((String)context.getValue(ServiceSolver.SOLVE + CPS
						+ ServiceSolver.A));
				b = Double.parseDouble((String)context.getValue(ServiceSolver.SOLVE + CPS
						+ ServiceSolver.B));
				
				result = solve(delta,a,b);				
			}

		} catch (Exception ex) {
			throw new SolverException(ex);
		}
		return context;
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
	private Provider partner;

	private Administrable admin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see sorcer.core.provider.proxy.Partnership#getPartner()
	 */
	public Remote getInner() throws RemoteException {
		return (Remote) partner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sorcer.base.Service#service(sorcer.base.Exertion)
	 */
	public Exertion service(Exertion exertion, Transaction transaction)
			throws RemoteException, ExertionException, TransactionException {
		System.out.println("BOOOOOOMABEn\n\n\n");
		return partner.service(exertion, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.jini.admin.Administrable#getAdmin()
	 */
	public Object getAdmin() throws RemoteException {
		return admin;
	}

	public void setInner(Object provider) {
		partner = (Provider) provider;
	}

	public void setAdmin(Object admin) {
		this.admin = (Administrable) admin;
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
