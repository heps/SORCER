package sorcer.project.junit;
import static sorcer.eo.operator.context;
import static sorcer.eo.operator.cxt;
import static sorcer.eo.operator.in;
import static sorcer.eo.operator.result;
import static sorcer.eo.operator.sig;
import static sorcer.eo.operator.srv;
import static sorcer.eo.operator.task;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import org.junit.Test;

import sorcer.core.SorcerConstants;
import sorcer.core.exertion.ObjectJob;
import sorcer.project.provider.Delta;
import sorcer.project.provider.Notifier;
import sorcer.project.provider.Solver;
import sorcer.service.Exertion;
import sorcer.service.Job;
import sorcer.service.ServiceExertion;
import sorcer.service.Task;
import sorcer.util.Sorcer;
@SuppressWarnings("unchecked")
public class ProjectTest implements SorcerConstants {

	private final static Logger logger = Logger
			.getLogger(ProjectTest.class.getName());
	
	static {
		ServiceExertion.debug = true;
		System.setProperty("java.security.policy", Sorcer.getHome()
				+ "/configs/policy.all");
		System.setSecurityManager(new RMISecurityManager());
		Sorcer.setCodeBase(new String[] { "DeltaProvider.jar", "SolverProvider.jar", "NotifierProvider.jar" });
		System.out.println("CLASSPATH :" + System.getProperty("java.class.path"));
		System.setProperty("java.protocol.handler.pkgs", "sorcer.util.url|org.rioproject.url");
	}
	
	
	
	@Test
	public void addTest() throws Exception {
		
		logger.info("TEST");
		
		double a = -4;
		double b = 8;
		double c = 0;
		
		Task t1 = task(
				"Delta",
				sig("delta", Delta.class, "DeltaProvider"),
				cxt("delta", in("a", a), in("b", b), in("c", c), result("Delta/delta") ));

		logger.info("B´d´ wywo∏ywaç task #1 o zadanych parametrach: "+t1.getContext());
		
		Task t2 = srv(
				"Solver",
				sig("solve", Solver.class, "SolverProvider"),
				cxt("solve", in("a", a), in("b", b), result("Solver/x1"), result("Solver/x2")));
		t1.getContext().connect("delta", "d", t2.getContext());
		
		logger.info("B´d´ wywo∏ywaç task #2 o zadanych parametrach: "+t2.getContext());
		
		Task t3 = srv("Notifier", sig("notify", Notifier.class, "NotifierProvider"), context("notifier"));
		t2.getContext().connect("x1", "x1", t3.getContext());
		t2.getContext().connect("x2", "x2", t3.getContext());
		
		logger.info("B´d´ wywo∏ywaç task #3 o zadanych parametrach: "+t3.getContext());
		
		Exertion job = new ObjectJob("Jobs");
		job.addExertion(t1);
		job.addExertion(t2);
		job.addExertion(t3);
		
		job = job.exert();
		logger.info("JOB :"+((Job)job).getJobContext());
		logger.info("Rozwiàzania równania: x1="+((Job)job).getJobValue("Jobs/Solver/x1")+" x2="+((Job)job).getJobValue("Jobs/Solver/x2"));
	
		
	}
	
}