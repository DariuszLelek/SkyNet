/*
 * Created by Dariusz Lelek on 9/21/17 7:55 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.event;

import dao.EventDao;
import org.joda.time.DateTime;
import process.instruction.Instruction;
import process.priority.Priority;
import process.processable.Processable;

public class Event extends Processable{
  private final String name;
  private DateTime lastProcessed;
  private final EventDao eventDao;

  public Event(EventDao eventDao) {
    this.eventDao = eventDao;

    this.name = eventDao.getName();
    this.lastProcessed = eventDao.getDateTime();
    this.setInstruction(new Instruction());
  }

  @Override
  public boolean process() {
    lastProcessed = DateTime.now();

    // TODO process event
    System.out.println(name + " - event");

    return true;
  }

  @Override
  public int getPriority() {
    return Priority.MEDIUM.getValue();
  }

  public boolean canProcess(){
    return lastProcessed.plus(eventDao.getDelay()).isBefore(DateTime.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Event event = (Event) o;

    return name.equals(event.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
