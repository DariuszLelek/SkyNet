/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import config.DataBaseConfig;
import config.GlobalStrings;
import dao.WordDao;
import file.FileUtility;
import hibernate.HibernateUtilityFactory;
import hibernate.preserver.DaoPreserver;
import javassist.Loader;
import load.DataBaseLoader;
import load.DictionaryDataBaseLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

//    MessageCreator messageCreator = new MessageCreator();
//
//    Message m1 = messageCreator.create("", MessageType.VOICE);
//    Message m2 = messageCreator.create("send email to DARIUS lele", MessageType.VOICE);
//    Message m3 = messageCreator.create("remind verb me man add remove", MessageType.VOICE);
//
//    ProcessorFactory.getMessageProcessor().process(m1);
//
//    Thread.sleep(5000);
//
//    DaoPreserver<TimeDao> timeSave = new DaoPreserver<>();
//
//    TimeDao t = new TimeDao();
//    t.setName("day");
//
//
//
//
//    timeSave.save(t);
    DataBaseLoader dictionaryLoader = new DictionaryDataBaseLoader();

    dictionaryLoader.loadToDataBase();

    HibernateUtilityFactory.closeAllSessionFactories();

    //DictionaryDataBaseLoader loader = new DictionaryDataBaseLoader(new File(DictionaryDataBaseLoader.class.getResourceAsStream("dictionary-EN.txt")).getPath());

    //ProcessableExecutor.stopExecutor();

    //HibernateUtilityFactory.getByDatabaseConfig(DataBaseConfig.DICTIONARY).closeSessionFactory();
  }
}
