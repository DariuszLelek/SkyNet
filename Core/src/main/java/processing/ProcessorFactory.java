/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package processing;

import processing.message.MessageType;

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
