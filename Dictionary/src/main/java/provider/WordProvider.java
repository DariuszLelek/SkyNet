package provider;

import hibernate.mappings.Word;

import java.util.Collection;
import java.util.List;

public class WordProvider {
  private final String SYNONYM_SPLITTER = ",";

  public Word getWord(String wordString){
    Word word = ProviderFactory.getDataProvider()
        .getEntityByUniqueKey(Word.class, "wordString", wordString);
    return word != null ? word : new Word();
  }

  public Collection<Word> getWords(String[] wordStrings){
    return ProviderFactory.getDataProvider()
        .getEntitiesByUniqueKeys(Word.class, "wordString", wordStrings);
  }
}
