package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventEmitter;
import events.newevents.BillPrintEvent;
import org.junit.Assert;
import org.junit.Test;
import restaurant.MenuItem;
import services.EventDriverService;
import services.IngredientListService;
import services.MenuItemsListService;
import services.framework.ServiceContainer;
import services.serialization.YamlDeserializerService;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class YamlSerializationTests {

  @Test
  public void testIngredientListSerialization() {
    ServiceContainer container = new ServiceContainer();
    IngredientListService ils = container.getInstance(IngredientListService.class);
    Assert.assertFalse(ils.getIngredients().isEmpty());
  }

  @Test
  public void testMenuItemListSerialization() {
    ServiceContainer container = new ServiceContainer();
    MenuItemsListService mls = container.getInstance(MenuItemsListService.class);
    Assert.assertFalse(mls.getMenuItems().isEmpty());
  }

  @Test
  public void testEventDriverService() {
    ServiceContainer container = new ServiceContainer();
    EventDriverService eds = container.getInstance(EventDriverService.class);
    EventEmitter em = container.getInstance(EventEmitter.class);
    AtomicBoolean eventsWereRun = new AtomicBoolean(false);
    em.registerEventHandler(e -> eventsWereRun.set(true), BillPrintEvent.class);
    eds.run();
    Assert.assertTrue(eventsWereRun.get());
  }

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