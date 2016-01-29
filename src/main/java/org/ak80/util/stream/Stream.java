package org.ak80.util.stream;

import org.ak80.util.function.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A stream that aims to provide some methods similar to Java 8 streams
 *
 * @param <T> the type of the stream elements
 */
public interface Stream<T> extends BaseStream<T, Stream<T>> {

  /**
   * Returns stream from a collection
   * <p/>
   * This method is an addition and not found on the Java8 interface. Replacements for Collection.stream()
   *
   * @param collection the collection from which the stream will be created
   * @param <T>        the type of the the stream elements
   * @return a stream that will supply the elements from the collection
   */
  public static <T> Stream<T> from(Collection<T> collection) {
    return new CollectionStream<>(collection);
  }

  boolean allMatch(Predicate<? super T> predicate);

  boolean anyMatch(Predicate<? super T> predicate);

  static <T> Stream.Builder<T> builder() {
    throw new NotImplementedException();
  }

  <R, A> R collect(Collector<? super T, A, R> collector);

  <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);

  static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b) {
    throw new NotImplementedException();
  }

  long count();

  Stream<T> distinct();

  static <T> Stream<T> empty() {
    throw new NotImplementedException();
  }

  Stream<T> filter(Predicate<? super T> predicate);

  Optional<T> findAny();

  Optional<T> findFirst();

  <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

  DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper);

  IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper);

  LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper);

  void forEach(Consumer<? super T> action);

  void forEachOrdered(Consumer<? super T> action);

  static <T> Stream<T> generate(Supplier<T> s) {
    throw new NotImplementedException();
  }

  static <T> Stream<T> iterate(T seed, UnaryOperator<T> f) {
    throw new NotImplementedException();
  }

  Stream<T> limit(long maxSize);

  <R> Stream<R> map(Function<? super T, ? extends R> mapper);

  DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper);

  IntStream mapToInt(ToIntFunction<? super T> mapper);

  LongStream mapToLong(ToLongFunction<? super T> mapper);

  Optional<T> max(Comparator<? super T> comparator);

  Optional<T> min(Comparator<? super T> comparator);

  boolean noneMatch(Predicate<? super T> predicate);

  /**
   * Returns stream of the given elements
   *
   * @param values the elements for the stream
   * @param <T>    the type of the the stream elements
   * @return a stream that will supply the elements
   */
  static <T> Stream<T> of(T... values) {
    return new ArrayStream<>(values);
  }

  static <T> Stream<T> of(T t) {
    throw new NotImplementedException();
  }

  Stream<T> peek(Consumer<? super T> action);

  Optional<T> reduce(BinaryOperator<T> accumulator);

  T reduce(T identity, BinaryOperator<T> accumulator);

  <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

  Stream<T> skip(long n);

  Stream<T> sorted();

  Stream<T> sorted(Comparator<? super T> comparator);

  Object[] toArray();

  <A> A[] toArray(IntFunction<A[]> generator);

  interface Builder<T> {
  }
}
