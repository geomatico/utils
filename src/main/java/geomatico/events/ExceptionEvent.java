package geomatico.events;

/**
 * Event for exception management.
 */
public class ExceptionEvent implements Event<ExceptionEventHandler> {

	public enum Severity {
		INFO, WARNING, ERROR
	}

	private String message;
	private Severity severity;
	private Throwable exception;

	public ExceptionEvent(Severity severity, String message, Throwable exception) {
		this.severity = severity;
		this.message = message;
		this.exception = exception;
	}

	/**
	 * Builds an exception event with a {@link Severity#ERROR} level
	 * 
	 * @param message
	 * @param exception
	 */
	public ExceptionEvent(String message, Throwable exception) {
		this.severity = Severity.ERROR;
		this.message = message;
		this.exception = exception;
	}

	@Override
	public void dispatch(ExceptionEventHandler handler) {
		handler.exception(severity, message, exception);
	}

}
