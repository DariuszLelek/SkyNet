package processing.message.handler;

import processing.message.MessageType;

public class HandlerFactory {
  private static CommandHandler commandHandler = new CommandHandler();
  private static ConfirmationHandler confirmationHandler = new ConfirmationHandler();

  public static Handler getHandlerByMessageType(MessageType messageType) {
    switch (messageType) {
      case COMMAND:
        return commandHandler;
      case CONFIRMATION:
        return confirmationHandler;
      default:
        return new Handler();
    }
  }
}
