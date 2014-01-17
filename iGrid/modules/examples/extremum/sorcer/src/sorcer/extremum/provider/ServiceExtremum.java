package sorcer.extremum.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
/**
 * @author m.weiss s8035
 *
 */
@SuppressWarnings("rawtypes")
public interface ServiceExtremum extends Remote {

	public Context extremum(Context extremum) throws RemoteException, ExtremumException;

	public final static String EXTREMUM = "extremum";

	public final static String DELTA = "delta";

	public final static String A = "a";

	public final static String B = "b";

	public final static String C = "c";

	public final static String RESULT = "result";
}
