package processing.message.handler;

import processing.message.Message;

public interface Handle {
  boolean canHandle();
  void handle(Message message);
}
