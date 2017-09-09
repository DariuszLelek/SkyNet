package processing.message.matcher;

import processing.message.MessageType;

public class Matcher{
  private final MessageType messageType;

  public String getMatch(String text){
    return isMatching(text) ? text : "";
  }

  private boolean isMatching(String text){
    return MatchesFactory.getMatches(messageType).stream().anyMatch(s -> s.equalsIgnoreCase(text));
  }

  Matcher(MessageType messageType) {
    this.messageType = messageType;
  }

  public MessageType getMessageType() {
    return messageType;
  }
}
