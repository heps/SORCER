package sorcer.solver.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
/**
 * @author m.weiss s8035
 *
 */
@SuppressWarnings("rawtypes")
public interface ServiceSolver extends Remote {

	public Context solve(Context solver) throws RemoteException, SolverException;

	public final static String SOLVE = "solve";

	public final static String DELTA = "delta";

	public final static String A = "a";

	public final static String B = "b";

	public final static String C = "c";

	public final static String RESULT = "result";
}
