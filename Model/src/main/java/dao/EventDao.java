/*
 * Created by Dariusz Lelek on 9/17/17 3:05 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "event", catalog = "entity")
public class EventDao extends Dao implements java.io.Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "EVENT_ID", unique = true, nullable = false)
  private int eventId;
  @Column(name = "NAME", unique = true, nullable = false)
  private String name;
  @Column(name = "REPEATABLE")
  private boolean repeatable;
  @Column(name = "TIME", nullable = false)
  private String time;
  @Column(name = "DELAY")
  private long delay;
  @Column(name = "LOCATION")
  private String location;
  @Column(name = "INSTRUCTION")
  private String instruction;

  public static final Property NAME = new Property("name");
  public static final Property REPEATABLE = new Property("repeatable");
  public static final Property LOCATION = new Property("location");

  public String getName() {
    return getNotNull(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isRepeatable() {
    return repeatable;
  }

  public void setRepeatable(boolean repeatable) {
    this.repeatable = repeatable;
  }

  public String getTime() {
    return time;
  }

  public DateTime getDateTime() {
    return DateTime.parse(time);
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getLocation() {
    return getNotNull(location);
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getInstruction() {
    return getNotNull(instruction);
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public long getDelay() {
    return delay;
  }

  public void setDelay(long delay) {
    this.delay = delay;
  }

  @Override
  public String toString() {
    return "EventDao{" +
        "name='" + name + '\'' +
        ", repeatable=" + repeatable +
        ", time=" + time +
        ", location='" + location + '\'' +
        ", instruction='" + instruction + '\'' +
        '}';
  }

  @Override
  public boolean isValid() {
    return !getName().isEmpty() && getTime() != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    EventDao eventDao = (EventDao) o;

    return name != null ? name.equals(eventDao.name) : eventDao.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
