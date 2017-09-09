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
      case COMMAND:
        return Arrays.stream(Command.values()).map(Command::name).collect(Collectors.toList());
      case REQUEST:
        return Arrays.stream(Request.values()).map(Request::name).collect(Collectors.toList());
      case DENIAL:
        return Arrays.stream(Denial.values()).map(Denial::name).collect(Collectors.toList());
      case CONFIRMATION:
        return Arrays.stream(Confirmation.values()).map(Confirmation::name).collect(Collectors.toList());
      case QUESTION:
        return Arrays.stream(Question.values()).map(Question::name).collect(Collectors.toList());
      default:
        return new ArrayList<>();
    }
  }
}
