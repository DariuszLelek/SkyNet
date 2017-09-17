/*
 * Created by Dariusz Lelek on 9/17/17 1:44 AM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;

public enum Month {
  JANUARY(1, 31),
  // TODO handle
  FEBRUARY(2, 28),
  MARCH(3, 31),
  APRIL(4, 30),
  MAY(5, 31),
  JUNE(6, 30),
  JULY(7, 31),
  AUGUST(8, 31),
  SEPTEMBER(9, 30),
  OCTOBER(10, 31),
  NOVEMBER(11, 30),
  DECEMBER(12, 31);

  private final TimeUnit unit = TimeUnit.DAY;
  private final int order;
  private final int days;

  Month(int order, int days) {
    this.order = order;
    this.days = days;
  }

  public int getOrder() {
    return order;
  }

  public int getDays() {
    return days;
  }

  public long getDuration() {
    return this.days * unit.getTimeSecond();
  }
}
