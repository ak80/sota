package org.ak80.util.stream;


import org.ak80.util.function.BiConsumer;
import org.ak80.util.function.Function;
import org.ak80.util.function.Supplier;

/**
 * For collecting a stream into a list
 *
 * @param <T> Type of the elements in the stream
 * @param <A> intermediate accumulated result
 * @param <R> Type of the final result
 */
public interface Collector<T, A, R> {


  Supplier<A> supplier();

  BiConsumer<A, T> accumulator();

  Function<A, R> finisher();
}