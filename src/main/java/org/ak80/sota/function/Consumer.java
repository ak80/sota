package org.ak80.sota.function;

/**
 * Operation that receives one input arguments and produces no result
 * <p/>
 *
 * @param <T> type of the the input argument
 */
@FunctionalInterface
public interface Consumer<T> {

  /**
   * Receive one arguments
   *
   * @param t the  argument
   */
  void accept(T t);

}
