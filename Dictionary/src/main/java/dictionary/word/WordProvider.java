package dictionary.word;

import dictionary.DictionaryFactory;
import hibernate.mappings.Word;

import java.util.List;
import java.util.stream.Collectors;

public class WordProvider {

  public Word getWord(String wordString){
    Word word = DictionaryFactory.getDictionaryProvider()
        .getEntityByUniqueKey(Word.class, "word", wordString);
    return word != null ? word : Word.UNKNOWN;
  }

  public List<Word> getWords(String[] wordStrings){
    return DictionaryFactory.getDictionaryProvider()
        .getEntitiesByUniqueKeys(Word.class, "word", wordStrings)
        .stream()
        .filter(w -> w != null)
        .map(Word.class::cast)
        .collect(Collectors.toList());
  }
}
