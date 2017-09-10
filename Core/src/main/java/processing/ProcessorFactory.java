package processing;

import processing.message.model.MessageType;

public class ProcessorFactory {
  private final static Processor voiceMessageProcessor = new VoiceMessageProcessor();

  public static Processor getMessageProcessor(MessageType messageType) {
    switch (messageType) {
      case VOICE:
        return voiceMessageProcessor;
      case SYSTEM:
        return null;

      case EMPTY:
      default:
        return null;
    }
  }
  
  


}
