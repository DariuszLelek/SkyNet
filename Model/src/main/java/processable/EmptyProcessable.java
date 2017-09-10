package processable;

public class EmptyProcessable extends Processable {

  public void execute() {
  }

  public boolean canBeProcessed() {
    return false;
  }

  public int getPriorityValue() {
    return Integer.MIN_VALUE;
  }

  @Override
  public String toString() {
    return EmptyProcessable.class.getName();
  }
}
