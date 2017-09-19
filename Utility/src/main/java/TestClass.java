/*
 * Created by Dariusz Lelek on 9/16/17 4:47 PM
 * Copyright (c) 2017. All rights reserved.
 */

import text.SentenceAnalyzer;
import text.sentence.Sentence;
import utilities.TimeUtility;

import java.util.Arrays;

public class TestClass {
  public static void main(String[] args) {
    //Collection<WordDao> fiveWord = DaoProviderFactory.getWordProvider().getByKey("word", "five");

    TimeUtility.getDateTime("today we will go to the cinema at five past 10".split(" "));

    String analyzeMe = "send an email to my friend with a message: Where is my five hundred pounds";

    Sentence sentence = SentenceAnalyzer.getSentence(analyzeMe.split(" "));

    System.out.println("sentence: " + sentence);

  }
}
