/*
 * Created by Dariusz Lelek on 9/15/17 7:23 PM
 * Copyright (c) 2017. All rights reserved.
 */

package load;

import config.GlobalStrings;
import dao.Dao;
import dao.WordDao;
import file.FileUtility;
import hibernate.builder.WordDaoBuilder;
import hibernate.preserver.DaoPreserver;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DictionaryDataBaseLoader implements DataBaseLoader {
  private static final Logger logger = Logger.getLogger(DictionaryDataBaseLoader.class);

  private final String splitter = GlobalStrings.SPLITTER_DB_RAW_FILE.getValue();
  private final DaoPreserver<WordDao> wordPreserver = new DaoPreserver<>();

  private final String resourceName = GlobalStrings.DB_FILE_DICTIONARY.getValue();
  private final InputStream fileInputStream = getClass().getResourceAsStream(resourceName);

  @Override
  public void loadToDataBase() {
    Collection<WordDao> uniqueWords = getUniqueWords(FileUtility.getFileLines(fileInputStream));
    wordPreserver.save(uniqueWords);
  }

  private Collection<WordDao> getUniqueWords(final Collection<String> fileContent) {
    return fileContent.stream()
        .map(line -> new WordDaoBuilder(line.split(splitter)).build())
        .filter(word -> !word.getWord().isEmpty())
        .collect(Collectors
            .collectingAndThen(Collectors
                .toCollection(() -> new TreeSet<>(Comparator.comparing(WordDao::getWord))), ArrayList::new));
  }

}
