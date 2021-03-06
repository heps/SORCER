package sorcer.arithmetic.provider;

import java.net.URL;
import java.rmi.RemoteException;

import net.jini.id.Uuid;
import net.jini.lookup.entry.UIDescriptor;
import net.jini.lookup.ui.MainUI;
import sorcer.arithmetic.provider.ui.CalculatorUI;
import sorcer.core.provider.dbp.DatabaseProvider;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.service.MonitorException;
import sorcer.ui.serviceui.UIComponentFactory;
import sorcer.ui.serviceui.UIDescriptorFactory;
import sorcer.util.Sorcer;

import com.sun.jini.start.LifeCycle;

public class AdderDBProvider extends DatabaseProvider implements RemoteAdder {
	private Arithmometer arithmometer = new Arithmometer();
	
	public AdderDBProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
	}
	
	public Context add(Context context) throws RemoteException,
			ContextException, MonitorException {
		Context out = arithmometer.add(context);		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.checkpoint();
//		Logger remoteLogger =  provider.getRemoteLogger();
//		remoteLogger.info("remote logging; add result: " + out);
		
		Uuid id =  store(context);
		Context scxt = getContext(id);
		System.out.println("stored context: " + context);
		
		return out;
	}
	
	public static UIDescriptor getCalculatorDescriptor() {
		UIDescriptor uiDesc = null;
		try {
			uiDesc = UIDescriptorFactory.getUIDescriptor(MainUI.ROLE,
					new UIComponentFactory(new URL[] { new URL(Sorcer
							.getWebsterUrl()
							+ "/calculator-ui.jar") }, CalculatorUI.class
							.getName()));
		} catch (Exception ex) {
			// do nothing
		}
		return uiDesc;
	}
}
