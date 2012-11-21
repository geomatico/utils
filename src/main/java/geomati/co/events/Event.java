package geomati.co.events;

/**
 * Interface that must be implemented by all the events managed by the
 * {@link EventBus}.
 */
public interface Event<H extends EventHandler> {
	/**
	 * Should only be called by {@link EventBus}
	 * 
	 * @param handler
	 *            handler
	 */
	void dispatch(H handler);

}