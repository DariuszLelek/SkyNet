/*
 * Created by Dariusz Lelek on 9/10/17 9:56 PM
 * Copyright (c) 2017. All rights reserved.
 */

package file;

import load.DictionaryDataBaseLoader;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtility {
  private static final Logger logger = Logger.getLogger(FileUtility.class);

  public static List<String> getFileNamesInDirectory(String directory) {
    return Arrays.stream(getValidFileArray(new File(directory))).
        filter(f -> !f.isDirectory())
        .map(f -> trimFileExtension(f.getName()))
        .collect(Collectors.toList());
  }

  private static File[] getValidFileArray(File file) {
    File[] fileArray = file.listFiles();
    return fileArray != null ? fileArray : new File[0];
  }

  private static String trimFileExtension(String fileName) {
    return fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
  }

  public static Collection<String> getFileLines(InputStream fileInputStream){
    Collection<String> content = new ArrayList<>();
    String line;

    try (BufferedReader br = new BufferedReader( new InputStreamReader(fileInputStream))) {
      while ((line = br.readLine()) != null) {
        content.add(line);
      }
    } catch (IOException e) {
      logger.error("getFileLines(InputStream fileInputStream)", e);
    }

    return content;
  }

  public static Collection<String> getFileLines(File file) throws Exception {
    Collection<String> content = new ArrayList<>();
    String line;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      while ((line = br.readLine()) != null) {
        content.add(line);
      }
    }

    return content;
  }
}
