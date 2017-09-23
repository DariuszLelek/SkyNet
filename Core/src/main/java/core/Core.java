/*
 * Created by Dariusz Lelek on 9/22/17 6:10 PM
 * Copyright (c) 2017. All rights reserved.
 */

package core;

import execute.ProcessableExecutor;
import hibernate.HibernateUtilityFactory;
import process.control.StateControl;
import process.message.MessageProcessor;
import process.processable.Processable;
import process.skill.SkillProcessor;
import work.WorkerSupervisor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Core implements StateControl{

  private final Lock coreLock = new ReentrantLock();

  private final WorkerSupervisor workerSupervisor = new WorkerSupervisor();
  private final ProcessableExecutor processableExecutor = new ProcessableExecutor();
  private boolean running = false;

  public void addProcessable(Processable processable){
    processableExecutor.addProcessable(processable);
  }

  @Override
  public void start() {
    if (coreLock.tryLock() && !isRunning()) {
      processableExecutor.start();
      workerSupervisor.start();
      setRunning(true);
      coreLock.unlock();
    } else {
      System.out.println("trying to start when there is a lock");
    }
  }

  @Override
  public void stop() {
    if (coreLock.tryLock() && isRunning()) {
      workerSupervisor.stop();
      processableExecutor.stop();
      HibernateUtilityFactory.closeAllAndClear();
      setRunning(false);
      coreLock.unlock();
    } else {
      System.out.println("trying to stop when there is a lock");
    }
  }

  private synchronized void setRunning(boolean value){
    running = value;
  }

  @Override
  public synchronized boolean isRunning(){
    return running;
  }
}
