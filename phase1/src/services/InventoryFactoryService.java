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

/**
 * A {@link Service} that provides an {@link Inventory} from
 * initial values seeded by inventory.yml
 */
public class InventoryFactoryService extends Service {

    /**
     * The {@link Inventory} instance to cache.
     */
    private final Inventory inventory;

    /**
     * ServiceConstructor to instantiate a InventoryFactoryService
     *
     * @param emitter      {@link EventEmitter} dependency intended
     *                     to be resolved by the {@link services.framework.ServiceContainer}
     * @param deserializer {@link YamlDeserializerService} dependency intended
     *                     to be resolved by the {@link services.framework.ServiceContainer}
     * @param resource     {@link ResourceResolverService} dependency intended
     *                     to be resolved by the {@link services.framework.ServiceContainer}
     */
    @ServiceConstructor
    public InventoryFactoryService(EventEmitter emitter,
                                   YamlDeserializerService deserializer,
                                   ResourceResolverService resource) {


        // load the initial inventory from inventory.yl
        ObjectMapper mapper = deserializer.getMapper();
        InputStream inventoryConfig = resource.getResource("inventory.yml");
        Map<Ingredient, Integer> initialInventory = new HashMap<>();


        try {
            initialInventory = mapper.readValue(inventoryConfig, mapper.getTypeFactory()
                    .constructMapType(HashMap.class, Ingredient.class, Integer.class));
        } catch (IOException ignored) {
        }

        inventory = new Inventory(emitter, initialInventory);
    }

    /**
     * Gets the instantiated inventory instance.
     *
     * @return The inventory instance.
     */
    public Inventory getInventory() {
        return inventory;
    }
}
