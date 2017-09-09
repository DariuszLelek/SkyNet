package processing.message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageProcessorTest {
  private final MessageProcessor instance = new MessageProcessor();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void processText() throws Exception {
    instance.processText("some text");
  }

}