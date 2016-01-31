package org.ak80.sota.stream;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Allows iteration over stream
 *
 * @param <T> the type of the stream elements
 */
class StreamIterator<T> implements Iterator<T> {

  private final IntermediateStream<T> stream;
  private T nextElement = null;

  /**
   * Creates new instance
   */
  public StreamIterator(IntermediateStream<T> stream) {
    this.stream = stream;
  }

  @Override
  public boolean hasNext() {
    if (nextElement != null) {
      return true;
    }
    try {
      nextElement = stream.next();
    } catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }

  @Override
  public T next() {
    if (nextElement == null) {
      return stream.next();
    } else {
      T element = nextElement;
      nextElement = null;
      return element;
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("not implemented");
  }

}
