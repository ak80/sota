package org.ak80.sopa.stream;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A stream that wraps a collection, this is the "beginning" of stream
 *
 * @param <T> the type of the stream elements
 */
class CollectionStream<T> extends IntermediateStream<T> {
  private Collection<T> collection;
  private Iterator<T> iterator;

  /**
   * Creates new instance to provide access to the collection
   *
   * @param collection the collection to wrap
   */
  CollectionStream(Collection<T> collection) {
    this.collection = collection;
  }

  /**
   * Used to get the "next" element from a stream
   *
   * @return the "next" element of the stream
   * @throws NoSuchElementException when stream has no more elements
   */
  @Override
  protected T next() {
    if (iterator == null) {
      iterator = collection.iterator();
    }
    if (iterator.hasNext()) {
      return iterator.next();
    }
    throw new NoSuchElementException();
  }

  /**
   * Returns the maximum number of elements in the stream
   *
   * @return the maximum number of element
   */
  @Override
  protected int getMaximumSize() {
    return collection.size();
  }

}