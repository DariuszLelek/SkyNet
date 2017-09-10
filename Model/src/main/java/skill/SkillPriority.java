package skill;

public enum SkillPriority {
  NONE(0),
  LOW(1),
  MEDIUM(2),
  HIGH(3);

  private final int value;

  SkillPriority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "SkillPriority{" +
        "value=" + value +
        '}';
  }
}
