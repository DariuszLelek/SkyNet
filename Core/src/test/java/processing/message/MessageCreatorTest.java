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

import java.util.Queue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dariusz Lelek
 */
public class MessageCreatorTest {
  private final MessageCreator instance = new MessageCreator();
  
  public MessageCreatorTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }

  /**
   * Test of getMessages method, of class MessageCreator.
   */
  @Test
  public void testGetMessages() {
    System.out.println("getMessages");
    String messageText = "add  {}{[s[ some text to file";
    Queue<Message> expResult = null;
    //Queue<Message> result = instance.getMessages(messageText);
    //assertEquals(expResult, result);
  }
  
}
