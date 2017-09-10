import hibernate.HibernateUtility;
import processing.WordProcessor;

public class TestClass {

  public static void main(String[] args) {
     //Word w = new Word("add", true, false, "insert");

     //util.saveEntity(w);

//    w.setCommand(true);

    //util.updateEntity(w);

    WordProcessor wp = new WordProcessor();

    System.out.println(wp.isCommand("add"));


    HibernateUtility.stopConnectionProvider();
  }
}
