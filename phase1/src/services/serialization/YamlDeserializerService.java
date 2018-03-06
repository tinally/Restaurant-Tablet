package services.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import events.EventArgs;
import events.simulator.EventDeserializer;
import kitchen.Ingredient;
import restaurant.MenuItem;
import services.IngredientListService;
import services.MenuItemsListService;
import services.framework.Service;
import services.framework.ServiceConstructor;

/**
 * Provides a Jackson {@link com.fasterxml.jackson.databind.ObjectMapper}
 * that is pre-configured to serialize events that nest {@link restaurant.MenuItem}
 * or {@link kitchen.Ingredient}.
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
    mapper.registerModule(module);

    SimpleModule eventArgsModule = new SimpleModule();
    eventArgsModule.addDeserializer(EventArgs.class, new EventDeserializer(this));
    mapper.registerModule(eventArgsModule);
  }

  public ObjectMapper getMapper() {
    return this.mapper;
  }
}
