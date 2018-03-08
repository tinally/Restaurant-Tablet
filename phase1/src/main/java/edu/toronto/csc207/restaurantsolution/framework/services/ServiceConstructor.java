package edu.toronto.csc207.restaurantsolution.framework.services;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a constructor to be used when instantiating a {@link Service}
 * through a {@link ServiceContainer}.
 * <p>
 * A constructor marked by this attribute must be marked as public
 * in order for instantiation to succeed.
 * <p>
 * Any constructors marked by this attribute must take in either no
 * parameters, or only parameters of objects that extend {@link Service}.
 * <p>
 * A constructor marked by this attribute that takes in a parameter that
 * does not extend {@link Service} will fail to be instantiated by
 * {@link ServiceContainer#getInstance(Class)}.
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceConstructor {
}
