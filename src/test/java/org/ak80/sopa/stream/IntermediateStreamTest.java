package org.ak80.sopa.stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IntermediateStreamTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void testToList_returnsList() throws Exception {
    // Given
    IntermediateStream<String> stream = new ArrayStream<>("one", "two", "three", "four");

    // When
    List<String> list = stream.toList();

    // Then
    assertThat(list, contains("one", "two", "three", "four"));
  }

  @Test
  public void testToArray_smallerThanMaxSize_returnsArray() throws Exception {
    // Given
    IntermediateStream<String> stream = new ArrayStream<>("one", "two", "three", "four");

    // When
    Object[] array = stream.filter(e->e.contains("t")).toArray();

    // Then
    assertThat(array.length, is(2));
    assertThat(array[0], is("two"));
    assertThat(array[1], is("three"));
  }

  @Test
  public void testToArray_withMaxSize_returnsArray() throws Exception {
    // Given
    IntermediateStream<String> stream = new ArrayStream<>("one", "two", "three", "four");

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
  public void integrityCheck_consumeAfterTerminate_throwsException() throws Exception {
    // Given
    IntermediateStream<String> stream = new ArrayStream<>("one", "two", "three", "four");

    // When Then
    stream.findFirst();

    expectedException.expect(IllegalStateException.class);
    stream.filter(e->e.contains("t")).toArray();
  }

  @Test
  public void integrityCheck_terminateAfterTerminate_throwsException() throws Exception {
    // Given
    IntermediateStream<String> stream = new ArrayStream<>("one", "two", "three", "four");

    // When Then
    stream.findFirst();

    expectedException.expect(IllegalStateException.class);
    stream.findFirst();
  }

}