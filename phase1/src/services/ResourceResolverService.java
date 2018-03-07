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
  A {@link Service} to resolve file resources in the resource folder.
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
}
