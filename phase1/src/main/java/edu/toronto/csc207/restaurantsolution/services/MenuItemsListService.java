package edu.toronto.csc207.restaurantsolution.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Contains the list of {@link MenuItem} instances that
 * consist of the menu of the restaurant.
 */
public class MenuItemsListService extends Service {
  /**
   * The list of menu items to store.
   */
  private List<MenuItem> menuItems;

  /**
   * ServiceConstructor to instantiate a MenuItemsListService
   *
   * @param resources             {@link ResourceResolverService} dependency to be
   *                              resolved by the {@link ServiceContainer};
   * @param ingredientListService {@link IngredientListService} dependency to be
   *                              resolved by the {@link ServiceContainer};
   */
  // TODO: Optimize this with a custom serializer?
  @SuppressWarnings("unchecked")
  @ServiceConstructor
  public MenuItemsListService(ResourceResolverService resources,
                              IngredientListService ingredientListService) {
    try {
      // We're going to deserialize menuitems.yml into
      // a list of IntermediateMenuItems.
      ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
      InputStream menuItemStream = resources.getResource("menuitems.yml");
      List<IntermediateMenuItem> intermediateMenuItems = objectMapper.readValue(menuItemStream,
          objectMapper.getTypeFactory().constructCollectionType(List.class, IntermediateMenuItem.class));

      // Instatiate an array list to store the proper menu items.
      this.menuItems = new ArrayList<>();

      // Transform each IntermediateMenuItem into an actual MenuItem.
      for (IntermediateMenuItem menuItem : intermediateMenuItems) {
        Map<Ingredient, Integer> ingredientMapping = new HashMap<>();
        for (Map.Entry<String, Integer> ingredient : menuItem.ingredients.entrySet()) {
          ingredientMapping.put(ingredientListService.getIngredient(ingredient.getKey()), ingredient.getValue());
        }
        this.menuItems.add(new MenuItem(menuItem.name, ingredientMapping, menuItem.price));
      }
    } catch (IOException e) {

      // If something happens, then just give an empty menu.
      this.menuItems = new ArrayList<>();
    }
  }

  /**
   * Returns a read-only view over all the items of the menu.
   *
   * @return A read-only view over all the items of the menu.
   */
  public List<MenuItem> getMenuItems() {
    return Collections.unmodifiableList(menuItems);
  }

  /**
   * Get the menu item with the given name.
   *
   * @param name The case-insensitive name of the menu item.
   * @return The requested menu item, or null if it does not exist.
   */
  // TODO: Optimize this with a HashMap?
  public MenuItem getMenuItem(String name) {
    return menuItems.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
  }

  /**
   * A representation of a menu item with
   * purely boxed primitive, serializable types
   * used for deserialization from menuitems.yml
   */
  private static class IntermediateMenuItem {
    @JsonProperty("name")
    String name;
    @JsonProperty("ingredients")
    Map<String, Integer> ingredients;
    @JsonProperty("price")
    Double price;
  }
}
