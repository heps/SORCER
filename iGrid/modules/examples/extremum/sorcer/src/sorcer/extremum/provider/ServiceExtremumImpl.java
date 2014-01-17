package sorcer.extremum.provider;

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
public class ServiceExtremumImpl implements Extremum, ServiceExtremum, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	Double result[] = new Double[2];

	public Context extremum(Context context) throws RemoteException, ExtremumException {
		logger.info("--------process() serviceExtremum------------");
		return process(context, ServiceExtremum.EXTREMUM);
	}
	public ServiceExtremumImpl(){
	}
	private Context process(Context context, String selector)
			throws RemoteException, ExtremumException {
		logger.info("--------process() extremumProvider------------");
		try {
			Double result[] = null;
			Double a = null, b = null;
			if (selector.equals(ServiceExtremum.EXTREMUM)) {
				a = Double.parseDouble((String) context.getValue(ServiceExtremum.EXTREMUM + CPS
						+ ServiceExtremum.A));
				b = Double.parseDouble((String)context.getValue(ServiceExtremum.EXTREMUM + CPS
						+ ServiceExtremum.B));
				
				result = extremum(a,b);				
			}

		} catch (Exception ex) {
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
