package tests;

import kitchen.Ingredient;
import org.junit.Assert;
import org.junit.Test;
import services.IngredientListService;
import services.MenuItemsListService;
import services.framework.ServiceContainer;

public class ResourceListTests {

  @Test
  public void testIngredientsListService() {
    ServiceContainer container = new ServiceContainer();
    IngredientListService ingredients = container.getInstance(IngredientListService.class);
    Assert.assertFalse(ingredients.getIngredients().isEmpty());
  }

  @Test
  public void testMenuItemListService() {
    ServiceContainer container = new ServiceContainer();
    MenuItemsListService menuItems = container.getInstance(MenuItemsListService.class);
    Assert.assertFalse(menuItems.getMenuItems().isEmpty());
  }
}
