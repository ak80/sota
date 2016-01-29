package org.ak80.sopa.function;

/**
 * Operation that receives and input arguments and produces a double result
 * <p/>
 *
 * @param <T> type of the input argument
 */
@FunctionalInterface
public interface ToDoubleFunction<T> {

  /**
   * Produce a result from the input
   *
   * @param t input argument
   * @return a result object
   */
  double applyAsDouble(T t);
}
