package bookstoread;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements Comparable<Book> {
  private final String title;
  private final String author;
  private final LocalDate publishedOn;
  private LocalDate startedReadingOn;
  private LocalDate finishedReadingOn;

  Book(String title, String author, LocalDate publishedOn) {
    this.title = title;
    this.author = author;
    this.publishedOn = publishedOn;
  }

  public String getTitle() {
    return title;
  }

  String getAuthor() {
    return author;
  }

  LocalDate getPublishedOn() {
    return publishedOn;
  }

  @Override
  public int compareTo(Book that) {
    return this.title.compareTo(that.title);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(title, book.title) &&
            Objects.equals(author, book.author) &&
            Objects.equals(publishedOn, book.publishedOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, publishedOn);
  }

  void startedReadingOn(LocalDate startedOn) {
    this.startedReadingOn = startedOn;
  }

  void finishedReadingOn(LocalDate finishedOn) {
    this.finishedReadingOn = finishedOn;
  }

  boolean isRead() {
    return startedReadingOn != null &&
            finishedReadingOn != null;
  }

  @Override
  public String toString() {
    final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Book")
            .add("title", title)
            .add("author", author)
            .add("publishedOn", publishedOn)
            .add("startedReadingOn", startedReadingOn)
            .add("finishedReadingOn", finishedReadingOn);

    return helper.toString();
  }
}
