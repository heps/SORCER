package sorcer.quadeq.requestor;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import sorcer.quadeq.provider.Money;
import sorcer.quadeq.provider.ServiceAccount;
import sorcer.core.context.ServiceContext;
import sorcer.core.exertion.NetJob;
import sorcer.core.exertion.NetTask;
import sorcer.core.signature.NetSignature;
import sorcer.service.Context;
import sorcer.service.Job;
import sorcer.util.Log;
import sorcer.util.Sorcer;

@SuppressWarnings("rawtypes")
public class AccountTester {

	private static Logger logger = Log.getTestLog();

	String CPS = "/";
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager());
		Job result = new AccountTester().test();
		logger.info("job context: \n" + result.getJobContext());
	}

	private Job test() throws Exception {
		Job result = (Job)getJob().exert();
		return result;
	}

	private Job getJob() throws Exception {
		NetTask task1 = getDepositTask();
		NetTask task2 = getWithdrawalTask();
		NetJob job = new NetJob("account");
		job.addExertion(task1);
		job.addExertion(task2);
		return job;
	}

	private NetTask getDepositTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceAccount.ACCOUNT);
		context.putValue(ServiceAccount.DEPOSIT + CPS + ServiceAccount.AMOUNT,
				new Money(10000)); // $100.00
		context.putValue(ServiceAccount.BALANCE + CPS + ServiceAccount.AMOUNT,
				Context.none);
		NetSignature signature = new NetSignature("makeDeposit",
				ServiceAccount.class, Sorcer.getActualName("Account1"));
		NetTask task = new NetTask("account-deposit", signature);
		task.setContext(context);
		return task;
	}

	private NetTask getWithdrawalTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceAccount.ACCOUNT);
		context.putValue(ServiceAccount.WITHDRAWAL + CPS + ServiceAccount.AMOUNT,
				new Money(10000)); // $100.00
		context.putValue(ServiceAccount.BALANCE + CPS + ServiceAccount.AMOUNT,
				Context.none);
		NetSignature signature = new NetSignature("makeWithdrawal",
				ServiceAccount.class, Sorcer.getActualName("Account2"));
		NetTask task = new NetTask("account-withdrawal", signature);
		task.setContext(context);
		return task;
	}
}
