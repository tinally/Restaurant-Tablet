package edu.toronto.csc207.restaurantsolution.framework.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A ServiceContainer serves as the context when initializing
 * {@link Service} instances.
 * <p>
 * The ServiceContainer is responsible for resolving all dependencies
 * automatically of requested {@link Service} instances. It also ensures
 * that there is only one instance of a given {@link Service} per container,
 * and that each subsequent request of an instantiated {@link Service}
 * provides the same instance.
 * <p>
 * In essence, the ServiceContainer encapsulates singleton objects
 * into a single context, and handles all instantiation of such
 * singletons within the context.
 */
public final class ServiceContainer {
  /**
   * The instances of each {@link Service}.
   */
  private Map<Class<? extends Service>, Service> serviceInstances;

  /**
   * Class constructor for {@link ServiceContainer}.
   */
  public ServiceContainer() {
    this.serviceInstances = new HashMap<>();
  }

  /**
   * Registers a previously instantiated instance of a
   * {@link Service} to this container, if one was not already
   * registered.
   *
   * @param serviceInstance The instance of the {@link Service}.
   * @param <T>             The type of the {@link Service}.
   */
  public <T extends Service> void registerInstance(T serviceInstance) {
    this.serviceInstances.putIfAbsent(serviceInstance.getClass(), serviceInstance);
  }

  /**
   * Gets an instance of a {@link Service}. If a {@link Service}
   * was previously registered, will simply return the previously
   * registered instance.
   * <p>
   * Otherwise, will attempt to instantiate
   * the specified {@link Service} through a constructor marked with
   * {@link ServiceConstructor}, register the new instance, and return
   * the new instance.
   *
   * @param serviceClass The class of the {@link Service} to instantiate.
   * @param <T>          The type of the {@link Service} to instantiate.
   * @return An instance of a {@link Service}, or null if the {@link Service}
   * is not previously present or unable to be instantiated.
   */
  @SuppressWarnings("unchecked")
  public <T extends Service> T getInstance(Class<T> serviceClass) {
    // Return the cached instance
    if (this.serviceInstances.containsKey(serviceClass)) {
      return (T) this.serviceInstances.get(serviceClass);
    }

    // Instantiate a new instance, cache it, then return it.
    T newInstance = this.instantiateNewFromExistingMembers(serviceClass);
    this.serviceInstances.put(serviceClass, newInstance);
    return newInstance;
  }

  /**
   * Instantiates an instance of a {@link Service} using its
   * {@link ServiceConstructor} marked constructor.
   * <p>
   * This method will recursively resolve dependencies specified in the
   * constructor so long as each dependency can be instantiated through
   * its {@link ServiceConstructor}.
   * <p>
   * Attempting to instantiate a {@link Service} with a circular dependency
   * will throw StackOverflowError.
   *
   * @param serviceClass The class of the {@link Service} to instantiate.
   * @param <T>          The type of class of the {@link Service} to instantiate.
   * @return An instance of the specified class if instantiation is successful,
   * or null otherwise.
   * @throws StackOverflowError if the class attempting to be instantiated has a circular dependency.
   * @see ServiceConstructor
   */
  @SuppressWarnings("unchecked")
  private <T extends Service> T instantiateNewFromExistingMembers(Class<T> serviceClass) {
    // Get the ServiceConstructor
    return Stream.of(serviceClass.getConstructors())
        .filter(constructor -> {
          // Forcibly expose the ServiceConstructor regardless of access modifiers.
          constructor.setAccessible(true);
          return constructor.getAnnotation(ServiceConstructor.class) != null;
        })
        .findFirst()
        .map(constructor -> {

          // Get an instance of all its dependent services.
          Object[] componentServices = Stream.of(constructor.getParameterTypes())
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
            // This will fail if there were any nulls or non-Service dependencies
            // when attempting to resolve services above.
            return (T) constructor.newInstance(componentServices);
          } catch (IllegalArgumentException
              | ReflectiveOperationException ex) {
            // Either the arguments are mismatched (non-'pure' constructor),
            // or some other problem with the reflective access occurred.
            return null;
          }
        }).orElse(null);
  }
}
