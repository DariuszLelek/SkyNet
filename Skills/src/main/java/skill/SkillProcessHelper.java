/*
 * Created by Dariusz Lelek on 9/13/17 12:39 AM
 * Copyright (c) 2017. All rights reserved.
 */

package skill;

import dictionary.DictionaryFactory;
import dictionary.WordClass;
import dictionary.word.Word;
import process.instruction.Instruction;

import java.util.Collection;
import java.util.stream.Collectors;

class SkillProcessHelper {

  private static Collection<Word> getWordsFromInstruction(final Instruction instruction){
    return DictionaryFactory.getWordProvider().getWords(instruction.getQueue());
  }

  private static Collection<WordClass> getWordClassesFromWords(final Collection<Word> words){
    return words.stream().map(Word::getWordClass).collect(Collectors.toList());
  }

  private static boolean wordsHaveAllExpectedWordClasses(final Collection<Word> words,
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
