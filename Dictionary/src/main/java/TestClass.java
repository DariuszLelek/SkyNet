import dictionary.DictionaryFactory;
import hibernate.HibernateUtility;
import hibernate.mappings.Word;

public class TestClass {

  public static void main(String[] args) {
    Word w = new Word("add", "noun", "insert");

    //DictionaryFactory.getDataPreserver().saveEntity(w);

    String[] words = {"none", "none2"};

    System.out.println(DictionaryFactory.getWordProvider().getWord("add").getWordClass());
    System.out.println(DictionaryFactory.getWordProvider().getWords(words).size());

    HibernateUtility.stopConnectionProvider();
  }
}
