/*
 * Created by Dariusz Lelek on 9/14/17 11:53 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import javax.persistence.*;

@Entity
@Table(name = "time", catalog = "entity")
public class TimeDao extends Dao {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TIME_ID", unique = true, nullable = false)
  private int timeId;
  @Column(name = "NAME", unique = true, nullable = false)
  private String name;
  @Column(name = "DURATION_SECOND")
  private long durationSecond;
  @Column(name = "REPEATABLE")
  private boolean repeatable;

  public TimeDao() {
  }

  public int getTimeId() {
    return timeId;
  }

  public void setTimeId(int timeId) {
    this.timeId = timeId;
  }

  public String getName() {
    return getString(name);
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getDurationSecond() {
    return durationSecond;
  }

  public void setDurationSecond(long durationSecond) {
    this.durationSecond = durationSecond;
  }

  public boolean isRepeatable() {
    return repeatable;
  }

  public void setRepeatable(boolean repeatable) {
    this.repeatable = repeatable;
  }
}
