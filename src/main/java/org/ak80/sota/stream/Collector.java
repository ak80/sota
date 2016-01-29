package org.ak80.sota.stream;


import org.ak80.sota.function.BiConsumer;
import org.ak80.sota.function.Function;
import org.ak80.sota.function.Supplier;

/**
 * For collecting a stream into a list
 *
 * @param <T> Type of the elements in the stream
 * @param <A> intermediate accumulated result
 * @param <R> Type of the final result (i.e. collection)
 */
public interface Collector<T, A, R> {


  Supplier<A> supplier();

  BiConsumer<A, T> accumulator();

  Function<A, R> finisher();
}