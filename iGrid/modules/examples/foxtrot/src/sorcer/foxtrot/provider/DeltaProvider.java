/**
 * 
 */
package sorcer.foxtrot.provider;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import sorcer.core.context.Contexts;
import sorcer.core.context.PositionalContext;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;

import com.sun.jini.start.LifeCycle;

/**
 * @author Jakub Nalewajko
 *
 */
public class DeltaProvider extends ServiceTasker implements Delta {
	private static Logger logger = Logger
			.getLogger(DeltaProvider.class.getName());
	

	public DeltaProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context delta(Context context) throws RemoteException,
			ContextException {
		// TODO Auto-generated method stub
		return findDelta(context);
	}
	
	private Context findDelta(Context context) throws RemoteException, ContextException {
		PositionalContext cxt = (PositionalContext) context;
		try {
			
			double a = (Double)cxt.get("a");
			double b = (Double)cxt.get("b");
			double c = (Double)cxt.get("c");
			double delta = b*b-4*a*c;
			cxt.putValue("delta", delta);
			
		} catch (Exception ex) {
			// ContextException, UnknownHostException
			ex.printStackTrace();
			context.reportException(ex);
			throw new ContextException(" calculate exception", ex);
		}
		return (Context) context;
	}
	
	

}
