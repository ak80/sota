package org.ak80.sopa.function;

/**
 * Operation that receives an input arguments and produces a result of a different type
 * <p/>
 *
 * @param <T> type of the input argument
 * @param <R> type of the result object
 */
@FunctionalInterface
public interface Function<T, R> {

  /**
   * Produce a result from the input
   *
   * @param t input argument
   * @return a result object
   */
  R apply(T t);
}
