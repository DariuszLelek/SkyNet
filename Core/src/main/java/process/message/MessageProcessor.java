/*
 * Created by Dariusz Lelek on 9/12/17 12:14 AM
 * Copyright (c) 2017. All rights reserved.
 */

package process.message;

import org.apache.log4j.Logger;
import process.Processor;

public class MessageProcessor extends Processor{

  private final static Logger logger = Logger.getLogger(MessageProcessor.class);

  public void process(Message message) {
    super.process(message);
  }
}
