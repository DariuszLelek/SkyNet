/*
 * Created by Dariusz Lelek on 9/15/17 8:21 PM
 * Copyright (c) 2017. All rights reserved.
 */

package hibernate.builder;

import dao.WordDao;
import entity.WordClass;
import utilities.StringUtilities;
import validator.StringValidator;

public class WordDaoBuilder implements DaoBuilder<WordDao> {
  private static final int maxDescLength = WordDao.DESCRIPTION_LENGTH;
  private static final int validChunkLength = 3;
  private final String[] chunks;

  public WordDaoBuilder(String[] chunks) {
    this.chunks = chunks;
  }

  @Override
  public WordDao build() {
    return validate() ? new WordDao(chunks[0], chunks[1], chunks[2]) : new WordDao();
  }

  private boolean validate() {
    if(chunks.length == validChunkLength){
      for(int i=0; i<validChunkLength; i++){
        chunks[i] = getValidStringByIndex(i, chunks[i]);
      }
      return !chunks[0].isEmpty();
    }
    return false;
  }

  private String getValidStringByIndex(final int index, final String chunk) {
    switch (index) {
      case 0:
        return getValidWordString(chunk);
      case 1:
        return getValidClassString(chunk);
      case 2:
        return getValidDescriptionString(chunk);
      default:
        return "";
    }
  }

  private String getValidDescriptionString(String chunk){
    return chunk != null ? (chunk.length() > maxDescLength ?
        chunk.substring(0, maxDescLength) : chunk) : "";
  }

  private String getValidWordString(String chunk){
    return StringUtilities.isAlphabetic(chunk) ? chunk.toLowerCase() : "";
  }

  private String getValidClassString(String chunk){
    if(chunk.contains("n.")){
      return WordClass.NOUN.getValue();
    }else if(chunk.contains("a.")){
      return WordClass.ADJECTIVE.getValue();
    }else if(chunk.contains("pl.")){
      return WordClass.PLURAL.getValue();
    }else if(chunk.contains("adv.")){
      return WordClass.ADVERB.getValue();
    }else if(chunk.contains("v.")){
      return WordClass.VERB.getValue();
    }else if(chunk.contains("imp.")){
      return WordClass.VERB.getValue();
    }

    return chunk;
  }
}
