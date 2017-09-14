/*
 * Created by Dariusz Lelek on 9/14/17 8:25 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import config.DataBaseConfig;
import dao.DAO;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;

public class DAOProvider <T extends DAO> implements Provider<T> {
  private final HibernateUtility hibernateUtility;
  private final Class<T> daoClass;

  public DAOProvider(Class<T> daoClass) {
    this.daoClass = daoClass;
    this.hibernateUtility = HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD);
  }

  @Override
  public Collection<T> getAll(){
    Session session = hibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).list();
    tx.commit();

    return getCastedCollection(collection);
  }

  @Override
  public int getQuantity() {
    Session session = hibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    int result = ((Number) session.createCriteria(daoClass).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    tx.commit();
    return result;
  }

  @Override
  public T getByUniqueKey(String keyName, String keyValue) {
    Session session = hibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    Object object = session.createCriteria(daoClass).add(Restrictions.eq(keyName, keyValue)).uniqueResult();
    tx.commit();
    return daoClass.isInstance(object) ? daoClass.cast(object) : null;
  }

  @Override
  public Collection<T> getByUniqueKeys(String keyName, Collection<String> keyValues) {
    Session session = hibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.in(keyName,
        keyValues.toArray(new String[keyValues.size()]))).list();
    tx.commit();
    return getCastedCollection(collection);
  }

  private Collection<T> getCastedCollection(Collection collection){
    Collection<T> result = new ArrayList<>();

    for(Object object : collection){
      if(DAO.class.isInstance(object)){
        result.add(daoClass.cast(object));
      }
    }

    return result;
  }
}
