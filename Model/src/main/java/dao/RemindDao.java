/*
 * Created by Dariusz Lelek on 9/14/17 10:46 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dao;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "remind", catalog = "entity")
public class RemindDao extends Dao implements java.io.Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "REMIND_ID", unique = true, nullable = false)
  private int remindId;
  @Column(name = "TEXT", unique = true, nullable = false)
  private String text;
  @Column(name = "REPEAT_DELAY_S", nullable = false)
  private int repeatDelayS;
  @Column(name = "TIME")
  private DateTime time;

  public RemindDao() {
  }

  public int getRemindId() {
    return remindId;
  }

  public void setRemindId(int remindId) {
    this.remindId = remindId;
  }

  public String getText() {
    return getNonNull(text);
  }

  public void setText(String text) {
    this.text = text;
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
