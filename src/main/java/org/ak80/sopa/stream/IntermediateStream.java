package org.ak80.sopa.stream;

import org.ak80.sopa.function.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;

/**
 * A stream that aims to provide some methods similar to Java 8 streams
 *
 * @param <T> the type of the stream elements
 */
abstract class IntermediateStream<T> implements Stream<T> {

  private Iterator<T> iterator;
  private boolean isTerminate = false;
  private boolean isConsuming = false;

  /**
   * Used to get the "next" element from a stream
   *
   * @return the "next" element of the stream
   * @throws NoSuchElementException when stream has no more elements
   */
  protected abstract T next();

  /**
   * Returns the maximum number of elements in the stream
   *
   * @return the maximum number of element
   */
  protected abstract int getMaximumSize();

  /**
   * Returns a stream that contains only elements that match the condition of the predicate
   *
   * @param predicate the predicate to use to decide whether an element will be in the new stream
   * @return a new stream with only the elements that match condition of the predicate
   */
  @Override
  public SupplierStream<T> filter(Predicate<? super T> predicate) {
    consume();
    Supplier<T> filterSupplier = () -> {
      while (iterator.hasNext()) {
        T element = iterator.next();
        if (predicate.test(element)) {
          return element;
        }
      }
      throw new NoSuchElementException();
    };

    return new SupplierStream<>(filterSupplier, getMaximumSize());
  }

  /**
   * Returns an Optional with the first element of the stream - or empty if the stream is empty
   *
   * @return Optional with the first element - or empty if the stream is empty
   */
  @Override
  public Optional<T> findFirst() {
    terminate();
    if (iterator.hasNext()) {
      return Optional.ofNullable(iterator.next());
    }
    return Optional.empty();
  }

  /**
   * Returns all elements as stored in an array
   *
   * @return an array of all elements
   */
  @Override
  public Object[] toArray() {
    terminate();
    Object[] array = new Object[getMaximumSize() + 1];

    int i = 0;
    while (iterator.hasNext()) {
      array[i] = iterator.next();
      i++;
    }
    return Arrays.copyOfRange(array, 0, i);
  }

  /**
   * Terminate stream into a list
   *
   * @return the list
   */
  public List<T> toList() {
    terminate();
    List<T> list = new ArrayList<>();
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    return list;
  }

  private void consume() {
    isConsuming = true;
    if (isTerminate) {
      throw new IllegalStateException("Stream is already terminated");
    }
    iterator = new StreamIterator<>(this);
  }

  /**
   * Returns iterator for the Stream
   *
   * @return the iterator
   */
  @Override
  public Iterator<T> iterator() {
    terminate();
    return iterator;
  }

  private void terminate() {
    if (isTerminate) {
      throw new IllegalStateException("Stream is already terminated");
    }
    isTerminate = true;
    if (!isConsuming) {
      iterator = new StreamIterator<>(this);
    }
  }

  @Override
  public boolean allMatch(Predicate<? super T> predicate) {
    throw new NotImplementedException();
  }

  @Override
  public boolean anyMatch(Predicate<? super T> predicate) {
    throw new NotImplementedException();
  }

  @Override
  public <R, A> R collect(Collector<? super T, A, R> collector) {
    A container = collector.supplier().get();
    Iterator<T> iterator = iterator();
    while (iterator.hasNext())
      collector.accumulator().accept(container, iterator.next());
    return collector.finisher().apply(container);
  }

  @Override
  public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
    throw new NotImplementedException();
  }

  @Override
  public long count() {
    throw new NotImplementedException();
  }

  @Override
  public IntermediateStream<T> distinct() {
    throw new NotImplementedException();
  }

  @Override
  public Optional<T> findAny() {
    throw new NotImplementedException();
  }

  @Override
  public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    throw new NotImplementedException();
  }

  @Override
  public void forEachOrdered(Consumer<? super T> action) {
    throw new NotImplementedException();
  }

  @Override
  public IntermediateStream<T> limit(long maxSize) {
    throw new NotImplementedException();
  }

  @Override
  public boolean isParallel() {
    return false;
  }

  @Override
  public <R> IntermediateStream<R> map(Function<? super T, ? extends R> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public IntStream mapToInt(ToIntFunction<? super T> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public LongStream mapToLong(ToLongFunction<? super T> mapper) {
    throw new NotImplementedException();
  }

  @Override
  public Optional<T> max(Comparator<? super T> comparator) {
    throw new NotImplementedException();
  }

  @Override
  public Optional<T> min(Comparator<? super T> comparator) {
    throw new NotImplementedException();
  }

  @Override
  public boolean noneMatch(Predicate<? super T> predicate) {
    throw new NotImplementedException();
  }

  @Override
  public IntermediateStream<T> peek(Consumer<? super T> action) {
    throw new NotImplementedException();
  }

  @Override
  public Optional<T> reduce(BinaryOperator<T> accumulator) {
    throw new NotImplementedException();
  }

  @Override
  public T reduce(T identity, BinaryOperator<T> accumulator) {
    throw new NotImplementedException();
  }

  @Override
  public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
    throw new NotImplementedException();
  }

  @Override
  public IntermediateStream<T> skip(long n) {
    throw new NotImplementedException();
  }

  @Override
  public IntermediateStream<T> sorted() {
    throw new NotImplementedException();
  }

  @Override
  public IntermediateStream<T> sorted(Comparator<? super T> comparator) {
    throw new NotImplementedException();
  }


  @Override
  public <A> A[] toArray(IntFunction<A[]> generator) {
    throw new NotImplementedException();
  }

  @Override
  public Stream<T> onClose(Runnable closeHandler) {
    throw new NotImplementedException();
  }

  @Override
  public Stream<T> parallel() {
    throw new NotImplementedException();
  }

  @Override
  public Stream<T> sequential() {
    throw new NotImplementedException();
  }

  @Override
  public Spliterator<T> spliterator() {
    throw new NotImplementedException();
  }

  @Override
  public Stream<T> unordered() {
    throw new NotImplementedException();
  }

  @Override
  public void close() throws Exception {
    // nothing to do
  }

}

