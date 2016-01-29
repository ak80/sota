package org.ak80.sopa.function;

/**
 * Functional interface for a predicate
 *
 * @param <T> type of object to operate on
 */
@FunctionalInterface
public interface Predicate<T> {

  /**
   * Returns true or false depending on the object
   *
   * @param object the object  to test
   * @return true or false
   */
  boolean test(T object);

}
