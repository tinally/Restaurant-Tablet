package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import events.EventArgs;
import events.EventEmitter;
import kitchen.Ingredient;
import kitchen.Order;
import org.junit.Assert;
import org.junit.Test;
import restaurant.MenuItem;
import services.ResourceResolverService;
import services.framework.ServiceContainer;
import services.serialization.YamlDeserializerService;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class YamlSerializationTests {
//  @Test
//  public void testEventDeserialization() {
//    ServiceContainer container = new ServiceContainer();
//    ResourceResolverService rrs = container.getInstance(ResourceResolverService.class);
//    YamlDeserializerService yds = container.getInstance(YamlDeserializerService.class);
//    ObjectMapper mapper = yds.getMapper();
//    AtomicBoolean eventsTriggered = new AtomicBoolean(false);
//    EventEmitter em = container.getInstance(EventEmitter.class);
//    em.registerEventHandler((e, s) -> {
//      try {
//        System.out.println(mapper.writeValueAsString(e));
//      } catch (JsonProcessingException e1) {
//        e1.printStackTrace();
//      }
//      eventsTriggered.set(true);
//    }, OrderInputEvent.class);

//    try {
//      List<EventArgs> events = mapper.readValue(rrs.getResource("events.yml"),
//          mapper.getTypeFactory().constructCollectionType(List.class, EventArgs.class));
//      events.forEach(e -> em.onEvent(e, null));
//    } catch (IOException e) {
//      e.printStackTrace();
//      Assert.fail();
//    }
//    Assert.assertTrue(eventsTriggered.get());

  @Test
  public void testMenuItemContainingSerializable() {
    ServiceContainer container = new ServiceContainer();
    YamlDeserializerService yds = container.getInstance(YamlDeserializerService.class);
    ObjectMapper mapper = yds.getMapper();
    String menuItem = "menuItem: Bread Sandwich";
    try {
      SomeClassWithMenuItem object = mapper.readValue(menuItem, SomeClassWithMenuItem.class);
      Assert.assertEquals("Bread Sandwich", object.menuItem.getName());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class SomeClassWithMenuItem {
    MenuItem menuItem;
    public SomeClassWithMenuItem() {

    }

    public void setMenuItem(MenuItem menuItem) {
      this.menuItem = menuItem;
    }
  }
}