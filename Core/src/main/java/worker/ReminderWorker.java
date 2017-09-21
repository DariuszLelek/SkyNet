/*
 * Created by Dariusz Lelek on 9/21/17 7:59 PM
 * Copyright (c) 2017. All rights reserved.
 */

package worker;

import hibernate.provider.DaoProviderFactory;
import process.reminder.Reminder;

import java.util.*;
import java.util.stream.Collectors;

public class ReminderWorker extends Worker {

  // TODO change to 60s
  private static final long workerDelay = 5 * 1000;
  private final Set<Reminder> CACHE = new HashSet<>();

  public ReminderWorker() {
    super(workerDelay);
  }

  @Override
  public void run() {
    if(working){
      updateEvents();
      processEvents();
    }
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
