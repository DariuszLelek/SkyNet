/*
 * Created by Dariusz Lelek on 9/13/17 12:39 AM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import dao.WordDAO;
import entity.WordClass;
import hibernate.provider.DAOProvider;
import process.instruction.Instruction;

import java.util.Collection;
import java.util.stream.Collectors;

class SkillProcessHelper {
  private static final DAOProvider<WordDAO> wordProvider = new DAOProvider<>(WordDAO.class);


  private static Collection<WordDAO> getWordsFromInstruction(final Instruction instruction){
    return wordProvider.getByUniqueKeys("word", instruction.getQueue());
  }

  private static Collection<WordClass> getWordClassesFromWords(final Collection<WordDAO> wordDAOImpls){
    return wordDAOImpls.stream()
        .map(WordDAO::getWordClassString)
        .map(WordClass::getEnumByValue)
        .collect(Collectors.toList());
  }

  private static boolean wordsHaveAllExpectedWordClasses(final Collection<WordDAO> words,
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
