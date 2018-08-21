package bookstoread;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bookshelf")
class BookShelfSpec {

  private BookShelf shelf;
  private Book effectiveJava;
  private Book codeComplete;
  private Book mythicalManMonth;

  @BeforeEach
  void init() {
    shelf = new BookShelf();
    effectiveJava = new Book("Effective Java", "Joshua Bloch",
            LocalDate.of(2008, Month.MAY, 8));
    codeComplete = new Book("Code Complete", "Steve McConnel",
            LocalDate.of(2004, Month.JUNE, 9));
    mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks",
            LocalDate.of(1975, Month.JANUARY, 1));
  }

  @Test
  @DisplayName("an empty bookshelf is empty when no book is added to it")
  void shelfEmptyWhenNoBookAdded() {
    var books = shelf.books();
    assertTrue(books.isEmpty(), () -> "Bookshelf should be empty.");
  }

  @Test
  @DisplayName("an empty bookshelf contains two books when two books are added")
  void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
    shelf.add(effectiveJava, codeComplete);
    var books = shelf.books();
    assertEquals(2, books.size(), () -> "BookShelf should have two books.");
  }

  @Test
  @DisplayName("an empty bookshelf is empty when add is called without books")
  void emptyBookShelfWhenAddIsCalledWithoutBooks() {
    shelf.add();
    var books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
  }

  @Test
  @DisplayName("returned books must be immutable")
  void booksReturnedFromBookShelfIsImmutableForClient() {
    shelf.add(effectiveJava, effectiveJava);
    var books = shelf.books();
    try {
      books.add(mythicalManMonth);
      fail(() -> "Should not be able to add book to books");
    } catch (Exception e) {
      assertTrue(e instanceof UnsupportedOperationException,
              () -> "Should throw UnsupportedOperationException");
    }
  }

  @Disabled("Needs to implement Comparator")
  @Test
  @DisplayName("can return books arranged by title")
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
  @DisplayName("can return books arranged by provided criteria")
  void bookshelfArrangedByUserProvidedCriteria() {
    shelf.add(effectiveJava, codeComplete, mythicalManMonth);
    List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
    assertEquals(
            Arrays.asList(mythicalManMonth, effectiveJava, codeComplete),
            books,
            () -> "Books in a bookshelf are arranged in descending order of book title"
    );
  }
}
