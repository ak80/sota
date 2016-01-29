package org.ak80.sota.function;

/**
 * Operation that receives and input arguments and produces a int result
 * <p/>
 *
 * @param <T> type of the input argument
 */
@FunctionalInterface
public interface ToIntFunction<T> {

  /**
   * Produce a result from the input
   *
   * @param t input argument
   * @return a result object
   */
  int applyAsInt(T t);
}
