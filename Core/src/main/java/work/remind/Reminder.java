/*
 * Created by Dariusz Lelek on 9/21/17 10:22 PM
 * Copyright (c) 2017. All rights reserved.
 */

package work.remind;

import dao.EventDao;
import org.joda.time.DateTime;
import process.instruction.Instruction;
import process.priority.Priority;
import process.processable.Processable;

public class Reminder extends Processable{
  private final String name;
  private DateTime lastProcessed;
  private final EventDao eventDao;

  public Reminder(EventDao eventDao) {
    this.eventDao = eventDao;

    this.name = eventDao.getName();
    this.lastProcessed = eventDao.getDateTime();
    this.setInstruction(new Instruction());
  }

  @Override
  public boolean process() {

    lastProcessed = DateTime.now();

    // TODO send repeatable reminders to voice processing ?
    // TODO send important reminders to voice module with confirmation ?

    // TODO process remind
    System.out.println(name + " - remind");

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

    Reminder reminder = (Reminder) o;

    return name.equals(reminder.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
