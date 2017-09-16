/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import dao.WordDao;
import execute.ProcessableExecutor;
import hibernate.HibernateUtilityFactory;
import hibernate.preserver.DaoPreserverFactory;
import hibernate.provider.Provider;
import hibernate.provider.DaoProviderFactory;
import load.DataBaseLoader;
import load.DictionaryDataBaseLoader;
import org.apache.log4j.Logger;
import process.ProcessorFactory;
import process.message.Message;
import process.message.MessageCreator;
import process.message.MessageType;
import process.skill.SkillProcessor;


public class TestingClass {
  private final static Logger logger = Logger.getLogger(TestingClass.class);

  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

    Message m1 = messageCreator.create("", MessageType.VOICE);
    Message m2 = messageCreator.create("email to DARIUS cat", MessageType.VOICE);
    Message m3 = messageCreator.create("email darius lelek", MessageType.VOICE);

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
//
//    // save dictionary to db
//    DataBaseLoader dictionaryLoader = new DictionaryDataBaseLoader();
//    dictionaryLoader.loadToDataBase();

    System.out.println(DaoProviderFactory.getWordProvider()
        .getByKey("word", "a"));


    //HibernateUtilityFactory.closeAllSessionFactories();
    //ProcessableExecutor.stopExecutor();
  }
}
