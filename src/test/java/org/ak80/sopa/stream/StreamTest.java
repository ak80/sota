package org.ak80.sopa.stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StreamTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testFilter() throws Exception {
    // Given
    Stream<String> stream = Stream.from(Arrays.asList("one", "2", "3", "four"));

    // When
    Object[] filteredStream = stream.filter( e -> e.contains("o")).toArray();

    // Then
    assertThat(filteredStream.length,is(2));
    assertThat(filteredStream[0],is("one"));
    assertThat(filteredStream[1], is("four"));
  }

  @Test
  public void testFindFirst_found() throws Exception {
    // Given
    Stream<String> stream = Stream.from(Arrays.asList("one", "two", "three", "four"));

    // When
    Optional<String> firstElement = stream.findFirst();

    // Then
    assertThat(firstElement.get(), is("one"));
  }

  @Test
  public void testFindFirst_notFound() throws Exception {
    // Given
    Stream<String> stream = Stream.from(Arrays.asList());

    // When
    Optional<String> firstElement = stream.findFirst();

    // Then
    assertThat(firstElement.isPresent(), is(false));
  }

  @Test
  public void testToArray_returnsArray() throws Exception {
    // Given
    Stream<String> stream = Stream.of("one", "two", "three", "four");

    // When
    Object[] array = stream.toArray();

    // Then
    assertThat(array.length, is(4));
    assertThat(array[0], is("one"));
    assertThat(array[1], is("two"));
    assertThat(array[2], is("three"));
    assertThat(array[3], is("four"));
  }

  @Test
  public void iterator_allowsIteration() throws Exception {
    // Given
    Stream<String> stream = Stream.of("one", "two");

    // When
    Iterator<String> iterator = stream.iterator();

    // Then
    assertThat(iterator.hasNext(),is(true));
    assertThat(iterator.next(),is("one"));
    assertThat(iterator.hasNext(),is(true));
    assertThat(iterator.next(),is("two"));
    assertThat(iterator.hasNext(), is(false));

    expectedException.expect(NoSuchElementException.class);
    assertThat(iterator.next(), is("two"));
  }

  @Test
  public void collect_toList() {
    // Given
    Stream<String> stream = Stream.of("one", "two", "three");

    // When
    List<String> list = stream.collect(Collectors.toList());

    // Then
    assertThat(list,contains("one", "two", "three"));
  }


}