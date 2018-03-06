package services;

import com.fasterxml.jackson.annotation.JsonProperty;
import kitchen.Ingredient;
import restaurant.MenuItem;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MenuItemsListService extends Service {

  private static class IntermediateMenuItem {
    @JsonProperty("name") String name;
    @JsonProperty("ingredients") Map<String, Integer> ingredients;
    @JsonProperty("price") Double price;
  }

  private List<MenuItem> menuItems;

  // TODO: Optimize this with a custom serializer?
  @SuppressWarnings("unchecked")
  @ServiceConstructor
  public MenuItemsListService(ResourceResolverService resources,
                              IngredientListService ingredientListService) {
    try {
      List<IntermediateMenuItem> intermediateMenuItems =
          resources.getYamlDeserializedCollectionResource("menuitems.yml",
              List.class,
              IntermediateMenuItem.class);
      this.menuItems = new ArrayList<>();
      for (IntermediateMenuItem menuItem : intermediateMenuItems) {
        Map<Ingredient, Integer> ingredientMapping = new HashMap<>();
        for (Map.Entry<String, Integer> ingredient : menuItem.ingredients.entrySet()) {
          ingredientMapping.put(ingredientListService.getIngredient(ingredient.getKey()), ingredient.getValue());
        }
        this.menuItems.add(new MenuItem(menuItem.name, ingredientMapping, menuItem.price));
      }
    } catch (IOException e) {
      this.menuItems = new ArrayList<>();
    }
  }

  public List<MenuItem> getMenuItems() {
    return Collections.unmodifiableList(menuItems);
  }

  // TODO: Optimize this with a HashMap?
  public MenuItem getMenuItem(String name) {
    return menuItems.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
  }
}
