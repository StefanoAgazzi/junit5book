package bookstoread;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.pmw.tinylog.Logger;

public class LoggingTestExecutionExceptionHandler implements
        TestExecutionExceptionHandler {

  @Override
  public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
    Logger.info("Exception thrown: {}", throwable);
    throw throwable;
  }
}