package services.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public final class ServiceContainer {

  private Map<Class<? extends Service>, Service> serviceInstances;

  public ServiceContainer()
  {
    this.serviceInstances = new HashMap<>();
  }

  /**
   * Registers a previously instantiated instance of a
   * {@link Service} to this container, if one was not already
   * registered.
   *
   * @param serviceInstance The instance of the {@link Service}.
   * @param <T> The type of the {@link Service}.
   */
  public <T extends Service> void registerInstance(T serviceInstance) {
    this.serviceInstances.putIfAbsent(serviceInstance.getClass(), serviceInstance);
  }

  /**
   * Gets an instance of a {@link Service}. If a {@link Service}
   * was previously registered, will simply return the previously
   * registered instance.
   *
   * Otherwise, will attempt to instantiate
   * the specified {@link Service} through a constructor marked with
   * {@link ServiceConstructor}, register the new instance, and return
   * the new instance.
   *
   * @param serviceClass The class of the {@link Service} to instantiate.
   * @param <T> The type of the {@link Service} to instantiate.
   * @return An instance of a {@link Service}, or null if the {@link Service}
   *         is not previously present or unable to be instantiated.
   */
  @SuppressWarnings("unchecked")
  public <T extends Service> T getInstance(Class<T> serviceClass) {
    // Return the cached instance
    if (this.serviceInstances.containsKey(serviceClass)) {
      return (T)this.serviceInstances.get(serviceClass);
    }

    // Instantiate a new instance, cache it, then return it.
    T newInstance = this.instantiateNewFromExistingMembers(serviceClass);
    this.serviceInstances.put(serviceClass, newInstance);
    return newInstance;
  }

  /**
   * Instantiates an instance of a {@link Service} using its
   * {@link ServiceConstructor} marked constructor.
   *
   * This method will recursively resolve dependencies specified in the
   * constructor so long as each dependency can be instantiated through
   * its {@link ServiceConstructor}.
   *
   * Attempting to instantiate a {@link Service} with a circular dependency
   * will throw StackOverflowError.
   *
   * @throws StackOverflowError if the class attempting to be instantiated has a circular dependency.
   * @see ServiceConstructor
   * @param serviceClass The class of the {@link Service} to instantiate.
   * @param <T> The type of class of the {@link Service} to instantiate.
   * @return An instance of the specified class if instantiation is successful,
   *         or null otherwise.
   */
  @SuppressWarnings("unchecked")
  private <T extends Service> T instantiateNewFromExistingMembers(Class<T> serviceClass) {
      // Get the ServiceConstructor
      return Stream.of(serviceClass.getConstructors())
          .filter(c -> c.getAnnotation(ServiceConstructor.class) != null)
          .findFirst()
          .map(c -> {

            // Get an instance of all its dependent services.
            Object[] componentServices = Stream.of(c.getParameterTypes())
                .map(parameter -> this.getInstance((Class<? extends Service>) parameter))

                // We will ignore any nulls or any non-Service dependencies
                // ServiceConstructor constructors are assumed to be
                // 'pure' in that they contain either no dependencies, or purely
                // Service dependencies.

                // This is to ensure that there is no undefined behavior as a result
                // of initialization of default value of boxed primitives
                // and null values when constructing the object.

                .filter(Objects::nonNull)
                .filter(Service.class::isInstance)
                .toArray();

            try {
              // Forcibly expose the ServiceConstructor regardless of access modifiers.
              c.setAccessible(true);

              // This will fail if there were any nulls or non-Service dependencies
              // when attempting to resolve services above.
              return (T) c.newInstance(componentServices);
            } catch (IllegalArgumentException
                | ReflectiveOperationException ex) {
              // Either the arguments are mismatched (non-'pure' constructor),
              // or some other problem with the reflective access occurred.
              return null;
            }
          }).orElse(null);
  }
}
