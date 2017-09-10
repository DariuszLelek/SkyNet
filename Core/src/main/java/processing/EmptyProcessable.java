package processing;

public class EmptyProcessable implements Processable {
  @Override
  public void startProcessing() {
  }

  @Override
  public void stopProcessing() {
  }

  @Override
  public boolean isProcessed() {
    return true;
  }

  @Override
  public boolean canBeProcessed() {
    return false;
  }

  @Override
  public int getPriority() {
    return 0;
  }

  @Override
  public String getInfo() {
    return "EmptyProcessable";
  }
}
