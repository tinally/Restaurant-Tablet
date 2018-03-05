import services.IngredientListService;
import services.framework.ServiceContainer;

public class Main {
  public static void main(String[] args) {
    ServiceContainer container = new ServiceContainer();
    IngredientListService ingredients = container.getInstance(IngredientListService.class);
  }
}
