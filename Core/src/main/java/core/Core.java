/*
 * Created by Dariusz Lelek on 9/22/17 6:10 PM
 * Copyright (c) 2017. All rights reserved.
 */

package core;

import execute.ProcessableExecutor;
import hibernate.HibernateUtilityFactory;
import org.apache.log4j.Logger;
import process.control.StateControl;
import process.message.MessageProcessor;
import process.processable.Processable;
import process.skill.SkillProcessor;
import work.WorkerSupervisor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Core implements StateControl, CoreInterface{

  private final static Logger logger = Logger.getLogger(Core.class);

  private final Lock coreLock = new ReentrantLock();
  private final WorkerSupervisor workerSupervisor = new WorkerSupervisor();
  private final ProcessableExecutor processableExecutor = new ProcessableExecutor();
  private boolean running = false;

  @Override
  public boolean isProcessableExecutorRunning(){
    return processableExecutor.isRunning();
  }

  @Override
  public boolean isWorkerSupervisorRunning() {
    return workerSupervisor.isRunning();
  }

  @Override
  public void addProcessable(Processable processable){
    processableExecutor.addProcessable(processable);
  }

  @Override
  public void start() {
    if (coreLock.tryLock() && !isRunning()) {
      logger.debug("start");

      processableExecutor.start();
      workerSupervisor.start();
      setRunning(true);
      coreLock.unlock();
    }
  }

  @Override
  public void stop() {
    if (coreLock.tryLock() && isRunning()) {
      logger.debug("stop");

      workerSupervisor.stop();
      processableExecutor.stop();
      HibernateUtilityFactory.closeAllAndClear();
      setRunning(false);
      coreLock.unlock();
    }
  }

  @Override
  public synchronized boolean isRunning(){
    return running;
  }

  private synchronized void setRunning(boolean value){
    running = value;
  }

}
