package sorcer.delta.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;

@SuppressWarnings("rawtypes")
public interface ServiceDelta extends Remote {

	public Context getDelta(Context delta) throws RemoteException,
			DeltaException;


}
