package processing;

public interface Processable {
  void startProcessing();
  void stopProcessing();
  boolean isProcessed();
  boolean canBeProcessed();
  int getPriority();

  String getInfo();
}
