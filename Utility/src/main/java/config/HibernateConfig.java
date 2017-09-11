/*
 * Created by Dariusz Lelek on 9/11/17 8:56 PM
 * Copyright (c) 2017. All rights reserved.
 */

package config;

import credentials.Credentials;

public enum HibernateConfig {

  EMPTY(DataBaseSchema.NONE,
      "",
      "",
      "",
      "",
      "",
      ""),

  DICTIONARY(
      DataBaseSchema.DICTIONARY,
      Credentials.DB_DICTIONARY_USER.getValue(),
      Credentials.DB_DICTIONARY_PASSWORD.getValue(),
      "jdbc:mysql://localhost:3306",
      "com.mysql.jdbc.Driver",
      "dictionary",
      "org.hibernate.dialect.MySQLDialect"
  );

  private final DataBaseSchema schema;
  private final String userName;
  private final String password;
  private final String url;
  private final String driver;
  private final String catalog;
  private final String dialect;

  HibernateConfig(DataBaseSchema schema, String userName, String password, String url, String driver, String catalog, String dialect) {
    this.schema = schema;
    this.userName = userName;
    this.password = password;
    this.url = url;
    this.driver = driver;
    this.catalog = catalog;
    this.dialect = dialect;
  }

  public DataBaseSchema getSchema() {
    return schema;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getUrl() {
    return url;
  }

  public String getDriver() {
    return driver;
  }

  public String getCatalog() {
    return catalog;
  }

  public String getDialect() {
    return dialect;
  }

  public static HibernateConfig getBySchema(DataBaseSchema schema){
    for (HibernateConfig credentials : values()){
      if(schema == credentials.getSchema()){
        return credentials;
      }
    }
    return EMPTY;
  }
}
