package bookstoread;

class Progress {
  private final int completed;
  private final int toRead;
  private final int inProgress;

  Progress(int completed, int toRead, int inProgress) {
    this.completed = completed;
    this.toRead = toRead;
    this.inProgress = inProgress;
  }

  int completed() {
    return this.completed;
  }

  int toRead() {
    return this.toRead;
  }

  int inProgress() {
    return this.inProgress;
  }
}