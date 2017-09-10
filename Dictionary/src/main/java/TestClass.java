import hibernate.HibernateUtil;
import hibernate.UtilityProvider;
import hibernate.mappings.Word;
import processing.WordProcessor;
import provider.ProviderFactory;
import provider.WordProvider;

public class TestClass {

  public static void main(String[] args) {

    HibernateUtil util = UtilityProvider.getHibernateUtility();

     //Word w = new Word("add", true, false, "insert");

     //util.saveEntity(w);

//    w.setCommand(true);

    //util.updateEntity(w);

    WordProcessor wp = new WordProcessor();

    System.out.println(wp.isCommand("add"));


    util.stopConnectionProvider();
  }
}
