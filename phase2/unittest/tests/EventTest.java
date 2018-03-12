package tests;

import org.junit.Assert;
import org.junit.Test;
import edu.toronto.csc207.restaurantsolution.framework.events.EventEmitter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EventTest {

  @Test
  public void testEventHandling() {
    AtomicBoolean eventRaised = new AtomicBoolean(false);
    EventEmitter emitter = new EventEmitter();
    OrderInputStringEvent event = new OrderInputStringEvent("My Order!");
    emitter.registerEventHandler(
        (e) -> {
          Assert.assertEquals(e.getOrder(), "My Order!");
          eventRaised.set(true);
        },
        OrderInputStringEvent.class);
    emitter.onEvent(event);
    Assert.assertTrue(eventRaised.get());
   }


  @Test
  public void testMultipleEventHandling() {
    AtomicInteger eventRaisedCount = new AtomicInteger(0);
    EventEmitter emitter = new EventEmitter();
    OrderInputStringEvent event = new OrderInputStringEvent("My Order!");
    emitter.registerEventHandler(
        (e) -> {
          Assert.assertEquals(e.getOrder(), "My Order!");
          eventRaisedCount.incrementAndGet();
        },
        OrderInputStringEvent.class);
    emitter.registerEventHandler(
        (e) -> {
          Assert.assertEquals(e.getOrder(), "My Order!");
          eventRaisedCount.incrementAndGet();
        },
        OrderInputStringEvent.class);
    emitter.onEvent(event);
    Assert.assertEquals(eventRaisedCount.get(),2);
  }

}
