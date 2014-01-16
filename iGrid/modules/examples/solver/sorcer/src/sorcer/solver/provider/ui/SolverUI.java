package sorcer.solver.provider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.jini.core.lookup.ServiceItem;
import net.jini.lookup.entry.UIDescriptor;
import net.jini.lookup.ui.MainUI;
import sorcer.solver.provider.Solver;
import sorcer.core.provider.ServiceProvider;
import sorcer.ui.serviceui.UIComponentFactory;
import sorcer.ui.serviceui.UIDescriptorFactory;
import sorcer.util.Sorcer;

public class SolverUI extends JPanel {

	private static final long serialVersionUID = -3171243785170712405L;

	private JTextField balanceTextField;

	private JTextField withdrawalTextField;

	private JTextField depositTextField;

	private Solver solver;

	private ServiceItem item;

	private final static Logger logger = Logger.getLogger(SolverUI.class
			.getName());

	public SolverUI(Object provider) {
		super();
		getAccessibleContext().setAccessibleName("Solver Tester");
		item = (ServiceItem) provider;

		if (item.service instanceof Solver) {
			solver = (Solver) item.service;
			createUI();
		}

	}

	protected void createUI() {
		setLayout(new BorderLayout());
		add(buildSolverPanel(), BorderLayout.CENTER);
	}

	private JPanel buildSolverPanel() {
		JPanel panel = new JPanel();
		return panel;
	}

	private String readTextField(JTextField moneyField) {

		return null;
	}


	/**
	 * Returns a service UI descriptorfor this service. Usally this method is
	 * used as an entry in provider configuration files when smart proxies are
	 * deployed with a standard off the shelf {@link ServiceProvider}.
	 * 
	 * @return service UI descriptor
	 */
	public static UIDescriptor getUIDescriptor() {
		UIDescriptor uiDesc = null;
		try {
			uiDesc = UIDescriptorFactory.getUIDescriptor(MainUI.ROLE,
					new UIComponentFactory(new URL[] { new URL(Sorcer
							.getWebsterUrl()
							+ "/solver-ui.jar") }, SolverUI.class.getName()));
		} catch (Exception ex) {
			logger.throwing(SolverUI.class.getName(), "getUIDescriptor", ex);
		}
		return uiDesc;
	}
}
