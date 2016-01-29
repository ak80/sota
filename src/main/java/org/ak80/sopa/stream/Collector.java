package org.ak80.sopa.stream;


import org.ak80.sopa.function.BiConsumer;
import org.ak80.sopa.function.Function;
import org.ak80.sopa.function.Supplier;

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