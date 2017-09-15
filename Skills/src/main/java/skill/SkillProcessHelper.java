/*
 * Created by Dariusz Lelek on 9/13/17 12:39 AM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import dao.WordDao;
import entity.WordClass;
import hibernate.provider.DaoProvider;
import process.instruction.Instruction;

import java.util.Collection;
import java.util.stream.Collectors;

class SkillProcessHelper {
  private static final DaoProvider<WordDao> wordProvider = new DaoProvider<>(WordDao.class);


  private static Collection<WordDao> getWordsFromInstruction(final Instruction instruction){
    return wordProvider.getByUniqueKeys("word", instruction.getQueue());
  }

  private static Collection<WordClass> getWordClassesFromWords(final Collection<WordDao> wordDAOImpls){
    return wordDAOImpls.stream()
        .map(WordDao::getWordClassString)
        .map(WordClass::getEnumByValue)
        .collect(Collectors.toList());
  }

  private static boolean wordsHaveAllExpectedWordClasses(final Collection<WordDao> words,
                                                         final Collection<WordClass> expectedWordClasses){
    final Collection<WordClass> wordClasses = getWordClassesFromWords(words);

    for(WordClass expectedWordClass : expectedWordClasses){
      if(wordClasses.contains(expectedWordClass)){
        wordClasses.remove(expectedWordClass);
      }else{
        return false;
      }
    }

    return true;
  }

  static boolean instructionHasAllExpectedWordClasses(final Instruction instruction,
                                                      final Collection<WordClass> expectedWordClasses){
    return wordsHaveAllExpectedWordClasses(getWordsFromInstruction(instruction), expectedWordClasses);
  }
}
