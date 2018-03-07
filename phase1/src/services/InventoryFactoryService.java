package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventEmitter;
import kitchen.Ingredient;
import kitchen.Inventory;
import services.framework.Service;
import services.framework.ServiceConstructor;
import services.serialization.YamlDeserializerService;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InventoryFactoryService extends Service {

  private final Inventory inventory;
  @ServiceConstructor
  public InventoryFactoryService(EventEmitter em,
                                 YamlDeserializerService yds,
                                 ResourceResolverService rrs) {
    ObjectMapper mapper = yds.getMapper();
    InputStream inventoryConfig = rrs.getResource("inventory.yml");
    Map<Ingredient, Integer> initialInventory = new HashMap<>();
    try {
      initialInventory =  mapper.readValue(inventoryConfig, mapper.getTypeFactory()
          .constructMapType(HashMap.class, Ingredient.class, Integer.class));
    } catch (IOException ignored) {
      ignored.printStackTrace();
    }
    inventory = new Inventory(em, initialInventory);
  }

  public Inventory getInventory() {
    return inventory;
  }
}
