package org.ak80.sota.stream;

import org.ak80.sota.function.BiConsumer;
import org.ak80.sota.function.Function;
import org.ak80.sota.function.Supplier;

/**
 * A collector
 */

class CollectorImpl<T, A, R> implements Collector<T, A, R> {
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
