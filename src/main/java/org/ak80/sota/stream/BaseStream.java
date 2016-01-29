package org.ak80.sota.stream;

import java.util.Iterator;

/**
 * Interface for all streams
 */
public interface BaseStream<T, S extends BaseStream<T, S>> extends AutoCloseable {
  /**
   * Returns true if the stream will execute in parallel
   *
   * @return true if parallel
   */
  boolean isParallel();

  /**
   * Returns an iterator for the elements
   *
   * @return the iterator
   */
  Iterator<T> iterator();

  /**
   * Returns an equivalent stream a close handler
   *
   * @param closeHandler the close handler
   * @return stream with close handler
   */
  S onClose(Runnable closeHandler);

  /**
   * Returns an equivalent stream that is parallel
   *
   * @return parallel stream
   */
  S parallel();

  /**
   * Returns an equivalent stream that is sequential
   *
   * @return sequential stream
   */
  S sequential();

  /**
   * Returns spliterator for the stream
   *
   * @return spliterator
   */
  Spliterator<T> spliterator();

  /**
   * Returns an equivalent stream that is unordered
   *
   * @return unordered stream
   */
  S unordered();

}
