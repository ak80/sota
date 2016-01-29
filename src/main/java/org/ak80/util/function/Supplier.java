package org.ak80.util.function;

/**
 * A Supplier returns an object
 *
 * @param <T> the type of the returned object
 */
@FunctionalInterface
public interface Supplier<T> {

  /**
   * Return the object
   *
   * @return the next object
   */
  T get();
}
