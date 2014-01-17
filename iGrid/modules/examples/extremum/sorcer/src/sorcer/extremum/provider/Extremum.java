package sorcer.extremum.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
/**
 * @author m.weiss s8035
 *
 */
public interface Extremum extends Remote {

	public Context extremum(Context context) throws RemoteException,ExtremumException;
	

}

