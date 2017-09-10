/*
 * Created by Dariusz Lelek on 9/10/17 9:56 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.Stoppable;

public class HibernateUtility {
  private final static Logger logger = Logger.getLogger(HibernateUtility.class);

  private static final SessionFactory sessionFactory;
  private static Session session;

  static {
    Configuration configuration = new Configuration().configure();
    StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
    serviceRegistryBuilder.applySettings(configuration.getProperties());
    ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }

  public static Session getSession() {
    if (session == null || !session.isOpen()) {
      try {
        session = sessionFactory.getCurrentSession();
      } catch (HibernateException he) {
        logger.error("getSession()", he);
        session = sessionFactory.openSession();
      }
    }
    return session;
  }

  public static void stopConnectionProvider() {
    // TODO refactor this since deprecated
    final SessionFactoryImplementor sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;
    ConnectionProvider connectionProvider = sessionFactoryImplementor.getConnectionProvider();
    if (Stoppable.class.isInstance(connectionProvider)) {
      ((Stoppable) connectionProvider).stop();
    }
  }
}
