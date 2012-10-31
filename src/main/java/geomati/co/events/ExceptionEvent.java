package geomati.co.events;


/**
 * Event for exception management.
 */
public class ExceptionEvent implements Event<ExceptionEventHandler> {
	public enum Severity {
		INFO, WARNING, ERROR
	}

	public static final Type<ExceptionEventHandler> TYPE = new Type<ExceptionEventHandler>();

	private String message;
	private Severity severity;
	private Throwable exception;

	public ExceptionEvent(Severity severity, String message, Throwable exception) {
		this.severity = severity;
		this.message = message;
		this.exception = exception;
	}

	@Override
	public void dispatch(ExceptionEventHandler handler) {
		handler.exception(severity, message, exception);
	}

	@Override
	public Type<ExceptionEventHandler> getAssociatedType() {
		return TYPE;
	}
}
