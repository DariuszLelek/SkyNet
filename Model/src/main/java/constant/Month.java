/*
 * Created by Dariusz Lelek on 9/17/17 1:44 AM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;


import model.Duration;

public enum Month {
  UNKNOWN(0, new Duration(TimeUnit.NONE, 0)),

  JANUARY(1,  new Duration(TimeUnit.DAY, 31)),
  // TODO handle
  FEBRUARY(2, new Duration(TimeUnit.DAY, 28)),
  MARCH(3,  new Duration(TimeUnit.DAY, 31)),
  APRIL(4, new Duration(TimeUnit.DAY, 30)),
  MAY(5, new Duration(TimeUnit.DAY, 31)),
  JUNE(6, new Duration(TimeUnit.DAY, 30)),
  JULY(7, new Duration(TimeUnit.DAY, 31)),
  AUGUST(8, new Duration(TimeUnit.DAY, 31)),
  SEPTEMBER(9, new Duration(TimeUnit.DAY, 30)),
  OCTOBER(10, new Duration(TimeUnit.DAY, 31)),
  NOVEMBER(11, new Duration(TimeUnit.DAY, 30)),
  DECEMBER(12, new Duration(TimeUnit.DAY, 30));

  private static final TimeUnit timeUnit = TimeUnit.DAY;
  private final Duration duration;
  private final int order;

  Month(int order, Duration duration) {
    this.order = order;
    this.duration = duration;
  }

  public Duration getDuration() {
    return duration;
  }

  public int getOrder() {
    return order;
  }
}
