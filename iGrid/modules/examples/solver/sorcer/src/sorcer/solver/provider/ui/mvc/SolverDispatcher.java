package sorcer.solver.provider.ui.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import sorcer.solver.provider.Solver;

public class SolverDispatcher implements ActionListener {

	private final static Logger logger = Logger
			.getLogger("sorcer.provider.solver.ui.mvc");

	private SolverModel model;

	private SolverView view;

	private Solver solver;

	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		logger.info("actionPerformed>>action: " + action);

	}
}
