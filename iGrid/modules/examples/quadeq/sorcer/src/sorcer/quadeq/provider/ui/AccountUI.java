package sorcer.quadeq.provider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
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
import sorcer.quadeq.provider.Account;
import sorcer.quadeq.provider.Money;
import sorcer.core.provider.ServiceProvider;
import sorcer.ui.serviceui.UIComponentFactory;
import sorcer.ui.serviceui.UIDescriptorFactory;
import sorcer.util.Sorcer;


public class AccountUI extends JPanel {

	private static final long serialVersionUID = -3171243785170712405L;

	private JTextField eqA, eqB, eqC, equationTF, balanceTextField;

	private JTextField withdrawalTextField;

	private JTextField depositTextField;

	private Account account;

	private ServiceItem item;

	private final static Logger logger = Logger.getLogger(AccountUI.class
			.getName());

	public AccountUI(Object provider) {
		super();
		getAccessibleContext().setAccessibleName("QuadraticEquationSolver");
		item = (ServiceItem) provider;

		if (item.service instanceof Account) {
			account = (Account) item.service;
			createUI();
		}

	}

	protected void createUI() {
		setLayout(new BorderLayout());
		add(buildAccountPanel(), BorderLayout.CENTER);
		resetBalanceField();
	}

	private void resetBalanceField() {
		try {
			Money balance = account.getBalance();
			balanceTextField.setText(balance.value());
		} catch (Exception e) {
			logger.info("Error occurred while getting account balance");
			logger.throwing(getClass().getName(), "resetBalanceField", e);
		}
	}

	private JPanel buildAccountPanel() {
		JPanel panel = new JPanel();
		JPanel actionPanel = new JPanel(new GridLayout(10, 1, 2, 2));
		equationTF = new JTextField();
		actionPanel.add(new JLabel("Enter equation:"));

		JPanel eqPanel = new JPanel(new GridLayout(1, 6, 0, 0));
		eqA = new JTextField();
		eqB = new JTextField();
		eqC = new JTextField();
		eqPanel.add(eqA);
		eqPanel.add(new JLabel("x2 + "));
		eqPanel.add(eqB);
		eqPanel.add(new JLabel("x + "));
		eqPanel.add(eqC);
		eqPanel.add(new JLabel(" = 0"));

		actionPanel.add(eqPanel);

		JButton calcBt = new JButton("Calculate");
		calcBt.addActionListener(new CalculateAction());
		actionPanel.add(calcBt);

		JLabel result = new JLabel("x1 = 25 & x2 = 12");
		result.setFont(new Font("Arial", 0, 20));
		actionPanel.add(result);

		panel.add(actionPanel);
		return panel;
	}

	private Money readTextField(JTextField moneyField) {
		try {
			Float floatValue = new Float(moneyField.getText());
			float actualValue = floatValue.floatValue();
			int cents = (int) (actualValue * 100);

			return new Money(cents);
		} catch (Exception e) {
			logger.info("Field doesn't contain a valid value");
		}
		return null;
	}

	private class WithdrawAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				Money withdrawalAmount = readTextField(withdrawalTextField);
				account.makeWithdrawal(withdrawalAmount);
				withdrawalTextField.setText("");
				resetBalanceField();
			} catch (Exception exception) {
				logger.info("Couldn't talk to account. Error was" + exception);
				logger.throwing(getClass().getName(), "actionPerformed",
						exception);
			}
		}
	}

	public class CalculateAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				Money withdrawalAmount = readTextField(withdrawalTextField);
				account.makeWithdrawal(withdrawalAmount);
				withdrawalTextField.setText("");
				resetBalanceField();
			} catch (Exception exception) {
				logger.info("Couldn't talk to account. Error was" + exception);
				logger.throwing(getClass().getName(), "actionPerformed",
						exception);
			}
		}
	}

	
	private class DepositAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				Money depositAmount = readTextField(depositTextField);
				account.makeDeposit(depositAmount);
				depositTextField.setText("");
				resetBalanceField();
			} catch (Exception exception) {
				logger.info("Couldn't talk to account. Error was \n"
						+ exception);
				logger.throwing(getClass().getName(), "actionPerformed",
						exception);
			}
		}
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
							+ "/accout-ui.jar") }, AccountUI.class.getName()));
		} catch (Exception ex) {
			logger.throwing(AccountUI.class.getName(), "getUIDescriptor", ex);
		}
		return uiDesc;
	}
}
