package sorcer.quadeq.provider;

public class AccountException extends Exception {
	public boolean withdrawalSucceeded;

	public AccountException(Exception cause) {
		super(cause);
	}
}
