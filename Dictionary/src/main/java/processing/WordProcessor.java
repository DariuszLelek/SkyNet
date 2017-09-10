package processing;

import hibernate.mappings.Word;
import provider.ProviderFactory;
import provider.WordProvider;

public class WordProcessor {
  private final String SYNONYM_SPLITTER = ",";
  private final WordProvider wordProvider = ProviderFactory.getWordProvider();

  public boolean isCommand(String wordString) {
    Word word = wordProvider.getWord(wordString);
    return word.isCommand() || synonymIsCommand(word.getSynonyms());
  }

  private boolean synonymIsCommand(String synonyms){
    return wordProvider.getWords(getSynonymsArray(synonyms)).stream().anyMatch(Word::isCommand);
  }

  private String[] getSynonymsArray(String synonyms){
    return synonyms.split(SYNONYM_SPLITTER);
  }
}
