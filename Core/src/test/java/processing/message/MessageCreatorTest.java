/*
 * Copyright 2017 Dariusz Lelek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package processing.message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageCreatorTest {
  private final MessageCreator instance = new MessageCreator();

  private final String alphabetic = "abcABC";
  private final String numeric = "0123456789";
  private final String space = " ";
  private final String[] invalidChunks = {"!@#$%^&*()", ":{}[]|", "  ><  .", "// /'' \" \n"};
  private final String[] content = {"some", "content", "here"};
  private final MessageType defaultType = MessageType.COMMAND;

  private final Map<MessageType, List<String>> validChunksByType = new HashMap<>();
  private List<String> defaultChunks;

  private final int MESSAGE_TEXT_NUM = 4;

  @Before
  public void setUp() throws Exception {
    validChunksByType.put(MessageType.COMMAND, new ArrayList<>(Arrays.asList("add", "remove", "find")));
    validChunksByType.put(MessageType.CONFIRMATION, new ArrayList<>(Arrays.asList("yes", "confirm")));
    validChunksByType.put(MessageType.DENIAL, new ArrayList<>(Arrays.asList("no", "not")));

    defaultChunks = validChunksByType.get(defaultType);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testGetMessagesNonNull() {
    System.out.println("testGetMessagesNonNull");

    assertNotNull(instance.getMessages(null));
    assertNotNull(instance.getMessages(getValidText(defaultType, 0)));
    assertNotNull(instance.getMessages(getValidText(defaultType, 1)));
    assertNotNull(instance.getMessages(getInvalidText()));
  }

  @Test
  public void testGetMessagesNonAlphanumeric() {
    System.out.println("testGetMessagesNonAlphanumeric");

    assertEquals(0, instance.getMessages(getInvalidText()).size());
  }

  @Test
  public void testGetMessagesFromValidChunks() {
    System.out.println("testGetMessagesFromValidChunks");

    for(int i = 0; i< MESSAGE_TEXT_NUM; i++){
      assertEquals(i, instance.getMessages(getValidText(defaultType, i)).size());
    }

    assertEquals(0, instance.getMessages(alphabetic).size());
    assertEquals(0, instance.getMessages(numeric).size());
  }

  @Test
  public void testGetMessageFromMixedChunks() {
    System.out.println("testGetMessageFromMixedChunks");

    for(int i = 0; i< MESSAGE_TEXT_NUM; i++){
      assertEquals(i, instance.getMessages(getValidText(defaultType, i) + getInvalidText()).size());
    }
  }

  @Test
  public void testGetMessagesHeader() {
    System.out.println("testGetMessagesHeader");

    Queue<Message> messages = instance.getMessages(getValidText(defaultType, defaultChunks.size()));

    int chunkIndex = 0;
    while (!messages.isEmpty()){
      assertEquals(messages.poll().getHeader(), defaultChunks.get(chunkIndex));
      chunkIndex++;
    }
  }

  @Test
  public void testGetMessagesContent() {
    System.out.println("testGetMessagesContent");

    String firstValidTextWithContent = getValidTextWithContent(defaultChunks.get(0), content[0]);
    String secondValidTextWithContent = getValidTextWithContent(defaultChunks.get(0), content[0]);
    secondValidTextWithContent += getValidTextWithContent(defaultChunks.get(1), content[1]);

    Queue<Message> messages = instance.getMessages(firstValidTextWithContent);
    assertEquals(content[0], messages.poll().getContent().get(0));

    messages = instance.getMessages(secondValidTextWithContent);

    Message firstMessage = messages.poll();
    assertEquals(content[0], firstMessage.getContent().get(0));
    assertEquals(defaultChunks.get(1), firstMessage.getContent().get(1));
    assertEquals(content[1], firstMessage.getContent().get(2));

    Message secondMessage = messages.poll();
    assertEquals(content[1], secondMessage.getContent().get(0));
  }

  @Test
  public void testGetMessagesContentFromDistorted() {
    System.out.println("testGetMessagesContentFromDistorted");

    String distortedContent = "a&b*c=1--2";
    String expectedContent = "abc12";
    String messageText = getValidTextWithContent(defaultChunks.get(0), distortedContent);

    Queue<Message> messages = instance.getMessages(messageText);
    assertEquals(expectedContent, messages.poll().getContent().get(0));
  }

  @Test
  public void testGetMessagesType() {
    System.out.println("testGetMessagesType");

    Queue<Message> messages = instance.getMessages(getValidText(defaultType, defaultChunks.size()));

    while (!messages.isEmpty()){
      assertEquals(MessageType.COMMAND, messages.poll().getType());
    }

    messages = instance.getMessages(getValidText(MessageType.CONFIRMATION, 1));
    assertEquals(MessageType.CONFIRMATION, messages.poll().getType());

    messages = instance.getMessages(getValidText(MessageType.DENIAL, 1));
    assertEquals(MessageType.DENIAL, messages.poll().getType());
  }

  private String getValidTextWithContent(String validChunk, String content){
    return validChunk + space + content + space;
  }

  private String getInvalidText(){
    StringBuilder result = new StringBuilder();

    for (String invalidChunk : invalidChunks) {
      result.append(space).append(invalidChunk);
    }

    return result.toString();
  }

  private String getValidText(MessageType type, int numOfValidChunks){
    StringBuilder result = new StringBuilder();

    List<String> listByType = validChunksByType.get(type);

    for (int i = 0; i < numOfValidChunks; i++) {
      result.append(space + alphabetic)
          .append(space).append(listByType.get(i < listByType.size() ? i : listByType.size() - 1))
          .append(space + numeric);
    }

    return result.toString();
  }

}
