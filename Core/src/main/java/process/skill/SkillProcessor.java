/*
 * Created by Dariusz Lelek on 9/12/17 12:31 AM
 * Copyright (c) 2017. All rights reserved.
 */

package process.skill;

import processable.Processable;
import process.Processor;
import execute.ProcessableExecutor;

public class SkillProcessor extends Processor{

  @Override
  public void process(Processable processable) {
    ProcessableExecutor.addProcessable(processable);
  }
}
