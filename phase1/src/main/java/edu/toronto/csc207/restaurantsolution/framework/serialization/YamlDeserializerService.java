package edu.toronto.csc207.restaurantsolution.framework.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.toronto.csc207.restaurantsolution.data.Ingredient;
import edu.toronto.csc207.restaurantsolution.data.MenuItem;
import edu.toronto.csc207.restaurantsolution.framework.events.EventArgs;
import edu.toronto.csc207.restaurantsolution.framework.events.EventDeserializer;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.services.IngredientListService;
import edu.toronto.csc207.restaurantsolution.services.MenuItemsListService;

/**
 * Provides a Jackson {@link com.fasterxml.jackson.databind.ObjectMapper}
 * that is pre-configured to serialize events that nest {@link MenuItem}
 * or {@link Ingredient}.
 */
public class YamlDeserializerService extends Service {
  private final ObjectMapper mapper;

  @ServiceConstructor
  public YamlDeserializerService(MenuItemsListService menuItems, IngredientListService ingredients) {
    this.mapper = new ObjectMapper(new YAMLFactory());
    SimpleModule module = new SimpleModule();
    module.addDeserializer(MenuItem.class, new MenuItemDeserializer(menuItems));
    module.addDeserializer(Ingredient.class, new IngredientDeserializer(ingredients));
    module.addKeyDeserializer(Ingredient.class, new IngredientKeyDeserializer(ingredients));
    module.addKeySerializer(Ingredient.class, new IngredientKeySerializer(null));
    mapper.registerModule(module);

    // We need to instantiate the EventDeserializer separately. Perhaps we can take this out of the service?
    SimpleModule eventArgsModule = new SimpleModule();
    eventArgsModule.addDeserializer(EventArgs.class, new EventDeserializer(this));
    mapper.registerModule(eventArgsModule);
  }

  public ObjectMapper getMapper() {
    return this.mapper;
  }
}
