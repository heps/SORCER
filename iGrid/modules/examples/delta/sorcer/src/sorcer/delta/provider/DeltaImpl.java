package sorcer.delta.provider;

import java.rmi.RemoteException;

public class DeltaImpl implements Delta {

	public double countDelta(double a, double b, double c) throws RemoteException {
		return b*b-4*a*c;
	}

}
