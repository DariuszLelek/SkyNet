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

    TimeUtility.getDateTime("remind me about something tomorrow at five thirty".split(" "));
    String analyzeMe = "remind me about something tomorrow at five thirty";

    Sentence sentence = SentenceAnalyzer.getSentence(analyzeMe.split(" "));

    System.out.println("sentence: " + sentence);

  }
}
