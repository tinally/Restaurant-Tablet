package tests;

import org.junit.Assert;
import org.junit.Test;
import edu.toronto.csc207.restaurantsolution.services.IngredientListService;
import edu.toronto.csc207.restaurantsolution.services.MenuItemsListService;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

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
