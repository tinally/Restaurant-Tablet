package edu.toronto.csc207.restaurantsolution.services;

import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

import java.io.InputStream;

/**
 * A {@link Service} to resolve file resources in the resource folder.
 */
public final class ResourceResolverService extends Service {
  /**
   * Service Constructor to instantiate a {@link ResourceResolverService} from
   * {@link ServiceContainer}.
   */
  @ServiceConstructor
  public ResourceResolverService() {

  }

  /**
   * Gets a resource as an {@link InputStream}
   *
   * @param resourceName The file name of the resource.
   * @return The resource as a {@link InputStream}
   */
  public InputStream getResource(String resourceName) {
    return this.getClass().getClassLoader().getResourceAsStream(resourceName);
  }
}
