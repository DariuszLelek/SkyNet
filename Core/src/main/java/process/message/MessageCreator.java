/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */
package process.message;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class MessageCreator {

  public Message create(final String messageText, final MessageType messageType) {
    final Message message = new Message(messageType);

    dequeueTextIntoMessage(validateMessageText(messageText), message);

    return message;
  }

  private void dequeueTextIntoMessage(final String messageText, final Message message) {
    getMessageChunks(messageText).stream().filter(text -> !text.isEmpty())
        .forEach(text -> message.getInstruction().add(text));
  }

  private Queue<String> getMessageChunks(String messageText) {
    return new LinkedList<>(
        Arrays.stream(messageText.split("\\s+"))
            .filter(chunk -> !chunk.isEmpty())
            .map(x -> x.replaceAll("[^A-Za-z0-9]", ""))
            .collect(Collectors.toList()));
  }

  private String validateMessageText(String messageText) {
    return messageText != null ? messageText : "";
  }
}
