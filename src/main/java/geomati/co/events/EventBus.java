package geomati.co.events;

import geomati.co.events.Event.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that dispatches all {@link Event}s to interested parties.
 * <p>
 * The {@link Event}, {@link EventHandler} and {@link EventBus}
 * classes/interfaces mirror the <a href=
 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.1/com/google/gwt/event/shared/EventBus.html"
 * >GWT API</a>
 * 
 * @param <H>
 *            The event handler.
 */
public class EventBus {
	private static final EventBus instance = new EventBus();

	/**
	 * Singleton
	 * 
	 * @return singleton
	 */
	public static EventBus getInstance() {
		return instance;
	}

	private EventBus() {
	}

	private Map<Type<?>, Set<EventHandler>> map = new HashMap<Event.Type<?>, Set<EventHandler>>();

	/**
	 * Copied from the <a href=
	 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.1/com/google/gwt/event/shared/EventBus.html#addHandler%28com.google.gwt.event.shared.GwtEvent.Type,%20H%29"
	 * >GWT API</a>:
	 * <p>
	 * <i>Adds an unfiltered handler to receive events of this type from all
	 * sources.</i>
	 * 
	 * @param type
	 *            <i>the event type associated with this handler</i>
	 * @param handler
	 *            <i>the handler</i>
	 */
	public synchronized <H extends EventHandler> void addHandler(Type<H> type,
			H handler) {
		Set<EventHandler> handlers = map.get(type);
		if (handlers == null) {
			handlers = new HashSet<EventHandler>();
			map.put(type, handlers);
		}

		handlers.add(handler);
	}

	/**
	 * Copied from the <a href=
	 * "http://google-web-toolkit.googlecode.com/svn/javadoc/2.1/com/google/gwt/event/shared/EventBus.html#fireEvent%28com.google.gwt.event.shared.GwtEvent%29"
	 * >GWT API</a>:
	 * <p>
	 * <i>Fires the event from no source. Only unfiltered handlers will receive
	 * it.</i>
	 * 
	 * @param event
	 *            <i>the event to fire</i>
	 */
	@SuppressWarnings("unchecked")
	public synchronized <H extends EventHandler> void fireEvent(Event<H> event) {
		Set<EventHandler> handlers = map.get(event.getAssociatedType());
		for (EventHandler handler : handlers) {
			event.dispatch((H) handler);
		}
	}
}
