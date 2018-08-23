package bookstoread;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookFilterSpec {
  private Book cleanCode;
  private Book codeComplete;

  @BeforeEach
  void init() {
    cleanCode = new Book("Clean Code", "Robert C. Martin", LocalDate.
            of(2008, Month.AUGUST, 1));
    codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.
            of(2004, Month.JUNE, 9));
  }

  @Nested
  @DisplayName("book published date")
  class BookPulishedFilterSpec {
    @Test
    @DisplayName("is after specified year")
    void validateBookPublishedDatePostAskedYear() {
      BookFilter filter = BookPublishedYearFilter.after(2006);
      assertTrue(filter.apply(cleanCode));
      assertFalse(filter.apply(codeComplete));
    }

    // FIXME: should use BookPublishedYearFilter.Before(2006)
    @Disabled
    @Test
    @DisplayName("is before specified year")
    void validateBookPublishedDateBeforeAskedYear() {
      BookFilter filter = BookPublishedYearFilter.after(2006);
      assertFalse(filter.apply(cleanCode));
      assertTrue(filter.apply(codeComplete));
    }

    @Test
    @DisplayName("composite criteria is based on multiple filters")
    void shouldFilterOnMultiplesCriteria() {
      CompositeFilter compositeFilter = new CompositeFilter();
      compositeFilter.addFilter(b -> false);
      assertFalse(compositeFilter.apply(cleanCode));
    }

    @Test
    @DisplayName("composite criteria does not invoke after first failure")
    void shouldNotInvokeAfterFirstFailure(){
      CompositeFilter compositeFilter = new CompositeFilter();
      compositeFilter.addFilter( b -> false);
      compositeFilter.addFilter( b -> true);
      assertFalse(compositeFilter.apply(cleanCode));
    }

    @Test
    @DisplayName("composite criteria invokes all filters")
    void shouldInvokeAllFilters(){
      CompositeFilter compositeFilter = new CompositeFilter();
      compositeFilter.addFilter( b -> true);
      compositeFilter.addFilter( b -> true);
      assertTrue(compositeFilter.apply(cleanCode));
    }
  }
}

