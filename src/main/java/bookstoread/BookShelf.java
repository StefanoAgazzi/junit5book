package bookstoread;

import java.util.*;
import java.util.stream.Collectors;

class BookShelf {

  private final List<Book> books = new ArrayList<>();

  List<Book> books() {
    return Collections.unmodifiableList(books);
  }

  void add(Book... bookToAdd) {
    Arrays.stream(bookToAdd).forEach(books::add);
  }

  List<Book> arrange() {
    return arrange(Comparator.naturalOrder());
  }

  List<Book> arrange(Comparator<Book> criteria) {
    return books.stream().sorted(criteria).collect(Collectors.toList());
  }
}