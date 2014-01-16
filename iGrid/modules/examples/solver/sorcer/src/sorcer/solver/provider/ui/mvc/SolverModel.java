package sorcer.solver.provider.ui.mvc;

import java.util.Observable;


public class SolverModel extends Observable {


	private String withdrawalAmount;

	private String depositAmount;

	final static String DEPOSIT = "$ Deposit";

	final static String WITHDRAW = "$ Withdraw";

	final static String BALANCE = "Balance";

	public SolverModel() {
	}

}