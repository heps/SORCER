/**
 * 
 */
package sorcer.project.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;
import sorcer.service.Context;
import sorcer.service.ContextException;


@SuppressWarnings("rawtypes")
public interface Solver extends Remote {

	
	public Context solve(Context context) throws RemoteException, ContextException;
	
}
