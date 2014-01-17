package sorcer.delta.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Delta extends Remote {

	public double countDelta(double a, double b, double c) throws RemoteException;
	
}
