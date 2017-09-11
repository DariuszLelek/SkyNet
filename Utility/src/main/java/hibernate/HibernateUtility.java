/*
 * Created by Dariusz Lelek on 9/11/17 7:38 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate;

import config.DataBaseSchema;
import config.HibernateConfig;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtility {
  private final Logger logger = Logger.getLogger(HibernateUtility.class);

  private final String urlProperty = "hibernate.connection.url";
  private final String driverProperty = "hibernate.connection.driver_class";
  private final String usernameProperty = "hibernate.connection.username";
  private final String passwordProperty = "hibernate.connection.password";
  private final String catalogProperty = "hibernate.default_catalog";
  private final String dialectProperty = "hibernate.dialect";

  private final Configuration configuration;
  private final HibernateConfig hibernateConfig;

  private SessionFactory sessionFactory;
  private ServiceRegistry serviceRegistry;
  private Session session;

  HibernateUtility(DataBaseSchema schema) {
    configuration = new Configuration();
    hibernateConfig = HibernateConfig.getBySchema(schema);

    configure();
    buildSessionFactory();
  }

  private void configure(){
    configuration.configure();

    configuration.setProperty(urlProperty, hibernateConfig.getUrl());
    configuration.setProperty(driverProperty, hibernateConfig.getDriver());
    configuration.setProperty(usernameProperty, hibernateConfig.getUserName());
    configuration.setProperty(passwordProperty, hibernateConfig.getPassword());
    configuration.setProperty(catalogProperty, hibernateConfig.getCatalog());
    configuration.setProperty(dialectProperty, hibernateConfig.getDialect());
  }

  private void buildSessionFactory(){
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
    builder.applySettings(configuration.getProperties());
    serviceRegistry = builder.build();
    sessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
  }

  public Session getSession() {
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

  public void closeSessionFactory() {
    sessionFactory.close();
    StandardServiceRegistryBuilder.destroy(serviceRegistry);
  }
}
