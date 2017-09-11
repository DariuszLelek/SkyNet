/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary;

import config.DataBaseSchema;
import hibernate.HibernateUtilityFactory;
import hibernate.TransactionType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

// TODO move to Utils/hibernate
public class DictionaryPreserver {
  private final static Logger logger = Logger.getLogger(DictionaryProvider.class);

  public <T> int saveEntity(T entity) {
    return performTransaction(entity, TransactionType.SAVE);
  }

  public <T> void updateEntity(T entity) {
    performTransaction(entity, TransactionType.UPDATE);
  }

  public <T> void saveOrUpdateEntity(T entity) {
    performTransaction(entity, TransactionType.SAVE_OR_UPDATE);
  }

  public <T> void deleteEntity(T entity) {
    performTransaction(entity, TransactionType.DELETE);
  }

  private <T> int performTransaction(T entity, TransactionType type) {
    int id = -1;
    Session localSession = HibernateUtilityFactory.getBySchema(DataBaseSchema.DICTIONARY).getSession();
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
