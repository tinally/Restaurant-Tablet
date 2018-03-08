package edu.toronto.csc207.restaurantsolution.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains the list of {@link IngredientListService} instances that
 * consist of the menu of the restaurant.
 */
public class IngredientListService extends Service {

    /**
     * The list of inigredients to store.
     */
    private List<Ingredient> ingredients;

<<<<<<< HEAD:phase1/src/services/IngredientListService.java
    /**
     * ServiceConstructor to instantiate a MenuItemsListService
     *
     * @param resources {@link ResourceResolverService} dependency to be
     *                  resolved by the {@link services.framework.ServiceContainer};
     */
    @SuppressWarnings("unchecked")
    @ServiceConstructor
    public IngredientListService(ResourceResolverService resources) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            InputStream ingredientsStream = resources.getResource("ingredients.yml");
            this.ingredients = objectMapper.readValue(ingredientsStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Ingredient.class));
        } catch (IOException e) {
            this.ingredients = new ArrayList<>();
        }
=======
  /**
   * ServiceConstructor to instantiate a MenuItemsListService
   * @param resources {@link ResourceResolverService} dependency to be
   *                  resolved by the {@link ServiceContainer};
   */
  @SuppressWarnings("unchecked")
  @ServiceConstructor
  public IngredientListService(ResourceResolverService resources) {
    try {
      ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
      InputStream ingredientsStream = resources.getResource("ingredients.yml");
      this.ingredients = objectMapper.readValue(ingredientsStream,
          objectMapper.getTypeFactory().constructCollectionType(List.class, Ingredient.class));
    } catch (IOException e) {
      this.ingredients = new ArrayList<>();
>>>>>>> 6c2ba67e6d43c990817f7455d710287af5146462:phase1/src/main/java/edu/toronto/csc207/restaurantsolution/services/IngredientListService.java
    }

    /**
     * Gets a read-only view on all the ingredients loaded.
     *
     * @return A read-only view on all the ingredients loaded.
     */
    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    /**
     * Gets an ingredient with the given name.
     *
     * @param name The name of the ingredient.
     * @return The ingredient with the given name, or null if it does not exist.
     */
    // TODO: Optimize this with a HashMap?
    public Ingredient getIngredient(String name) {
        return ingredients.stream().filter(i -> i.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
