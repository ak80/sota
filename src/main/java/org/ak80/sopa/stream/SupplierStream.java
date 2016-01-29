package org.ak80.sopa.stream;

import org.ak80.sopa.function.Supplier;


/**
 * A stream that holds a supplier to get elements
 *
 * @param <T> the type of the stream elements
 */
class SupplierStream<T> extends IntermediateStream<T> {

  private final Supplier<T> supplier;
  private final int maxSize;

  /**
   * Creates new instance
   *
   * @param supplier to get elements, e.g. from another stream while applying an operation
   * @param maxSize  the maximum number of elements
   */
  SupplierStream(Supplier<T> supplier, int maxSize) {
    this.supplier = supplier;
    this.maxSize = maxSize;
  }

  /**
   * Internally used to get the "next" element from a stream
   *
   *  throws NoSuchFieldException when stream has no more elements
   * @return the "next" element of the stream
   */
  @Override
  protected T next() {
    return supplier.get();
  }


  /**
   * Returns the maximum number of elements in the stream
   *
   * @return the maximum number of element
   */
  @Override
  protected int getMaximumSize() {
    return maxSize;
  }


}
