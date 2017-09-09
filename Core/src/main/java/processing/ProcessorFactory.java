package processing;

import processing.message.MessageProcessor;

public class ProcessorFactory {
  private final static Processor messageProcessor = new MessageProcessor();

  public static Processor getMessageProcessor() {
    return messageProcessor;
  }
}
