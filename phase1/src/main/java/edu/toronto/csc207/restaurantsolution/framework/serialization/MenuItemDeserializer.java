package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.services.MenuItemsListService;

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
