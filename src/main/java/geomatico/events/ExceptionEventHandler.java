package geomatico.events;

import geomatico.events.ExceptionEvent.Severity;

/**
 * Handler for exception management.
 */
public interface ExceptionEventHandler extends EventHandler {
	/**
	 * Manages an exception error.
	 * 
	 * @param severity
	 *            The severity of the message.
	 * @param message
	 *            The error message.
	 * @param t
	 *            exception.
	 */
	void exception(Severity severity, String message, Throwable t);
}
