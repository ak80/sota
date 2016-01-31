package org.ak80.sota.stream;

import java.util.ArrayList;
import java.util.List;


/**
 * utilities for collecting a stream into a collection
 */
public class Collectors<T, A, R> {

  public static <T> Collector<T, ?, List<T>> toList() {
    return new CollectorImpl<T, List<T>, List<T>>(
        ArrayList::new,
        List::add,
        l -> (List) l
    );
  }

}
