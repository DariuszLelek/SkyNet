/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */
package process;

import execute.ProcessableExecutor;
import instruction.Instruction;
import org.apache.log4j.Logger;
import process.message.MessageProcessor;
import processable.Processable;

public abstract class Processor {

  private final static Logger logger = Logger.getLogger(Processor.class);

  public final void process(Processable processable){
    if(processable.canProcess()){
      ProcessableExecutor.addProcessable(processable);
    }else{
      logger.warn("process() - Can't process - " + processable.toString());
    }
  }

}
