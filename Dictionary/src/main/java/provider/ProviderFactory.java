package provider;

public class ProviderFactory {
  private final static WordProvider wordProvider = new WordProvider();
  private final static DataProvider dataProvider = new DataProvider();

  public static WordProvider getWordProvider(){
    return wordProvider;
  }

  public static DataProvider getDataProvider() {
    return dataProvider;
  }
}
