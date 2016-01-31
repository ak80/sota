package org.ak80.sota.stream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayStreamTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test
  public void getNext_fromArray_givesNext() throws Exception {
    // Given
    ArrayStream<String> stream = new ArrayStream("one", "two", "three");

    // When Then
    assertThat(stream.next(), is("one"));
    assertThat(stream.next(), is("two"));
    assertThat(stream.next(), is("three"));
  }

  @Test
  public void getNext_fromArrayContainingNull_givesNext() throws Exception {
    // Given
    ArrayStream<String> stream = new ArrayStream<>("one", null, "three");

    // When Then
    assertThat(stream.next(), is("one"));
    assertThat(stream.next(), nullValue());
    assertThat(stream.next(), is("three"));
  }

  @Test
  public void getNext_untilExhausted_throwsException() throws Exception {
    // Given
    ArrayStream<String> stream = new ArrayStream("one", "two");

    // When Then
    assertThat(stream.next(), is("one"));
    assertThat(stream.next(), is("two"));

    expectedException.expect(NoSuchElementException.class);
    stream.next();
  }

  @Test
  public void getMaximumSize_fromArray_returnsArrayLength() throws Exception {
    // Given
    ArrayStream<String> stream = new ArrayStream("one", "two");

    // When Then
    assertThat(stream.getMaximumSize(), is(2));
  }

}
