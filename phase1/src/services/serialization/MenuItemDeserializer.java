package services.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import kitchen.Ingredient;
import restaurant.MenuItem;
import services.MenuItemsListService;

import java.io.IOException;

public class MenuItemDeserializer extends StdDeserializer<MenuItem> {

  private MenuItemsListService loadedMenuItems;

  MenuItemDeserializer(MenuItemsListService loadedMenuItems) {
    this((Class<?>) null);
    this.loadedMenuItems = loadedMenuItems;
  }

  private MenuItemDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public MenuItem deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    String menuItemName = p.getValueAsString();
    return loadedMenuItems.getMenuItem(menuItemName);
  }
}
