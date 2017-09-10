package provider;

import hibernate.HibernateUtility;
import hibernate.TransactionType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DataProvider {
  private final static Logger logger = Logger.getLogger(DataProvider.class);

  public int getNumberOfEntities(Class clazz) {
    Session session = HibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    int result = ((Number) session.createCriteria(clazz).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    tx.commit();
    return result;
  }

  @SuppressWarnings("unchecked")
  public <T> T getEntityByUniqueKey(Class clazz, String criterion, String value) {
    Session session = HibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    T t = (T)session.createCriteria(clazz).add(Restrictions.eq(criterion, value)).uniqueResult();
    tx.commit();
    return t;
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> getEntitiesByUniqueKeys(Class clazz, String criterion, String[] values) {
    Session session = HibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    List<T> list = session.createCriteria(clazz).add(Restrictions.in(criterion, values)).list();
    tx.commit();
    return list;
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> getAllEntities(Class clazz) {
    Session session = HibernateUtility.getSession();
    Transaction tx = session.beginTransaction();
    List<T> list = session.createCriteria(clazz).list();
    tx.commit();
    return list;
  }


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
