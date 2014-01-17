package sorcer.delta.provider;

public class DeltaException extends Exception {
	public boolean deltaSucceed;

	public DeltaException(Exception cause) {
		super(cause);
	}
}
