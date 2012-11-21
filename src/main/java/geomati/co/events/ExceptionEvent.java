package geomati.co.events;

/**
 * Event for exception management.
 */
public class ExceptionEvent implements Event<ExceptionEventHandler> {

	private Throwable exception;

	public ExceptionEvent(Throwable exception) {
		this.exception = exception;
	}

	@Override
	public void dispatch(ExceptionEventHandler handler) {
		handler.exception(exception);
	}

}
