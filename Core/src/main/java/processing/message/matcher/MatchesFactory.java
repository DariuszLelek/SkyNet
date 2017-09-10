package processing.message.matcher;

import processing.message.model.MessageType;

import java.util.ArrayList;
import java.util.List;

public class MatchesFactory {
  static List<String> getMatches(MessageType messageType) {
    switch (messageType) {

      default:
        return new ArrayList<>();
    }
  }
}
