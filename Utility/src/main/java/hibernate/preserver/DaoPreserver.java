/*
 * Created by Dariusz Lelek on 9/14/17 9:16 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import config.DataBaseConfig;
import dao.Dao;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import hibernate.TransactionType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Collections;

class DaoPreserver<T extends Dao> implements Preserver<T> {
  private final static Logger logger = Logger.getLogger(DaoPreserver.class);

  private final Session session = HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD).getSession();

  //TODO add return type int save if needed
  @Override
  public void save(T dao) {
    performTransaction(Collections.singletonList(dao), TransactionType.SAVE);
  }

  @Override
  public void update(T dao) {
    performTransaction(Collections.singletonList(dao), TransactionType.UPDATE);
  }

  @Override
  public void saveOrUpdate(T dao) {
    performTransaction(Collections.singletonList(dao), TransactionType.SAVE_OR_UPDATE);
  }

  @Override
  public void delete(T dao) {
    performTransaction(Collections.singletonList(dao), TransactionType.DELETE);
  }

  @Override
  public void save(Collection<T> daos) {
    performTransaction(daos, TransactionType.SAVE);
  }

  @Override
  public void update(Collection<T> daos) {
    performTransaction(daos, TransactionType.UPDATE);
  }

  @Override
  public void saveOrUpdate(Collection<T> daos) {
    performTransaction(daos, TransactionType.SAVE_OR_UPDATE);
  }

  @Override
  public void delete(Collection<T> daos) {
    performTransaction(daos, TransactionType.DELETE);
  }

  private void performTransaction(Collection<T> daos, TransactionType type) {
    Transaction tx = null;
    try {
      tx = session.getTransaction();
      tx.begin();
      switch (type) {
        case SAVE:
          daos.stream().filter(this::isValid).forEach(session::save);
          break;
        case UPDATE:
          daos.stream().filter(this::isValid).forEach(session::update);
          break;
        case SAVE_OR_UPDATE:
          daos.stream().filter(this::isValid).forEach(session::saveOrUpdate);
          break;
        case DELETE:
          // TODO probably not needed validation here
          daos.stream().filter(this::isValid).forEach(session::delete);
          break;
        default:
          break;
      }
      tx.commit();
    } catch (Exception e) {
      logger.error("performTransaction()", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null && session.isOpen()) {
        session.flush();
        session.close();
      }
    }
  }

  private boolean isValid(T dao){
    if(dao.isValid()){
      return true;
    }else{
      logger.warn("isValid(dao) - FAIL - Cannot perform transaction on:" + dao.toString());
      return false;
    }
  }
}
