/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */
package processing.message;

import dictionary.DictionaryFactory;
import hibernate.Word;
import processing.message.model.Message;
import processing.message.model.MessageType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class MessageCreator {

  public Message getMessage(final String messageText, final MessageType messageType) {
    final Message message = new Message(messageType);
    final Queue<String> messageChunks = getMessageChunks(validateMessageText(messageText));
    final Queue<Word> words = getWordsFromChunks(messageChunks);

    dequeueWordsIntoMessage(words, message);

    return message;
  }

  private void dequeueWordsIntoMessage(final Queue<Word> messageWords, final Message message){
    messageWords.forEach(message::addWord);
  }

  private Queue<Word> getWordsFromChunks(final Queue<String> messageChunks) {
    return messageChunks.stream()
        .map(chunk -> DictionaryFactory.getWordProvider().getWord(chunk))
        .collect(Collectors.toCollection(LinkedList::new));
  }

  private Queue<String> getMessageChunks(String messageText) {
    return new LinkedList<>(
        Arrays.stream(messageText.split("\\s+"))
            .filter(chunk -> !chunk.isEmpty())
            .map(x -> x.replaceAll("[^A-Za-z0-9]", ""))
            .collect(Collectors.toList()));
  }

  private String validateMessageText(String messageText){
    return messageText != null ? messageText : "";
  }
}
