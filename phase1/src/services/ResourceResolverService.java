package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import services.framework.Service;
import services.framework.ServiceConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public final class ResourceResolverService extends Service {

  @ServiceConstructor
  public ResourceResolverService() {

  }

  public InputStream getResource(String resourceName) {
    return this.getClass().getClassLoader().getResourceAsStream( resourceName);
  }

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
