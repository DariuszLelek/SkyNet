package processing.message.matcher;

import processing.message.MessageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MessageMatcherFactory {
  private static final Collection<Matcher> matchers = new ArrayList<>();

  static{
    initMatchers();
  }

  public static Collection<Matcher> getMatchers(){
    return matchers;
  }

  private static void initMatchers(){
    Arrays.stream(MessageType.values()).forEach(type -> matchers.add(new Matcher(type)));
  }
}
