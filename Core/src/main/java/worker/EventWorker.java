/*
 * Created by Dariusz Lelek on 9/21/17 7:59 PM
 * Copyright (c) 2017. All rights reserved.
 */

package worker;

import dao.EventDao;
import execute.ProcessableExecutor;
import hibernate.provider.DaoProviderFactory;
import org.joda.time.DateTime;
import process.event.Event;

import java.util.*;
import java.util.stream.Collectors;

public class EventWorker extends Worker {

  // TODO change to 60s
  private static final long workerDelay = 5 * 1000;
  private final Set<Event> CACHE = new HashSet<>();

  public EventWorker() {
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

  private Collection<Event> getAllEvents(){
    return DaoProviderFactory.getEventProvider().getAll().stream()
        .map(Event::new).collect(Collectors.toList());
  }

  private void processEvents(){
    CACHE.stream().filter(Event::canProcess).forEach(ProcessableExecutor::addProcessable);
  }
}
