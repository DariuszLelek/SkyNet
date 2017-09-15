/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import config.DataBaseConfig;
import config.GlobalStrings;
import dao.TimeDao;
import dao.WordDao;
import execute.ProcessableExecutor;
import file.FileUtility;
import hibernate.HibernateUtilityFactory;
import hibernate.preserver.DaoPreserver;
import javassist.Loader;
import load.DataBaseLoader;
import load.DictionaryDataBaseLoader;
import process.ProcessorFactory;
import process.message.Message;
import process.message.MessageCreator;
import process.message.MessageType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


public class TestingClass {
  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.create("", MessageType.VOICE);
    Message m2 = messageCreator.create("email to DARIUS cat", MessageType.VOICE);
    Message m3 = messageCreator.create("add cat to dog and then do something else", MessageType.VOICE);

    //ProcessorFactory.getMessageProcessor().process(m3);


//    DaoPreserver<TimeDao> timeSave = new DaoPreserver<>();
//
//    TimeDao t = new TimeDao();
//    t.setName("day");
//
//
//
//
//    timeSave.save(t);

    // save dictionary to db
    DataBaseLoader dictionaryLoader = new DictionaryDataBaseLoader();
    dictionaryLoader.loadToDataBase();


    HibernateUtilityFactory.closeAllSessionFactories();
    //ProcessableExecutor.stopExecutor();
  }
}
