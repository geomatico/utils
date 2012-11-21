package geomati.co.events;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import geomati.co.events.EventBus;
import geomati.co.events.ExceptionEvent;
import geomati.co.events.ExceptionEventHandler;
import junit.framework.TestCase;

import org.mockito.Mockito;

public class EventBusTest extends TestCase {

	public void testSimpleEvent() {
		ExceptionEventHandler handler = mock(ExceptionEventHandler.class);
		Exception exception = mock(Exception.class);
		ExceptionEvent event = new ExceptionEvent(exception);

		EventBus.getInstance().addHandler(ExceptionEvent.class, handler);
		EventBus.getInstance().fireEvent(event);
		verify(handler).exception(exception);
	}

	public void testMultipleSubscriptionOneDispatch() {
		ExceptionEventHandler handler = mock(ExceptionEventHandler.class);
		Exception exception = mock(Exception.class);
		ExceptionEvent event = new ExceptionEvent(exception);

		EventBus.getInstance().addHandler(ExceptionEvent.class, handler);
		EventBus.getInstance().addHandler(ExceptionEvent.class, handler);
		EventBus.getInstance().addHandler(ExceptionEvent.class, handler);

		EventBus.getInstance().fireEvent(event);
		verify(handler).exception(exception);
	}

	public void testSeveralEvents() {
		ExceptionEventHandler handler = mock(ExceptionEventHandler.class);
		Exception exception = mock(Exception.class);
		ExceptionEvent event = new ExceptionEvent(exception);

		EventBus.getInstance().addHandler(ExceptionEvent.class, handler);
		EventBus.getInstance().addHandler(ExceptionEvent.class, handler);

		EventBus.getInstance().fireEvent(event);
		EventBus.getInstance().fireEvent(event);
		EventBus.getInstance().fireEvent(event);
		verify(handler, times(3)).exception(exception);
	}
	
	public synchronized void testGarbageCollectedHandler() throws Exception {
		ExceptionEventHandler handler1 = mock(ExceptionEventHandler.class);
		ExceptionEventHandler handler2 = mock(ExceptionEventHandler.class);
		Exception exception = mock(Exception.class);
		ExceptionEvent event = new ExceptionEvent(exception);

		EventBus.getInstance().addHandler(ExceptionEvent.class, handler1);
		EventBus.getInstance().addHandler(ExceptionEvent.class, handler2);

		EventBus.getInstance().fireEvent(event);
		verify(handler1).exception(exception);
		verify(handler2).exception(exception);
		
		Mockito.doThrow(RuntimeException.class).when(handler2).exception(any(Throwable.class));
		handler2 = null;
		Runtime.getRuntime().gc();
		
		EventBus.getInstance().fireEvent(event);
	}
}
