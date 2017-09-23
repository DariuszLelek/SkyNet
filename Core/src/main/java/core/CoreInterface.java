/*
 * Created by Dariusz Lelek on 9/23/17 10:51 PM
 * Copyright (c) 2017. All rights reserved.
 */

package core;

import process.processable.Processable;

public interface CoreInterface {
  boolean isProcessableExecutorRunning();
  boolean isWorkerSupervisorRunning();
  void addProcessable(Processable processable);
}
