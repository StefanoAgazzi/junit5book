package bookstoread;

import java.util.function.BooleanSupplier;

public interface BookFilter {
  boolean apply(Book b);
}
