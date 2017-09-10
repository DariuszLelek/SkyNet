package processing.skill.model;

import processing.Processable;

public class Skill implements Processable {
  @Override
  public void startProcessing() {
    
  }

  @Override
  public void stopProcessing() {

  }

  @Override
  public boolean isProcessed() {
    return false;
  }

  @Override
  public boolean canBeProcessed() {
    return false;
  }

  @Override
  public int getPriorityValue() {
    return 0;
  }

  @Override
  public String getInfo() {
    return null;
  }
}
