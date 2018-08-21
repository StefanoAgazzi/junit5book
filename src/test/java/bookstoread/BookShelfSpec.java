package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bookshelf")
class BookShelfSpec {

  private BookShelf shelf;
  private Book effectiveJava;
  private Book codeComplete;
  private Book mythicalManMonth;
  private Book cleanCode;

  @BeforeEach
  void init() {
    shelf = new BookShelf();
    effectiveJava = new Book("Effective Java", "Joshua Bloch",
            LocalDate.of(2008, Month.MAY, 8));
    codeComplete = new Book("Code Complete", "Steve McConnel",
            LocalDate.of(2004, Month.JUNE, 9));
    mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
            LocalDate.of(1975, Month.JANUARY, 1));
    cleanCode = new Book("Clean Code", "Robert C. Martin",
            LocalDate.of(2008, Month.JANUARY, 1));
  }

  @Nested
  @DisplayName("is empty")
  class IsEmpty {
    @Test
    @DisplayName("an empty bookshelf is empty when no book is added to it")
    void shelfEmptyWhenNoBookAdded() {
      var books = shelf.books();
      assertTrue(books.isEmpty(), () -> "Bookshelf should be empty.");
    }

    @Test
    @DisplayName("an empty bookshelf is empty when add is called without books")
    void emptyBookShelfWhenAddIsCalledWithoutBooks() {
      shelf.add();
      var books = shelf.books();
      assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }
  }

  @Nested
  @DisplayName("after adding books")
  class BooksAreAdded {
    @Test
    @DisplayName("an empty bookshelf contains two books when two books are added")
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
      shelf.add(effectiveJava, codeComplete);
      var books = shelf.books();
      assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }


    @Test
    @DisplayName("returned books must be immutable")
    void booksReturnedFromBookShelfIsImmutableForClient() {
      shelf.add(effectiveJava, effectiveJava);
      var books = shelf.books();
      try {
        books.add(mythicalManMonth);
        fail(() -> "Should not be able to add book to books");
      } catch (UnsupportedOperationException e) {
        assertTrue(true);
      }
    }
  }

  @Nested
  @DisplayName("is arranged")
  class BooksAreArranged {

    @Test
    @DisplayName("return books arranged lexicographically by book title")
    void bookshelfArrangedByBookTitle() {
      shelf.add(effectiveJava, effectiveJava, effectiveJava);
      List<Book> books = shelf.arrange();
      assertEquals(Arrays.asList(effectiveJava, effectiveJava, effectiveJava),
              books,
              () -> "Books in a bookshelf should be arranged lexicographically by book title"
      );
    }

    @Test
    @DisplayName("contains book in insertion order after calling BookShelf.Arrange()")
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
      shelf.add(effectiveJava, effectiveJava, effectiveJava);
      shelf.arrange();
      var books = shelf.books();
      assertEquals(Arrays.asList(effectiveJava, effectiveJava, effectiveJava),
              books,
              () -> "Books in bookshelf are in insertion order"
      );
    }

    @Test
    @DisplayName("return books arranged by provided criteria")
    void bookshelfArrangedByUserProvidedCriteria() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth);
      Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
      List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
      assertThat(books).isSortedAccordingTo(reversed);
    }

    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
      Map<Year, List<Book>> booksByPublicationYear = shelf.
              groupByPublicationYear();
      assertThat(booksByPublicationYear)
              .containsKey(Year.of(2008))
              .containsValues(Arrays.asList(effectiveJava, cleanCode));
      assertThat(booksByPublicationYear)
              .containsKey(Year.of(2004))
              .containsValues(singletonList(codeComplete));
      assertThat(booksByPublicationYear)
              .containsKey(Year.of(1975))
              .containsValues(singletonList(mythicalManMonth));
    }

    @Test
    @DisplayName("books inside bookshelf are grouped according to user provided criteria(group by author name)")
    void groupBooksByUserProvidedCriteria() {
      shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
      Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);
      assertThat(booksByAuthor)
              .containsKey("Joshua Bloch")
              .containsValues(singletonList(effectiveJava));
      assertThat(booksByAuthor)
              .containsKey("Steve McConnel")
              .containsValues(singletonList(codeComplete));
      assertThat(booksByAuthor)
              .containsKey("Frederick Phillips Brooks")
              .containsValues(singletonList(mythicalManMonth));
      assertThat(booksByAuthor)
              .containsKey("Robert C. Martin")
              .containsValues(singletonList(cleanCode));
    }
  }

}
