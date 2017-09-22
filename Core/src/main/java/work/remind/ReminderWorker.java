/*
 * Created by Dariusz Lelek on 9/21/17 10:21 PM
 * Copyright (c) 2017. All rights reserved.
 */

package work.remind;

import hibernate.provider.DaoProviderFactory;
import work.Worker;
import work.WorkerConfig;

import java.util.*;
import java.util.stream.Collectors;

public class ReminderWorker extends Worker {

  private final Set<Reminder> CACHE = new HashSet<>();

  public ReminderWorker() {
    super(WorkerConfig.REMINDER);
  }

  @Override
  public void runWorker() {
    updateEvents();
    processEvents();
  }

  private void updateEvents(){
    if(CACHE.size() != DaoProviderFactory.getEventProvider().getQuantity()){
      CACHE.clear();
      CACHE.addAll(getAllEvents());
    }
  }

  private Collection<Reminder> getAllEvents(){
    return DaoProviderFactory.getEventProvider().getAll().stream()
        .map(Reminder::new).collect(Collectors.toList());
  }

  private void processEvents(){
    CACHE.stream().filter(Reminder::canProcess).forEach(Reminder::process);
  }

}
