/*
 * Created by Dariusz Lelek on 9/17/17 12:34 AM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;

public enum TimeUnit {
  NOW(0, false),
  LATER(15 * 60, false), // ...

  SECOND(1, false),
  MINUTE(60 * SECOND.timeSecond, false),
  HOUR(60 * MINUTE.timeSecond, false),
  DAY(24 * HOUR.timeSecond, false),
  WEEK(7 * DAY.timeSecond, false),
  // TODO handle this!
  MONTH(31 * DAY.timeSecond, false),
  YEAR(12 * MONTH.timeSecond, false),

  TOMORROW(DAY.timeSecond, false),
  YESTERDAY(- DAY.timeSecond, false),
  TODAY(0, false),

  EVERYDAY(DAY.timeSecond, true),
  WEEKLY(WEEK.timeSecond, true),
  HOURLY(HOUR.timeSecond, true),
  MONTHLY(MONTH.timeSecond, true),
  YEARLY(YEAR.timeSecond, true);

  private final long timeSecond;
  private final boolean repeatable;

  TimeUnit(long timeSecond, boolean repeatable) {
    this.timeSecond = timeSecond;
    this.repeatable = repeatable;
  }

  public long getTimeSecond() {
    return timeSecond;
  }

  public boolean isRepeatable() {
    return repeatable;
  }
}
