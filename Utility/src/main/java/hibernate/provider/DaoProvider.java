/*
 * Created by Dariusz Lelek on 9/14/17 8:25 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.provider;

import config.DataBaseConfig;
import dao.Dao;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;

class DaoProvider<T extends Dao> implements Provider<T> {
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
    tx.commit();

    return getCastedCollection(collection);
  }

  @Override
  public int getQuantity() {
    Transaction tx = session.beginTransaction();
    int result = ((Number) session.createCriteria(daoClass).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    tx.commit();
    return result;
  }

  @Override
  public Collection<T> getByKey(String propertyName, String value) {
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.eq(propertyName, value)).list();
    tx.commit();
    return getCastedCollection(collection);
  }

  @Override
  public Collection<T> getByKeys(String propertyName, Collection<String> values) {
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.in(propertyName,
        values.toArray(new String[values.size()]))).list();
    try {
      tx.commit();
    }catch (Exception e){
      e.printStackTrace();
    }

    return getCastedCollection(collection);
  }


  @Override
  public T getByUniqueKey(String propertyName, String value) {
    Transaction tx = session.beginTransaction();
    Object object = session.createCriteria(daoClass).add(Restrictions.eq(propertyName, value)).uniqueResult();
    tx.commit();
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

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("destroy provider");
  }
}
