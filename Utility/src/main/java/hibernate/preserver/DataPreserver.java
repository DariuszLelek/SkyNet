/*
 * Created by Dariusz Lelek on 9/11/17 9:57 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.preserver;

import config.DataBaseSchema;
import hibernate.HibernateUtility;
import hibernate.HibernateUtilityFactory;
import hibernate.TransactionType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataPreserver implements Preserve {
  private final static Logger logger = Logger.getLogger(DataPreserver.class);

  private final HibernateUtility hibernateUtility;
  private final DataBaseSchema schema;

  public DataPreserver(DataBaseSchema schema) {
    this.schema = schema;

    hibernateUtility = HibernateUtilityFactory.getBySchema(schema);
  }

  public DataBaseSchema getSchema() {
    return schema;
  }

  @Override
  public <T> int saveEntity(T entity) {
    return performTransaction(entity, TransactionType.SAVE);
  }

  @Override
  public <T> void updateEntity(T entity) {
    performTransaction(entity, TransactionType.UPDATE);
  }

  @Override
  public <T> void saveOrUpdateEntity(T entity) {
    performTransaction(entity, TransactionType.SAVE_OR_UPDATE);
  }

  @Override
  public <T> void deleteEntity(T entity) {
    performTransaction(entity, TransactionType.DELETE);
  }

  private <T> int performTransaction(T entity, TransactionType type) {
    int id = -1;
    Session localSession = hibernateUtility.getSession();
    Transaction tx = null;
    try {
      tx = localSession.getTransaction();
      tx.begin();
      switch (type) {
        case SAVE:
          id = (Integer) localSession.save(entity);
          break;
        case UPDATE:
          localSession.update(entity);
          break;
        case SAVE_OR_UPDATE:
          localSession.saveOrUpdate(entity);
          break;
        case DELETE:
          localSession.delete(entity);
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
