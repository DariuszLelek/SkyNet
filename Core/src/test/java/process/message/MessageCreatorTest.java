/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */
package process.message;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageCreatorTest {
  private static final MessageCreator INSTANCE = new MessageCreator();

  private static final String VALID_TEXT = "abcABC cdeFGH  IJKlmn   ";
  private static final String VALID_TEXT_NUMERIC = "0123456789";

  private static final String EMPTY_TEXT = "";
  private static final String INVALID_TEXT = "!@#$%  ^&*()\", \":{}[  ]|\"><  ";
  private static final MessageType DEFAULT_TYPE = MessageType.EMPTY;

  @Test
  public void testGetMessagesNonNull() {
    System.out.println("testGetMessagesNonNull");

    assertNotNull(INSTANCE.create(null, DEFAULT_TYPE));
    assertNotNull(INSTANCE.create(EMPTY_TEXT, DEFAULT_TYPE));
    assertNotNull(INSTANCE.create(VALID_TEXT, DEFAULT_TYPE));
  }

  @Test
  public void testGetMessagesNonAlphanumeric() {
    System.out.println("testGetMessagesNonAlphanumeric");

    assertEquals(0, INSTANCE.create(INVALID_TEXT, DEFAULT_TYPE).getWords().size());
  }

  @Test
  public void testGetMessagesFromValidText() {
    System.out.println("testGetMessagesFromValidText");

    assertEquals(1, INSTANCE.create(VALID_TEXT_NUMERIC, DEFAULT_TYPE).getWords().size());
    assertEquals(3, INSTANCE.create(VALID_TEXT, DEFAULT_TYPE).getWords().size());
  }

  @Test
  public void testGetMessageFromMixedChunks() {
    System.out.println("testGetMessageFromMixedChunks");

    String messageText = VALID_TEXT + " " + INVALID_TEXT;
    int expected = 3;

    assertEquals(expected, INSTANCE.create(messageText, DEFAULT_TYPE).getWords().size());
  }

  @Test
  public void testGetMessagesWords() {
    System.out.println("testGetMessagesWords");

    assertEquals(VALID_TEXT_NUMERIC,
        INSTANCE.create(VALID_TEXT_NUMERIC, DEFAULT_TYPE).getWords().peek());

    String messageText = "some message text";
    Message message = INSTANCE.create(messageText, DEFAULT_TYPE);

    assertEquals("some", message.getWords().poll());
    assertEquals("message", message.getWords().poll());
    assertEquals("text", message.getWords().poll());

    messageText = "some d<i$stor&ted 2 !!";
    message = INSTANCE.create(messageText, DEFAULT_TYPE);

    assertEquals("some", message.getWords().poll());
    assertEquals("distorted", message.getWords().poll());
    assertEquals("2", message.getWords().poll());
  }

  @Test
  public void testGetMessagesType() {
    System.out.println("testGetMessagesType");

    assertEquals(DEFAULT_TYPE, INSTANCE.create(null, DEFAULT_TYPE).getType());
    assertEquals(DEFAULT_TYPE, INSTANCE.create(EMPTY_TEXT, DEFAULT_TYPE).getType());
    assertEquals(MessageType.VOICE, INSTANCE.create(VALID_TEXT, MessageType.VOICE).getType());
  }
}
