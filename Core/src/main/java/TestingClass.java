/*
 * Created by Dariusz Lelek on 9/10/17 9:53 PM
 * Copyright (c) 2017. All rights reserved.
 */


import dao.EventDao;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import process.message.MessageCreator;
import worker.WorkerSupervisor;


public class TestingClass {
  private final static Logger logger = Logger.getLogger(TestingClass.class);

  public static void main(String[] args) throws InterruptedException {

    MessageCreator messageCreator = new MessageCreator();

//    Message m1 = messageCreator.create("", MessageType.VOICE);
//    Message m2 = messageCreator.create("email to DARIUS cat", MessageType.VOICE);
//    Message m3 = messageCreator.create("remind tomorrows then day day other", MessageType.VOICE);
//
//    ProcessorFactory.getMessageProcessor().process(m3);

    EventDao ed = new EventDao();
    ed.setName("reminder1");
    ed.setDelay(10 * 1000L);
    ed.setRepeatable(true);
    ed.setTime(DateTime.now().toString());

    //ed.setTime(DateTime.now().toString());

    //DaoPreserverFactory.getEventPreserver().save(ed);

    WorkerSupervisor ws=new WorkerSupervisor();

    ws.startWorkers();
    System.out.println("ss");


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
    // save dictionary to db
//    DataBaseSaver dictionaryLoader = new DictionaryDataBaseSaver();
//    dictionaryLoader.saveToDataBase();


//    System.out.println(DaoProviderFactory.getWordProvider()
//        .getByKey("word", "a"));


    //HibernateUtilityFactory.closeAllSessionFactories();
    //ProcessableExecutor.stopExecutor();
  }
}
