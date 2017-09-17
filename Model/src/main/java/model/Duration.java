/*
 * Created by Dariusz Lelek on 9/17/17 2:40 AM
 * Copyright (c) 2017. All rights reserved.
 */

package model;

import constant.TimeUnit;

public class Duration implements Validator{
  private final TimeUnit timeUnit;
  private final long multiplier;

  public Duration(TimeUnit timeUnit, long multiplier) {
    this.timeUnit = timeUnit;
    this.multiplier = multiplier;
  }

  public Duration(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
    this.multiplier = 1L;
  }

  public long get(){
    // TODO check long overflow
    return timeUnit.getTimeSecond() * multiplier;
  }

  @Override
  public boolean isValid() {
    return timeUnit.getTimeSecond() > 0;
  }
}
