/*
 * Created by Dariusz Lelek on 9/21/17 7:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package work;

import work.remind.ReminderWorker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkerSupervisor {

  private static  ScheduledExecutorService executor;
  private static final Collection<Worker> workers = new ArrayList<>();

  static{
    workers.add(new ReminderWorker());
  }

  public void start(){
    executor = Executors.newScheduledThreadPool(workers.size());

    startWorkers();
    scheduleWorkers();
  }

  public void stop(){
    stopWorkers();

    executor.shutdownNow();
    executor = null;
  }

  private void startWorkers(){
    workers.forEach(Worker::start);
  }

  private void stopWorkers(){
    workers.forEach(Worker::stop);
  }

  private static void scheduleWorkers(){
    workers.forEach(worker -> executor.scheduleAtFixedRate(worker, 0, worker.getDelay(), TimeUnit.MILLISECONDS));
  }
}
