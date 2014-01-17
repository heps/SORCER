package sorcer.solver.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.solver.provider.SolverException;
/**
 * @author m.weiss s8035
 *
 */
public interface Solver extends Remote {

	public Context solve(Context context) throws RemoteException, SolverException;
	

}

