package global;

public enum GlobalStrings {
  SYNONYM_SPLITTER(",");

  private final String value;

  GlobalStrings(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
