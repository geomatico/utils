package geomati.co.events;

/**
 * Interface that must be implemented by all the events managed by the
 * {@link EventBus}.
 * <p>
 * The {@link Event}, {@link EventHandler} and {@link EventBus}
 * classes/interfaces mirror the <a href=
 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/shared/GwtEvent.html"
 * >GWT API</a>
 * 
 * @param <H>
 *            The event handler.
 */
public interface Event<H extends EventHandler> {
	/**
	 * Copied from the <a href=
	 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/shared/GwtEvent.html#dispatch%28H%29"
	 * >GWT API</a>:
	 * <p>
	 * <i>Should only be called by {@link EventBus}. In other words, do not use
	 * or call.</i>
	 * 
	 * @param handler
	 *            handler
	 */
	void dispatch(H handler);

	/**
	 * Copied from the <a href=
	 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/shared/GwtEvent.html#getAssociatedType%28%29"
	 * >GWT API</a>:
	 * <p>
	 * <i>Returns the type used to register this event. Used by handler manager
	 * to dispatch events to the correct handlers.</i>
	 * 
	 * @return <i>the type</i>
	 * 
	 */
	Type<H> getAssociatedType();

	/**
	 * Copied from the <a href=
	 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.0/com/google/gwt/event/shared/GwtEvent.Type.html"
	 * >GWT API</a>:
	 * <p>
	 * <i>Type class used to register events with the {@link EventBus}.
	 * <p>
	 * Type is parameterized by the handler type in order to make the addHandler
	 * method type safe. </i>
	 * 
	 * @param <H>
	 *            The event handler.
	 */
	class Type<H> {
	}
}