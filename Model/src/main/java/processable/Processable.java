package processable;

public interface Processable {
  void startProcessing();
  void stopProcessing();
  boolean isProcessed();
  boolean canBeProcessed();
  int getPriorityValue();

  String getInfo();
}
