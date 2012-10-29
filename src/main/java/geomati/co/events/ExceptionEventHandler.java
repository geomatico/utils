package geomati.co.events;

/**
 * Handler for exception management.
 */
public interface ExceptionEventHandler extends EventHandler {
	/**
	 * Manages an exception error
	 * 
	 * @param message
	 *            The error message.
	 */
	void error(String message);
}
