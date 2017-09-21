/*
 * Created by Dariusz Lelek on 9/21/17 8:07 PM
 * Copyright (c) 2017. All rights reserved.
 */

package worker;

public enum WorkerConfig {
  // TODO add runtime hours for example Reminders between 7-24 or 6-22
  REMINDER(60 * 1000);

  private final long delay;

  WorkerConfig(int delay) {
    this.delay = delay;
  }

  public long getDelay() {
    return delay;
  }
}
