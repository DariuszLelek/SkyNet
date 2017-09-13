/*
 * Created by Dariusz Lelek on 9/11/17 8:56 PM
 * Copyright (c) 2017. All rights reserved.
 */

package config;

import credentials.Credentials;

public enum DataBaseConfig {
  EMPTY("",
      "",
      "",
      "",
      "",
      ""),

  PROD(
      Credentials.PROD_DB_USER.getValue(),
      Credentials.PROD_DB_PASSWORD.getValue(),
      "jdbc:mysql://localhost:3306",
      "com.mysql.jdbc.Driver",
      "entity",
      "org.hibernate.dialect.MySQLDialect"
  ),

  TEST(
      Credentials.TEST_DB_USER.getValue(),
      Credentials.TEST_DB_PASSWORD.getValue(),
      "jdbc:mysql://localhost:3306/test?createDatabaseIfNotExist=true",
      "com.mysql.jdbc.Driver",
      "test",
      "org.hibernate.dialect.MySQLDialect"
  );

  private final String userName;
  private final String password;
  private final String url;
  private final String driver;
  private final String catalog;
  private final String dialect;

  DataBaseConfig(String userName, String password, String url, String driver, String catalog, String dialect) {
    this.userName = userName;
    this.password = password;
    this.url = url;
    this.driver = driver;
    this.catalog = catalog;
    this.dialect = dialect;
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

}
