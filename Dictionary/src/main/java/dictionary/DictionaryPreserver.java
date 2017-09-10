package dictionary;

import hibernate.HibernateUtility;
import hibernate.TransactionType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    Session localSession = HibernateUtility.getSession();
    Transaction tx = null;
    try {
      tx = HibernateUtility.getSession().getTransaction();
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