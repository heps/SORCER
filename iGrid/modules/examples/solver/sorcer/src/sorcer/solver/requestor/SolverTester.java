package sorcer.solver.requestor;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import sorcer.solver.provider.ServiceSolver;
import sorcer.core.context.ServiceContext;
import sorcer.core.exertion.NetJob;
import sorcer.core.exertion.NetTask;
import sorcer.core.signature.NetSignature;
import sorcer.service.Context;
import sorcer.service.Job;
import sorcer.util.Log;
import sorcer.util.Sorcer;

@SuppressWarnings("rawtypes")
public class SolverTester {

	private static Logger logger = Log.getTestLog();

	String CPS = "/";
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager());
		Job result = new SolverTester().test();
		logger.info("job context: \n" + result.getJobContext());
	}

	private Job test() throws Exception {
		Job result = (Job)getJob().exert();
		return result;
	}

	private Job getJob() throws Exception {
		NetTask task1 = getSolveTask();
		NetJob job = new NetJob("solver");
		job.addExertion(task1);
		return job;
	}

	private NetTask getSolveTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceSolver.SOLVE);
		context.putValue(ServiceSolver.SOLVE + CPS + ServiceSolver.DELTA,
				new Double(23)); 
		NetSignature signature = new NetSignature("solve",
				ServiceSolver.class, Sorcer.getActualName("Solver1"));
		NetTask task = new NetTask("solver-solve", signature);
		task.setContext(context);
		return task;
	}
}
