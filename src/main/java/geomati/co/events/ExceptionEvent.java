package geomati.co.events;

/**
 * Event for exception management.
 */
public class ExceptionEvent implements Event<ExceptionEventHandler> {
	public static final Type<ExceptionEventHandler> TYPE = new Type<ExceptionEventHandler>();
	private String message;

	public ExceptionEvent(String message) {
		this.message = message;
	}

	@Override
	public void dispatch(ExceptionEventHandler handler) {
		handler.error(message);
	}

	@Override
	public Type<ExceptionEventHandler> getAssociatedType() {
		return TYPE;
	}
}
