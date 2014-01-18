/**
 * 
 */
package sorcer.foxtrot.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.service.ContextException;

/**
 * @author Jakub Nalewajko
 *
 */
@SuppressWarnings("rawtypes")
public interface Delta extends Remote {

	
	public Context delta(Context context) throws RemoteException, ContextException;
	
}
