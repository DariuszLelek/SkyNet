package processing;

import processing.message.MessageProcessor;

public class ProcessorFactory {
  private final static MessageProcessor messageProcessor = new MessageProcessor();

  public static MessageProcessor getMessageProcessor() {
    return messageProcessor;
  }
}
