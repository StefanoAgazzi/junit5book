package bookstoread;

import java.time.LocalDate;
import java.util.Objects;

public class Book implements Comparable<Book> {
  private final String title;
  private final String author;
  private final LocalDate publishedOn;

  Book(String title, String author, LocalDate publishedOn) {
    this.title = title;
    this.author = author;
    this.publishedOn = publishedOn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public LocalDate getPublishedOn() {
    return publishedOn;
  }

  @Override
  public int compareTo(Book that) {
    return this.title.compareTo(that.title);
  }

  @Override
  public String toString() {
    return "Book{" +
            "title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", publishedOn=" + publishedOn +
            '}';
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
}
