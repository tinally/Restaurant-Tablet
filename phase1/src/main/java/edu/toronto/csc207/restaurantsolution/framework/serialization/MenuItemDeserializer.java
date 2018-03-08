package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.services.MenuItemsListService;

import java.io.IOException;

/**
 * Jackson deserializer for representing previously loaded {@link MenuItem}
 * instances as strings in configuration files.
 * <p>
 * Simply maps a string as a key to the name of an existing MenuItem
 * from {@link MenuItemsListService}
 * <p>
 * Mostly used when deserializing events.txt
 */
public class MenuItemDeserializer extends StdDeserializer<MenuItem> {

  /**
   * The list of services with MenuItems.
   */
  private MenuItemsListService loadedMenuItems;

  /**
   * Class constructor specifying loadedMenuItems.
   *
   * @param loadedMenuItems list of services with MenuItems
   */
  MenuItemDeserializer(MenuItemsListService loadedMenuItems) {
    this((Class<?>) null);
    this.loadedMenuItems = loadedMenuItems;
  }

  /***
   * Default constructor.
   * @param vc the class to deserialize as
   */
  private MenuItemDeserializer(Class<?> vc) {
    super(vc);
  }

  /**
   * Creates an instance of MenuItem from p and ctxt.
   *
   * @param p    parsing the file
   * @param ctxt context to be deserialized
   * @return an instance of MenuItem
   * @throws IOException             halts the program
   * @throws JsonProcessingException a more general exception
   */
  @Override
  public MenuItem deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    String menuItemName = p.getValueAsString();
    return loadedMenuItems.getMenuItem(menuItemName);
  }
}
