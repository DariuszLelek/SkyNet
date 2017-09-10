package file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtility {
  public List<String> getFileNamesInDirectory(String directory) {
    return Arrays.stream(getValidFileArray(new File(directory))).
        filter(f -> !f.isDirectory())
        .map(f -> trimFileExtension(f.getName()))
        .collect(Collectors.toList());
  }

  private File[] getValidFileArray(File file) {
    File[] fileArray = file.listFiles();
    return fileArray != null ? fileArray : new File[0];
  }

  private String trimFileExtension(String fileName) {
    return fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
  }
}
