package org.ak80.util.stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StreamIteratorTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
     public void next_whileThereAreElements_returnsElements() {
    // Given
    Stream<String> stream = Stream.of("one", "two", "three");

    // When
    Iterator<String> iterator = stream.iterator();

    // Then
    assertThat(iterator.next(),is("one"));
    assertThat(iterator.next(),is("two"));
    assertThat(iterator.next(), is("three"));
  }

  @Test
  public void next_withNullElement_returnsElements() {
    // Given
    Stream<String> stream = Stream.of("one", null, "three");

    // When
    Iterator<String> iterator = stream.iterator();

    // Then
    assertThat(iterator.next(),is("one"));
    assertThat(iterator.next(),is(nullValue()));
    assertThat(iterator.next(), is("three"));
  }

  @Test
  public void hasNext_whileThereAreElements_returnsTrue() {
    // Given
    Stream<String> stream = Stream.of("one", "two");

    // When
    Iterator<String> iterator = stream.iterator();

    // Then
    assertThat(iterator.hasNext(), is(true));
    iterator.next();
    assertThat(iterator.hasNext(), is(true));
    iterator.next();
    assertThat(iterator.hasNext(), is(false));
  }

  @Test
  public void hasNext_calledDouble_doesNotAdvanceLikeNext() {
    // Given
    Stream<String> stream = Stream.from(Arrays.asList("one", "two"));

    // When
    Iterator<String> iterator = stream.iterator();

    // Then
    assertThat(iterator.hasNext(), is(true));
    assertThat(iterator.hasNext(), is(true));
    iterator.next();
    assertThat(iterator.hasNext(), is(true));
    assertThat(iterator.hasNext(), is(true));
    iterator.next();
    assertThat(iterator.hasNext(), is(false));
    assertThat(iterator.hasNext(), is(false));
  }

  @Test
  public void next_untilNoMoreElements_throwsException() {
    // Given
    Stream<String> stream = Stream.from(Arrays.asList("one", "two"));

    // When
    Iterator<String> iterator = stream.iterator();

    // Then
    iterator.next();
    iterator.next();
    expectedException.expect(NoSuchElementException.class);
    iterator.next();
  }

}