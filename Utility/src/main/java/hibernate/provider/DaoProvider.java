/*
 * Created by Dariusz Lelek on 9/14/17 8:25 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import config.DataBaseConfig;
import dao.Dao;
import hibernate.HibernateUtilityFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;

class DaoProvider<T extends Dao> implements Provider<T> {
  private final static Logger logger = Logger.getLogger(DaoProvider.class);

  private final Session session = HibernateUtilityFactory
      .getByDatabaseConfig(DataBaseConfig.PROD).getSession();
  private final Class<T> daoClass;

  DaoProvider(Class<T> daoClass) {
    this.daoClass = daoClass;
  }

  @Override
  public Collection<T> getAll(){
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).list();
    commit(tx);
    return getCastedCollection(collection);
  }

  @Override
  public int getQuantity() {
    Transaction tx = session.beginTransaction();
    int result = ((Number) session.createCriteria(daoClass).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    commit(tx);
    return result;
  }

  @Override
  public Collection<T> getByKey(String propertyName, String value) {
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.eq(propertyName, value)).list();
    commit(tx);
    return getCastedCollection(collection);
  }

  @Override
  public Collection<T> getByKeys(String propertyName, Collection<String> values) {
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.in(propertyName,
        values.toArray(new String[values.size()]))).list();
    commit(tx);
    return getCastedCollection(collection);
  }


  @Override
  public T getByUniqueKey(String propertyName, String value) {
    Transaction tx = session.beginTransaction();
    Object object = session.createCriteria(daoClass).add(Restrictions.eq(propertyName, value)).uniqueResult();
    commit(tx);
    return daoClass.isInstance(object) ? daoClass.cast(object) : null;
  }

//  @Override
//  public Collection<T> getByUniqueKeys(String propertyName, Collection<String> values) {
//    Session session = hibernateUtility.getSession();
//    Transaction tx = session.beginTransaction();
//    Collection collection = session.createCriteria(daoClass).add(Restrictions.in(propertyName,
//        values.toArray(new String[values.size()]))).list();
//    tx.commit();
//    return getCastedCollection(collection);
//  }

  private Collection<T> getCastedCollection(Collection collection){
    Collection<T> result = new ArrayList<>();

    for(Object object : collection){
      if(Dao.class.isInstance(object)){
        result.add(daoClass.cast(object));
      }
    }

    return result;
  }

  private void commit(Transaction tx){
    try{
      tx.commit();
    }catch (Exception e){
      logger.error("performTransaction()", e);
      if (tx != null) {
        tx.rollback();
      }
    }finally {
      if (session != null && session.isOpen()) {
        session.flush();
        session.close();
      }
    }
  }
}
