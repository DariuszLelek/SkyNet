package processing.message.handler;

import processing.message.Message;

public class Handler implements Handle {

  @Override
  public boolean canHandle() {
    return true;
  }

  @Override
  public void handle(Message message) {
  }
}
