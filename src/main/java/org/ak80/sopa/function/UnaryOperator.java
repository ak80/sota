package org.ak80.sopa.function;

/**
 * Operation that receives and input arguments and produces a result of different type
 * <p/>
 *
 * @param <T> type of the input argument and the output
 */
@FunctionalInterface
public interface UnaryOperator<T> {

  /**
   * Produce a result from the input
   *
   * @param t input argument
   * @return a result object
   */
  T apply(T t);
}
