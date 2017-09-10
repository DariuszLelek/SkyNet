package processing.message.model;

public enum MessagePriority {
  NONE(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final int value;

  MessagePriority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
