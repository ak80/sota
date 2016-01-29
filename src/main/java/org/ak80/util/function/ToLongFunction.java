package org.ak80.util.function;

/**
 * Operation that receives and input arguments and produces a long result
 * <p/>
 *
 * @param <T> type of the input argument
 */
@FunctionalInterface
public interface ToLongFunction<T> {

  /**
   * Produce a result from the input
   *
   * @param t input argument
   * @return a result object
   */
  long applyAsLong(T t);
}
