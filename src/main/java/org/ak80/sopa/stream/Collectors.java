package org.ak80.sopa.stream;

import org.ak80.sopa.function.BiConsumer;
import org.ak80.sopa.function.Function;
import org.ak80.sopa.function.Supplier;

import java.util.ArrayList;
import java.util.List;


/**
 * utilities for collecting a stream into a collection
 */
public class Collectors<T, A, R> {

  public static <T> Collector<T, ?, List<T>> toList() {
    return new CollectorImpl<>(
        ArrayList::new,
        List::add,
        l -> (List) l
    );
  }

  private static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
    private final Supplier<A> supplier;
    private final BiConsumer<A, T> accumulator;
    private final Function<A, R> finisher;

    CollectorImpl(Supplier<A> supplier,
                  BiConsumer<A, T> accumulator,
                  Function<A, R> finisher) {
      this.supplier = supplier;
      this.accumulator = accumulator;
      this.finisher = finisher;
    }

    @Override
    public BiConsumer<A, T> accumulator() {
      return accumulator;
    }

    @Override
    public Supplier<A> supplier() {
      return supplier;
    }

    @Override
    public Function<A, R> finisher() {
      return finisher;
    }
  }


}
