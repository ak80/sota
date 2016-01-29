package org.ak80.sota.stream;


import java.util.NoSuchElementException;

/**
 * A stream that wraps an array, this is the "beginning" of stream
 *
 * @param <T> the type of the stream elements
 */
class ArrayStream<T> extends IntermediateStream<T> {
  private final T[] array;
  private int position = 0;

  /**
   * Creates new instance to provide access to the array
   *
   * @param values the elements for the array
   */
  ArrayStream(T... values) {
    this.array = values;
  }

  /**
   * Used to get the "next" element from a stream
   *
   * @return the "next" element of the stream
   * @throws NoSuchElementException when stream has no more elements
   */
  @Override
  protected T next() {
    if (position == array.length) {
      throw new NoSuchElementException();
    }
    return array[position++];
  }

  /**
   * Returns the maximum number of elements in the stream
   *
   * @return the maximum number of element
   */
  @Override
  protected int getMaximumSize() {
    return array.length;
  }

}