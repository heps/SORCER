package sorcer.solver.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * @author m.weiss s8035
 *
 */
public interface Solver extends Remote {

	public Double[] solve(double delta,double a, double b ) throws RemoteException;
	

}

