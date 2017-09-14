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

public class DAOPreserver<T extends DAO> implements Preserver<T> {
  private final static Logger logger = Logger.getLogger(DAOPreserver.class);

  private final HibernateUtility hibernateUtility;

  public DAOPreserver() {
    hibernateUtility = HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.PROD);
  }

  public int save(T dao) {
    return performTransaction(dao, TransactionType.SAVE);
  }

  public void update(T dao) {
    performTransaction(dao, TransactionType.UPDATE);
  }

  public void saveOrUpdate(T dao) {
    performTransaction(dao, TransactionType.SAVE_OR_UPDATE);
  }

  public void delete(T dao) {
    performTransaction(dao, TransactionType.DELETE);
  }

  private int performTransaction(T dao, TransactionType type) {
    int id = -1;
    Session localSession = hibernateUtility.getSession();
    Transaction tx = null;
    try {
      tx = localSession.getTransaction();
      tx.begin();
      switch (type) {
        case SAVE:
          id = (Integer) localSession.save(dao);
          break;
        case UPDATE:
          localSession.update(dao);
          break;
        case SAVE_OR_UPDATE:
          localSession.saveOrUpdate(dao);
          break;
        case DELETE:
          localSession.delete(dao);
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
    return id;
  }
}
