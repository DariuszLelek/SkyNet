/*
 * Created by Dariusz Lelek on 9/17/17 7:29 PM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;

public enum TimeKeyWord {
  UNKNOWN("", TimeUnit.UNKNOWN, Direction.NONE),

  AM_1("a.m.", TimeUnit.UNKNOWN, Direction.LEFT),
  AM_2("am", TimeUnit.UNKNOWN, Direction.LEFT),
  AM_3("am.", TimeUnit.UNKNOWN, Direction.LEFT),
  AM_4("morning", TimeUnit.UNKNOWN, Direction.LEFT),
  AM_5("a", TimeUnit.UNKNOWN, Direction.LEFT),


  PM_1("p.m.", TimeUnit.UNKNOWN, Direction.LEFT),
  PM_2("pm", TimeUnit.UNKNOWN, Direction.LEFT),
  PM_3("pm.", TimeUnit.UNKNOWN, Direction.LEFT),
  PM_4("evening.", TimeUnit.UNKNOWN, Direction.LEFT),
  PM_5("p", TimeUnit.UNKNOWN, Direction.LEFT),

  MINUTE_1("to", TimeUnit.MINUTE, Direction.LEFT),
  MINUTE_2("past", TimeUnit.MINUTE, Direction.LEFT),

  HOUR_1("o'clock", TimeUnit.HOUR, Direction.LEFT),
  HOUR_2("o", TimeUnit.HOUR, Direction.LEFT),
  HOUR_3("in", TimeUnit.HOUR, Direction.LEFT),
  HOUR_4("at", TimeUnit.HOUR, Direction.RIGHT),
  HOUR_5("exactly", TimeUnit.HOUR, Direction.RIGHT);

  private final String value;
  private final TimeUnit timeUnit;
  private final Direction direction;

  TimeKeyWord(String value, TimeUnit timeUnit, Direction direction) {
    this.value = value;
    this.timeUnit = timeUnit;
    this.direction = direction;
  }

  public String getValue() {
    return value;
  }

  public TimeUnit getTimeUnit() {
    return timeUnit;
  }

  public Direction getDirection() {
    return direction;
  }

  private enum Direction{
    LEFT(-1), RIGHT(1), NONE(0);
    private final int idx;

    Direction(int idx) {
      this.idx = idx;
    }

    public int get(){
      return idx;
    }
  }
}
