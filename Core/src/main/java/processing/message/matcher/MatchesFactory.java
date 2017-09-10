package processing.message.matcher;

import processing.message.MessageType;
import processing.message.predefined.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatchesFactory {
  static List<String> getMatches(MessageType messageType) {
    switch (messageType) {

      default:
        return new ArrayList<>();
    }
  }
}
