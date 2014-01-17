package sorcer.delta.provider;

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
public class DeltaProvider extends ServiceTasker implements Delta,
		ServiceDelta, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	/**
	 * Constructs an instance of the SORCER account provider implementing
	 * SorcerAccount and Account. This constructor is required by Jini 2 life
	 * cycle management.
	 * 
	 * @param args
	 * @param lifeCycle
	 * @throws Exception
	 */
	public DeltaProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
	}
	
	public double countDelta(double a, double b, double c) throws RemoteException {
		return b*b-4*a*c;
	}
	
	public Context countDelta(Context context) throws RemoteException,
		DeltaException {
		return process(context);
	}
	
	public Context getDelta(Context delta) throws RemoteException,
	DeltaException {
		return process(delta);
	}
	
	private Context process(Context context)
			throws RemoteException, DeltaException {
		try {
			logger.info("input context: \n" + context);

		} catch (Exception ex) {
			throw new DeltaException(ex);
		}
		return context;
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
