package org.ak80.sopa.function;

/**
 * Operation that receives two input arguments and produces no result
 * <p/>
 *
 * @param <T> type of the first input argument
 * @param <U> type of the second input argument
 */
@FunctionalInterface
public interface BiConsumer<T, U> {

  /**
   * Receives two arguments
   *
   * @param t the first argument
   * @param u the second argument
   */
  void accept(T t, U u);

}
