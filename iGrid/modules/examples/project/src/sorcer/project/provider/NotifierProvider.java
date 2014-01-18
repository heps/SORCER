/**
 * 
 */
package sorcer.project.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;

import com.sun.jini.start.LifeCycle;

/**
 * @author Maciek
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class NotifierProvider extends ServiceTasker implements Notifier {
	private static Logger logger = Logger
			.getLogger(NotifierProvider.class.getName());
	
	public NotifierProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
	}

	/* (non-Javadoc)
	 * @see sorcer.project.provider.Head#head(sorcer.service.Context)
	 */
	
	@Override
	public Context notify(Context context) throws ContextException,RemoteException, MalformedURLException, IOException {
		
		URLConnection yc = (new URL("http://dom.heps.pl/push.php?t=Equation%20Solving%20Requested&m=Results:"+context.get("x1")+"%20"+context.get("x2"))).openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            //logger.info(inputLine);
        in.close();

		return context;
	}
	
	

}
