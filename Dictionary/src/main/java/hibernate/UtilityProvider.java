package hibernate;

public class UtilityProvider {
  public static final HibernateUtil hibernateUtility = new HibernateUtil();

  public static HibernateUtil getHibernateUtility() {
    return hibernateUtility;
  }
}
