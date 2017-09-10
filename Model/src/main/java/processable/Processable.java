package processable;

public abstract class Processable {
  public abstract void execute();

  public abstract boolean canBeProcessed();

  public abstract int getPriorityValue();

}
