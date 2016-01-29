package org.ak80.util.function;

/**
 * Operation that receives an input arguments of type int and produces a result of a different type
 * <p/>
 *
 * @param <R> type of the result object
 */
@FunctionalInterface
public interface IntFunction<R> {

  /**
   * Produce a result from the input
   *
   * @param i the input argument
   * @return a result object
   */
  R apply(int i);
}
