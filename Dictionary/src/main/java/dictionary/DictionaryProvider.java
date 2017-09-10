/*
 * Created by Dariusz Lelek on 9/10/17 9:54 PM
 * Copyright (c) 2017. All rights reserved.
 */

package dictionary;

import hibernate.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DictionaryProvider {
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
}
