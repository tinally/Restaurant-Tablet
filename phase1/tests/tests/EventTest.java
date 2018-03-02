package tests;

import org.junit.Assert;
import org.junit.Test;
import events.EventEmitter;
import events.eventtypes.OrderInputEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EventTest {

  @Test
  public void testEventHandling() {
    AtomicBoolean eventRaised = new AtomicBoolean(false);
    EventEmitter emitter = new EventEmitter();
    OrderInputEvent event = new OrderInputEvent("My Order!");
    emitter.registerEventHandler(
        (e, s) -> {
          Assert.assertEquals(e.getOrder(), "My Order!");
          eventRaised.set(true);
        },
        OrderInputEvent.class);
    emitter.onEvent(event, null);
    Assert.assertTrue(eventRaised.get());
   }

  @Test
  public void testMultipleEventHandling() {
    AtomicInteger eventRaisedCount = new AtomicInteger(0);
    EventEmitter emitter = new EventEmitter();
    OrderInputEvent event = new OrderInputEvent("My Order!");
    emitter.registerEventHandler(
        (e, s) -> {
          Assert.assertEquals(e.getOrder(), "My Order!");
          eventRaisedCount.incrementAndGet();
        },
        OrderInputEvent.class);
    emitter.registerEventHandler(
        (e, s) -> {
          Assert.assertEquals(e.getOrder(), "My Order!");
          eventRaisedCount.incrementAndGet();
        },
        OrderInputEvent.class);
    emitter.onEvent(event, null);
    Assert.assertEquals(eventRaisedCount.get(),2);
  }

}