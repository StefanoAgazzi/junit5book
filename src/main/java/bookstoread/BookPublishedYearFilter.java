package bookstoread;

import java.time.LocalDate;

class BookPublishedYearFilter implements BookFilter {
  private LocalDate startDate;

  static BookPublishedYearFilter after(int year) {
    BookPublishedYearFilter filter = new BookPublishedYearFilter();
    filter.startDate = LocalDate.of(year, 12, 31);
    return filter;
  }

  // TODO: refactor in order to use also b.getPublishedOn().isBefore(startDate)
  // maybe accepting a Function as bookstoread.BookShelf.groupBy
  @Override
  public boolean apply(final Book b) {
    if (b == null)
      return false;
    return b.getPublishedOn().isAfter(startDate);
  }
}