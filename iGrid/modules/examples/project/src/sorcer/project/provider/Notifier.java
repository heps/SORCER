/**
 * 
 */
package sorcer.project.provider;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.service.ContextException;

/**
 * @author Maciek
 *
 */
@SuppressWarnings("rawtypes")
public interface Notifier extends Remote {
	
	public Context notify(Context context) throws ContextException,RemoteException, MalformedURLException, IOException;
}
