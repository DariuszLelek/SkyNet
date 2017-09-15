/*
 * Created by Dariusz Lelek on 9/14/17 9:16 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import config.DataBaseConfig;
import dao.DAO;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import hibernate.TransactionType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Collections;

public class DAOPreserver<T extends DAO> implements Preserver<T> {
  private final static Logger logger = Logger.getLogger(DAOPreserver.class);

  private final HibernateUtility hibernateUtility;

  public DAOPreserver() {
    hibernateUtility = HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD);
  }

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
    final Session localSession = hibernateUtility.getSession();
    Transaction tx = null;
    try {
      tx = localSession.getTransaction();
      tx.begin();
      switch (type) {
        case SAVE:
          daos.forEach(localSession::save);
          break;
        case UPDATE:
          daos.forEach(localSession::update);
          break;
        case SAVE_OR_UPDATE:
          daos.forEach(localSession::saveOrUpdate);
          break;
        case DELETE:
          daos.forEach(localSession::delete);
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
      if (localSession != null && localSession.isOpen()) {
        localSession.close();
      }
    }
  }
}
