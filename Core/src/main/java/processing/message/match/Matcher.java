package processing.message.match;

import processing.message.MessageType;

public class Matcher extends Match{
  private final MessageType messageType;

  Matcher(MessageType messageType) {
    super(MatchesFactory.getMatches(messageType));
    this.messageType = messageType;
  }

  public MessageType getMessageType() {
    return messageType;
  }
}
