package sorcer.extremum.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author m.weiss s8035
 *
 */
public interface Extremum extends Remote {

	public Double[] extremum(double a, double b ) throws RemoteException;
	

}

