package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
  Service to resolve file resources in the resource folder.
 */
public final class ResourceResolverService extends Service {

  /**
   * Service Constructor to instantiate a {@link ResourceResolverService} from
   * {@link services.framework.ServiceContainer}.
   */
  @ServiceConstructor
  public ResourceResolverService() {

  }

  /**
   * Gets a resource as an {@link InputStream}
   * @param resourceName The file name of the resource.
   * @return The resource as a {@link InputStream}
   */
  public InputStream getResource(String resourceName) {
    return this.getClass().getClassLoader().getResourceAsStream( resourceName);
  }

  /**
   * Get a YAML resource and deserialize it with the default object mapper as
   * an object.
   * @param resourceName The file name of the resource.
   * @param outputClass The type of the class to deserialize as.
   * @param <T> The type parameter of the target class.
   * @return The 
   * @throws IOException
   */
  public <T> T getYamlDeserializedResource(String resourceName, Class<T> outputClass) throws IOException {
    InputStream yamlData = this.getResource(resourceName);
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    return mapper.readValue(yamlData, outputClass);
  }


  public <TObject, TCollection extends Collection<TObject>>
  TCollection getYamlDeserializedCollectionResource(String resourceName,
                                                     Class<TCollection> collectionClass,
                                                     Class<TObject> outputClass) throws IOException {
    InputStream yamlData = this.getResource(resourceName);
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    return mapper.readValue(yamlData, mapper.getTypeFactory()
        .constructCollectionType(collectionClass, outputClass));
  }
}
