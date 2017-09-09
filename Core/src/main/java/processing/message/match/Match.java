package processing.message.match;

import com.sun.deploy.util.StringUtils;
import processing.message.MessageType;

import java.util.List;

class Match {
  private final List<String> matches;

  Match(List<String> matches) {
    this.matches = matches;
  }

  public String getMatch(String text){
    return isMatching(text) ? text : "";
  }

  private boolean isMatching(String text){
    return matches.stream().anyMatch(s -> s.equalsIgnoreCase(text));
  }
}
