/*
 * Created by Dariusz Lelek on 9/21/17 7:58 PM
 * Copyright (c) 2017. All rights reserved.
 */

package worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkerSupervisor {

  private static final int workersNum = 1;
  private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(workersNum);
  private static final Collection<Worker> workers = new ArrayList<>();

  static{
    addWorkers();
    scheduleWorkers();
  }

  public void startWorkers(){
    workers.forEach(Worker::start);
  }

  public void stopWorkers(){
    workers.forEach(Worker::stop);
  }

  private static void addWorkers(){
    workers.add(new EventWorker());
  }

  private static void scheduleWorkers(){
    workers.forEach(worker -> executor.scheduleAtFixedRate(worker, 0, worker.getDelay(), TimeUnit.MILLISECONDS));
  }
}
