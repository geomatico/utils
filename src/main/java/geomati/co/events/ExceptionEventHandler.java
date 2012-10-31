package geomati.co.events;

import geomati.co.events.ExceptionEvent.Severity;

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
	 * @param The
	 *            exception.
	 */
	void exception(Severity severity, String message, Throwable t);
}
