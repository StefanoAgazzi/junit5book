package bookstoread;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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

  public Map<Year, List<Book>> groupByPublicationYear() {
    return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
  }

  public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
    return books
            .stream()
            .collect(groupingBy(fx));
  }
}