package processing.message.handler;

import processing.message.MessageType;

public class HandlerFactory {
  private static UserMessageHandler userMessageHandler = new UserMessageHandler();

  public static Handler getHandlerByMessageType(MessageType messageType) {
    switch (messageType) {
      case VOICE:
        return userMessageHandler;
      default:
        return new Handler();
    }
  }
}
