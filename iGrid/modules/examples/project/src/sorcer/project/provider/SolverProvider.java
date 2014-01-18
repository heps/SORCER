/**
 * 
 */
package sorcer.project.provider;

import static sorcer.eo.operator.path;
import static sorcer.eo.operator.revalue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import com.sun.jini.start.LifeCycle;

import sorcer.core.context.ArrayContext;
import sorcer.core.context.Contexts;
import sorcer.core.context.PositionalContext;
import sorcer.core.context.ServiceContext;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.project.provider.SolverProvider;


@SuppressWarnings({"rawtypes","unchecked"})
public class SolverProvider extends ServiceTasker implements Solver {
	private static Logger logger = Logger
			.getLogger(SolverProvider.class.getName());
	
	public SolverProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see sorcer.project.provider.Head#head(sorcer.service.Context)
	 */
	
	@Override
	public Context solve(Context context) throws RemoteException,
			ContextException {
		PositionalContext cxt = (PositionalContext) context;
		try {

			double delta = (Double)context.get("d");
			double a = (Double)context.get("a");
			double b = (Double)context.get("b");
			
			Double x1,x2;
			
			if(delta < 0) {
				//brak rozwiazan
				x1 = null;
				x2 = null;
			} else if(delta == 0) {
				//jedno rozwiazanie 
				x1 = -b/(2*a);
				x2 = null;
			} else {
				//dwa rozwiazania
				double sqD = Math.sqrt(delta);
				x1 = (-b-sqD)/(2*a);
				x2 = (-b+sqD)/(2*a);
			}
			cxt.putValue("x1", x1);
			cxt.putValue("x2", x2);
	        
			
		} catch (Exception ex) {
			// ContextException, UnknownHostException
			ex.printStackTrace();
			context.reportException(ex);
			throw new ContextException(" calculate exception", ex);
		}
		return (Context) context;
	}
}
