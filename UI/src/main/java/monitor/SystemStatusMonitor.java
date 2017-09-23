/*
 * Created by Dariusz Lelek on 9/22/17 6:42 PM
 * Copyright (c) 2017. All rights reserved.
 */

package monitor;

import core.CoreProvider;
import hibernate.HibernateUtilityFactory;
import javafx.beans.property.SimpleBooleanProperty;

public class SystemStatusMonitor extends Monitor {

  private static final long delay = 2 * 1000L;
  private final SimpleBooleanProperty hibernateSessionFactory = new SimpleBooleanProperty(false);
  private final SimpleBooleanProperty core = new SimpleBooleanProperty(false);

  public SystemStatusMonitor() {
    super(delay);
    start();
  }

  public SimpleBooleanProperty coreProperty() {
    return core;
  }

  public SimpleBooleanProperty hibernateSessionFactoryProperty() {
    return hibernateSessionFactory;
  }

  @Override
  void update() {
    hibernateSessionFactory.set(HibernateUtilityFactory.isSessionFactoryOpen());
    core.set(CoreProvider.getCore().isRunning());
  }
}
