package org.ak80.sopa.function;

/**
 * Operation that receives two input arguments of different types and produces a result of a different type
 * <p/>
 *
 * @param <T> type of the first input argument
 * @param <U> type of the second input argument
 * @param <R> type of the result object
 */
@FunctionalInterface
public interface BiFunction<T, U, R> {

  /**
   * Produce a result from the input
   *
   * @param t first input argument
   * @param u second input argument
   * @return a result object
   */
  R apply(T t, U u);

}
