package org.ak80.sota.stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CollectionStreamTest {


  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void getNext_fromCollection_givesNext() throws Exception {
    // Given
    CollectionStream<String> stream = new CollectionStream(Arrays.asList("one", "two", "three"));

    // When Then
    assertThat(stream.next(), is("one"));
    assertThat(stream.next(), is("two"));
    assertThat(stream.next(), is("three"));
  }

  @Test
  public void getNext_fromCollectionContainingNull_givesNext() throws Exception {
    // Given
    CollectionStream<String> stream = new CollectionStream(Arrays.asList("one", null, "three"));

    // When Then
    assertThat(stream.next(), is("one"));
    assertThat(stream.next(), nullValue());
    assertThat(stream.next(), is("three"));
  }

  @Test
  public void getNext_untilExhausted_throwsException() throws Exception {
    // Given
    CollectionStream<String> stream = new CollectionStream(Arrays.asList("one", "two"));

    // When Then
    assertThat(stream.next(), is("one"));
    assertThat(stream.next(), is("two"));

    expectedException.expect(NoSuchElementException.class);
    stream.next();
  }

  @Test
  public void getMaximumSize_fromCollection_returnsCollectionSize() throws Exception {
    // Given
    CollectionStream<String> stream = new CollectionStream(Arrays.asList("one", "two"));

    // When Then
    assertThat(stream.getMaximumSize(), is(2));
  }
}