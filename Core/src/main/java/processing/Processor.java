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
package processing;

import org.apache.log4j.Logger;
import processing.executor.ProcessableExecutor;

/**
 *
 * @author Dariusz Lelek
 */
public class Processor {
  final static Logger logger = Logger.getLogger(Processor.class);

  public void processText(String text) {
    logger.debug("processText(): \"" + text + "\"");
  }

  public void processInt(int number) {
    logger.debug("processInt(): " + number);
  }

  public void process(Processable processable){
    logger.debug("process(): " + processable.getInfo());

    ProcessableExecutor.addProcessable(processable);
  }
}
