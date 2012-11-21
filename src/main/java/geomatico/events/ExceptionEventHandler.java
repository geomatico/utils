package geomatico.events;

/**
 * Handler for exception management.
 */
public interface ExceptionEventHandler extends EventHandler {
	/**
	 * Manages an exception error.
	 * 
	 * @param t
	 *            exception.
	 */
	void exception(Throwable t);
}
