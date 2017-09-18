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
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
  public boolean isInUse() {
    return session.isOpen();
  }

  @Override
  public int getQuantity() {
    Transaction tx = session.beginTransaction();
    int result = ((Number) session.createCriteria(daoClass).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    commit(tx);
    return result;
  }

  @Override
  public Collection<T> getByKey(Dao.Property property, Object value) {
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.eq(property.getValue(), value)).list();
    commit(tx);
    return getCastedCollection(collection);
  }

  @Override
  public Collection<T> getByKeys(Dao.Property property, Collection values) {
    Transaction tx = session.beginTransaction();
    Collection collection = session.createCriteria(daoClass).add(Restrictions.in(property.getValue(),
        values.toArray(new Object[values.size()]))).list();
    commit(tx);
    return getCastedCollection(collection);
  }

  private Collection<T> getCastedCollection(Collection collection){
    List<T> result = new ArrayList<>();

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
