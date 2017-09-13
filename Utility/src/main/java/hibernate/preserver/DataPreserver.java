/*
 * Created by Dariusz Lelek on 9/11/17 9:57 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import config.DataBaseConfig;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import hibernate.TransactionType;
import dao.DAO;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataPreserver implements Preserve {
  private final static Logger logger = Logger.getLogger(DataPreserver.class);

  private final HibernateUtility hibernateUtility;
  private final DataBaseConfig schema;

  public DataPreserver(DataBaseConfig schema) {
    this.schema = schema;

    hibernateUtility = HibernateUtilityFactory.getByDatabaseConfig(schema);
  }

  public DataBaseConfig getSchema() {
    return schema;
  }

  public int save(DAO dao) {
    return performTransaction(dao, TransactionType.SAVE);
  }

  @Override
  public void update(DAO dao) {
    performTransaction(dao, TransactionType.UPDATE);
  }

  @Override
  public void saveOrUpdate(DAO dao) {
    performTransaction(dao, TransactionType.SAVE_OR_UPDATE);
  }

  @Override
  public void delete(DAO dao) {
    performTransaction(dao, TransactionType.DELETE);
  }

  private int performTransaction(DAO dao, TransactionType type) {
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
