/*
 * Created by Dariusz Lelek on 9/13/17 7:24 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao.entity;

import dao.DAO;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "remind", catalog = "entity")
public class RemindDAO extends DAO implements java.io.Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "REMIND_ID", unique = true, nullable = false)
  private int remindId;
  @Column(name = "TEXT", unique = true, nullable = false)
  private String text;
  @Column(name = "REPEATABLE", nullable = false)
  private boolean repeatable;
  @Column(name = "REPEAT_DELAY_S")
  private int repeatDelayS;
  @Column(name = "TIME")
  private DateTime time;

  public RemindDAO() {
  }

  public int getRemindId() {
    return remindId;
  }

  public void setRemindId(int remindId) {
    this.remindId = remindId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isRepeatable() {
    return repeatable;
  }

  public void setRepeatable(boolean repeatable) {
    this.repeatable = repeatable;
  }

  public int getRepeatDelayS() {
    return repeatDelayS;
  }

  public void setRepeatDelayS(int repeatDelayS) {
    this.repeatDelayS = repeatDelayS;
  }

  public DateTime getTime() {
    return time;
  }

  public void setTime(DateTime time) {
    this.time = time;
  }

}
