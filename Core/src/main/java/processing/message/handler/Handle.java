package processing.message.handler;

import processing.message.model.Message;

public interface Handle {
  boolean canHandle();
  void handle(Message message);
}
