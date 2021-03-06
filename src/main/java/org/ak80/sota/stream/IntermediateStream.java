package org.ak80.sota.stream;

import org.ak80.sota.Optional;
import org.ak80.sota.function.*;

import java.util.*;

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
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean anyMatch(Predicate<? super T> predicate) {
    throw new UnsupportedOperationException();
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
    throw new UnsupportedOperationException();
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntermediateStream<T> distinct() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<T> findAny() {
    throw new UnsupportedOperationException();
  }

  @Override
  public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void forEachOrdered(Consumer<? super T> action) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntermediateStream<T> limit(long maxSize) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isParallel() {
    return false;
  }

  @Override
  public <R> IntermediateStream<R> map(Function<? super T, ? extends R> mapper) {
    consume();
    Supplier<R> mapperSupplier = () -> mapper.apply(iterator.next());
    return new SupplierStream<>(mapperSupplier, getMaximumSize());
  }

  @Override
  public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntStream mapToInt(ToIntFunction<? super T> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public LongStream mapToLong(ToLongFunction<? super T> mapper) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<T> max(Comparator<? super T> comparator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<T> min(Comparator<? super T> comparator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean noneMatch(Predicate<? super T> predicate) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntermediateStream<T> peek(Consumer<? super T> action) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<T> reduce(BinaryOperator<T> accumulator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T reduce(T identity, BinaryOperator<T> accumulator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntermediateStream<T> skip(long n) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntermediateStream<T> sorted() {
    throw new UnsupportedOperationException();
  }

  @Override
  public IntermediateStream<T> sorted(Comparator<? super T> comparator) {
    throw new UnsupportedOperationException();
  }


  @Override
  public <A> A[] toArray(IntFunction<A[]> generator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<T> onClose(Runnable closeHandler) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<T> parallel() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<T> sequential() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Spliterator<T> spliterator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<T> unordered() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void close() throws Exception {
    // nothing to do
  }

}

