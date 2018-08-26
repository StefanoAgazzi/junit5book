package bookstoread;

class BusinessException extends RuntimeException {

  private final ErrorCode errorCode;
  private static final long serialVersionUID = 7718828512143293558L;

  BusinessException(ErrorCode errorCode) {
    super();
    this.errorCode = errorCode;
  }

  BusinessException(ErrorCode errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.errorCode = errorCode;
  }

  BusinessException(ErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  BusinessException(ErrorCode errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  BusinessException(ErrorCode errorCode, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
