package org.ak80.sopa.function;

/**
 * Operation that receives two input arguments of the same and produces a result of the same type
 * <p/>
 *
 * @param <T> type of the input argument and the output
 */
@FunctionalInterface
public interface BinaryOperator<T> {

  /**
   * Produce a result from the input
   *
   * @param t1 first input argument
   * @param t2 second input argument
   * @return a result object
   */
  T apply(T t1, T t2);
}
