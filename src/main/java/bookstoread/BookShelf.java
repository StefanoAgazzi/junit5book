package bookstoread;

import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

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
    return books.stream().sorted(criteria).collect(toList());
  }

  Map<Year, List<Book>> groupByPublicationYear() {
    return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
  }

  <K> Map<K, List<Book>> groupBy(Function<Book, K> fx) {
    return books.stream().collect(groupingBy(fx));
  }

  Progress progress() {
    int booksRead = (int) books.stream().filter(Book::isRead).count();
    int booksToRead = books.size() - booksRead;
    int percentageCompleted = booksRead * 100 / books.size();
    int percentageToRead = booksToRead * 100 / books.size();
    return new Progress(percentageCompleted, percentageToRead, 0);
  }

  List<Book> findBooksByTitle(String searchTerm) {
    return books.stream()
            .filter(s -> StringUtils.containsIgnoreCase(s.getTitle(), searchTerm))
            .collect(toList());
  }

  List<Book> findBooksByTitle(String searchTerm, BookFilter filter) {
    return books.stream()
            .filter(s -> StringUtils.containsIgnoreCase(s.getTitle(), searchTerm))
            .filter(b -> filter.apply(b))
            .collect(toList());
  }
}