/*
 * Created by Dariusz Lelek on 9/22/17 6:10 PM
 * Copyright (c) 2017. All rights reserved.
 */

package core;import execute.ProcessableExecutor;
import hibernate.HibernateUtilityFactory;
import work.WorkerSupervisor;

public class Core {

  private static WorkerSupervisor workerSupervisor;
  private static boolean running = false;

  public static void start(){
    if(!isRunning()){
      setRunning(true);
      ProcessableExecutor.startExecutor();
      getWorkerSupervisor().startWorkers();
    }
  }

  public static void stop(){
    if(isRunning()){
      setRunning(false);
      getWorkerSupervisor().stopWorkers();
      HibernateUtilityFactory.closeAllSessionFactories();
      ProcessableExecutor.stopExecutor();
    }
  }

  private static synchronized WorkerSupervisor getWorkerSupervisor(){
    if(workerSupervisor == null){
      workerSupervisor = new WorkerSupervisor();
    }
    return workerSupervisor;
  }

  private static synchronized void setRunning(boolean value){
    running = value;
  }

  private static synchronized boolean isRunning(){
    return running;
  }
}
