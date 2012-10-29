package geomati.co.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import geomati.co.events.Event.Type;
import junit.framework.TestCase;


public class EventBusTest extends TestCase {

	@SuppressWarnings("unchecked")
	public void testSimpleEvent() {
		Type<EventHandler> type = (Type<EventHandler>) mock(Type.class);
		EventHandler handler = mock(EventHandler.class);
		Event<EventHandler> event = (Event<EventHandler>) mock(Event.class);
		when(event.getAssociatedType()).thenReturn(type);

		EventBus.getInstance().addHandler(type, handler);
		EventBus.getInstance().fireEvent(event);
		verify(event).dispatch(handler);
	}

	@SuppressWarnings("unchecked")
	public void testMultipleSubscription() {
		Type<EventHandler> type = (Type<EventHandler>) mock(Type.class);
		EventHandler handler = mock(EventHandler.class);
		Event<EventHandler> event = (Event<EventHandler>) mock(Event.class);
		when(event.getAssociatedType()).thenReturn(type);

		EventBus.getInstance().addHandler(type, handler);
		EventBus.getInstance().addHandler(type, handler);
		EventBus.getInstance().addHandler(type, handler);

		EventBus.getInstance().fireEvent(event);
		verify(event).dispatch(handler);
	}

	@SuppressWarnings("unchecked")
	public void testSeveralEvents() {
		Type<EventHandler> type = (Type<EventHandler>) mock(Type.class);
		EventHandler handler = mock(EventHandler.class);
		Event<EventHandler> event = (Event<EventHandler>) mock(Event.class);
		when(event.getAssociatedType()).thenReturn(type);

		EventBus.getInstance().addHandler(type, handler);
		EventBus.getInstance().addHandler(type, handler);
		EventBus.getInstance().addHandler(type, handler);

		EventBus.getInstance().fireEvent(event);
		EventBus.getInstance().fireEvent(event);
		EventBus.getInstance().fireEvent(event);
		verify(event, times(3)).dispatch(handler);
	}

	public void testExceptionEvents() {
		ExceptionEventHandler handler = mock(ExceptionEventHandler.class);
		EventBus.getInstance().addHandler(ExceptionEvent.TYPE, handler);
		String message = "message";
		EventBus.getInstance().fireEvent(new ExceptionEvent(message));
		verify(handler).error(message);
	}
}
