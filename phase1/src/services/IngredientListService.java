package services;

import kitchen.Ingredient;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IngredientListService extends Service {

  private List<Ingredient> ingredients;

  @SuppressWarnings("unchecked")
  @ServiceConstructor
  public IngredientListService(ResourceResolverService resources) {
    try {
      this.ingredients =
          resources.getYamlDeserializedCollectionResource("ingredients.yml", List.class, Ingredient.class);
    } catch (IOException e) {
      e.printStackTrace();
      this.ingredients = new ArrayList<>();
    }
  }

  public List<Ingredient> getIngredients() {
    return Collections.unmodifiableList(ingredients);
  }

  // TODO: Optimize this with a HashMap?
  public Ingredient getIngredient(String name) {
    return ingredients.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
  }
}
