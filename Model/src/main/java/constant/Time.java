/*
 * Created by Dariusz Lelek on 9/17/17 12:34 AM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;

public enum Time {
  NOW(0, false),
  SECOND(1, false),

  MINUTE(60 * SECOND.duration, false),
  HOUR(60 * MINUTE.duration, false),
  DAY(24 * HOUR.duration, false),
  WEEK(7 * DAY.duration, false),
  // TODO handle this!
  MONTH(31 * DAY.duration, false),
  YEAR(12 * MONTH.duration, false),

  TOMORROW(60 * SECOND.duration, false),
  YESTERDAY(60 * SECOND.duration, false),
  TODAY(60 * SECOND.duration, false),

  LATER(15 * MINUTE.duration, false),

  EVERYDAY(DAY.duration, true),
  WEEKLY(Time.WEEK.duration, true),
  HOURLY(HOUR.duration, true),
  MONTHLY(MONTH.duration, true),
  YEARLY(YEAR.duration, true),

  JANUARY(31 * DAY.duration, false),
  FEBRUARY(28 * DAY.duration, false),
  MARCH(31 * DAY.duration, false),
  APRIL(30 * DAY.duration, false),
  MAY(31 * DAY.duration, false),
  JUNE(30 * DAY.duration, false),
  JULY(31 * DAY.duration, false),
  AUGUST(31 * DAY.duration, false),
  SEPTEMBER(30 * DAY.duration, false),
  OCTOBER(31 * DAY.duration, false),
  NOVEMBER(30 * DAY.duration, false),
  DECEMBER(31 * DAY.duration, false);

  private final long duration;
  private final boolean repeatable;

  Time(long duration, boolean repeatable) {
    this.duration = duration;
    this.repeatable = repeatable;
  }

  public long getDuration() {
    return duration;
  }

  public boolean isRepeatable() {
    return repeatable;
  }
}
