package org.ak80.sota;

import org.ak80.sota.function.Consumer;
import org.ak80.sota.function.Function;
import org.ak80.sota.function.Predicate;
import org.ak80.sota.function.Supplier;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Java 8 optional, wraps a value
 *
 * @param <T> type of the value
 */
public final class Optional<T> {

  private final T value;

  private Optional(T value) {
    this.value = value;
  }

  /**
   * Returns optional with non null value
   *
   * @param value the value to wrap
   * @param <T>   type of value
   * @return non empty optional
   */
  public static <T> Optional<T> of(T value) {
    return new Optional<>(Objects.requireNonNull(value));
  }

  /**
   * Returns an empty optional
   *
   * @param <T> type of optional
   * @return the options
   */
  public static <T> Optional<T> empty() {
    return new Optional<>(null);
  }

  /**
   * Returns an optional that may be empty
   *
   * @param value the value
   * @param <T>   type of value
   * @return the optional
   */
  public static <T> Optional<T> ofNullable(T value) {
    return new Optional<>(value);
  }

  /**
   * Returns value of throws NoSuchElementException
   *
   * @return the value if present
   */
  public T get() {
    if (isPresent()) {
      return value;
    }
    throw new NoSuchElementException();
  }

  /**
   * Returns true if value is present
   *
   * @return true if value is present
   */
  public boolean isPresent() {
    return value != null;
  }

  /**
   * If value is present, call consumer with value
   *
   * @param consumer the consumer for the value
   */
  public void ifPresent(Consumer<? super T> consumer) {
    if (isPresent()) {
      consumer.accept(value);
    }
  }

  /**
   * If value is present, then apply the predicate to decide if the same optional
   * or a new empty one will be returned
   * <p>If the predicate gives false an empty optional will be returned
   *
   * @param predicate ito decide if the optional will contain the value
   * @return this optional or a new empty one
   */
  public Optional<T> filter(Predicate<? super T> predicate) {
    if (isPresent()) {
      return predicate.test(value) ? this : empty();
    }
    return this;
  }

  /**
   * Apply the mapper, if the optional is not empty
   *
   * @param mapper the mapper
   * @param <U>    the type of result
   * @return an optional with the new type, may be null
   */
  public <U> Optional<U> map(Function<? super T, ? extends U> mapper) {
    if (isPresent()) {
      return new Optional<>(mapper.apply(value));
    }
    return empty();
  }

  /**
   * Returns the value if present, if not returns the first argument
   *
   * @param other the value that is returned if the optional is empty
   * @return the value if present, if not the first argument
   */
  public T orElse(T other) {
    return value != null ? value : other;
  }

  /**
   * Returns the value if present if not returns the result of the supplier
   *
   * @param supplier the supplier to use if the value is not present
   * @return value or the one from the supplier
   */
  public T orElseGet(Supplier<? extends T> supplier) {
    return value != null ? value : supplier.get();
  }

  /**
   * Returns hash code of the value or 0 if it is not present
   *
   * @return hash code
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Optional)) {
      return false;
    }

    return Objects.equals(value, ((Optional<?>) obj).value);
  }

  /**
   * Returns formatted string representation
   *
   * @return the formatted string
   */
  @Override
  public String toString() {
    return value != null ? "Optional[" + value + "s]" : "Optional.empty";
  }
}
